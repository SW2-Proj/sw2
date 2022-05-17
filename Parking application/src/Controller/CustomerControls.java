/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GUI.entryStation;
import static Model.Connectsql.setConnection;
import Model.SqlClass;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class CustomerControls {
    SqlClass get=new SqlClass();
    Connection connectDB ;
    private void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    public int checkPlate (String plateNo)
       { 
           String PlateNum=null;
           int Flag=0;
            try
           {  
             ResultSet QueryResult= get.getQuery("SELECT platenum From parkedcar");
               
               while(QueryResult.next())
           {
                PlateNum= QueryResult.getString("platenum");
                System.out.println(PlateNum);
               if(PlateNum.equals(plateNo))
               {Flag=1; break;}
               else {Flag= 0;}
           }
            
            
            int id = getID("parkedcar");
            String i = id + "";
            
           }
           catch(Exception e)
           {
               System.out.println("Errorrrr");
           }
            return Flag;
       }
   
    
    
    
    public int getID(String TableName) {
        
        int StartId = 2000;
        int id = 0;
        try {
        ResultSet QueryResult= get.getQuery("SELECT id FROM " + TableName + "");            
            while (QueryResult.next()) {
                id = QueryResult.getInt("id");
                StartId++;
            }
            if (StartId == 2000) {
                id = 2000;
            }
            QueryResult.close();
        } catch (Exception e) {
            System.out.println("Error Occuer");
        }
        return id + 1;
    }
    
    
    public void insertEntryData(String plateNum)
    {

        try {
            
            int id = getID("parkedcar");

            entryStation entry = new entryStation();
            int spot = getSpot("freespots");
            ResultSet QueryResult= get.getQuery("INSERT INTO parkedcar (id,spot,platenum) VALUES (" + id + "," + spot + ",'" + plateNum + "')");
            setStartTime("parkedcar", id);
//            st.close();
//            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }

        new entryStation().setVisible(true);
        close();
    
    
    }
    public void setStartTime(String TableName, long id)
       {
           Connection con = setConnection();
           DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
           Date date = new Date();
           System.out.println(dateFormat.format(date));
           String time = dateFormat.format(date);
           try {
               ResultSet QueryResult= get.getQuery("UPDATE " + TableName + " set starttime = '" + time + "' WHERE ID = " + id + "");

               System.out.println("Select Query Run Successfully");
               QueryResult.close();
               connectDB.close();
           } catch (SQLException e) {
               System.out.println("Error");
           }
       }

    public void deleteSpot() {
        try {
            

           ResultSet QueryResult= get.getQuery("DELETE FROM freespots limit 1 ");
            System.out.println("Deleted");
            QueryResult.close();
            Con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    
    
    public void deleteRow(long id) {
        try {
            ResultSet QueryResult= get.getQuery("SELECT spot from parkedcar where id=" + id + "");
            QueryResult.next();
            int reservedSpot = QueryResult.getInt("spot");
             get.updateQuery("INSERT INTO freespots VALUES (" + reservedSpot + ")");
             get.updateQuery("DELETE FROM parkedcar WHERE id=" + QueryResult + "");
            System.out.println("Deleted");
//            st.close();
//            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
        public void deleteRow (String p)
    {
        try {
            ResultSet QueryResult= get.getQuery("SELECT spot from parkedcar where platenum='"+p+"'");
            QueryResult.next();
            int freeSpot=QueryResult.getInt("spot");
            get.executeUpdate("INSERT INTO freespots VALUES ("+freeSpot+")");
            get.executeUpdate("DELETE FROM parkedcar WHERE platenum='"+p+"'");
            System.out.println("Deleted");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    public void deleteRows (int s)
    {
        try {
             get.updateQuery("INSERT INTO freespots VALUES ("+s+")");
            get.updateQuery("DELETE FROM parkedcar WHERE spot="+s+"");
            System.out.println("Deleted");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    
    public int freeSpots() {
        
        int free = 0;
        try {
           ResultSet QueryResult= get.getQuery("SELECT * FROM freespots");
            while (QueryResult.next()) {
                free++;
            }
            QueryResult.close();
//            st.close();
        } catch (Exception e) {
            System.out.println("Error Occuer");
        }
       
           
        return free;
    }

    public int busySpots() {
        Connection con = setConnection();
        int k = 0;
        try {
            ResultSet QueryResult= get.getQuery("SELECT * FROM parkedcar");
            while (QueryResult.next()) {
                k++;
            }
            QueryResult.close();
//            st.close();
        } catch (Exception e) {
            System.out.println("Error Occuer");
        }
        return k;
    }

    public int getSpot(String tableName) {
        int freeSpot = 0;
        try {
            ResultSet QueryResult= get.getQuery("select * from freespots limit 1");
            while (QueryResult.next()) {
                freeSpot = QueryResult.getInt("spot");
                System.out.println(freeSpot);
            }

//            st.close();
//            con.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return freeSpot;
    }
}
