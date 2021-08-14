import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class TableDisp extends JFrame {

	private JPanel pBill = new JPanel ();
	private JScrollPane scroller;
	private JTable table;
	public TableDisp(JTable j){
		super("Bill Display");
		setSize(600,600);
		pBill.setLayout (new FlowLayout());
		table=j;
		scroller = new JScrollPane (table);	//Adding Table to ScrollPane.
		scroller.setBounds (20,100, 1024, 1180);	//Aligning ScrollPane.
		pBill.add(scroller);
		getContentPane().add (pBill, BorderLayout.CENTER);
		setVisible(true);
	}
	
}