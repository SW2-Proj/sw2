package Model;
import java.sql.Connection;
import java.sql.*;


public class Connectsql {

    public static Connection setConnection() {
        Connection connectDB = null;
        try {
            String url = "jdbc:mysql://localhost:3306/parking";
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "root";
            String password = "";
            connectDB = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return connectDB;
    }
  
}
