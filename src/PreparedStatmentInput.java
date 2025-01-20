import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PreparedStatmentInput {
    static private int rollNo;
    static private String name;
    static private int marks;
    static private String grade;
    static private String city;
    public static void main(String[] args){
        String url="jdbc:mysql://localhost:3306/COLLAGE";
        String userName="root";
        String password="nevermind";
        String query="insert into STUDENT(RollNo,NAME,Marks,grade,city) value(?,?,?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers load secesfuly");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection conn=DriverManager.getConnection(url,userName,password);
            PreparedStatement preparedStatement =conn.prepareStatement(query);
            inputData();
            preparedStatement.setInt(1,rollNo);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,marks);
            preparedStatement.setString(4,grade);
            preparedStatement.setString(5,city);

            int rowEfected=preparedStatement.executeUpdate();
            if (rowEfected>0){
                System.out.println("Data insert seceful");
            }
            else {
                System.out.println("Data not inserted");
            }

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    static void inputData(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter data for insert in a database...");
        System.out.print("Enert Roll No : ");
        rollNo=sc.nextInt();
        System.out.print("Enert Name : ");
        name=sc.next();
        System.out.print("Enter marks : ");
        marks=sc.nextInt();
        System.out.print("Enter grade : ");
        grade=sc.next();
        System.out.print("Enter city : ");
        city=sc.next();
    }
}
