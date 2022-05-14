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
public class Check {
    public int checkFspot(int s) {
        int i = 0;
        int z = 0;
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            ResultSet pc = st.executeQuery("SELECT spot FROM freespots ");

            while (pc.next()) {
                i = pc.getInt("spot");
                System.out.println(i);
                if (i == s) {
                    z = 1;
                    break;
                } else {
                    z = 0;
                }
            }

            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Errorrrr");
        }
        return z;
    }
    public int checkPlate(String p) {
        String x = null;
        int z = 0;
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            ResultSet pc = st.executeQuery("SELECT platenum FROM parkedcar ");

            while (pc.next()) {
                x = pc.getString("platenum");
                if (x.equals(p)) {
                    z = 1;
                    break;
                } else {
                    z = 0;
                }
            }

            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Errorrrr");
        }
        return z;
    }

    public int checkId(int id) {
        int i = 0;
        int z = 0;
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            ResultSet pc = st.executeQuery("SELECT id FROM parkedcar ");

            while (pc.next()) {
                i = pc.getInt("id");
                System.out.println(i);
                if (i == id) {
                    z = 1;
                    break;
                } else {
                    z = 0;
                }
            }

            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return z;
    }

    public int freeSpots() {
        Connection con = setConnection();
        int k = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM freespots");
            while (rs.next()) {
                k++;
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error Occuer");
        }
        return k;
    }

    public int busySpots() {
        Connection con = setConnection();
        int k = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM parkedcar");
            while (rs.next()) {
                k++;
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error Occuer");
        }
        return k;
    }

    

}
