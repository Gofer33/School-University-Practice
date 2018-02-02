import java.awt.Color;
import java.awt.geom.Ellipse2D;

/**
 * @author Damian Borek
 *
 */
public class JEllipse extends Ellipse2D.Float {
	public boolean active = false;
	Color kolor = new Color(0, 0, 200);

	public JEllipse(double d, double e, double f, double g) {

		setFrame(d, e, f, g);

	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isHit(float x, float y) {

		return contains(x, y);
	}

	/**
	 * @param x
	 */
	public void addX(float x) {

		this.x += x;
	}

	/**
	 * @param y
	 */
	public void addY(float y) {

		this.y += y;
	}

	/**
	 * @param w
	 */
	public void addWidth(float w) {

		this.width += w;
	}

	/**
	 * @param h
	 */
	public void addHeight(float h) {

		this.height += h;
	}
}
