import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author Damian Borek
 *  main applet
 */
public class JWolf extends JApplet {
	Random randomno = new Random();
	int n = 0;
	int m = 0;
	int time = 0;
	int nh = 0;
	int finish = 0;
	static ArrayList<Hare> hares = new ArrayList<Hare>();
	int[][] tab;
	JRectangle[][] recttab;
	int wolfx, wolfy;
	int har = 0;
	JPanel p = new JPanel();
	int size = 50;

	/**
	 * Constructor, initialise needed variables, creates array of rectangles, and arraylist of hares
	 * @param nn width of array
	 * @param mm height of array
	 * @param kk length of thread cycle
	 * @param xx number of hares
	 */
	public JWolf(int nn, int mm, int kk, int xx) {
		n = nn;
		m = mm;
		time = kk;
		nh = xx;
		finish = xx;
		tab = new int[n][m];
		recttab = new JRectangle[n][m];
		int l1 = randomno.nextInt(n);
		int l2 = randomno.nextInt(m);
		wolfx = l1;
		wolfy = l2;
		tab[l1][l2] = 1;
		int xxx = p.getSize().width + 30, yyy = p.getSize().height + 30;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				recttab[i][j] = new JRectangle(xxx + (i * 50), yyy + (j * 50), 49, 49);
			}
		}
		for (int i = 0; i < nh; i++) {

			do {
				l1 = randomno.nextInt(n);
				l2 = randomno.nextInt(m);
			} while (tab[l1][l2] != 0);
			hares.add(new Hare(this, l1, l2));
			hares.get(har).start();
			har++;
			tab[l1][l2] = 2;
		}
	}
	/** This method initialising applet. */
	public void init() {

		setLayout(new BorderLayout());

		add("Center", new MyJPanel(this));

	}
	/** This repainting graphics */
	public void repaint() {

		Graphics g = p.getGraphics();
		if (g != null) {
			((Graphics2D) g).setPaint(new Color(80, 80, 80));
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (size * n > p.getSize().width) {
						size = p.getSize().width / n;
					} else if (size * n < p.getSize().width) {
						size = p.getSize().width / n;
					}
					if (size * m > p.getSize().height) {
						size = p.getSize().height / m;
					}
					recttab[i][j].setRect((i * size), (j * size), size - 1, size - 1);
					if (tab[i][j] == 1)
						((Graphics2D) g).setPaint(new Color(200, 0, 0));
					if (tab[i][j] == 2)
						((Graphics2D) g).setPaint(new Color(0, 200, 0));
					((Graphics2D) g).fill(recttab[i][j]);
					((Graphics2D) g).setPaint(new Color(80, 80, 80));

				}
			}
		}
	}
	/** This method painting graphics. */
	public void paint(Graphics g) {
		super.paint(((Graphics2D) g));
		repaint();
	}
	
	/** Main function
	 * @param args starting parameters
	 */
	public static void main(String[] args) {

		JFrame f = new JFrame("JWolf");

		f.setSize(1280, 768);

		int x = 0, y = 0, k = 0, n = 0;
		if (args.length != 4) {
			System.out.println("Error!");
			System.exit(0);
		}
		try {
			x = Integer.parseInt(args[0]);
			y = Integer.parseInt(args[1]);
			k = Integer.parseInt(args[2]);
			n = Integer.parseInt(args[3]);
		} catch (NumberFormatException ex) {
			System.out.println("Error!");
		}
		if (x <= 0 || y <= 0 || k <= 0 || n <= 0 || n > (x * y - 1)) {
			System.out.println("Error!");
			System.exit(0);
		} else {
			JWolf w = new JWolf(x, y, k, n);

			f.add(w);

			w.init();
			f.setVisible(true);
			Wolf wolf = new Wolf(w);
			f.addWindowListener(new JWindowAdapter());
			wolf.start();

		}
	}
}