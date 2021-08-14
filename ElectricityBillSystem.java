import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class ElectricityBillSystem extends JFrame implements ActionListener
{
   //Main Place on Form where All Child Forms will Shown.

	private JDesktopPane desktop = new JDesktopPane ();

	//For Program's MenuBar.
	private JMenuBar bar;
	private JMenu mnuFile, mnuMasterTable, mnuTransactions,mnuReports, mnuHelp;
	private JMenuItem newExit; //File Menu Options....
	private JMenuItem newCustomer, newMeter, deleteCustomer;  //MasterTable Menu Options....
	private JMenuItem createBill, viewBill;	//Transactions Menu Options....
	private JMenuItem ViewCustomer, BillView, ViewMeter; //Reports Menu Options...
	private JMenuItem about;  //Help Menu Options....
	
	//Panel For Main Form StatusBar where Program's Name & Welcome Message Display.
	
	private JPanel statusBar = new JPanel ();
	private JLabel lbStatus;	//For Program's Status.
	private JLabel lbWelcome;	//For Welcome Message.
	
	//Getting the Current System Date.
	
	private java.util.Date currDate = new java.util.Date (); //Creating Object.
	private SimpleDateFormat sdf = new SimpleDateFormat ("dd MMMM yyyy", Locale.getDefault());	//Changing Format.
	private String d = sdf.format (currDate);	
	
	private Connection con;		//For Creating the Connection Between Program & Database.
	private Statement st;		//For Getting the Tables From Database.
	
	private String userName;	//For Getting the Current User's Name.
	
	public ElectricityBillSystem (int type,int user ,Connection conn)
	//public ElectricityBillSystem()
	{
	//super (Title, Resizable, Closable, Maximizable, Iconifiable)
		super("ELECTRICITY BILL SYSTEM.");

		//Setting the Main Window of Program.
		setSize (700, 550);						//Setting Main Window Size.
		//Setting the Location of Program on User's Computer Screen By Getting the Screen's Height & Width.

		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getWidth()) / 2,
			(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
			
			//Closing Code of Main Window.

		addWindowListener (new WindowAdapter () {
		public void windowClosing (WindowEvent we) {	//Overriding the windowClosing Function.
				dispose(); //Closing the Form..
			}
			}
		);
		bar = new JMenuBar ();		//Creating the MenuBar Object.
		setJMenuBar (bar);		//Setting Main Window MenuBar.
		//		Creating the Menus of Program & Assigning the Key too to Open them.
		mnuFile = new JMenu ("File");
		mnuFile.setMnemonic ((int)'F');
		mnuMasterTable = new JMenu ("MasterTable");
		mnuMasterTable.setMnemonic((int)'M');
		mnuTransactions = new JMenu("Transactions");
		mnuTransactions.setMnemonic((int)'T');
		mnuReports = new JMenu("Reports");
		mnuReports.setMnemonic((int)'R');
		mnuHelp = new JMenu("Help");
		mnuHelp.setMnemonic((int)'H');
		
		//Creating All the MenuItems of Program.
		//MenuItems for FileMenu.
		newExit = new JMenuItem("EXIT");
		newExit.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
		newExit.setMnemonic((int)'E');
		newExit.addActionListener (this);
		
		//MenuItems for MasterTable Menu...
		newCustomer = new JMenuItem("ADD CUSTOMER");
		newCustomer.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
		newCustomer.setMnemonic ((int)'C');
		newCustomer.addActionListener (this);
	    newMeter = new JMenuItem("ADD METER");
		newMeter.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK));
		newMeter.setMnemonic((int)'M');
		newMeter.addActionListener(this);
		deleteCustomer = new JMenuItem("DELETE CUSTOMER");
		deleteCustomer.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_D,Event.CTRL_MASK));
		deleteCustomer.setMnemonic((int)'D');
		deleteCustomer.addActionListener(this);
		
		//MenuItem For Transactions Menu...
		createBill = new JMenuItem("CREATE BILL");
		createBill.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK));
		createBill.setMnemonic ((int)'B');
		createBill.addActionListener (this);
		viewBill = new JMenuItem("VIEW BILL");
		viewBill.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_V,Event.CTRL_MASK));
		viewBill.setMnemonic((int)'V');
		viewBill.addActionListener(this);

		ViewCustomer = new JMenuItem("Customer's LIST");
		ViewCustomer.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_L,Event.CTRL_MASK));
		ViewCustomer.setMnemonic((int)'L');
		ViewCustomer.addActionListener(this);
		BillView = new JMenuItem("BILL REPORT");
		BillView.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_R,Event.CTRL_MASK));
		BillView.setMnemonic((int)'R');
		BillView.addActionListener(this);
		ViewMeter = new JMenuItem("METER REPORT");
		ViewMeter.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_E,Event.CTRL_MASK));
		ViewMeter.setMnemonic((int)'E');
		ViewMeter.addActionListener(this);
	
		
		//MenuItems for HelpMenu.
		about = new JMenuItem ("About ElectrcityBillSystem");
	    about.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
	    about.setMnemonic ((int)'S');
	    about.addActionListener (this);
		//Adding All MenuItems to their Menu.

	    //File Menu Items.
		mnuFile.add(newExit);
		
		//MasterTable Menu Items.
		mnuMasterTable.add(newCustomer);
		mnuMasterTable.addSeparator();
		mnuMasterTable.add(newMeter);
		mnuMasterTable.addSeparator();
		mnuMasterTable.add(deleteCustomer);
		
		//Transactions Menu Items.
		mnuTransactions.add(createBill);
		mnuTransactions.add(viewBill);

		//Reports Menu Items...
		mnuReports.add(ViewCustomer);
		mnuReports.add(BillView);
		mnuReports.add(ViewMeter);
		
		//Help Menu Items.
		mnuHelp.add(about);
		//Adding All Menus to MenuBar.
		bar.add (mnuFile);
		bar.add (mnuMasterTable);
		bar.add (mnuTransactions);
		bar.add(mnuReports);
		bar.add (mnuHelp);
		
		lbStatus = new JLabel (" " + "Electricity Bill System.", Label.LEFT);
	    lbStatus.setForeground (Color.black);
	    lbStatus.setToolTipText ("Program's Title");
	    lbWelcome = new JLabel ( " Today is " + d + " ", JLabel.RIGHT);
	    lbWelcome.setForeground (Color.black);
	    statusBar.setLayout (new BorderLayout());
	    statusBar.add (lbStatus, BorderLayout.WEST);
	    statusBar.add (lbWelcome, BorderLayout.EAST);
		
		//Setting the Contents of Programs.

	    //getContentPane().add (toolBar, BorderLayout.NORTH);
	      getContentPane().add (desktop, BorderLayout.CENTER);
	      getContentPane().add (statusBar, BorderLayout.SOUTH);
		  //Getting the Database.

	     con = conn;
		 setVisible (true);
	
	}

public void actionPerformed (ActionEvent ae) {
		
		Object obj = ae.getSource();

		if (obj == newCustomer) {

			
			boolean b = openChildWindow ("Add Customer");
			if (b == false) {
				AddCustomer adCustomer = new AddCustomer (con);
				desktop.add (adCustomer);			//Adding Child Window to DesktopPane.
				adCustomer.show ();				//Showing the Child Window.
			}

		}	
		else if (obj == newMeter) {

			boolean b = openChildWindow ("Add Meter");
			if (b == false) {
				AddMeter adMeter = new AddMeter (con);
				desktop.add (adMeter);
				adMeter.show ();
				
			} 

		}
		else if (obj == newExit) {
		try{
		//Show a confirmation dialog...
		int reply = JOptionPane.showConfirmDialog (this, 
				"Do you really want to exit From\n Electricity Bill System?","ElectricityBillSystem - Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				//Check the User Selection.
			if (reply == JOptionPane.YES_OPTION) {				//If User's Choice Yes then.
				setVisible (false);					//Hide the Frame.
				dispose();            					//Free the System Resources.
				System.exit (0);        				//Close the Application.
			}
			else if (reply == JOptionPane.NO_OPTION) {			//If User's Choice No then.
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	//Do Nothing Return to Program.
		//dispose();
		}
		}
		catch (Exception e) {}
		}
		else if(obj == deleteCustomer) {
		boolean b = openChildWindow("Delete Customer");
		if(b == false) {
		  DeleteCustomer dlCustomer = new DeleteCustomer (con);
		  desktop.add(dlCustomer);
		  dlCustomer.show();
		  }
		  }
		  else if(obj == createBill) {
		  boolean b = openChildWindow("Create Bill");
		  if(b == false) {
		  CreateBill cBill = new CreateBill (con);
          desktop.add(cBill);
		  cBill.show();
		  }
		  }
		 
		  else if(obj == viewBill) {
		  boolean b = openChildWindow("View Bill");
		  if(b == false) {
		  ViewBill vBill = new ViewBill(con);
		  desktop.add(vBill);
		  vBill.show();
		  }
		  }
		  else if  (obj == ViewCustomer)
		  {
			  boolean b = openChildWindow("Customer's List");
			  if(b == false) {
				  ViewCustomer vCustomer = new ViewCustomer(con);
				  desktop.add(vCustomer);
				  vCustomer.show();
			  }
			  
		  }
		  else if (obj == BillView)
		  {
		      boolean b = openChildWindow("BILL REPORT");
			  if(b == false) {
			  BillView bView = new BillView(con);
			  desktop.add(bView);
			  bView.show();
			  }
			  }
			  else if (obj == ViewMeter)
			  {
			    boolean b = openChildWindow("METER REPORT");
				if(b == false) {
				ViewMeter vMeter = new ViewMeter(con);
				desktop.add(vMeter);
				vMeter.show();
				}
			  }	
		  else if (obj == about) {

			
			String msg = "Bikash's ElectricityBillSystem.\n\n" + "Created & Designed By:\n" + 
				"Bikash Mahapatra\n\n" + "E-mail:\n bikashmohapatra1997@gmail.com";
			JOptionPane.showMessageDialog (this, msg, "About ElectricityBillSystem", JOptionPane.PLAIN_MESSAGE);

		}
		}
		private boolean openChildWindow (String title) {

		JInternalFrame[] childs = desktop.getAllFrames ();		//Get All Open Child Windows.
		for (int i = 0; i < childs.length; i++) {
			if (childs[i].getTitle().equalsIgnoreCase (title)) {	//Getting the Title of Child Window.
				childs[i].show ();				//Setting Focus on the Child Window.
				return true;
			}
		}
		return false;
		}
}
		
		

	    
		
	
	