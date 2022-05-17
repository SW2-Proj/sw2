/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Connectsql.setConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DELL
 */
public class SqlClass {
    
  public static ResultSet getQuery(String Query) throws SQLException{
   Connection connect = setConnection();
   Statement QueryStatement = connect.createStatement();
   ResultSet QueryResult = QueryStatement.executeQuery(Query);
   
   connect.close();
   QueryStatement.close();
   return QueryResult;
}
  public static ResultSet getQuery(String Query,String var) throws SQLException{
   Connection connect = setConnection();
   Statement QueryStatement = connect.createStatement();
   ResultSet QueryResult = QueryStatement.executeQuery(Query);
   
   connect.close();
   QueryStatement.close();
   return QueryResult;
}
  public static void UpdateQuery(String Query) throws SQLException{
   Connection connect = setConnection();
   Statement QueryStatement = connect.createStatement();
   QueryStatement.executeUpdate(Query);
   
   connect.close();
   QueryStatement.close();
}
  public static ResultSet getQuery(String Query,String var,int var2) throws SQLException{
   Connection connect = setConnection();
   Statement QueryStatement = connect.createStatement();
   ResultSet QueryResult = QueryStatement.executeQuery(Query,var,var2);
   
//   connect.close();
//   QueryStatement.close();
   return QueryResult;
}

    public void updateQuery(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}