package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

/**
 * @author Damian Borek
 * @version 0.99
 */
public class MyActionListenerType implements ActionListener {
	int a;
	PrintStream p;
	/**
	 * @param i Integer flag
	 * @param pp prinstream object
	 * constructor
	 */
	public MyActionListenerType(int i,PrintStream pp) {
		a = i;
		p=pp;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		p.println(a);
	}
}
