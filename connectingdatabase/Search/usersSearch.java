/*
 * usersSearch.java
 *

 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package connectingdatabase.Search;


import connectingdatabase.PKResultModel.CashingResultTableModel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author codemiles
 */
public class usersSearch extends JDialog{
    
    /** Creates a new instance of usersPanel */
    public usersSearch(Connection connection,JFrame myMother)
    {
        super(myMother,"Search users",true);
        this.connection=connection;
        MetaData=new MyComboBox(connection,"USERS");
        setSize(500,500);
         setLocation(100,100);
        //  myModel=new  CashingResultTableModel(res);
       //   JTable myTable=new JTable(myModel);
          
    //
          //add(myTable);
          Search.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  DoSearch();
                   
              }
          });
          cancel.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                 
                  hide();
              }
          });
               
          setLayout(new FlowLayout());
          forma.setBorder(new TitledBorder(new LineBorder(Color.GRAY,2), "Search panel"));
          forma.setSize(400,100);
          forma.setLocation(10,30);
          forma.setLayout(new GridLayout(2,2));
      
       
  
          
         forma.add(MetaData);
          forma.add(Search);
          forma.add(SearchLabel);
          forma.add(SearchFiled);
          add(forma);
         
          add(cancel);
          
    
    }
    
     
    public void DoSearch()
    {
        
        String Col=(String) MetaData.getSelectedItem();
        String Value=SearchFiled.getText();
        mySearchResult showrs;
       
        
        String query="Select * from users  where "+Col+" Like('%"+Value+"%')";
        try {
        
            Statement stat=connection.createStatement();
            rs=stat.executeQuery(query);
            
            showrs=new mySearchResult(rs,this);
            showrs.show();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private Connection connection;
 
 
    private JTextField SearchFiled=new JTextField(20);
 
 
    private MyComboBox MetaData ;
    private JLabel SearchLabel=new JLabel("Search :");
    private JPanel forma=new JPanel() ;
    private JButton Search=new JButton("Search");
    private JButton cancel=new JButton("Cancel");
            
 
    private ResultSet rs;
   
}
 