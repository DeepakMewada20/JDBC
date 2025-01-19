import  java.sql.*;
import java.util.concurrent.CancellationException;

public class DeletOperationInMysql {
    public static void main(String[] args) throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/COLLAGE";
        String userName="root";
        String password="nevermind";
        String query="delete from STUDENT where RollNo=108;";
//        String query="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers load sesesfuly");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con =DriverManager.getConnection(url,userName,password);
            System.out.println("Connection stablised sacesfuly");

            Statement stam=con.createStatement();
            int rowEfected=stam.executeUpdate(query);

            if (rowEfected>0){
                System.out.println("deletion sacesful:"+rowEfected+"row's efected");
            }
            else {
                System.out.println("deletion not seasefull");
            }
            stam.close();
            con.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }

    }
}
