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
import javax.swing.JOptionPane;
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
public class employeeRemovePanel extends JDialog {
	
	 public employeeRemovePanel(Connection connection,JFrame myMother) 
	    {
	    	 
	    	super(myMother,"Remove employee",true) ;
	    	this.connection=connection;   
	    	employeeNamesComboBox=new employeeNamesComboBox(connection);
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
	        forma.add(employeeNamesComboBox);
	        add(forma);
	        add(ok);
	        add(cancel);
	    
	    }
	 public void deleteQuery()
	 {
	 	 String employeeName=(String)employeeNamesComboBox.getSelectedItem();
	 	String query="Delete FROM employee WHERE name='"+employeeName+"'";
	 	try {
			Statement stat=connection.createStatement();
			 
			if(!stat.execute(query))
	        {
	             Main.myArea.append("ADDED HAHAHAHA !!!\n"); 
	        }
			 
			else 
			 	 Main.myArea.append("ERROR HAPPENED !!!\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 Main.myArea.append(e.toString()+"\n");
		}   
           /*  JOptionPane.showMessageDialog(this,"Can't Remove employees From The System","Error"
                     ,JOptionPane.ERROR_MESSAGE);/*/
	 }
	 private Connection connection;
	 private JPanel forma=new JPanel();
	 private JLabel nameLabel=new JLabel("Select employee Name : ");
	 private employeeNamesComboBox employeeNamesComboBox;
	 private JButton ok=new JButton("Ok");
	 private JButton cancel=new JButton("Cancel");

}
class employeeNamesComboBox extends JComboBox 
{
     public employeeNamesComboBox(Connection con)
     { 
       
         myConn=con;        
         try 
		 {      
         	stat = myConn.createStatement();
            ResultSet res=stat.executeQuery("SELECT Name FROM employee");
      
            String Name = null;    
            while(res.next())
            {      
               Name=res.getString("Name");
               addItem(Name);
            }
                
		 } catch (SQLException ex) {
                   Main.myArea.append(ex.toString()+"\n");       
		 }      
     }
     
     private Connection myConn;
     private Statement stat;
}