import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class CreateBill extends JInternalFrame implements ActionListener, FocusListener {
private JPanel pCBill = new JPanel ();
private JLabel lbBillId,lbCustomerId,lbMeterId,lbAmount,lbBillDate,lbAmountPaid;
private JTextField txtBillId,txtCustomerId,txtCustomerName,txtMeterId,txtBillDate,txtAmount,txtAmountPaid;
private Connection con;
private JButton jb1,jb2,jb3,jb4;
    private Statement st;			//Statement for Getting the Required Table.
	private long id = 0;			//To Hold the BookId.
	
	private int id1,im,iy,vd,vm,vy;
	private String pdate;

		public CreateBill (Connection con){
	//super (Title, Resizable, Closable, Maximizable, Iconifiable)
		super ("Create Bill", false, true, false, true);
		setSize (580, 560);

		//Setting the Form's Labels.
		lbBillId = new JLabel ("Bill Id:");
		lbBillId.setForeground (Color.black);
		lbBillId.setBounds (15, 15, 100, 20);
		
		lbCustomerId = new JLabel ("Customer Id:");
		lbCustomerId.setForeground (Color.black);
		lbCustomerId.setBounds(15,45,100,20);
		
		lbMeterId = new JLabel ("Meter Id:");
		lbMeterId.setForeground (Color.black);
		lbMeterId.setBounds(15,105,100,20);
		
		lbAmount = new JLabel ("Amount:");
		lbAmount.setForeground (Color.black);
		lbAmount.setBounds(15,145,130,20);
		
		lbBillDate = new JLabel ("Bill Date:");
		lbBillDate.setForeground (Color.black);
		lbBillDate.setBounds(15,175,130,20);
		
		lbAmountPaid = new JLabel ("Amount Paid:");
		lbAmountPaid.setForeground (Color.red);
		lbAmountPaid.setBounds(15,205,100,20);
						
		//Setting the Form's TextFields n ComboBox...
		txtBillId = new JTextField();
		txtBillId.setHorizontalAlignment (JTextField.RIGHT);
		txtBillId.addFocusListener (this);
		txtBillId.setBounds (200, 15, 175, 25);
		
		txtCustomerId = new JTextField();
		txtCustomerId.setHorizontalAlignment (JTextField.RIGHT);
		txtCustomerId.addFocusListener (this);
		txtCustomerId.setBounds (200, 45, 175, 25);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setHorizontalAlignment (JTextField.RIGHT);
		txtCustomerName.addFocusListener (this);
		txtCustomerName.setBounds (200, 75, 175, 25);
		
		txtMeterId = new JTextField();
		txtMeterId.setHorizontalAlignment (JTextField.RIGHT);
		txtMeterId.addFocusListener (this);
		txtMeterId.setBounds (200, 105, 175, 25);
		
		txtAmount = new JTextField();
		txtAmount.addFocusListener (this);
		txtAmount.setBounds (200, 145, 175, 25);
		
		txtBillDate = new JTextField();
		txtBillDate.setHorizontalAlignment (JTextField.RIGHT);
		txtBillDate.addFocusListener (this);
		txtBillDate.setBounds (200, 175, 175, 25);
		
		txtAmountPaid = new JTextField();
		txtAmountPaid.setHorizontalAlignment (JTextField.RIGHT);
		txtAmountPaid.addFocusListener(this);
		txtAmountPaid.setBounds(200,205,175,25);
						
		jb1 = new JButton("CREATE");
		jb1.setBounds(40,430,90,20);
		jb1.addActionListener(this);
		
		jb2 = new JButton("SEARCH");
		jb2.setBounds(140,430,90,20);
		jb2.addActionListener(this);
		
		jb3 = new JButton("CANCEL");
		jb3.setBounds(240,430,90,20);
		jb3.addActionListener(this);
		
		jb4 = new JButton("FIND");
		jb4.setBounds(340,430,90,20);
		jb4.addActionListener(this);
		
		
		txtBillId.addKeyListener (new KeyAdapter () {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (! ((Character.isDigit (c)) || (c == KeyEvent.VK_BACK_SPACE))) {
					getToolkit().beep ();
					ke.consume ();
				}
			}
		}
		);
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
		txtMeterId.addKeyListener (new KeyAdapter () {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (! ((Character.isDigit (c)) || (c == KeyEvent.VK_BACK_SPACE))) {
					getToolkit().beep ();
					ke.consume ();
				}
			}
		}
		);
		txtAmountPaid.addKeyListener (new KeyAdapter () {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (! ((Character.isDigit (c)) || (c == KeyEvent.VK_BACK_SPACE))) {
					getToolkit().beep ();
					ke.consume ();
				}
			}
		}
		);
		
		pCBill.setLayout(null);
		pCBill.add(txtBillId);
		pCBill.add(txtCustomerId);
		pCBill.add(txtCustomerName);
		pCBill.add(txtMeterId);
		pCBill.add(txtAmount);
		pCBill.add(txtBillDate);
		pCBill.add(txtAmountPaid);
		pCBill.add(lbBillId);
		pCBill.add(lbCustomerId);
		pCBill.add(lbMeterId);
		pCBill.add(lbAmount);
		pCBill.add(lbBillDate);
		pCBill.add(lbAmountPaid);
		
		pCBill.add(jb1);
		pCBill.add(jb2);
		pCBill.add(jb3);
		pCBill.add(jb4);
		getContentPane().add (pCBill, BorderLayout.CENTER);
		setVisible (true);
		}
		public void actionPerformed(ActionEvent ae)
	{
			Object obj = ae.getSource();
	
	if (obj == jb1) {		//If OK Button Pressed.
	
	                         //Validating to Check All Required Information Provided or Not.
							 if(txtBillId.getText().equals("")){
			JOptionPane.showMessageDialog (this, "Bill's Id is not Provided.");
				txtBillId.requestFocus ();
			}
			 else if (txtCustomerId.getText().equals ("")) {
			JOptionPane.showMessageDialog (this, "Customer's Id not Provided.");
			txtCustomerId.requestFocus ();
			}
			 else if(txtMeterId.getText().equals("")){
			JOptionPane.showMessageDialog (this, "Meter's Id is not Provided.");
				txtMeterId.requestFocus ();
			}
			
			else if(txtBillDate.getText().equals("")){
			JOptionPane.showMessageDialog (this, "Bill Date is not provided.");
			txtBillDate.requestFocus();
			}
			else if(txtAmountPaid.getText().equals("")){
			JOptionPane.showMessageDialog (this, "Amount Paid is not Provided.");
				txtAmountPaid.requestFocus ();
			}
			else{
			try{
			String loc = "jdbc:odbc:ELECTRICITYBILLSYSTEM";
			con=DriverManager.getConnection("jdbc:odbc:ELECTRICITYBILLSYSTEM","raj","jesus");
			PreparedStatement pstmt=con.prepareStatement("INSERT INTO Bill VALUES(?,?,?,?,?,?) ");
			pstmt.setInt(1,Integer.parseInt(txtBillId.getText()));
            pstmt.setString(2,txtCustomerId.getText());
            pstmt.setString(3,txtMeterId.getText());
            pstmt.setString(4,txtAmount.getText());
			pstmt.setString(5,txtBillDate.getText());
			pstmt.setString(6,txtAmountPaid.getText());
			int i=pstmt.executeUpdate();
			if(i>=1)
			{
			JOptionPane.showMessageDialog(null,"BILL CREATED SUCCESSFULLY",
            "CONFIRM MESSAGE",JOptionPane.INFORMATION_MESSAGE);
			txtClear();
            txtBillId.requestFocus();
			txtCustomerId.requestFocus();
			txtMeterId.requestFocus();
			}
			else
			{ 
			 JOptionPane.showMessageDialog(null,"ERROR CREATING OF BILL!!","CONFIRM MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
             }
				catch(SQLException sqlex) { 
				
				 }
				 }
				 }
				 
				 
	
	else if(obj == jb2)
		{
		  try
		  {
		    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String loc = "jdbc:odbc:ELECTRICITYBILLSYSTEM";
			con=DriverManager.getConnection("jdbc:odbc:ELECTRICITYBILLSYSTEM", "raj","jesus");
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery("SELECT M_ID,AMOUNT FROM METER WHERE C_ID='"+txtCustomerId.getText()+"'");
			if(rs.next())
						{
						
						  txtMeterId.setText(rs.getString(1));
						  txtAmount.setText(rs.getString(2));
						  txtMeterId.setEnabled(false);
						  txtAmount.setEnabled(false);
						}
							else
					 {
					   JOptionPane.showMessageDialog(this,"NOT FOUND","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);}
				       
					}
					
					catch(Exception e)
				{
					//......................
					
				}
				}
		else if(obj == jb3)
		{
			txtClear ();     //Clearing all the fields...
			setVisible(false);
			dispose();
		}
		else if(obj == jb4)
		{
		try
		  {
		    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String loc = "jdbc:odbc:ELECTRICITYBILLSYSTEM";
			con=DriverManager.getConnection("jdbc:odbc:ELECTRICITYBILLSYSTEM", "raj","jesus");
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery("SELECT C_NAME FROM CUSTOMER WHERE C_ID='"+txtCustomerId.getText()+"'");
			if(rs.next())
						{
						
						  txtCustomerName.setText(rs.getString(1));
						  txtCustomerName.setEnabled(false);
						}
							else
					 {
					   JOptionPane.showMessageDialog(this,"NOT FOUND","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);}
				       
					}
					
					catch(Exception e)
				{
					//......................
				}	
		}
	}
		public void focusGained (FocusEvent fe) { }
		public void focusLost (FocusEvent fe) {
		if (txtCustomerId.getText().equals ("")) {	//If TextField is Empty.
		}
		else{ 
		}}
		private void txtClear () {
		txtBillId.setText ("");
		txtCustomerId.setText ("");
		txtMeterId.setText ("");
		txtAmount.setText("");
		txtBillDate.setText("");
		txtAmountPaid.setText("");
		txtBillId.requestFocus();
		txtCustomerId.requestFocus ();
		txtMeterId.requestFocus();
		}
		

}		