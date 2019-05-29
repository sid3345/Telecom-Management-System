/*
 * employeePanel.java
 *

 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package connectingdatabase;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import connectingdatabase.PKResultModel.CashingResultTableModel;

/**
 *

 */
public class employeePanel extends JDialog {
    
    /** Creates a new instance of employeePanel */
    public employeePanel(Connection connection,JFrame myMother) 
    {
    	 
    	super(myMother,"Add employee",true) ;
    	this.connection=connection;   
    	setSize(500,500);
        setLocation(50,50) ;
    	
    	ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAddQuery();
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
          forma.setLayout(new GridLayout(5,2));
          forma.add(nameLabel);
          forma.add(NameFiled);
          forma.add(addressLabel);
          forma.add(AddressFiled);
          forma.add(phoneLabel);
          forma.add(PhoneFiled);
          forma.add(NatLabel);
          forma.add(NatFiled);
        
          add(forma);
          add(ok);
          add(cancel);
    }
    
    public void createAddQuery() 
    {   
        String name=NameFiled.getText(); 
        String Address=AddressFiled.getText();
        String Phone=PhoneFiled.getText();
        String Nat=NatFiled.getText();
        
        
           
         String query="Insert into employee values('"+name+"','"+Address+"',"+Phone+",'"+Nat+"')";
         
          try {
              Statement stat=connection.createStatement();
            if(!stat.execute(query))
            {
                 System.out.println("ADDED HAHAHAHA !!!"); 
            }
            else System.out.println("ERROR HAPPENED !!!");
        } catch (SQLException ex) {
             Main.myArea.append(ex.toString());
        }
    }
    private Connection connection;
    private CashingResultTableModel myModel;
     
    private JTextField NameFiled=new JTextField(20);
    private JTextField AddressFiled=new JTextField(20);
    private JTextField PhoneFiled=new JTextField(20);
    private JTextField NatFiled=new JTextField(20); 
    private JLabel nameLabel=new JLabel("Name :");
    private JLabel addressLabel=new JLabel("Address :");
    private JLabel NatLabel=new JLabel("Nationality :");
    private JLabel phoneLabel=new JLabel("Phone :");
    private JPanel forma=new JPanel() ;
    private JButton ok=new JButton("Ok");
    private JButton cancel=new JButton("Cancel");
}
