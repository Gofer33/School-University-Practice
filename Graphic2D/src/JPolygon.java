import java.awt.Color;
import java.awt.Polygon;

/**
 * @author Damian Borek
 *
 */
public class JPolygon extends Polygon {

	public boolean active = false;

	Color kolor = new Color(0, 0, 200);

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isHit(float x, float y) {

		return contains(x, y);

	}

}
