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
public class employeeModifyPanel extends  JDialog {

	 public employeeModifyPanel(Connection connection,JFrame myMother) 
	    {
	    	 
	    	super(myMother,"Modify employee",true) ;
	    	this.connection=connection;   
	    	employeeNamesComboBox=new employeeNames_ComboBox(connection);
	    	setSize(500,500);
	        setLocation(50,50);
	    	
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
	        forma.add(employeeNamesComboBox);
	        forma.add(attributesLabel);
	        forma.add(employeeAttributesComboBox);
	        forma.add(newValueLabel);
	        forma.add(newValueField);
	        add(forma);
	        add(ok);
	        add(cancel);
	    
	    }
	 public void modifyQuery()
	 {
	 	String employeeName=(String)employeeNamesComboBox.getSelectedItem();
	 	String employeeAttribute=(String)employeeAttributesComboBox.getSelectedItem();
	 	String newValue=newValueField.getText();
	 	String query="Update employee set "+employeeAttribute+"='"+newValue+"'  WHERE Name='"+employeeName+"'";
	 	try {
			Statement stat=connection.createStatement();
			 
			stat.executeUpdate(query);
	        
	  
	        
		} catch (SQLException e) {
			 Main.myArea.append( e.toString());
		}
	 }
	 private Connection connection;
	 private JPanel forma=new JPanel();
	 private JLabel nameLabel=new JLabel("Select employee Name : ");
	 private JLabel attributesLabel=new JLabel("Select Attribute Name : ");
	 private employeeNames_ComboBox employeeNamesComboBox;
	 private employeeAttributesComboBox employeeAttributesComboBox=new employeeAttributesComboBox();
	 private JLabel newValueLabel=new JLabel("Enter New Value : ");
	 private JTextField newValueField=new JTextField(20);
	 private JButton ok=new JButton("Ok");
	 private JButton cancel=new JButton("Cancel");

}
class employeeNames_ComboBox extends JComboBox 
{
  public employeeNames_ComboBox(Connection con)
  { 
    
      myConn=con;        
      try 
		 {      
      	stat = myConn.createStatement();
         ResultSet res=stat.executeQuery("SELECT Name FROM employee ");
   
         String Name = null;    
         while(res.next())
         {      
            Name=res.getString("Name");
            addItem(Name);
         }
             
		 } catch (SQLException ex) {
                Main.myArea.append(ex.toString());       
		 }      
  }
  
  private Connection myConn;
  private Statement stat;
}
class employeeAttributesComboBox extends JComboBox 
{
  public employeeAttributesComboBox()
  {
  	addItem("Name");
  	addItem("Address");
  	addItem("Phone");
  	addItem("Nationality");
  }
     
}