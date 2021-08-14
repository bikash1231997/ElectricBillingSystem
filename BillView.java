import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class  BillView extends JInternalFrame  
{
   JTable table;
   JScrollPane jsp;
   private JPanel p=new JPanel();
   private Connection con;
   private Statement st;
   public BillView(Connection con)
   {
     super ("BILL REPORT", false, true, false, true);
	 setSize (600, 550);
     String s[][]=new String[50][6]; 
     String colheads[]={"BillId","CustomerId","MeterId","BillAmount","BillDate","AmountPaid"}; 
     getContentPane().add (p, BorderLayout.CENTER);
		setVisible(true);
    
    try
    {        
         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		 String loc = "jdbc:odbc:ELECTRICITYBILLSYSTEM";
         con=DriverManager.getConnection("jdbc:odbc:ELECTRICITYBILLSYSTEM","raj","jesus");
         Statement stmt=con.createStatement();
         ResultSet rs=stmt.executeQuery("SELECT * FROM Bill"); 
         for(int i=0;rs.next();i++)
          {
             for(int j=0;j<6;j++)
             {  
                      s[i][j]= rs.getString(j+1);
              } 
          }
       } 
      catch(Exception e)
     {          
     JOptionPane.showMessageDialog(this,"ERROR OCCURED IN DATABASE CONNECTION","Error Message",JOptionPane.ERROR_MESSAGE);
     }
     table=new JTable(s,colheads);
     jsp=new JScrollPane(table);
     p.add(jsp);
      }
}
