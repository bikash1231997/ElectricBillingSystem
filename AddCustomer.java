import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.sql.*;
public class AddCustomer extends JInternalFrame implements  ActionListener,FocusListener {
private JPanel pCustomer = new JPanel ();
private JLabel lbCustomerId, lbCustomerName, lbCustomerAddress, lbCustomerTelephone;
private JTextField txtCustomerId, txtCustomerName, txtCustomerTelephone;
private JTextArea txtaCustomerAddress;
private JScrollPane scrollpane;
private Connection con;
private JButton jb1,jb2;
private Statement st;			//Statement for Getting the Required Table.
private long id = 0;			
public AddCustomer (Connection con){

//super (Title, Resizable, Closable, Maximizable, Iconifiable)
		super ("Add New Customer", false, true, false, true);
		setSize (500, 450);
				//Setting the Form's Labels.
        lbCustomerId = new JLabel ("Customer Id:");
		lbCustomerId.setForeground (Color.black);
		lbCustomerId.setBounds (15, 15, 130, 20);
		lbCustomerName = new JLabel ("Customer Name:");
		lbCustomerName.setForeground (Color.black);
		lbCustomerName.setBounds (15, 45, 130, 20);
		lbCustomerAddress = new JLabel ("Customer Address:");
		lbCustomerAddress.setForeground (Color.black);
		lbCustomerAddress.setBounds (15, 95, 130, 20);
		lbCustomerTelephone = new JLabel("Customer Telephone");
		lbCustomerTelephone.setForeground (Color.black);
		lbCustomerTelephone.setBounds(15,225,130,20);
		
		//Setting the Form's Text Fields....
		txtCustomerId = new JTextField ();
		txtCustomerId.setHorizontalAlignment (JTextField.RIGHT);
		txtCustomerId.addFocusListener (this);
		
		txtCustomerId.setBounds (190, 15, 175, 25);
		txtCustomerName = new JTextField ();
		txtCustomerName.setHorizontalAlignment (JTextField.RIGHT);
		txtCustomerName.addFocusListener (this);
		
		txtCustomerName.setBounds (190, 45, 175, 25);
		txtaCustomerAddress = new JTextArea(3,8);
		txtaCustomerAddress.addFocusListener (this);
		txtaCustomerAddress.setBounds (190, 80, 220, 100);
		JScrollPane scrollPane = new JScrollPane(txtaCustomerAddress,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		txtCustomerTelephone = new JTextField ();
		txtCustomerTelephone.setHorizontalAlignment (JTextField.LEFT);
		txtCustomerTelephone.addFocusListener (this);
		txtCustomerTelephone.setBounds (190, 220, 175, 25);	
		
		jb1 = new JButton("SAVE");
		jb1.setBounds(90,380,80,20);
		jb1.addActionListener(this);
		jb2 = new JButton("CANCEL");
		jb2.setBounds(200,380,80,20);
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
		txtCustomerName.addKeyListener (new KeyAdapter () {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (! ((Character.isLetter (c)) || (c == KeyEvent.VK_BACK_SPACE))) {
					getToolkit().beep ();
					ke.consume ();
				}
			}
		}
		);
		txtCustomerTelephone.addKeyListener (new KeyAdapter () {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (! ((Character.isDigit (c)) || (c == KeyEvent.VK_BACK_SPACE))) {
					getToolkit().beep ();
					ke.consume ();
				}
			}
		}
		);
		
		pCustomer.setLayout (null);
		pCustomer.add (lbCustomerId);
		pCustomer.add (lbCustomerName);
		pCustomer.add (lbCustomerAddress);
		pCustomer.add (lbCustomerTelephone);
		pCustomer.add (txtCustomerId);
		pCustomer.add (txtCustomerName);
		pCustomer.add (txtaCustomerAddress);
		pCustomer.add (txtCustomerTelephone);
		pCustomer.add (jb1);
		pCustomer.add (jb2);
		//Adding Panel to Form.
		
		getContentPane().add (pCustomer, BorderLayout.CENTER);
		setVisible(true);
		}
		
		public void actionPerformed (ActionEvent ae) {
	
	Object obj = ae.getSource();
	
	if (obj == jb1) {		//If OK Button Pressed.
	
                                                  	//Validating to Check All Required Information Provided or Not.
	
	                                               if (txtCustomerId.getText().equals ("")) {
			                                       JOptionPane.showMessageDialog (this, "Customer's Id not Provided.");
			                                       txtCustomerId.requestFocus ();
			                                        }
			                                      else if (txtCustomerName.getText().equals ("")) {
				                                  JOptionPane.showMessageDialog (this, "Customer's Name not Provided.");
				                                  txtCustomerName.requestFocus ();
			                                       }
			                                      else if (txtaCustomerAddress.getText().equals ("")) {
				                                  JOptionPane.showMessageDialog (this, "Customer's Address is not Provided.");
				                                  txtaCustomerAddress.requestFocus ();
			                                       }
			                                      else if(txtCustomerTelephone.getText().equals("")){
			                                      JOptionPane.showMessageDialog (this, "Customer's Telephone Number is not Provided.");
				                                  txtCustomerTelephone.requestFocus ();
			                                       }
		                    	else
	                          	{

			                                   	try {
				                                             // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
								                           	  String loc = "jdbc:odbc:ELECTRICITYBILLSYSTEM";
                                                             con=DriverManager.getConnection("jdbc:odbc:ELECTRICITYBILLSYSTEM",
                                                                                "raj","jesus");
                                                             PreparedStatement pstmt=con.prepareStatement("INSERT INTO Customer VALUES(?,?,?,?) ");
                                                             pstmt.setInt(1,Integer.parseInt(txtCustomerId.getText()));
                                                             pstmt.setString(2,txtCustomerName.getText());
                                                             pstmt.setString(3,txtaCustomerAddress.getText());
                                                             pstmt.setString(4,txtCustomerTelephone.getText());
                                                             int i=pstmt.executeUpdate();
                                      
									                       if(i>=1)
		                                                        {
									                                    JOptionPane.showMessageDialog(null,"CUSTOMER ADDED SUCCESSFULLY",
                                                                        "CONFIRM MESSAGE",JOptionPane.INFORMATION_MESSAGE);
																		txtClear();
                  		                                                   txtCustomerId.requestFocus();
		                                                         }
                                                              else		
							                         {            JOptionPane.showMessageDialog(null,"ERROR CUSTOMER WHILE INSERTING","CONFIRM MESSAGE",JOptionPane.ERROR_MESSAGE);
			                                          }
				
			                                     }
				                                         catch (SQLException sqlex) { 
				                                          
			                                                 }
		                   }
	}
		if(obj == jb2) {			//If Cancel Button Pressed Unload the Form.
            txtClear();
			setVisible(false);
			dispose();
		}
	}
		
		public void focusGained (FocusEvent fe) { }
         public void focusLost (FocusEvent fe) {
		 Object obj = fe.getSource ();
		 if (obj == txtCustomerId) {
	      if (txtCustomerId.getText().equals ("") && txtCustomerName.getText().equals ("")  && txtCustomerTelephone.getText().equals ("")) {	//If TextField is Empty.
			}
			else {
				                         
			        }
		 }
		 }
		
		private void txtClear () {

		txtCustomerId.setText ("");
		txtCustomerName.setText ("");
		txtaCustomerAddress.setText ("");
		txtCustomerTelephone.setText ("");
		txtCustomerId.requestFocus ();
	}
}