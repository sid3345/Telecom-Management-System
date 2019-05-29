/*
 * usersPanel.java
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
import java.sql.ResultSet;
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

import connectingdatabase.PKResultModel.CashingResultTableModel;

/**
 *
 * @author codemiles
 */
public class usersPanel extends JDialog{
    
    /** Creates a new instance of usersPanel */
    public usersPanel(Connection connection,JFrame myMother)
    {
        super(myMother,"Add users",true);
        this.connection=connection;
        
        setSize(500,500);
         setLocation(50,50);
       /*myModel=new  CashingResultTableModel(res);
        JTable myTable=new JTable(myModel);
        add(myTable);*/
        
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
          forma.setLayout(new GridLayout(7,2));
          forma.add(nameLabel);
          forma.add(NameFiled);
          forma.add(addressLabel);
          forma.add(AddressFiled);
          forma.add(phoneLabel);
          forma.add(PhoneFiled);
          forma.add(AgeLabel);
          forma.add(AgeFiled);
          forma.add(GenderLabel);
          forma.add(Gender);
          forma.add(IsMember);
          forma.add(IDLabel);
          forma.add(IDFiled);
          
          
          add(forma);
          add(ok);
          add(cancel);
          
       // add(myTable);
    }
    
    public void createAddQuery()
    {
        String id=IDFiled.getText();
        String name=NameFiled.getText();
        String Address=AddressFiled.getText();
        String Phone=PhoneFiled.getText();
        String Age=AgeFiled.getText();
        
        String gen;
        String isMember="0";
        
         gen=(String)Gender.getSelectedItem();
         
         if(IsMember.isSelected()) {
             isMember="1";    
         }
         
         String query="Insert into users values('"+name+"','"+Address+"',"+Phone+","+Age+",'"+gen+"',0,"+id+",null,null)";
            
          try {
              Statement stat=connection.createStatement();
            
           // if(!stat.execute(query))
            //{
         
                 //System.out.println("ADDED HAHAHAHA !!!"); 
                  
                   if(IsMember.isSelected())
                   {
               
               //String pay=IsMember.PaymentValue();
               String end=IsMember.MemberShipEndValue();
               String Serial=IsMember.SerialValue();
               
              String Mquery="Insert into users values('"+name+"','"+Address+"',"+Phone+","+Age+",'"+gen+"',1,"+id+","+Serial+","+end+")";
           System.out.println(Mquery);
              stat.execute(Mquery);
         }
            //}
            else 
                   { System.out.println(query);
              stat.execute(query);
              //System.out.println("ERROR HAPPENED !!!");
                   }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private Connection connection;
    private CashingResultTableModel myModel;
    private JTextField IDFiled=new JTextField(20);
    private JTextField NameFiled=new JTextField(20);
    private JTextField AddressFiled=new JTextField(20);
    private JTextField PhoneFiled=new JTextField(12);
    private JTextField AgeFiled=new JTextField(12);
    private MyComboBox Gender=new MyComboBox();
    private MyCheckBox IsMember=new MyCheckBox(this);
    private JLabel nameLabel=new JLabel("Name :");
    private JLabel addressLabel=new JLabel("Address :");
    private JLabel AgeLabel=new JLabel("Age :");
    private JLabel IDLabel=new JLabel("ID :");
    private JLabel phoneLabel=new JLabel("Phone :");
    private JLabel GenderLabel=new JLabel("Gender :");
    private JPanel forma=new JPanel() ;
    private JButton ok=new JButton("Ok");
    private JButton cancel=new JButton("Cancel");
            
    private JTable myTable;
    private ResultSet res;
   
}
class MyComboBox extends JComboBox 
{
     public MyComboBox()
     {addItem("Male");
          addItem("Female");
          
          
          
          
          
     }
}
class MyCheckBox extends JCheckBox
{
    public MyCheckBox(usersPanel mypanel)
    {
          setText("Is member");
              
          forma.setBorder(new TitledBorder(new LineBorder(Color.GRAY,2), "Membership"));
          forma.setSize(400,90);
          forma.setLocation(10,200);
          forma.setLayout(new GridLayout(3,2));    
          //forma.add(PaymentLabel);
          //forma.add(PaymentField);
          forma.add(MemberShipEndLabel);
          forma.add(MemberShipEndFileld);
          forma.add(SerialLabel);
          forma.add(SerialField);
          mypanel.add(forma);
          //PaymentField.setEditable(false);
          MemberShipEndFileld.setEditable(false);
          SerialField.setEditable(false);
           
          addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   if(isSelected())
                   {
                       //PaymentField.setEditable(true);
           MemberShipEndFileld.setEditable(true);
           SerialField.setEditable(true);
                   }
                   else 
                   {
                       //PaymentField.setEditable(false);
           MemberShipEndFileld.setEditable(false);
           SerialField.setEditable(false);
                   }
               }
           });
                  
 
    }
    /*public String PaymentValue()
    {
        return PaymentField.getText();
    } */  
    public String MemberShipEndValue()
    {
        return MemberShipEndFileld.getText();
    }
    public String SerialValue()
    {
        return SerialField.getText();
    }
    private JPanel forma=new JPanel();
    private CashingResultTableModel myModel;
    //private JTextField PaymentField=new JTextField(20);
    private JTextField SerialField=new JTextField(20);
    private JTextField MemberShipEndFileld=new JTextField(20);
     
   
     
    //private JLabel PaymentLabel=new JLabel("Payment :");
    private JLabel SerialLabel=new JLabel("Membership Serial :");
    private JLabel MemberShipEndLabel=new JLabel("Membership Due Date :");
    
    
}