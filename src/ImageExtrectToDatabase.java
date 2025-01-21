import java.sql.*;
import java.io.*;
public class ImageExtrectToDatabase {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/hotalDb";
        String username="root";
        String password="nevermind";
        String folderPath="/home/deepakmewada/Pictures/";
        String query="select image_data from image_table where image_id = ?;";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers load secesfuly");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection conn = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement=conn.prepareStatement(query);
            preparedStatement.setInt(1,1);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                String image_path=folderPath+"extrected.jpeg";
                try {
                    FileOutputStream fileOutputStream=new FileOutputStream(image_path);
                    byte[] image_data=rs.getBytes("image_data");
                    fileOutputStream.write(image_data);
                    System.out.println("Image extrect seccesfuly");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
            else {
                System.out.println("image not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
