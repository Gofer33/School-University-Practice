import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Damian Borek
 *
 */
public class JWindowAdapter extends WindowAdapter {
	/* (non-Javadoc)
	 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
	 */
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
