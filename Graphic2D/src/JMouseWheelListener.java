import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


/**
 * @author Damian Borek
 *
 */
class JMouseWheelListener implements MouseWheelListener {

	JPaint p;

	/**
	 * @param l
	 */
	public JMouseWheelListener(JPaint l) {
		p = l;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.MouseWheelEvent)
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		doScale(e);

	}
	/**
	 * @param e
	 */
	private void doScale(MouseWheelEvent e) {

		int x = e.getX();
		int y = e.getY();

		if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

			for (int i = 0; i < p.liczbakwadratow; i++) {
				if (p.RectangleArray.get(i) != null && p.RectangleArray.get(i).active) {

					float amount = e.getWheelRotation() * 5f;
					p.RectangleArray.get(i).addWidth(amount);
					p.RectangleArray.get(i).addHeight(amount);
					p.repaint();
				}
			}
			for (int i = 0; i < p.liczbawielokatow; i++) {
				if (p.PolygonArray.get(i) != null && p.PolygonArray.get(i).active) {

					p.deltax += e.getWheelRotation() * (float) 0.1;
					p.repaint();

				}
			}
			for (int i = 0; i < p.liczbaokregow; i++) {
				if (p.CircleArray.get(i) != null && p.CircleArray.get(i).active) {

					float amount = e.getWheelRotation() * 5f;
					p.CircleArray.get(i).addWidth(amount);
					p.CircleArray.get(i).addHeight(amount);
					p.repaint();
				}
			}

		}
	}
}