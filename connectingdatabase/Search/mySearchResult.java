/*
 * mySearchResult.java
 *
 
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package connectingdatabase.Search;

import connectingdatabase.PKResultModel.CashingResultTableModel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 
 */
public class mySearchResult extends JDialog{
    
    /** Creates a new instance of mySearchResult */
    public mySearchResult(ResultSet rs ,JDialog myMother) 
    {
        super(myMother,"Search Results",true);
            setSize(600,600);
       setLocation(100,100);
        setLayout(new FlowLayout());
        myModel=new CashingResultTableModel(rs);
        table=new JTable(myModel);
         
           myPane=new JScrollPane(table
                                          ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                          JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                add(myPane);
        
        
        myButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                dispose();
            }
        });
        add(myButton);
        
        
    }
    
    private JPanel myPanel=new JPanel();
    
    
    private CashingResultTableModel myModel;
    private JScrollPane myPane;
    private JTable table;
    private JButton myButton=new JButton("Cancel");
}
