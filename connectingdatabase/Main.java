/*
 * Main.java
 *
 *
 */

package connectingdatabase;

 
//import Gen.ReportPanel;
import connectingdatabase.Search.employeeSearch;
//import connectingdatabase.Search.recordSearch;
//import connectingdatabase.Search.recordStoreSearch;
//import connectingdatabase.Search.PublisherSearch;
import connectingdatabase.Search.usersSearch;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import myMenuPack.myJMenu; 
import java.awt.*;
import javax.swing.*;
 
/**
 *

 */
public class Main extends JFrame {
    
    /** Creates a new instance of Main */
    public Main() 
    {
             start.show();  
      setTitle("TELECOM MANAGEMENT SYSTEM");
      setSize(800,700);
      setLocation(200,0);
      
          try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Exits !!!");
            
           connection = DriverManager.getConnection(
    "jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
            Main.myArea.append("Connection Established !!");
            } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
          catch (SQLException ex) {
            Main.myArea.append(ex.toString());
        }
      
      Container cp=getContentPane();
     
      
      
      myTables[0]=new usersTable(connection);
      myTables.setSize(300,300);
       //myTables.setBounds(40,80,200,200);
     // myTables[1]=new recordTable( connection);
      myTables[1]=new employeeTable(connection);
      myTables.setSize(300,300);
       //myTables.setBounds(40,80,200,200);
      //myTables[3]=new PublisherTable(connection);
      //myTables[4]=new recordStoreTable(connection);
      
      mySP[0]=new JScrollPane(myTables[0],JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       
      mySP[1]=new JScrollPane(myTables[1],JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      mySP[2]=new JScrollPane(myTables[2],JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      mySP[3]=new JScrollPane(myTables[3],JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      mySP[4]=new JScrollPane(myTables[4],JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      
      myToolbar =new myToolbar(tabbedPane,this,connection);
      
      tabbedPane.addTab("USERS", mySP[0]);
       
     // tabbedPane.addTab("RECORD", mySP[1]);
      tabbedPane.addTab("EMPLOYEE", mySP[1]);
     // tabbedPane.addTab("Publisher", mySP[3]);
      //tabbedPane.addTab("record Store", mySP[4]);
      
       tabbedPane.addChangeListener(new ChangeListener() {
           public void stateChanged(ChangeEvent e) {
                switch(tabbedPane.getSelectedIndex())
                  {
                      case 0:((usersTable)(myTables[0])).ChangeModel() ;break;      
                     // case 1:((recordTable)(myTables[1])).ChangeModel();break;
                      case 1:((employeeTable)(myTables[1])).ChangeModel();break;
                     // case 3:((PublisherTable)(myTables[3])).ChangeModel();break;
                      //case 4:((recordStoreTable)(myTables[4])).ChangeModel();break;
                      
                      default :break;
                  }
               
           }
       });
      mySplitePane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,tabbedPane,myControlArea);
      cp.add(myToolbar,BorderLayout.NORTH);
      cp.add(mySplitePane); 
       
        
      addWindowListener(new WindowListener() {
          public void windowActivated(WindowEvent e) {
          }
          public void windowClosed(WindowEvent e) {
          }
          public void windowClosing(WindowEvent e) {
            Main.myArea.append("Connection Closed !!!\n")     ;
                try {
                    if(connection!=null) {
                        connection.close();
                    }
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
              
          }
          public void windowDeactivated(WindowEvent e) {
          }
          public void windowDeiconified(WindowEvent e) {
          }
          public void windowIconified(WindowEvent e) {
          }
          public void windowOpened(WindowEvent e) {
          }
      }); 
      
    
           start.dispose();
        setJMenuBar(myJM);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true); 
    }
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
         
        // TODO code application logic here
        
        new Main();
    }
    
    public static Connection connection;
    private JTabbedPane tabbedPane=new JTabbedPane();
     
    public static JTextArea myArea=new JTextArea(1,1);
    private JScrollPane myControlArea=new JScrollPane(myArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
            ,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private JSplitPane mySplitePane;
    private JPanel[] myTables=new JPanel[5];

    private JScrollPane[] mySP=new JScrollPane[5];
    private myToolbar myToolbar ;
    private myJMenu myJM=new myJMenu();
    public static String CompName="sid3345";
    public static String Port="3345";
      private myStart start=new myStart();
}


class myToolbar extends JToolBar
{
    public myToolbar(final JTabbedPane Tabbed,final JFrame myMother,final Connection conn)
    {
      //  setLayout(null);

            AddImag=new ImageIcon("icons/Add.png");
            
        SearchImag=new ImageIcon("icons/Search.png");
        ModifyImag=new ImageIcon("icons/Modify.png");
        RemoveImag=new ImageIcon("icons/Remove.png");
       // BorrowImag=new ImageIcon("icons/Borrow.png");
        //ReturnImag=new ImageIcon("icons/Return.png");
       // ReportImag=new ImageIcon("icons/Report.png");
       // MasterImag=new ImageIcon("icons/Master.png"); 
        
        AddBut=new JButton("Add", AddImag);
        SearchBut=new JButton("Search",SearchImag);
        ModifyBut=new JButton("Modify",ModifyImag);
         RemoveBut=new JButton("Remove",RemoveImag);
        // BorrowBut=new JButton("Borrow",BorrowImag);
        // ReturnBut=new JButton("Return",ReturnImag);
         //ReportBut=new JButton("Report",ReportImag);
        // MasterBut=new JButton("Master",MasterImag);
          AddBut.setAlignmentY(CENTER_ALIGNMENT);
           SearchBut.setAlignmentY(CENTER_ALIGNMENT);
            ModifyBut.setAlignmentY(CENTER_ALIGNMENT);
              RemoveBut.setAlignmentY(CENTER_ALIGNMENT);
              //ReturnBut.setAlignmentY(CENTER_ALIGNMENT);
                  // ReportBut.setAlignmentY(CENTER_ALIGNMENT);
                  // MasterBut.setAlignmentY(CENTER_ALIGNMENT);
         add(AddBut);
        add(SearchBut);
        add(ModifyBut);
        add(RemoveBut);
        //add(BorrowBut);
       // add(ReturnBut);
       // add(ReportBut);
        //add(MasterBut);
        AddBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
            	          
                  switch(Tabbed.getSelectedIndex())
                  {
                      case 0:((new usersPanel(conn,myMother))).show();break;      
                     // case 1:(new recordPanel(conn,myMother)).show();break;
                      case 1:(new employeePanel(conn,myMother)).show();break;
                      //case 3:(new PublisherPanel(conn,myMother)).show();break;
                      //case 4:(new recordStorePanel(conn,myMother)).show();break;
                      
                      default :break;
                  }
              
            }
        });
         SearchBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  
            	
            	switch(Tabbed.getSelectedIndex())
                {
                    case 0:(new usersSearch(conn,myMother)).show();break;      
                   // case 1:(new recordSearch(conn,myMother)).show();break;
                    case 1:(new employeeSearch(conn,myMother)).show();break;
                    //case 3:(new PublisherSearch(conn,myMother)).show();break;
                    //case 4:(new recordStoreSearch(conn,myMother)).show();break;
                    
                    default :break;
                }
                  
                
            }
        });
         ModifyBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 
             
            	
            	switch(Tabbed.getSelectedIndex())
                {
                    case 0:(new usersModifyPanel(conn,myMother)).show();break;      
                   // case 1:(new recordModifyPanel(conn,myMother)).show();break;
                    case 1:(new employeeModifyPanel(conn,myMother)).show();break;
                    //case 3:(new PublisherModifyPanel(conn,myMother)).show();break;
                    //case 4:(new recordStoreModifyPanel(conn,myMother)).show();break;
                    
                    default :break;
                }
            	
            }
        });
         RemoveBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	switch(Tabbed.getSelectedIndex())
                 {
                     case 0:(new usersRemovePanel(conn,myMother)).show();break;      
                     //case 1:(new recordRemovePanel(conn,myMother)).show();break;
                     case 1:(new employeeRemovePanel(conn,myMother)).show();break;
                     //case 3:(new PublisherRemovePanel(conn,myMother)).show();break;
                     //case 4:(new recordStoreRemovePanel(conn,myMother)).show();break;
                     
                     default :break;
                 }
            }
        });
        
         /*BorrowBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
               // new BorrowrecordPanel(conn,myMother).show();
            }
        });*/
        
        /* ReturnBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                new ReturnedrecordPanel(conn,myMother).show();
            }
        });*/
        
        /* ReportBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ReportPanel(conn,myMother).show();
            }
        });*/
       /* MasterBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //new BKStoreMaster(conn,myMother).show();
            }
        });*/

    }
    
    

  private JButton AddBut ;
  private JButton SearchBut ;
  private JButton ModifyBut ;
  private JButton RemoveBut ;
  //private JButton BorrowBut ;
 // private JButton ReturnBut ;
  //private JButton ReportBut ;
  //private JButton MasterBut ;
  private  Icon AddImag; 
  private  Icon SearchImag;
  private  Icon ModifyImag;
  private  Icon RemoveImag;
 // private  Icon BorrowImag;
 // private  Icon ReturnImag;
//  private  Icon ReportImag;
 // private  Icon MasterImag;
}
class myStart extends JDialog
{
    myStart()
    {
        
        setUndecorated(true);
      //  setIgnoreRepaint(true);
        setResizable(false);
        setSize(2000,2000);
        setLocation(200,100);
        
    }
     
    private Image Imgstart=new ImageIcon("icons/back.png").getImage();
    public void paint(Graphics g) {
        g.drawImage(Imgstart,0,0,this);
    }
}