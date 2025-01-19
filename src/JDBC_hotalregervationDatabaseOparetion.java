import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBC_hotalregervationDatabaseOparetion {
    private Statement stam;
    private Connection con;
    private Scanner sc=new Scanner(System.in);
    JDBC_hotalregervationDatabaseOparetion() throws ClassNotFoundException,SQLException{
        final String url="jdbc:mysql://localhost:3306/hotalDb";
        final String userName = "root";
        final String password = "nevermind";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver load secefuly");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            con=DriverManager.getConnection(url,userName,password);
            System.out.println("Connection stablist sacsefully");
            stam = con.createStatement();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    int newReservation(){
        int rowEfected;
        int roomNo;
        String guest_name;
        String guestContectNo;
        System.out.print("Enter room No : ");
        roomNo=sc.nextInt();
        System.out.print("Guest Name : ");
        guest_name=sc.next();
        System.out.print("Guest Contect No : ");
        guestContectNo=sc.next();
        String query="insert into reservation(room_no,guest_name,contect_no) value("+roomNo+",\""+guest_name+"\",\""+guestContectNo+"\");";
        //System.out.println(query);
        try {
            //stam =con.createStatement();
            rowEfected=stam.executeUpdate(query);
            if(rowEfected>0){
                System.out.println("reservation secesfuly done!");
                System.out.println("------------------------------");
            }
            else {
                System.out.println("Reservation not done!!!");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
        return rowEfected;
    }
    void checkReservation(){
        ResultSet rs;
        try {
            rs=stam.executeQuery("select * from reservation");
            while (rs.next()){
                System.out.println();
                System.out.println("Reservation ID : "+rs.getInt("reservation_ID"));
                System.out.println("Room No : "+rs.getInt("room_no"));
                System.out.println("Guest name : "+rs.getString("guest_name"));
                System.out.println("Guest comtect no : "+rs.getString("contect_no"));
                System.out.println("Reservation Date : "+rs.getTimestamp("reservation_date"));
                System.out.println();
                System.out.println("------------------------------------------");
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    int updateReservation(){
        int rowEfected;
        int reservation_ID;
        int roomNo;
        String guest_name;
        String guestContectNo;
        System.out.print("Enter Reservation ID, where you want to update : ");
        reservation_ID=sc.nextInt();
        System.out.print("Enter room No : ");
        roomNo=sc.nextInt();
        System.out.print("Guest Name : ");
        guest_name=sc.next();
        System.out.print("Guest Contect No : ");
        guestContectNo=sc.next();

        String query="update reservation set room_no = "+roomNo+",guest_name = \""+guest_name+"\",contect_no = \""+guestContectNo+"\" where reservation_ID = "+reservation_ID+";";
        try {
            rowEfected=stam.executeUpdate(query);
            if(rowEfected>0){
                System.out.println("data sacsefuly updated");
            }
            else {
                System.out.println("Data not updated");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
        return rowEfected;
    }
    void checkRoomNo(){
        int reservation_ID;
        System.out.print("Enter reservation Id to find guest room_no : ");
        reservation_ID=sc.nextInt();
        String query="select room_no from reservation where reservation_ID = "+reservation_ID+";";
        try {
            ResultSet rs=stam.executeQuery(query);
            while (rs.next()){
                System.out.println("Room No : "+rs.getInt("room_no"));
            }
            System.out.println("---------------------------");
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    void deletresarvation(){
        int reservation_ID;
        int rowefected;
        System.out.println("Enter reservation ID to delete reservations : ");
        reservation_ID=sc.nextInt();
        String query="delete from reservation where reservation_ID = "+reservation_ID+";";
        try {
            rowefected= stam.executeUpdate(query);
            if (rowefected>0){
                System.out.println("reservation secefuly deleted");
            }
            else {
                System.out.println("Reservation Not deleted!!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void exit(){
        try {
            stam.close();
            con.close();
            sc.close();
            System.out.print("System is exiting ");
            for (int i=0;i<5;i++){
                System.out.print(".");
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
