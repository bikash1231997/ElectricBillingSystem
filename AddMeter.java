import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class AddMeter extends JInternalFrame implements  FocusListener ,ActionListener{
private JPanel pMeter = new JPanel ();
private JLabel lbMeterId, lbMachineId, lbCustomerId, lbMeterStatus, lbMeterPrevReading, lbMeterPresReading, lbAmount;
private JTextField txtMeterId, txtMachineId, txtCustomerId,txtMeterPrevReading,txtMeterPresReading, txtAmount;
private JRadioButton jrb1,jrb2;               
ButtonGroup bg;
private JButton jb1,jb2,jb3;
private Connection con;
private String[] cn =new String[100];
private Statement st;
private long id = 0;			//To Hold the MeterId.
private int customerId = 0;
private int i,j,ref=0;
public AddMeter (Connection con){
//public AddMeter () 
//super (Title, Resizable, Closable, Maximizable, Iconifiable)
		super ("Add New Meter", false, true, false, true);
		setSize (500, 360);
		
		lbMeterId = new JLabel ("Meter Id:");
		lbMeterId.setForeground (Color.black);
		lbMeterId.setBounds (15, 15, 100, 20);
		lbMachineId = new JLabel ("Machine Id:");
		lbMachineId.setForeground (Color.black);
		lbMachineId.setBounds (15, 45, 100, 20);
		lbCustomerId = new JLabel ("Customer Id:");
		lbCustomerId.setForeground (Color.black);
		lbCustomerId.setBounds (15, 75, 100, 20);
		lbMeterStatus = new JLabel ("Meter Status:");
		lbMeterStatus.setForeground (Color.black);
		lbMeterStatus.setBounds (15, 105, 100, 20);
		lbMeterPrevReading = new JLabel ("Meter Prev Reading:");
		lbMeterPrevReading.setForeground (Color.black);
		lbMeterPrevReading.setBounds (15, 135, 100, 20);
		lbMeterPresReading = new JLabel ("Meter Pres Reading:");
		lbMeterPresReading.setForeground (Color.black);
		lbMeterPresReading.setBounds (15, 165, 100, 20);
		lbAmount = new JLabel ("Amount:");
		lbAmount.setForeground (Color.black);
		lbAmount.setBounds (15, 195, 100, 20);
		
		//Setting the Form's Text Fields n ComboBox..
		txtMeterId = new JTextField ();
		txtMeterId.setHorizontalAlignment (JTextField.RIGHT);
		txtMeterId.addFocusListener (this);
		txtMeterId.setBounds (150, 15, 175, 25);
		txtMachineId = new JTextField ();
		txtMachineId.setHorizontalAlignment (JTextField.LEFT);
		txtMachineId.addFocusListener (this);
		txtMachineId.setBounds (150, 45, 175, 25);
		txtCustomerId = new JTextField ();
		txtCustomerId.setHorizontalAlignment (JTextField.RIGHT);
		//txtCustomerId.setEditable(false);
		//txtCustomerId.setEnabled (false);
		txtCustomerId.addFocusListener (this);
		txtCustomerId.setBounds (150, 75, 175, 25);
		jrb1 = new JRadioButton("WORKING");
		jrb1.setBounds (150, 105, 70, 25);
		jrb2 = new JRadioButton("NOT WORKING");
		jrb2.setBounds (260,105,100,25);
		bg = new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
		txtMeterPrevReading = new JTextField ();
		txtMeterPrevReading.setHorizontalAlignment (JTextField.RIGHT);
		txtMeterPrevReading.addFocusListener (this);
		txtMeterPrevReading.setBounds (150, 135, 175, 25);
		txtMeterPresReading = new JTextField ();
		txtMeterPresReading.setHorizontalAlignment (JTextField.RIGHT);
		txtMeterPresReading.addFocusListener (this);
		txtMeterPresReading.setBounds (150, 165, 175, 25);
		txtAmount = new JTextField ();
		txtAmount.setHorizontalAlignment (JTextField.RIGHT);
		txtAmount.addFocusListener (this);
		txtAmount.setBounds (150, 195, 175, 25);
		
		jb1 = new JButton("ADD");
		jb1.setBounds(90,220,80,20);
		jb1.addActionListener(this);
		jb2 = new JButton("CANCEL");
		jb2.setBounds(200,220,80,20);
		jb2.addActionListener(this);
		jb3 = new JButton("CAL");
		jb3.setBounds(290,220,80,20);
		jb3.addActionListener(this);
		
		
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
		txtMachineId.addKeyListener (new KeyAdapter () {
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
		txtMeterPrevReading.addKeyListener (new KeyAdapter () {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (! ((Character.isDigit (c)) || (c == KeyEvent.VK_BACK_SPACE))) {
					getToolkit().beep ();
					ke.consume ();
				}
			}
		}
		);
		txtMeterPresReading.addKeyListener (new KeyAdapter () {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (! ((Character.isDigit (c)) || (c == KeyEvent.VK_BACK_SPACE))) {
					getToolkit().beep ();
					ke.consume ();
				}
			}
		}
		);
		
		pMeter.setLayout (null);
		pMeter.add (lbMeterId);
		pMeter.add (lbMachineId);
		pMeter.add (lbCustomerId);
		pMeter.add (lbMeterStatus);
		pMeter.add (lbMeterPrevReading);
		pMeter.add (lbMeterPresReading);
		pMeter.add (lbAmount);
		pMeter.add (txtMeterId);
		pMeter.add (txtMachineId);
		pMeter.add (txtCustomerId);
		pMeter.add (jrb1);
		pMeter.add (jrb2);
		pMeter.add (txtMeterPrevReading);
		pMeter.add (txtMeterPresReading);
		pMeter.add (txtAmount);
		pMeter.add (jb1);
		pMeter.add (jb2);
		pMeter.add (jb3);
		
		//Adding Panel to Form.
		
		getContentPane().add (pMeter, BorderLayout.CENTER);
		setVisible(true);
		
	}
	//Performing Actions...
	public void actionPerformed (ActionEvent ae) {
	
	Object obj = ae.getSource();
	
	if (obj == jb1) {		//If OK Button Pressed.
	
	                               //Validating to Check All Required Information Provided or Not.
	
	                               if (txtMeterId.getText().equals ("")) {
			                                           	JOptionPane.showMessageDialog (this, "Meter's Id not Provided.");
				                                        txtMeterId.requestFocus ();
			                                               }
			                      else if (txtMachineId.getText().equals ("")) {
			                                           	JOptionPane.showMessageDialog (this, "Machine's Id not Provided.");
				                                        txtMachineId.requestFocus ();
			                                                }
			
			                     else if(txtMeterPrevReading.getText().equals("")){
			                    JOptionPane.showMessageDialog (this, "Meter's Previous Reading is not Provided.");
			                   	txtMeterPrevReading.requestFocus ();
			                                                }
			                    else if(txtMeterPresReading.getText().equals("")){
			                    JOptionPane.showMessageDialog (this, "Meter's Present Reading is not Provided.");
			                	txtMeterPresReading.requestFocus ();
			                                                  }
	
			else
			{
			try
			{
				String loc = "jdbc:odbc:ELECTRICITYBILLSYSTEM";
				con=DriverManager.getConnection("jdbc:odbc:ELECTRICITYBILLSYSTEM",
                               "raj","jesus");
			PreparedStatement pstmt=con.prepareStatement("INSERT INTO METER VALUES(?,?,?,?,?,?,?)"); //Running Query...
	        pstmt.setInt(1,Integer.parseInt(txtMeterId.getText()));
		    pstmt.setString(2,txtMachineId.getText());
			pstmt.setString(3,txtCustomerId.getText());
			
			pstmt.setString(5,txtMeterPrevReading.getText());
			pstmt.setString(6,txtMeterPresReading.getText());
			pstmt.setString(7,txtAmount.getText());	
				String s = "";
				if(jrb1.isSelected())
				{
				 s = jrb1.getLabel();
				 }
				 else if(jrb2.isSelected())
				 s = jrb2.getLabel();
				 pstmt.setString(4,s);
				 
			int i=pstmt.executeUpdate();
			
			if (i>=1) {			//If Query Successful.
						JOptionPane.showMessageDialog (null, "Record has been Saved.","CONFIRM MESSAGE", JOptionPane.INFORMATION_MESSAGE);
						txtClear ();							//Clearing the TextFields.
						txtMeterId.requestFocus();
						//txtCustomerId.requestFocus();
					}
					else {					//If Query Failed.
						JOptionPane.showMessageDialog (null, "Problem while Saving the Record.");
						txtClear ();			//Clearing the TextFields.
					}
				}
				catch (SQLException sqlex) {
					JOptionPane.showMessageDialog (null, "Problem while Saving the Record Exception");
					txtClear();
				}
			}
			}
		else if (obj == jb2) {		//If Cancel Button Pressed Unload the Form.
            txtClear();
			setVisible(false);
			dispose();

		}
		else if (obj == jb3)
		{
		 int  i = Integer.parseInt(txtMeterPrevReading.getText());
		 int j = Integer.parseInt(txtMeterPresReading.getText());
		 int k = (j-i)*5;
		 if(k>0){
		   
		  txtAmount.setText(String.valueOf(k));
		  txtAmount.setEnabled(false);
		  }
		 else
		 {
		 txtMeterPrevReading.setText("");
		 txtMeterPresReading.setText("");
		 JOptionPane.showMessageDialog(this,"Not Correct Values To Calculate Amount");
		 }
		 
		 
		}
    }
	public void focusGained (FocusEvent fe) { }
    public void focusLost (FocusEvent fe) {
	Object obj = fe.getSource ();
	if (obj == txtMeterId) {
	if (txtMeterId.getText().equals ("") && txtMachineId.getText().equals ("") && txtCustomerId.getText().equals ("")) {	//If TextField is Empty.
			
			}
			else
		{
				long id = Integer.parseInt (txtMeterId.getText ());	//Converting String to Numeric.
			    long meterNo;					//Use for Comparing the Meter's Id.
			    boolean found = false;				//To Confirm the Meter's Id Existance.
		        try {	//SELECT Query to Retrieved the Record.
			                       	//String q = "SELECT * FROM Meter WHERE M_Id = "+id+"";
				                     ResultSet rs = st.executeQuery("select M_Id from Meter where M_Id = '"+txtMeterId.getText()+"'");	//Executing the Query.
				                     rs.next ();				//Moving towards the Record.
				                     meterNo = rs.getLong ("M_Id");		//Storing the Record.
				                      if (meterNo == id) {			//If Record Found then Display Message.
				                                	found = true;
			 	                                 	txtClear ();			//Clearing the TextFields.
				                                   	JOptionPane.showMessageDialog (this, +id+ " is already assigned.");
				                            }
				                            else {
				                                          	found = false;
			                                      	}
			}
			catch (SQLException sqlex) { 
				
			}
		}
		}
	}
		private void txtClear () {

		txtMeterId.setText ("");
		txtMachineId.setText ("");
		txtCustomerId.setText ("");
		txtMeterPrevReading.setText ("");
		txtMeterPresReading.setText ("");
		
		txtMeterId.requestFocus ();
		txtMachineId.requestFocus ();
		

	}
}
		