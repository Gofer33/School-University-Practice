import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Damian Borek
 * Shutting down program, after clicking X
 */
public class JWindowAdapter extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);

	}
}
