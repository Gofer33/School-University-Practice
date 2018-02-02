import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * @author Damian Borek
 *
 */
public class JPaint extends JApplet {

	int tryb = 0;
	int liczbakwadratow = 0;
	int liczbaokregow = 0;
	int liczbawielokatow = 0;
	int liczbakresek = 0;
	int liczbaprzeksztalcen = 0;
	double a, b;
	double x1, y1, x2, y2;
	int stadrysujemyx;
	int stadrysujemyy;
	int tutajkolkox;
	int tutajkolkoy;
	float deltax = 1;
	boolean[] tablicaaktywnychprzeksztalcen = new boolean[10];

	JPanel rys = new JPanel();
	MyJButton rectangle;
	MyJButton polygon;
	MyJButton circle;
	MyJButton save;
	MyJButton load;
	MyJButton info;
	ArrayList<JRectangle> RectangleArray = new ArrayList<JRectangle>();
	ArrayList<JEllipse> CircleArray = new ArrayList<JEllipse>();
	ArrayList<JPolygon> PolygonArray = new ArrayList<JPolygon>();
	JEllipse czarodziejskiekolko = new JEllipse(1, 1, 0, 0);
	JPolygon bla = new JPolygon();
	JRectangle ble = new JRectangle(1, 1, 1, 1);
	Line2D[] tablicakresek = new Line2D[100];

	Shape[] tablicaprzeksztalcen = new Shape[10];

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#init()
	 */
	public void init() {

		setLayout(new BorderLayout());
		add("Center", new MyJPanel(this));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		super.paint(((Graphics2D) g));
		repaint();
	}

	/**
	 * @param a1
	 * @param b1
	 */
	public void printCircle(int a1, int b1) {

		Graphics g = rys.getGraphics();

		((Graphics2D) g).setColor(Color.black);
		((Graphics2D) g).drawOval(tutajkolkox - 5, tutajkolkoy - 5, 10, 10);

	}

	/**
	 * @param a1
	 * @param b1
	 * @param a2
	 * @param b2
	 */
	public void printLine(int a1, int b1, int a2, int b2) {

		repaint();

		Graphics g = rys.getGraphics();

		((Graphics2D) g).drawLine(a1, b1, a2, b2);
		if (tryb == 4)
			printCircle(a1, b1);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#repaint()
	 */
	public void repaint() {
		int xx = rys.getSize().width - 100, yy = rys.getSize().height - 100;
		Graphics g = rys.getGraphics();

		g.clearRect(0, 0, xx + 100, yy + 100);
		g.setColor(Color.black);
		g.drawRect(5, 5, xx + 90, yy + 90);

		for (int i = 0; i < liczbakwadratow; i++) {

			if (RectangleArray.get(i).active == true)
				((Graphics2D) g).setPaint(new Color(200, 0, 0));
			else
				((Graphics2D) g).setPaint(RectangleArray.get(i).kolor);
			((Graphics2D) g).fill(RectangleArray.get(i));

		}
		for (int i = 0; i < liczbaokregow; i++) {

			if (CircleArray.get(i).active == true)
				((Graphics2D) g).setPaint(new Color(200, 0, 0));
			else
				((Graphics2D) g).setPaint(CircleArray.get(i).kolor);
			((Graphics2D) g).fill(CircleArray.get(i));

		}

		if (tryb == 4) {
			for (int i = 0; i < liczbakresek; i++) {

				((Graphics2D) g).drawLine((int) tablicakresek[i].getX1(), (int) tablicakresek[i].getY1(),
						(int) tablicakresek[i].getX2(), (int) tablicakresek[i].getY2());

			}
		}

		Graphics g2 = rys.getGraphics();
		AffineTransform scaleMatrix = new AffineTransform();

		scaleMatrix.scale(deltax, deltax);

		for (int i = 0; i < liczbawielokatow; i++) {

			//((Graphics2D) g).setTransform(scaleMatrix); //TUTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJ

			tablicaprzeksztalcen[i] = scaleMatrix.createTransformedShape(PolygonArray.get(i));

			if (PolygonArray.get(i).active == true)
				((Graphics2D) g).setPaint(new Color(200, 0, 0));
			else
				((Graphics2D) g).setPaint(PolygonArray.get(i).kolor);
			((Graphics2D) g).fill(PolygonArray.get(i));

			if (tablicaaktywnychprzeksztalcen[i] == true) {
				((Graphics2D) g2).setPaint(new Color(200, 0, 0));
				((Graphics2D) g2).fill(tablicaprzeksztalcen[i]);
			} else {
				((Graphics2D) g2).setPaint(PolygonArray.get(i).kolor);
				((Graphics2D) g2).fill(tablicaprzeksztalcen[i]);

			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame("JPaint");
		f.setSize(1280, 768);
		f.addWindowListener(new JWindowAdapter());
		JPaint w = new JPaint();
		f.add(w);
		w.init();
		f.setVisible(true);
	}

}
