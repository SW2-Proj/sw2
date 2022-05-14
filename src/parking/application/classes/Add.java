
package parking.application.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static parking.application.classes.Connectsql.setConnection;

/**
 *
 * @author fady_
 */
public class Add {

    public void bookaSpot(int id, int spot, String p) throws Exception {
        try {
            new Get().getID("parkedcar");
            Connection con = setConnection();
            Statement st = con.createStatement();

            setStartTime("parkedcar", id);
            st.executeUpdate("INSERT INTO parkedcar (spot) VALUES (" + spot + ") ");

            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void addSpot() {
        Connection con = setConnection();
        int s, max1 = 0, max2 = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT spot FROM parkedcar");
            while (rs.next()) {
                s = rs.getInt("spot");
                if (max1 < s) {
                    max1 = s;
                }
            }
            ResultSet re = st.executeQuery("SELECT spot FROM freespots");
            while (re.next()) {
                s = re.getInt("spot");
                if (max2 < s) {
                    max2 = s;
                }
            }
            if (max2 > max1) {
                max2 += 1;
                st.executeUpdate("insert into freespots VALUES (" + max2 + ")");
            } else {
                max1 += 1;
                st.executeUpdate("insert into freespots VALUES (" + max1 + ")");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void setStartTime(String TableName, long id) {
        Connection con = setConnection();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String time = dateFormat.format(date);
        try {
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE " + TableName + " set starttime = '" + time + "' WHERE ID = " + id + "");
            System.out.println("Select Query Run Successfully");
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public void setEndTime(String TableName, long id) {
        Connection con = setConnection();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String time = dateFormat.format(date);
        try {
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE " + TableName + " set endtime = '" + time + "' WHERE ID = " + id + "");
            System.out.println("Select Query Run Successfully");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

    public void setTotalTime(String TableName, long id) {
        Connection con = setConnection();
        try {
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE " + TableName + " SET `totaltime`=(SELECT TIMEDIFF(endtime,starttime)) WHERE id=" + id + "");
            System.out.println("Select Query Run Successfully");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

    }
}
