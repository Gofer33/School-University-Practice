import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * @author Damian Borek
 *
 */
public class MyJPanel extends JPanel {

	/**
	 * @param p
	 */
	public MyJPanel(JPaint p) {

		JMouseAdapter ma = new JMouseAdapter(p);
		addMouseMotionListener(ma);
		addMouseListener(ma);
		addMouseWheelListener(new JMouseWheelListener(p));

		setLayout(new BorderLayout());


		JPanel ster = new JPanel();
		ster.setLayout(new GridLayout(1, 2));

		p.info = new MyJButton(p, "Info", 0);
		p.info.addActionListener(p.info);
		ster.add(p.info);

		p.save = new MyJButton(p, "Save", 6);
		p.save.addActionListener(p.save);
		ster.add(p.save);

		p.load = new MyJButton(p, "Load", 7);
		p.load.addActionListener(p.load);
		ster.add(p.load);

		p.rectangle = new MyJButton(p, "Rectangle", 1);
		p.rectangle.addActionListener(p.rectangle);
		ster.add(p.rectangle);

		p.circle = new MyJButton(p, "Circle", 2);
		p.circle.addActionListener(p.circle);
		ster.add(p.circle);

		p.polygon = new MyJButton(p, "Polygon", 3);
		p.polygon.addActionListener(p.polygon);
		ster.add(p.polygon);
		add("South", ster);

		p.rys = new JPanel(true);
		add("Center", p.rys);

		setSize(1280, 768);
	}
}