package parking.application.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static parking.application.classes.Connectsql.setConnection;

/**
 *
 * @author fady_
 */
public class Delete {
      public void removeSpot() {
        Connection con = setConnection();
        int s, max = 0;
        try {
            Statement st = con.createStatement();
            ResultSet re = st.executeQuery("SELECT spot FROM freespots");
            while (re.next()) {
                s = re.getInt("spot");
                if (max < s) {
                    max = s;
                }
            }
            st.executeUpdate("DELETE FROM freespots where spot=" + max + "");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
        public void deleteSpot() {
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();

            st.executeUpdate("DELETE FROM freespots limit 1 ");
            System.out.println("Deleted");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void deleteRow(long id) {
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT spot from parkedcar where id=" + id + "");
            rs.next();
            int ss = rs.getInt("spot");
            st.executeUpdate("INSERT INTO freespots VALUES (" + ss + ")");
            st.executeUpdate("DELETE FROM parkedcar WHERE id=" + id + "");
            System.out.println("Deleted");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void deleteRow(String p) {
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT spot from parkedcar where platenum='" + p + "'");
            rs.next();
            int ss = rs.getInt("spot");
            st.executeUpdate("INSERT INTO freespots VALUES (" + ss + ")");
            st.executeUpdate("DELETE FROM parkedcar WHERE platenum='" + p + "'");
            System.out.println("Deleted");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void deleteRows(int s) {
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO freespots VALUES (" + s + ")");
            st.executeUpdate("DELETE FROM parkedcar WHERE spot=" + s + "");
            System.out.println("Deleted");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}
