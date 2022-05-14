package parking.application.classes;
import java.sql.Connection;
import java.sql.*;


public class Connectsql {

    public static Connection setConnection() {
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/parking";
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "root";
            String password = "";
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Not Connected");
        }
        return con;
    }
}
