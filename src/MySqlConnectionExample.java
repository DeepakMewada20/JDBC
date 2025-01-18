import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
public class MySqlConnectionExample {
    public static void main(String[] args) {
        String url ="jdbc:mysql://localhost:3306/COLLAGE";
        String username = "root";
        String password ="nevermind";

        try(Connection connection= DriverManager.getConnection(url,username,password)) {
            System.out.println("connect to the database");
        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println("connection faild " + e);
        }
    }
}
