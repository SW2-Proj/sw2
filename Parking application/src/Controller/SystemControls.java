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
import static javax.management.remote.JMXConnectorFactory.connect;
import static javax.management.remote.JMXConnectorFactory.connect;


/**
 *
 *
 */
public class SystemControls {
    Admin admin;
    CustomerControls Cust;
    SqlClass get;
//    Connection connect = setConnection();

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
   
   
    public void setStartTime(String TableName, long id)
       {
           DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
           Date date = new Date();
           System.out.println(dateFormat.format(date));
           String time = dateFormat.format(date);
           System.out.println(id);
           get.updateQuery("UPDATE " + TableName + " set starttime = '" + time + "' WHERE ID = " + id + "");
           System.out.println("Select Query Run Successfully");
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
               ResultSet QueryResult=get.getQuery("SELECT totaltime From "+TableName+" WHERE id="+id+"");
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
    
    public void setPayment (float payment,int id)
        {
           try{
           get.updateQuery("UPDATE parkedcar set payment = '"+payment+"' WHERE ID = "+id+"");
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
     
    
public void reserveSpot() {
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
    public void deleteSpot() {
        try {
           get.updateQuery("DELETE FROM freespots limit 1 ");
            System.out.println("Deleted");          
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
             get.updateQuery("DELETE FROM parkedcar WHERE id=" + id + "");
            System.out.println("Deleted");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
        public void deleteRow (String plateNum)
    {
        try {
            ResultSet QueryResult= get.getQuery("SELECT spot from parkedcar where platenum='"+plateNum+"'");
            QueryResult.next();
            int freeSpot=QueryResult.getInt("spot");
            get.updateQuery("INSERT INTO freespots VALUES ("+freeSpot+")");
            get.updateQuery("DELETE FROM parkedcar WHERE platenum='"+plateNum+"'");
            System.out.println("Deleted");

        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    public void deleteRows (int spot)
    {
        try {
             get.updateQuery("INSERT INTO freespots VALUES ("+spot+")");
            get.updateQuery("DELETE FROM parkedcar WHERE spot="+spot+"");
            System.out.println("Deleted");

        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    
 



    public int getSpot(String tableName) {
        ResultSet QueryResult;
        int freeSpot = 0;
        try {
            QueryResult= get.getQuery("select * from freespots limit 1");

            while (QueryResult.next()) {
                freeSpot = QueryResult.getInt("spot");
                System.out.println(freeSpot);
            }

        } catch (SQLException e) {
            System.out.println("Erroooooor");
        }
        return freeSpot;
    }
    public Time calulateTotalTime(int id)
    {
        setEndTime("parkedcar", id);
        System.out.println("ggggggggggggggg");
        setTotalTime("parkedcar", id);
        System.out.println("hhhhhhhhhhhhhhhh");
        Time time=getTotalTime("parkedcar", id);
       return time;
    }

    public float calculatePayment(Time time) {
        float ttime=(float) ((time.getSeconds()/3600.0)+(time.getMinutes()/60.0)+(time.getHours()));
        System.out.println(ttime);
        float pay=(float) (ttime*5.0);
        return pay;
    }
    public int checkId (int id)
       { 
           int Id=0;
           int flag=0;
            try
           {
                ResultSet QueryResult =get.getQuery("SELECT id FROM parkedcar ");
               
               while(QueryResult.next())
           {
                Id= QueryResult.getInt("id");
                System.out.println(Id);
               if(Id==id)
               {flag=1; break;}
               else {flag= 0;}
           }
           }
           catch(Exception e)
           {
               System.out.println("Errorrrr");
           }
            return flag;
       }
    
    public void updatePlate(String p,int id)
{
               try
           {
               get.updateQuery("UPDATE parkedcar set platenum = '"+p+"' Where id = "+id+"");
               System.out.println("Updated");
           }
           catch(Exception e)
           {
               System.out.println(e);
           }
}
    public int checkFreespot (int spot)
       { 
           int freeSpot=0;
           int flag=0;
            try
           {
               ResultSet QueryResult = get.getQuery("SELECT spot FROM freespots ");
               
               while(QueryResult.next())
           {
                freeSpot= QueryResult.getInt("spot");
                System.out.println(freeSpot);
               if(freeSpot==spot)
               {flag=1; break;}
               else {flag= 0;}
           }

           
           }
           catch(Exception e)
           {
               System.out.println("Errorrrr");
           }
            return flag;
       }
       
public void updateSpot(int id,int spot)
{
    try {
         ResultSet QueryResult =get.getQuery("SELECT spot from parkedcar where id="+id+"");
        System.out.println("selected");
        QueryResult.next();
        int spots=QueryResult.getInt("spot");
        get.updateQuery("insert into freespots VALUES ("+spots+")");
        System.out.println("inserted");
       get.updateQuery("Update parkedcar set spot='"+spot+"' where id='"+id+"'");
        System.out.println("updated");
        get.updateQuery("Delete from freespots where spot="+spot+"");
        System.out.println("deleted");
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
}

    public int freeSpotsNumber() {
         int freeSpots = 0;
        try {
           ResultSet QueryResult= get.getQuery("SELECT * FROM freespots");
            while (QueryResult.next()) {
                freeSpots++;
            }
        } catch (Exception e) {
            System.out.println("Error Occuer");
        }
       
        return freeSpots;
    }

    public int busySpotsNumber() {
          int NumOfSpots = 0;
        try {
            ResultSet QueryResult= get.getQuery("SELECT * FROM parkedcar");
            while (QueryResult.next()) {
                NumOfSpots++;
            }
 
        } catch (Exception e) {
            System.out.println("Error Occuer");
        }
        return NumOfSpots;
    }
}
