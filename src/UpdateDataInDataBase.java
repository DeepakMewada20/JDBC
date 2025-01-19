import java.sql.*;
public class UpdateDataInDataBase {
    static String url="jdbc:mysql://localhost:3306/COLLAGE";
    static String userName="root";
    static String password="nevermind";
    static String query="update STUDENT set NAME='mohan' where RollNo=109;";
    public static void main(String[] args) throws ClassNotFoundException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers load sesefuly");
        }
        catch (ClassNotFoundException e){
            System.out.println(e);
        }

        try {
            Connection con = DriverManager.getConnection(url,userName,password);
            System.out.println("connection satablised sacesfuly");
            Statement stam=con.createStatement();
            int rowEfected=stam.executeUpdate(query);
            if(rowEfected>0){
                System.out.println("update opretion perform sesesfuly");
            }
            else {
                System.out.println("update oparreton not perform");
            }

            stam.close();
            con.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }

    }
}
