/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GUI.Admin;
import Model.Connectsql;
import static Model.Connectsql.setConnection;
import Model.SqlClass;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 *
 */
public class SystemControls {
    Admin admin;
    CustomerControls Cust;
    Connection connectDB ;
    SqlClass get;
   public void addSpot() {
        
        int spot, maxReservedSpot = 0, maxFreeSpot = 0;
        try {
           ResultSet QueryResult= get.getQuery("SELECT spot FROM parkedcar");
            while (QueryResult.next()) {
                spot = QueryResult.getInt("spot");
                if (maxReservedSpot < spot) {
                    maxReservedSpot = spot;
                }
            }
            ResultSet QueryResult2= get.getQuery("SELECT spot FROM freespots");
            while (QueryResult2.next()) {
                spot = QueryResult2.getInt("spot");
                if (maxFreeSpot < spot) {
                    maxFreeSpot = spot;
                }
            }
            if (maxFreeSpot > maxReservedSpot) {
                maxFreeSpot += 1;
                get.updateQuery("insert into freespots VALUES (" + maxFreeSpot + ")");
            } else {
                maxReservedSpot += 1;
                get.updateQuery("insert into freespots VALUES (" + maxReservedSpot + ")");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
   
   public void removeSpot() {
        int spot, maxFreeSpot = 0;
        try {
            ResultSet QueryResult=get.getQuery("SELECT spot FROM freespots");
            while (QueryResult.next()) {
                spot = QueryResult.getInt("spot");
                if (maxFreeSpot < spot) {
                    maxFreeSpot = spot;
                }
            }
            get.updateQuery("DELETE FROM freespots where spot=" + maxFreeSpot + "");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
   public void setStartTime(String TableName, long id)
       {
           DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
           Date date = new Date();
           System.out.println(dateFormat.format(date));
           String time = dateFormat.format(date);
//           try {
                  get.updateQuery("UPDATE " + TableName + " set starttime = '" + time + "' WHERE ID = " + id + "");
                  System.out.println("Select Query Run Successfully");
//               }catch (SQLException e) {
//               System.out.println("Error");}
          
       }
   
   public void setEndTime(String TableName, long id)
       {
           DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	   Date date = new Date();
	   System.out.println(dateFormat.format(date)); 
           String time = dateFormat.format(date);
           try{
           get.updateQuery("UPDATE "+TableName+" set endtime = '"+time+"' WHERE ID = "+id+"");
           System.out.println("Select Query Run Successfully");
           }
           catch(Exception e)
           {
               System.out.println("Error");
           }
       }
   
   public void setTotalTime(String TableName, long id)
       {
           try{
           get.updateQuery("UPDATE "+TableName+" SET `totaltime`=(SELECT TIMEDIFF(endtime,starttime)) WHERE id="+id+"");
           System.out.println("Select Query Run Successfully");
           }
           catch(Exception e)
           {
               System.out.println("Error");
           }
       }
    public Time getTotalTime(String TableName, int id)
       {
           DateFormat totaltime = new SimpleDateFormat("HH:mm:ss");
           Time time = null;
           try{
               ResultSet QueryResult=get.("SELECT totaltime From "+TableName+" WHERE id="+id+"");
               System.out.println("Select Query Run Successfully");
               QueryResult.next();
               time=QueryResult.getTime("totaltime");
               System.out.println(time);
           }
           catch(SQLException e)
           {
               System.out.println(e);
           }
           return time;
       }
    
    public void setPayment (String TableName,float plateNum,int id)
        {
           try{
           get.updateQuery("UPDATE "+TableName+" set payment = '"+plateNum+"' WHERE ID = "+id+"");
           System.out.println("Select Query Run Successfully");
           }
           catch(Exception e)
           {
               System.out.println("Error");
           }
        }
    
     public void bookaSpot (int id,int spot,String p)throws Exception
        {          
            try {
                 id = Cust.getID("parkedcar");
                

                setStartTime("parkedcar", id);
                get.updateQuery("INSERT INTO parkedcar (spot) VALUES (" + spot + ") ");
            } catch (Exception e) {
                System.out.println("Error");
            }
       }
}