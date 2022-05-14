package parking.application.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import static parking.application.classes.Connectsql.setConnection;

/**
 *
 * @author fady_
 */
public class Payment {
  public void setPayment(String TableName, float p, int id) {
        Connection con = setConnection();
        try {
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE " + TableName + " set payment = '" + p + "' WHERE ID = " + id + "");
            System.out.println("Select Query Run Successfully");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void pay(int id) throws SQLException {
        int i = 0, spot = 0;
        String p = null;
        Time st = null, et = null, tt = null;
        float payment = (float) 0.0;
        Connection con = setConnection();
        Statement stt = con.createStatement();
        try {
            ResultSet rs = stt.executeQuery("SELECT * From parkedcar WHERE id=" + id + "");
            System.out.println("Select Query Run Successfully");
            rs.next();
            i = rs.getInt("id");
            spot = rs.getInt("spot");
            p = rs.getString("platenum");
            payment = rs.getFloat("payment");
            st = rs.getTime("starttime");
            et = rs.getTime("endtime");
            tt = rs.getTime("totalTime");
            stt.executeUpdate("INSERT INTO totalcars VALUES (" + i + "," + spot + ",'" + st + "','" + et + "','" + tt + "','" + p + "', '" + payment + "')");
            System.out.println("insert Query Run Successfully");
            ResultSet hu = stt.executeQuery("SELECT spot From parkedcar WHERE id=" + id + "");
            hu.next();
            int s = hu.getInt("spot");
            stt.executeUpdate("INSERT INTO freespots VALUES (" + s + ")");
            stt.executeUpdate("DELETE From parkedcar WHERE id=" + id + "");
            stt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
