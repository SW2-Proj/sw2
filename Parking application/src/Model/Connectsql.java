package Model;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        } catch (Exception e) {
            System.out.println(e);
        }
        return connectDB;
    }
   public static void main(String[] args) throws SQLException
   {
       
   }
}
