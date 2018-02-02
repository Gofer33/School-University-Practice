import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * @author Damian Borek
 *
 */
public class JRectangle extends Rectangle2D.Float {
	public boolean active = false;

	float a, b, c, d;
	Color kolor;

	/**
	 * 
	 */
	public JRectangle() {

		setRect(0, 0, 0, 0);
		kolor = new Color(0, 0, 200);
		active = false;

	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public JRectangle(float x, float y, float width, float height) {

		setRect(x, y, width, height);
		a = x;
		b = y;
		c = width;
		d = height;
		kolor = new Color(0, 0, 200);

	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isHit(float x, float y) {

		return getBounds2D().contains(x, y);

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
