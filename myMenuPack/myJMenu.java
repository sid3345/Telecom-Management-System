/*
 * myJMenu.java
 *
 *
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myMenuPack;

import connectingdatabase.Main;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
 

/**
 *
 * @employee codemiles
 */
public class myJMenu extends  JMenuBar {
    
    /** Creates a new instance of myJMenu */
    public myJMenu() 
    {
        
        
        add(JMfile);
        add(JMWindow);
        JMfile.add(ComputerName);
        JMfile.add(Exit);
        JMWindow.add(AboutUs);
        
        ComputerName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                new ComputerName().show();
            }
        });
        Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                try {
                    Main.connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        AboutUs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                new AboutUs().show();
            }
        });
    }
     
    private JMenu JMfile=new JMenu("File");
    private JMenu JMWindow=new JMenu("Window");
    private JMenuItem ComputerName=new JMenuItem("Computer Name");
    private JMenuItem Exit=new JMenuItem("Exit");
    private JMenuItem AboutUs=new JMenuItem("About Us");
}
class ComputerName extends JDialog
{
    ComputerName()
    {
        setSize(400,400);
        setLocation(200,200);
        setTitle("set Server");
        setLayout(new FlowLayout());
             myPanel.setLayout(new GridLayout(2,2));
             myPanel.add(ComName);
              myPanel.add(ComNameText);
              myPanel.add(ServPort);
              myPanel.add(ServPortText);
              add(myPanel);
              add(Ok);
              add(Cancel);
              
             Ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                Main.CompName=ComNameText.getText();
                Main.Port=ServPortText.getText();
                 dispose();
              }
            });
            Cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                dispose();
              }
            });
    }
    private JPanel myPanel=new JPanel();
    private JButton Ok=new JButton("Ok");
    private JButton Cancel=new JButton("Cancel");
    private JLabel ComName=new JLabel("Computer Name:");
    private JLabel ServPort=new JLabel("Server Port :");
    private JTextField ComNameText=new JTextField(10);
    private JTextField ServPortText=new JTextField(10);
    
     
}
class AboutUs extends JDialog
{
    AboutUs()
    {
        
        setSize(400,400);
        setLocation(200,200);
        setTitle("About Us");
        setLayout(new FlowLayout());
        myPanel.setLayout(new GridLayout(3,2));
        myPanel.add(num1);
        myPanel.add(Name1);
        myPanel.add(num2);
        //myPanel.add(Name2);
        //myPanel.add(num3);
        //myPanel.add(Name3);
        
        add(myPanel);
        add(Cancel);
        
          Cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                dispose();
              }
            });
    }
    private JPanel myPanel=new JPanel();
      private JLabel  Name1=new JLabel("Coders");
        //private JLabel  Name2=new JLabel("");
         // private JLabel  Name3=new JLabel("Free Open Source Version1.0");
            private JLabel  num1=new JLabel("1 :");
              private JLabel  num2=new JLabel("2 :");
                private JLabel  num3=new JLabel("3 :");
             private JButton Cancel=new JButton("Cancel");
}
