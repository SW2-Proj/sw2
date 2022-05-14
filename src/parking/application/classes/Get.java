package parking.application.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static parking.application.classes.Connectsql.setConnection;

/**
 *
 * @author fady_
 */
public class Get {
       public Time getTotalTime(String TableName, int id) {
        DateFormat total = new SimpleDateFormat("HH:mm:ss");
        Connection con = setConnection();
        Time time = null;
        try {
            ResultSet rs;
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT totaltime From " + TableName + " WHERE id=" + id + "");
            System.out.println("Select Query Run Successfully");
            rs.next();
            time = rs.getTime("totaltime");
            System.out.println(time);
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return time;
    }
      public int getSpot(String tableName) {
        ResultSet pc = null;
        int x = 0;
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();

            pc = st.executeQuery("select * from " + tableName + " limit 1");
            while (pc.next()) {
                x = pc.getInt("spot");
            }

            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return x;
    }
    public int getID(String TableName) {
        Connection con = setConnection();
        int k = 2000;
        int i = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM " + TableName + "");
            while (rs.next()) {
                i = rs.getInt("id");
                k++;
            }
            if (k == 2000) {
                i = 2000;
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error Occuer");
        }
        return i + 1;
    }

    
}
