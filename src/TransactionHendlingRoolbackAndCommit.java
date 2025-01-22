import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionHendlingRoolbackAndCommit {
    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/banking_system";
        String userName="root";
        String password="nevermind";
        String withdrowquery="update accounts set belance = belance - ? where account_num = ?";
        String depositquery="update accounts set belance = belance + ? where account_num = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("all Drivers are lodaed seccefuly");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection conn=DriverManager.getConnection(url,userName,password);
            System.out.println("connection stablist secefuly");
            conn.setAutoCommit(false);
            try {
                PreparedStatement withdrow = conn.prepareStatement(withdrowquery);
                PreparedStatement deposit = conn.prepareStatement(depositquery);

                Double amountOfTransection=500.00;

                withdrow.setDouble(1, amountOfTransection);
                withdrow.setString(2, "1234567890");

                deposit.setDouble(1,amountOfTransection);
                deposit.setString(2,"2576849395");
                int rowEfectedWithdrow = withdrow.executeUpdate();
                int rowEfectedDeposit = deposit.executeUpdate();
                if (rowEfectedWithdrow > 0 && rowEfectedDeposit > 0) {
                    conn.commit();
                    System.out.println("tranjection sacesfull !!");
                } else {
                    conn.rollback();
                    System.out.println("Transection faiiled !!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
