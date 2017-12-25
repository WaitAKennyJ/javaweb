package servlet;

import Dao.ProductDao;
import bean.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kennywzj on 2017/12/25.
 */
@WebServlet(name = "FindServlet")
public class FindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currPage = 1;
        int pages ;
        int count ;
        if (request.getParameter("page")!=null){
            currPage = Integer.parseInt(request.getParameter("page"));
        }
        ProductDao dao = new ProductDao();
        List<Product> list = dao.find(currPage);
        request.setAttribute("list",list);

        count = dao.findCount();
        if (count%Product.PAGE_SIZE ==0){
            pages = count / Product.PAGE_SIZE;
        }else {
            pages = count / Product.PAGE_SIZE+1;
        }

        StringBuffer sb = new StringBuffer();
        for (int i=1; i <= pages; i++){
            if (i==currPage){
                sb.append("["+i+"]");
            }else {
                sb.append("<a href='FindServlet?page="+i+"'>"+i+"</a>");
            }
            sb.append("  ");
        }

        request.setAttribute("bar",sb);
        request.getRequestDispatcher("product_list.jsp").forward(request,response);
    }


}
