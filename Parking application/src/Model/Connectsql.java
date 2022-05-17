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

//    public int getID(String TableName) {
//        Connection con = setConnection();
//        int k = 2000;
//        int i = 0;
//        try {
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("SELECT id FROM " + TableName + "");
//            while (rs.next()) {
//                i = rs.getInt("id");
//                k++;
//            }
//            if (k == 2000) {
//                i = 2000;
//            }
//            rs.close();
//            st.close();
//        } catch (Exception e) {
//            System.out.println("Error Occuer");
//        }
//        return i + 1;
//    }

       public void setStartTime(String TableName, long id)
       {
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
       public void setEndTime(String TableName, long id)
       {
           Connection con = setConnection();
           DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	   Date date = new Date();
	   System.out.println(dateFormat.format(date)); 
           String time = dateFormat.format(date);
           try{
           Statement st = con.createStatement();
           st.executeUpdate("UPDATE "+TableName+" set endtime = '"+time+"' WHERE ID = "+id+"");
           System.out.println("Select Query Run Successfully");
           st.close();
           con.close();
           }
           catch(Exception e)
           {
               System.out.println("Error");
           }

       }
       
        public void setTotalTime(String TableName, long id)
       {
           Connection con = setConnection();
           try{
           Statement st = con.createStatement();
           st.executeUpdate("UPDATE "+TableName+" SET `totaltime`=(SELECT TIMEDIFF(endtime,starttime)) WHERE id="+id+"");
           System.out.println("Select Query Run Successfully");
           st.close();
           con.close();
           }
           catch(Exception e)
           {
               System.out.println("Error");
           }

       }

        public Time getTotalTime(String TableName, int id)
       {
           DateFormat total = new SimpleDateFormat("HH:mm:ss");
           Connection con = setConnection();
           Time time = null;
           try{
               ResultSet rs;
               Statement st = con.createStatement();
               rs = st.executeQuery("SELECT totaltime From "+TableName+" WHERE id="+id+"");
               System.out.println("Select Query Run Successfully");
               rs.next();
               time=rs.getTime("totaltime");
               System.out.println(time);
               st.close();
               con.close();
           }
           catch(SQLException e)
           {
               System.out.println(e);
           }
           return time;
       }
        public void setPayment (String TableName,float p,int id)
        {
           Connection con = setConnection();
           try{
           Statement st = con.createStatement();
           st.executeUpdate("UPDATE "+TableName+" set payment = '"+p+"' WHERE ID = "+id+"");
           System.out.println("Select Query Run Successfully");
           st.close();
           con.close();
           }
           catch(Exception e)
           {
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
            tt = rs.getTime("totaltime");
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
        
       
       
        public static void bookaSpot (int id,int spot,String p)throws Exception
        {          
            Connectsql q = new Connectsql();
            try {
                int i = q.getID("parkedcar");
                Connection con = setConnection();
                Statement st = con.createStatement();

                q.setStartTime("parkedcar", id);
                st.executeUpdate("INSERT INTO parkedcar (spot) VALUES (" + spot + ") ");

                st.close();
                con.close();
            } catch (Exception e) {
                System.out.println("Error");
            }
       }
   public static void main(String[] args) throws SQLException
   {
       
   }
}
