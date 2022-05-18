
package Controller;

import GUI.entryStation;
import static Model.Connectsql.*;
import Model.SqlClass;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;


/**
 *
 * 
 */
public class CustomerControls {
SystemControls Systems=new SystemControls();
//entryStation e=new entryStation();
SqlClass get= new SqlClass();
AdminControls admin= new AdminControls();
   
    
    
    
    
    public int checkPlate (String plateNo)
       { 
           Connection connect=setConnection();
          String PlateNum = null;
        int Flag = 0;
        try {
            ResultSet QueryResult = get.getQuery("SELECT platenum From parkedcar");

            while (QueryResult.next()) {
                PlateNum = QueryResult.getString("platenum");
                System.out.println(PlateNum);
                if (PlateNum.equals(plateNo)) {
                    Flag = 1;
                    break;
                } 
            }
            //System.out.println("ddsds");
//            int id = getID("parkedcar");
//            String id_String = id + "";
        } catch (Exception e) {
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
            System.out.println("Occuer");
        }
        return id + 1;
    }
    
    
    public void insertEntryData(String plateNum)
    {

        try {
            
            int id = getID("parkedcar");
            int spot = Systems.getSpot("freespots");
            //String  
           get.updateQuery("INSERT INTO parkedcar (id,spot,platenum) VALUES (" + id + "," + spot + ",'" + plateNum + "')");
            Systems.setStartTime("parkedcar", id);
//            st.close();
//            con.close();
        } catch (Exception e) {
            System.out.println("Errorrrrrrrrrr");
        }

       
    }
  
    public void pay(int id) throws SQLException {
        {
           
            try{
                int Id = 0, spot = 0;
            String plateNum = null;
            Time startTime = null, exitTime = null, totalTime = null;
            float payment = (float) 0.0;
            ResultSet QueryResult= get.getQuery("SELECT * From parkedcar WHERE id=" + id + "");
            System.out.println("Select Query Run Successfully");
            QueryResult.next();
            Id = QueryResult.getInt("id");
            spot = QueryResult.getInt("spot");
            plateNum = QueryResult.getString("platenum");
            payment = QueryResult.getFloat("payment");
            startTime = QueryResult.getTime("starttime");
            exitTime = QueryResult.getTime("endtime");
            totalTime = QueryResult.getTime("totaltime");
            get.updateQuery("INSERT INTO totalcars VALUES (" + Id + "," + spot + ",'" + startTime + "','" + exitTime + "','" + totalTime + "','" + plateNum + "', '" + payment + "')");
            System.out.println("insert Query Run Successfully");
           ResultSet QueryResult2= get.getQuery("SELECT spot From parkedcar WHERE id=" + id + "");
            QueryResult2.next();
            int spots = QueryResult2.getInt("spot");
            get.updateQuery("INSERT INTO freespots VALUES (" + spots + ")");
            get.updateQuery("DELETE From parkedcar WHERE id=" + id + "");
        } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
