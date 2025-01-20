import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
public class PreparedStatementTest {
    public static void main(String[] args) throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/COLLAGE";
        String userName="root";
        String password="nevermind";
//        String query="select * from STUDENT where Marks>=? AND grade = ?";
        String query="select * from STUDENT";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("All drivers loade secefuly");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection conn=DriverManager.getConnection(url,userName,password);
            PreparedStatement preparedStatement = conn.prepareStatement(query);
//            preparedStatement.setInt(1,80);
//            preparedStatement.setString(2,"O");
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                System.out.println("roll nuber : "+rs.getInt("ROLLNo"));
                System.out.println("NAME : "+rs.getString("NAME"));
                System.out.println("Marks : "+rs.getInt("Marks"));
                System.out.println("Grade : "+rs.getString("grade"));
                System.out.println("City : "+rs.getString("city"));
                System.out.println("---------------------------------");
            }
            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //void multipalPlaceHolder
}
