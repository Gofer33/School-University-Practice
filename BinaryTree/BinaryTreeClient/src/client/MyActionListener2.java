package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Damian Borek
 * @version 0.99
 */
public class MyActionListener2 implements ActionListener {

	PrintStream p;
	Scanner sc;
	JTextArea text;
	JTextField textField;
	/**
	 * @param pp PrintStream object
	 * @param s Scanner object
	 * @param texxt JTextArea object
	 * @param tf JTextField object
	 * constructor
	 */
	public MyActionListener2(PrintStream pp, Scanner s, JTextArea texxt, JTextField tf)
	{
		p=pp;
		sc=s;
		text=texxt;
		textField=tf;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{ 
		String temp;
		p.println(3);
		p.println(textField.getText());
		temp=sc.nextLine();
		textField.setText(temp);
	}
}
