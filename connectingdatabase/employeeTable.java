/*
 * employeeTable.java
 *

 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package connectingdatabase;

import connectingdatabase.PKResultModel.CashingResultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 
 */
public class employeeTable extends JPanel {
    
    /** Creates a new instance of employeeTable */
    public employeeTable(Connection conn) {
            if(conn==null) {
             return ;
         }
    try {
          this.conn=conn;
            String query="Select * from employee";
            Statement stat=conn.createStatement();
            rs=stat.executeQuery(query);
            myModel=new CashingResultTableModel(rs); 
            myTable=new JTable(myModel);
            add(myTable);
                mySPane=new JScrollPane(myTable
                                          ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                          JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                add(mySPane);
        } catch (SQLException ex) {
           Main.myArea.append(ex.toString());
        }
    }
    
    public void ChangeModel()
    {
            String query="Select * from employee";
            if(conn==null)
            {
                return;
            }
        Statement stat;
        try {
            stat = conn.createStatement();
            
            rs=stat.executeQuery(query);
            myModel=new CashingResultTableModel(rs); 
            myTable.setModel(myModel);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    JTable myTable;
    JScrollPane mySPane;
    ResultSet rs;
    CashingResultTableModel myModel;
    Connection conn;
}
