import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.*;
public class ImageSaveInDatabase {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/hotalDb";
        String username="root";
        String password="nevermind";
        String imagePath="/home/deepakmewada/Pictures/IMG_20250105_180216.jpg";
        String query="insert into image_table(image_data) value(?);";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers load secesfuly");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection conn=DriverManager.getConnection(url,username,password);
            System.out.println("connection stablised sacesfuly");
            FileInputStream fileInputStream = new FileInputStream(imagePath);
            byte[] imagedata=new byte[fileInputStream.available()];
            fileInputStream.read(imagedata);

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setBytes(1,imagedata);
            int rowEfected=preparedStatement.executeUpdate();
            if(rowEfected>0){
                System.out.println("store image succesfuly");
            }
            else {
                System.out.println("Image not insert ");
            }

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
