import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

import javax.swing.JColorChooser;

/**
 * @author Damian Borek
 *
 */
public class JMouseAdapter extends MouseAdapter {

	public int x = 0;
	private int y = 0;
	int a1, b1, a2, b2;
	boolean pierwsza, pierwsza2;

	JPaint p = new JPaint();

	/**
	 * @param l
	 */
	public JMouseAdapter(JPaint l) {
		p = l;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {

		x = e.getX();
		y = e.getY();

		if (e.getButton() == 3) {
			for (int i = 0; i < p.liczbakwadratow; i++) {
				if (p.RectangleArray.get(i) != null && p.RectangleArray.get(i).active) {
					Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.GREEN);
					p.RectangleArray.get(i).kolor = newColor;

				}
			}
			for (int i = 0; i < p.liczbaokregow; i++) {
				if (p.CircleArray.get(i) != null && p.CircleArray.get(i).active) {
					Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.GREEN);
					p.CircleArray.get(i).kolor = newColor;

				}
			}
			for (int i = 0; i < p.liczbawielokatow; i++) {
				if (p.PolygonArray.get(i) != null && p.PolygonArray.get(i).active) {
					Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.GREEN);
					p.PolygonArray.get(i).kolor = newColor;

				}
			}

		}

		if (p.liczbakwadratow > 0) {
			for (int i = 0; i < p.liczbakwadratow; i++) {
				if (p.RectangleArray.get(i).isHit(e.getX(), e.getY())) {
					p.RectangleArray.get(i).active = true;

					p.repaint();

				} else {
					p.RectangleArray.get(i).active = false;
					p.repaint();
				}
			}

		}
		if (p.liczbaokregow > 0) {
			for (int i = 0; i < p.liczbaokregow; i++) {
				if (p.CircleArray.get(i).isHit(e.getX(), e.getY())) {
					p.CircleArray.get(i).active = true;

					p.repaint();

				} else {
					p.CircleArray.get(i).active = false;
					p.repaint();
				}
			}

		}
		if (p.liczbawielokatow > 0) {
			for (int i = 0; i < p.liczbawielokatow; i++) {
				if (p.PolygonArray.get(i).isHit(e.getX(), e.getY())) {
					p.PolygonArray.get(i).active = true;

					p.repaint();

				} else {
					p.PolygonArray.get(i).active = false;
					p.repaint();
				}
			}

		}
		if (p.liczbaprzeksztalcen > 0) {
			for (int i = 0; i < p.liczbaprzeksztalcen; i++) {
				if (p.tablicaprzeksztalcen[i].contains(e.getX(), e.getY()) && p.tablicaprzeksztalcen[i] != null) {

					p.tablicaaktywnychprzeksztalcen[i] = true;

					p.repaint();

				} else {
					p.tablicaaktywnychprzeksztalcen[i] = false;

					p.repaint();
				}
			}

		}
		if (pierwsza) {

			p.x1 = e.getX();
			p.y1 = e.getY();
			pierwsza = false;
		} else {

			p.x2 = e.getX();
			p.y2 = e.getY();
			pierwsza = true;
		}
		if (p.tryb == 0) {
			a1 = 0;
			a2 = 0;
			b1 = 0;
			b2 = 0;
		}

		if (p.tryb == 1 || p.tryb == 2) {

			if (pierwsza2) {
				a1 = e.getX();
				b1 = e.getY();
				pierwsza2 = false;
			} else {
				a2 = e.getX();
				b2 = e.getY();
				pierwsza2 = true;
			}
		}
		if (p.tryb == 3) {
			a1 = e.getX();
			b1 = e.getY();
		}

		if (p.tryb == 1 && a1 != 0 && a2 != 0) {

			if (a2 < a1) {
				int temp = a2;
				a2 = a1;
				a1 = temp;
			}
			if (b2 < b1) {
				int temp = b2;
				b2 = b1;
				b1 = temp;
			}
			p.RectangleArray.add(new JRectangle(a1, b1, Math.abs(a2 - a1), Math.abs(b2 - b1)));
			p.liczbakwadratow++;
			p.repaint();
			a1 = 0;
			a2 = 0;
			b1 = 0;
			b2 = 0;
			p.tryb = 0;

		}
		if (p.tryb == 2 && a1 != 0 && a2 != 0) {
			if (a2 < a1) {
				int temp = a2;
				a2 = a1;
				a1 = temp;
			}
			if (b2 < b1) {
				int temp = b2;
				b2 = b1;
				b1 = temp;
			}
			double radius = Math.sqrt(((a1 - a2) * (a1 - a2)) + ((b1 - b2) * (b1 - b2)));
			p.CircleArray.add(new JEllipse(Math.abs(a1 - radius), Math.abs(b1 - radius), 2.0 * radius, 2.0 * radius));
			p.liczbaokregow++;
			p.repaint();
			p.tryb = 0;
			a1 = 0;
			a2 = 0;
			b1 = 0;
			b2 = 0;
			pierwsza = false;
		}

		if (p.tryb == 4) {

			if (p.czarodziejskiekolko.isHit(e.getX(), e.getY()) && p.liczbakresek != 0
					&& p.czarodziejskiekolko != null) {
				p.PolygonArray.get(p.liczbawielokatow).addPoint(p.tutajkolkox, p.tutajkolkoy);
				p.tablicaprzeksztalcen[p.liczbaprzeksztalcen] = new JPolygon();
				p.liczbaprzeksztalcen++;

				p.liczbawielokatow++;
				p.repaint();
				p.tryb = 0;
				p.liczbakresek = 0;
				a1 = 0;
				a2 = 0;
				b1 = 0;
				b2 = 0;

			} else {
				p.PolygonArray.get(p.liczbawielokatow).addPoint(e.getX(), e.getY());
				p.tablicakresek[p.liczbakresek] = new Line2D.Double(p.stadrysujemyx, p.stadrysujemyy, e.getX(),
						e.getY());
				p.liczbakresek++;

				p.stadrysujemyx = e.getX();
				p.stadrysujemyy = e.getY();
			}
		}
		if (p.tryb == 3 && a1 != 0) {

			p.PolygonArray.add(new JPolygon());
			p.stadrysujemyx = a1;
			p.stadrysujemyy = b1;
			p.printCircle(e.getX(), e.getY());
			p.printLine(p.stadrysujemyx, p.stadrysujemyy, e.getX(), e.getY());
			p.czarodziejskiekolko = new JEllipse(a1 - 5, b1 - 5, 10, 10);
			p.tutajkolkox = a1;
			p.tutajkolkoy = b1;
			p.tryb = 4;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {

		doMove(e);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {

		if (p.tryb == 4) {
			p.printLine(p.stadrysujemyx, p.stadrysujemyy, e.getX(), e.getY());
		}

	}

	/**
	 * @param e
	 */
	private void doMove(MouseEvent e) {

		int dx = e.getX() - x;
		int dy = e.getY() - y;

		for (int i = 0; i < p.liczbakwadratow; i++) {
			if (p.RectangleArray.get(i) != null && p.RectangleArray.get(i).active) {
				p.RectangleArray.get(i).addX(dx);
				p.RectangleArray.get(i).addY(dy);
				p.repaint();
			}
		}
		for (int i = 0; i < p.liczbaokregow; i++) {
			if (p.CircleArray.get(i) != null && p.CircleArray.get(i).active) {
				p.CircleArray.get(i).addX(dx);
				p.CircleArray.get(i).addY(dy);
				p.repaint();
			}
		}
		for (int i = 0; i < p.liczbawielokatow; i++) {
			if (p.PolygonArray.get(i) != null && p.PolygonArray.get(i).active) {
				System.out.println(p.liczbawielokatow);
				p.repaint();
			}
		}
		for (int i = 0; i < p.liczbaprzeksztalcen; i++) {
			if (p.tablicaprzeksztalcen[i] != null && p.tablicaaktywnychprzeksztalcen[i] == true) {

				p.PolygonArray.get(i).translate(dx, dy);

				p.repaint();
			}
		}

		x += dx;
		y += dy;
	}
}
