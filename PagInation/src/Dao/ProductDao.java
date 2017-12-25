package Dao;

import bean.Product;

import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kennywzj on 2017/12/25.
 */
public class ProductDao {
    /*
     *获取数据库连接
     * @return Connection 连接
     */
    public Connection getConnection(){
        Connection conn =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db_PagInation";
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection("jdbc:mysql://localhost/db_PagInation","root","root");
            if (conn != null){
                System.out.println("Connection Success!");
            }else{
                System.out.println("Connection Failed!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    /*
     *分页查询商品
     * @param page 页数
     * @return List<Product>
     */
    public List<Product> find(int page){
        List<Product> list = new ArrayList<Product>();
        Connection conn = getConnection();
        String sql = "select * from tb_product order by id desc limit ?,?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(new Integer(1),(page-1)*Product.PAGE_SIZE);
            ps.setInt(new Integer(2),Product.PAGE_SIZE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setNum(rs.getInt("num"));
                p.setPrice(rs.getDouble("price"));
                p.setUnit(rs.getString("unit"));
                list.add(p);
            }

            rs.close();
            ps.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /*
     *查询总记录数
     * @return 总记录数
     */
    public int findCount(){
        int count = 0;
        Connection conn =getConnection();
        String sql ="select count(*) from tb_product";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()){
                count = rs.getInt(1);
            }
            rs.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }
}
