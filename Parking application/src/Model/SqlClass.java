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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class SqlClass {
    
    /**
     *
     * @param Query
     * @return
     * @throws SQLException
     */
    public static ResultSet getQuery(String Query) throws SQLException {
        ResultSet QueryResult=null;
        Connection connect = setConnection();
        Statement QueryStatement = connect.createStatement();
        QueryResult = QueryStatement.executeQuery(Query);

        return QueryResult;
    }
//
//  public static void UpdateQuery(String Query) throws SQLException{
//   Connection connect = setConnection();
//   Statement QueryStatement = connect.createStatement();
//   QueryStatement.executeUpdate(Query);
//}
//  public static ResultSet getQuery(String Query,String var,int var2) throws SQLException{
//   Connection connect = setConnection();
//   Statement QueryStatement = connect.createStatement();
//   ResultSet QueryResult = QueryStatement.executeQuery(Query,var,var2);
//   
////   connect.close();
////   QueryStatement.close();
//   return QueryResult;
//}

    public void updateQuery(String string) {
        try {
            Connection connect = setConnection();
            Statement QueryStatement = connect.createStatement();
            QueryStatement.executeUpdate(string);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


  

    

}