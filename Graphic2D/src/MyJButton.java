import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * @author Damian Borek
 *
 */
class MyJButton extends JButton implements ActionListener {

	JPaint p;
	private int tryb;

	/**
	 * @param pp
	 * @param string
	 * @param x
	 */
	MyJButton(JPaint pp, String string, int x) {
		super(string);
		tryb = x;
		p = pp;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		p.tryb = tryb;
		if (tryb == 0) {
			JOptionPane.showMessageDialog(null, "Damian Borek, program rysuje figury i takie tam");
		}
		if (tryb == 6) {
			Test t = new Test();
			t.savetofile(p.RectangleArray, p.PolygonArray, p.CircleArray, p);
		}
		if (tryb == 7) {
			Test t = new Test();
			t.loadfromfile(p);
			p.repaint();
		}

	}
}
