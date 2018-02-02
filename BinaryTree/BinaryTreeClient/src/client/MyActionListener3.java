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
public class MyActionListener3 implements ActionListener {

	PrintStream p;
	Scanner sc;
	JTextArea text;
	JTextField textField;
	client ap;

	/**
	 * @param pp PrintStream object
	 * @param s Scanner object
	 * @param texxt JTextArea object
	 * @param tf JTextField object
	 * @param app client object
	 * construcor
	 */
	public MyActionListener3(PrintStream pp, Scanner s, JTextArea texxt, JTextField tf, client app) {
		p = pp;
		sc = s;
		text = texxt;
		textField = tf;
		ap = app;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		String temp, temp2;
		p.println(4);
		p.println(textField.getText());
		temp = sc.nextLine();
		
			System.out.println("JEST");
			temp2 = sc.nextLine();
			System.out.println(temp + " " + temp2);
			ap.clean();
			if (temp2.length() >= temp.length()) {
				ap.painttree(temp2 + "", ap.rys.getWidth() / 2, 35, Integer.parseInt(temp), 1, 1);
			} else {

				ap.painttree(temp + "", ap.rys.getWidth() / 2, 35, Integer.parseInt(temp2), 1, 1);
			}
		
	}
}