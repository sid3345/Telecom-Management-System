/*
 * MyComboBox.java
 *

 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package connectingdatabase.Search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;

/**
 *
 
 */
class MyComboBox extends JComboBox 
{
     public MyComboBox(Connection conn,String TableName)
     {
          if(conn==null) {
             return ;
         }
        Statement stat;
        try {
            stat = conn.createStatement();
             ResultSet rs=stat.executeQuery("Select * from "+TableName+"");
           ResultSetMetaData  rsmd= rs.getMetaData();
            int size=rsmd.getColumnCount();
             for(int i=0;i<size;i++)
             {
                addItem(rsmd.getColumnName(i+1));
             }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
           
     }
}