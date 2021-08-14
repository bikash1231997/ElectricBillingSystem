import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DeleteCustomer extends JInternalFrame implements ActionListener, FocusListener {
private JPanel pdCustomer = new JPanel ();
private JLabel lbCustomerId;
private JTextField txtCustomerId;
private JButton jb1,jb2;
private Statement st;			//Statement for Getting the Required Table.
private long id = 0;			//To Hold the CustomerId.
private Connection con;
public DeleteCustomer (Connection con){

//super (Title, Resizable, Closable, Maximizable, Iconifiable)
		super ("Delete Customer", false, true, false, true);
		setSize (310, 210);
		//Setting the Form's Labels.
		lbCustomerId = new JLabel ("Customer Id:");
		lbCustomerId.setForeground (Color.black);
		lbCustomerId.setBounds (15, 15, 100, 20);
		//Setting the Form's TextField..
		txtCustomerId = new JTextField ();
		txtCustomerId.setHorizontalAlignment (JTextField.RIGHT);
		txtCustomerId.addFocusListener (this);
		txtCustomerId.setBounds (120, 15, 175, 25);
		
		//Setting Form Buttons..
		jb1 = new JButton("DELETE");
		jb1.setBounds(40,80,90,20);
		jb1.addActionListener(this);
		jb2 = new JButton("CANCEL");
		jb2.setBounds(150,80,90,20);
		jb2.addActionListener(this);
		
		//Adding ActionListeners...
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
		pdCustomer.setLayout (null);
		pdCustomer.add (lbCustomerId);
		pdCustomer.add (txtCustomerId);
		pdCustomer.add (jb1);
		pdCustomer.add (jb2);
		getContentPane().add (pdCustomer, BorderLayout.CENTER);
		setVisible (true);
		}
		public void actionPerformed (ActionEvent ae) {

		Object obj = ae.getSource();

		if (obj == jb1) {		//If OK Button Pressed.
		//Validating to Check All Required Information Provided or Not.

			if (txtCustomerId.getText().equals ("")) {
				JOptionPane.showMessageDialog (this, "Customer's Id not Provided.");
				txtCustomerId.requestFocus ();
			}
			else
			{
			try{
				String loc = "jdbc:odbc:ELECTRICITYBILLSYSTEM";
				con=DriverManager.getConnection("jdbc:odbc:ELECTRICITYBILLSYSTEM", "raj","jesus");
			PreparedStatement pstmt = con.prepareStatement("Delete from Customer Where C_Id =?");
			
			pstmt.setInt(1, Integer.parseInt(txtCustomerId.getText()));
			
            	int i=pstmt.executeUpdate();
			 if (i>=1) {			//If Query Successful.
						JOptionPane.showMessageDialog (this, "Record has been Deleted.");
						txtClear ();			//Clearing the TextFields.
					}
					else {					//If Query Failed.
						JOptionPane.showMessageDialog (this, "Problem while Deleting the Record.");
						txtClear();
					}
				}
				catch (SQLException sqlex) {
					JOptionPane.showMessageDialog (this, "Problem while Deleting the Record Excep.");
					txtClear();
				}
				}
				}
				else if( obj == jb2 )
				{
					txtClear();
				  setVisible(false);
				  dispose();
				  }
			}
		public void focusGained (FocusEvent fe) { }
		public void focusLost (FocusEvent fe) {
		if (txtCustomerId.getText().equals ("")) {	//If TextField is Empty.
		
		}
		else {
			id = Integer.parseInt (txtCustomerId.getText ());	//Converting String to Numeric.
			long customerNo;					//Use for Comparing the Book's Id.
			boolean found = false;				//To Confirm the Customer's Id Existance.
			 try {	
					//SELECT Query to Retrieved the Record.
			        String q = "DELETE FROM CUSTOMER WHERE C_Id = "+id+"";
				    ResultSet rs = st.executeQuery (q);	//Executing the Query.
				    rs.next ();				//Moving towards the Record.
				    customerNo = rs.getLong ("C_Id");		//Storing the Record.
					if (customerNo != id) {			//If Record Found then Display Message.
					txtClear ();			//Clearing the TextFields.
					JOptionPane.showMessageDialog (this, id + " is Not Available. ");
				}
				else {
					found = false;
				}
			}
			catch (SQLException sqlex) { 
				
			}
			}
			}
			private void txtClear () {

		txtCustomerId.setText ("");
		txtCustomerId.requestFocus ();
		}
}