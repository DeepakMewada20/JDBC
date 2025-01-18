import com.sun.jdi.request.DuplicateRequestException;

import java.sql.*;
public class InsertDataInADatabase {
    public static void main(String[] args) throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/COLLAGE";
        String userName="root";
        String password="nevermind";
        String query="insert into STUDENT(RollNo,NAME,Marks,grade,city) value(109,'yogi',92,'O','gujrat');";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers load secesfuly");
        }
        catch (ClassNotFoundException e){
            System.out.println(e);
        }

        try {
            Connection con=DriverManager.getConnection(url,userName,password);
            System.out.println("conection stablised ");
            Statement stem = con.createStatement();
            try {
                int rowEfected = stem.executeUpdate(query);
                if (rowEfected > 0) {
                    System.out.println("ensert data sacefuly "+ rowEfected +"row's efected");
                } else {
                    System.out.println("data not insert");
                }
            }
            catch (SQLIntegrityConstraintViolationException e){
                System.out.println(e.getMessage());
            }
            stem.close();
            con.close();
            System.out.println();
            System.out.println("conection closed sacsesfuly");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
