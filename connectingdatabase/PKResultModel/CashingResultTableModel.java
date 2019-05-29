/*
 * CashingResultTableModel.java
 *
 
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package connectingdatabase.PKResultModel;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * 
 */
abstract class myResultTableModel extends AbstractTableModel
{
    public myResultTableModel(ResultSet myResultSet)
    {
        rs=myResultSet;
        try {
             
            rsmd=myResultSet.getMetaData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
     
   public String getColumnName(int Col)
   {
        try {
            return rsmd.getColumnName(Col+1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Unknown";
        }
   }
    
   public int getColumnCount()
   {
        try {
            return rsmd.getColumnCount();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
   }
   public ResultSet getResultSet()
   {
       return rs;
   }
   
   
      private ResultSet rs;
      private ResultSetMetaData rsmd;
}
public  class CashingResultTableModel extends myResultTableModel
{
       
    public CashingResultTableModel(ResultSet rs)
         {
          super(rs);
          myData=new ArrayList();
          int Cols=getColumnCount();
          ResultSet myresult=getResultSet();
        try {
            
            while(myresult.next())
            {
                Object[] row=new Object[Cols];
                
                for(int j=0;j<row.length;j++)
                    row[j]=myresult.getObject(j+1);
                
                myData.add(row);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
          
         }
    
    public int getRowCount()
    {
        
       return myData.size();
       
    }

    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        if(rowIndex<myData.size())
        {
            return ((Object[])myData.get(rowIndex))[columnIndex];
        }
        else 
            return null;
    }
    
    private ArrayList myData;
      
}