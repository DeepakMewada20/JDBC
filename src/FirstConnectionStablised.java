import java.sql.*;

public class FirstConnectionStablised {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url="jdbc:mysql://localhost:3306/COLLAGE";
        String userName="root";
        String password="nevermind";
        String query="select * from STUDENT;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Class found sacsecful");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,userName,password);
            System.out.println("connection stablist sacsesful");
            Statement stmt= con.createStatement();
            ResultSet rs=stmt.executeQuery(query);

            while (rs.next()){
                int rollno=rs.getInt("RollNo");
                String name=rs.getString("NAME");
                int marks=rs.getInt("Marks");
                String grade=rs.getString("grade");
                String city = rs.getString("city");
                System.out.println("rollno :"+ rollno);
                System.out.println("name :"+ name);
                System.out.println("marks :"+ marks);
                System.out.println("grade :"+ grade);
                System.out.println("city :"+ city);
                System.out.println();
                System.out.println("+++++++++++++++++++++++++");
            }
            rs.close();
            stmt.close();
            con.close();
            System.out.println("connection closed secesfuly");
        }
        catch (SQLException e) {
            System.out.println(e);
            //throw new SQLException("");
        }
    }
}
