import Dao.ProductDao;

/**
 * Created by kennywzj on 2017/12/25.
 */
public class TestJdbc {
    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
        dao.getConnection();
    }
}
