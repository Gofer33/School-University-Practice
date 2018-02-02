
import java.awt.geom.Rectangle2D;

/**
 * @author Damian Borek
 * 
 */
public class JRectangle extends Rectangle2D.Float {
	public JRectangle() {
		setRect(0, 0, 0, 0);
	}

	/**
	 * @param x coordinate x
	 * @param y coordinate y
	 * @param width width of the rectangle
	 * @param height height of the rectangle
	 */
	public JRectangle(float x, float y, float width, float height) {
		setRect(x, y, width, height);
	}

}
