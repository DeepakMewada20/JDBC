import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BetchProcessing {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/banking_system";
        String userName="root";
        String password="nevermind";
        String query="insert into accounts(account_num,belance) value(?, ?);";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("all Drivers are lodaed seccefuly");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection coon=DriverManager.getConnection(url,userName,password);
            System.out.println("Connection sablist sacesgully!!");
            coon.setAutoCommit(false);
            PreparedStatement preparedStatement=coon.prepareStatement(query);
            int acountNumber=1234527898;
            int balance=9000;
            for(int i=0;i<5;i++) {
                acountNumber=acountNumber+i;
                preparedStatement.setString(1, Integer.toString(acountNumber));
                preparedStatement.setInt(2, balance);
                preparedStatement.addBatch();
            }
            int rowefected[] = preparedStatement.executeBatch();
            for(int i:rowefected) {
                System.out.println(rowefected[i]);
            }
            coon.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
