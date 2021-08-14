import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ViewBill extends JInternalFrame implements ActionListener, FocusListener {
private JPanel pBill = new JPanel ();
private JLabel lbCustomerId;
private Connection con;
private long id = 0;
private JButton jb1,jb2;
private JTextField txtCustomerId;
private Statement st;
private String cid;
private JTable table;
private JScrollPane jsp;
private Object data1[][];
private int bref,bmid,bid,columns=0;
private Container c;
public ViewBill (Connection con){

//super (Title, Resizable, Closable, Maximizable, Iconifiable)
		super ("View Bill", false, true, false, true);
		setSize (310, 210);
		
		//Setting the Form's Labels.
        lbCustomerId = new JLabel("Customer Id: ");
		lbCustomerId.setForeground (Color.black);
		lbCustomerId.setBounds (15, 15, 100, 20);
		
		//Setting the Form's TextField,Buttons..
		
		txtCustomerId = new JTextField ();
		txtCustomerId.setBounds (120, 15, 175, 25);
		txtCustomerId.setHorizontalAlignment (JTextField.RIGHT);
		txtCustomerId.addFocusListener (this);
		
		jb1 = new JButton("VIEW");
		jb1.setBounds(40,80,90,20);
		jb1.addActionListener(this);
		jb2 = new JButton("CLEAR");
		jb2.setBounds(150,80,90,20);
		jb2.addActionListener(this);
		
		
		
		txtCustomerId.addKeyListener (new KeyAdapter () {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (! ((Character.isDigit (c)) || (c == KeyEvent.VK_BACK_SPACE))) {
					getToolkit().beep ();
					ke.consume ();
				}
			}
		}
		);
		
		pBill.setLayout (null);
		pBill.add(lbCustomerId);
		pBill.add(txtCustomerId);
		pBill.add (jb1);
		pBill.add (jb2);
		
		
		
		getContentPane().add (pBill, BorderLayout.CENTER);
		c=getContentPane();
		
		
		setVisible (true);
		}
		
		public void actionPerformed (ActionEvent ae) {

		Object obj = ae.getSource();
		
		if (obj == jb1) {		//If View Button Pressed.
		if (txtCustomerId.getText().equals ("")) {
				JOptionPane.showMessageDialog (this, "Search Field not Provided.");
				}
				
			else 
			{
			     
                 try{
		
				 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				 String loc = "jdbc:odbc:ELECTRICITYBILLSYSTEM";
                con=DriverManager.getConnection("jdbc:odbc:ELECTRICITYBILLSYSTEM", "raj","jesus");
						 data1=new String[1][6];
		              String[] colheads={"Bill Id","Customer Id","Meter Id","Bill Amount","Bill Date","Amount Paid"}; 
				
				Statement st=con.createStatement();
				String Id = txtCustomerId.getText();
				ResultSet rs = st.executeQuery("SELECT * FROM Bill WHERE C_Id ='"+Id+"'");
				
				
				for(int i=0;rs.next();i++)
				{
						
						for(int j=0;j<6;j++)
						{
							data1[i][j]=rs.getString(j+1);
						}
				}
				
				
				table=new JTable(data1,colheads);
				int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
				int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
				jsp=new JScrollPane(table,v,h);
				pBill.add(jsp);
				TableDisp td=new TableDisp(table);
				}
				
				catch(Exception sqlex) {
					
						//JOptionPane.showMessageDialog (this, "Some prob Found.");
					
				}
			}
			}
			else if( obj == jb2)
			{
			txtCustomerId.setText("");
			}
			}
		public void focusGained (FocusEvent fe) { }
		public void focusLost (FocusEvent fe) {
		if (txtCustomerId.getText().equals ("")) {	//If TextField is Empty.
		}
		else {
		              
			}
			}
}
				
				