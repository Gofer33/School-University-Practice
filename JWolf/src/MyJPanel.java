import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * @author Damian Borek
 * Just JPanel
 */
public class MyJPanel extends JPanel {
	public MyJPanel(JWolf w) {
		setLayout(new BorderLayout());
		w.p = new JPanel(true);
		add("Center", w.p);
	}
}
