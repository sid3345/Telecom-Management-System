/*
 
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * @employee Mohammed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class usersModifyPanel extends JDialog {

	/**
	 * 
	 */
	public usersModifyPanel(Connection connection,JFrame myMother) 
	{
		super(myMother,"Modify users",true) ;
    	this.connection=connection;   
    	usersNamesComboBox=new usersNames_ComboBox(connection);
    	setSize(1000,500);
        setLocation(50,80); 
    	
    	ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	modifyQuery();
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
        forma.setLayout(new GridLayout(4,2));
        forma.add(nameLabel);
        forma.add(usersNamesComboBox);
        forma.add(attributesLabel);
        forma.add(usersAttributesComboBox);
        forma.add(newValueLabel);
        forma.add(newValueField);
        add(forma);
        add(ok);
        add(cancel);
    
    }
 public void modifyQuery()
 {
 	String usersName=(String)usersNamesComboBox.getSelectedItem();
 	String usersAttribute=(String)usersAttributesComboBox.getSelectedItem();
 	String newValue=newValueField.getText();
 	String query="Update users set "+usersAttribute+"='"+newValue+"'  WHERE Name='"+usersName+"'";
 	try {
		Statement stat=connection.createStatement();
		 
		stat.executeUpdate(query);
        
        //System.out.println("ADDED HAHAHAHA !!!"); 
        
	} catch (SQLException e) {
		e.printStackTrace();
	}
 }
 private Connection connection;
 private JPanel forma=new JPanel();
 private JLabel nameLabel=new JLabel("Select users Name : ");
 private JLabel attributesLabel=new JLabel("Select Attribute Name : ");
 private usersNames_ComboBox usersNamesComboBox;
 private usersAttributesComboBox usersAttributesComboBox=new usersAttributesComboBox();
 private JLabel newValueLabel=new JLabel("Enter New Value : ");
 private JTextField newValueField=new JTextField(12);
 private JButton ok=new JButton("Ok");
 private JButton cancel=new JButton("Cancel");

}
class usersNames_ComboBox extends JComboBox 
{
public usersNames_ComboBox(Connection con)
{ 
  if(con==null) {
             return ;
         }
  myConn=con;        
  try 
	 {      
  	stat = myConn.createStatement();
     ResultSet res=stat.executeQuery("SELECT Name FROM users");

     String Name = null;    
     while(res.next())
     {      
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
class usersAttributesComboBox extends JComboBox 
{
public usersAttributesComboBox()
{
	addItem("Name");
	addItem("Address");
	addItem("Phone");
	addItem("Age");
	addItem("Gender");
}
 
}