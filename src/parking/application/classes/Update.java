package parking.application.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import static parking.application.classes.Connectsql.setConnection;

/**
 *
 * @author fady_
 */
public class Update {
    public void updateSpot(int id, int spot) {
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT spot from parkedcar where id=" + id + "");
            System.out.println("selected");
            rs.next();
            int s = rs.getInt("spot");
            st.executeUpdate("insert into freespots VALUES (" + s + ")");
            System.out.println("inserted");
            st.executeUpdate("Update parkedcar set spot='" + spot + "' where id='" + id + "'");
            System.out.println("updated");
            st.executeUpdate("Delete from freespots where spot=" + spot + "");
            System.out.println("deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updatePlate(String p, int id) {
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();

            st.executeUpdate("UPDATE parkedcar set platenum = '" + p + "' Where id = " + id + "");
            System.out.println("Updated");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
