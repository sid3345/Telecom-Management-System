package connectingdatabase;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/*
 * Created on 17/12/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @employee Mohammed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class usersRemovePanel extends JDialog {
	
	 public usersRemovePanel(Connection connection,JFrame myMother)     
	 { 	
	 	super(myMother,"Remove users",true) ;
	 	this.connection=connection;     	
	 	usersNamesComboBox=new usersNamesComboBox(connection);	
	 	setSize(500,500);
	        setLocation(50,50); 
	    	
	    	ok.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	deleteQuery();
	                dispose();
	            }
	        });
	        cancel.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               
	                dispose();
	            }
	        });
	        
	        setLayout(new FlowLayout());    
	        forma.setBorder(new TitledBorder(new LineBorder(Color.GRAY,2), "Add panel"));
	        forma.setSize(400,100);
	        forma.setLocation(10,30);
	        forma.setLayout(new GridLayout(2,2));
	        forma.add(nameLabel);
	        forma.add(usersNamesComboBox);
            
	        add(forma);
	        add(ok);
	        add(cancel);
	    }
	 public void deleteQuery()
	 {
	 	String usersName=(String)usersNamesComboBox.getSelectedItem();
                //String id=((String)usersNamesComboBox.getSelectedItem()).substring(0,6);
                
	 	try {
			Statement stat=connection.createStatement();
                        String query="Select * FROM users WHERE isMember=1 AND name='"+usersName+"'";
                        
                        ResultSet rs= stat.executeQuery(query);
                        if(rs.next())
                        {
                        query="Delete FROM Member WHERE name='"+usersName+"'";
                        stat.execute(query);
                        }
                        query="Delete FROM users WHERE name='"+usersName+"'";
                        
			if(!stat.execute(query))
	        {
	            System.out.println("ADDED HAHAHAHA !!!"); 
                     
	        }
			 
			else 
			 	System.out.println("ERROR HAPPENED !!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 private Connection connection;
	 private JLabel nameLabel=new JLabel("Select users Name : ");
	 private usersNamesComboBox usersNamesComboBox;
	 private JPanel forma=new JPanel();
	 private JButton ok=new JButton("Ok");
	 private JButton cancel=new JButton("Cancel");

}
class usersNamesComboBox extends JComboBox 
{
  public usersNamesComboBox(Connection con)
  { 
        
             
        myConn=con;
              if(con==null) {
             return ;
         }
                Statement stat;
                try {
                    stat = myConn.createStatement();
                         ResultSet res=stat.executeQuery("SELECT Name FROM users ");
                        String ID=null;
                        String Name = null;    
                        while(res.next())
                            {
                               //ID=Integer.toString(res.getInt("ID"));
                               Name=res.getString("name");
                               addItem(Name);
                            }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
  }
  
  private Connection myConn;
  private Statement stat;
}
