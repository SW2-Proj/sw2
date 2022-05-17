/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SqlClass;
import java.awt.Component;
import static java.awt.SystemColor.text;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

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
               Cust.setStartTime("parkedcar", id);
               DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
               Date date = new Date();
               //System.out.println(dateFormat.format(date));
               String time = dateFormat.format(date);
               Cust.deleteSpot();
              return time;
              
              }
   public void RemoveUser(int choose,String text)
        {
        if(choose==0)
        {
            int id=Integer.parseInt(text);
            Cust.deleteRow(id);
            Component frame = null;
            JOptionPane.showMessageDialog(frame, "User Removed Successfully");
        }
        else if (choose==1)
        {
            String plateNum=text;
            Cust.deleteRow(plateNum);
            Component frame = null;
            JOptionPane.showMessageDialog(frame, "User Removed Successfully");
        }
        else if (choose==2)
        {
            int spot=Integer.parseInt(text);
            Cust.deleteRows(spot);
            Component frame = null;
            JOptionPane.showMessageDialog(frame, "User Removed Successfully");}
        }
}
