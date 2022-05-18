/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GUI.shiftsReport;
import Model.SqlClass;
import java.awt.Component;
import static java.awt.SystemColor.text;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class AdminControls {
  CustomerControls Cust;
  SystemControls Systems;
  SqlClass get;
  
   public String addUser(long id,int spot,String plateNum)
              {
               get.updateQuery("INSERT INTO parkedcar (id,spot,platenum) VALUES (" + id + "," + spot + ",'" + plateNum + "')");
               Systems.setStartTime("parkedcar", id);
               DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
               Date date = new Date();
               //System.out.println(dateFormat.format(date));
               String time = dateFormat.format(date);
               Systems.deleteSpot();
              return time;
              
              }
   public void RemoveUser(int choose,String text)
        {
        if(choose==0)
        {
            int id=Integer.parseInt(text);
            Systems.deleteRow(id);
            Component frame = null;
            JOptionPane.showMessageDialog(frame, "User Removed Successfully");
        }
        else if (choose==1)
        {
            String plateNum=text;
            Systems.deleteRow(plateNum);
            Component frame = null;
            JOptionPane.showMessageDialog(frame, "User Removed Successfully");
        }
        else if (choose==2)
        {
            int spot=Integer.parseInt(text);
            Systems.deleteRows(spot);
            Component frame = null;
            JOptionPane.showMessageDialog(frame, "User Removed Successfully");}
        }
   
   public void UpdateUser(int index,String text,int Id)
   {
   if(index==0)
        {
            String plateNum=text;
           
            Systems.updatePlate(plateNum,Id);
            Component frame = null;
            JOptionPane.showMessageDialog(frame, "Plate Number Updated Successfully");
        }
        else if (index==1)
        {
            int spot=Integer.parseInt(text);
            int flag=Systems.checkFreespot(spot);
            
            if (flag==1)
            {
                Systems.updateSpot(Id,spot);
                Component frame = null;
                JOptionPane.showMessageDialog(frame, "Spot Number Updated Successfully");
            }
            else
            {
                Component frame = null;
                JOptionPane.showMessageDialog(frame, "No valid Spot");
            }
        }
}
   


}
