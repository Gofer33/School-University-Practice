import javax.swing.JOptionPane;

/**
 * @author Damian Borek
 * Wolf Thread, makes Wolf chasing hares
 */
public class Wolf extends Thread {
	JWolf w;

	/**
	 * Constructor
	 * @param ww applet
	 */
	public Wolf(JWolf ww) {
		w = ww;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * main method, it is working just after starting program, it makes wolf chasing hares
	 */
	public void run() {
		while (true) {
			double min = w.time * 0.5;
			double max = w.time * 1.5;
			try {
				double bla = w.randomno.nextInt((int) (max - min + 1)) + min;
				// System.out.println(bla);
				sleep((long) bla);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int x = 10000, y = 10000;
			int x1 = 0, y1 = 0;
			int rest = 0;

			int closestrabbitx = 0;
			int closestrabbity = 0;
			for (int i = 0; i < w.n; i++) {
				for (int j = 0; j < w.m; j++) {
					if (w.tab[i][j] == 2) {
						x1 = Math.abs(w.wolfx - i);
						y1 = Math.abs(w.wolfy - j);
						if (x1 + y1 < x + y) {

							x = x1;
							y = y1;
							closestrabbitx = i;
							closestrabbity = j;

						}
					}

				}
			}

			if (w.wolfx - closestrabbitx < 0 && w.wolfy - closestrabbity < 0) {
				w.tab[w.wolfx][w.wolfy] = 0;
				if (w.tab[w.wolfx + 1][w.wolfy + 1] == 2)
					rest = 1;
				w.tab[w.wolfx + 1][w.wolfy + 1] = 1;
				w.wolfx = w.wolfx + 1;
				w.wolfy = w.wolfy + 1;
			} else if (w.wolfx - closestrabbitx > 0 && w.wolfy - closestrabbity > 0) {
				w.tab[w.wolfx][w.wolfy] = 0;
				if (w.tab[w.wolfx - 1][w.wolfy - 1] == 2)
					rest = 1;
				w.tab[w.wolfx - 1][w.wolfy - 1] = 1;
				w.wolfx = w.wolfx - 1;
				w.wolfy = w.wolfy - 1;
			} else if (w.wolfx - closestrabbitx < 0 && w.wolfy - closestrabbity > 0) {
				w.tab[w.wolfx][w.wolfy] = 0;
				if (w.tab[w.wolfx + 1][w.wolfy - 1] == 2)
					rest = 1;
				w.tab[w.wolfx + 1][w.wolfy - 1] = 1;
				w.wolfx = w.wolfx + 1;
				w.wolfy = w.wolfy - 1;
			} else if (w.wolfx - closestrabbitx > 0 && w.wolfy - closestrabbity < 0) {
				w.tab[w.wolfx][w.wolfy] = 0;
				if (w.tab[w.wolfx - 1][w.wolfy + 1] == 2)
					rest = 1;
				w.tab[w.wolfx - 1][w.wolfy + 1] = 1;
				w.wolfx = w.wolfx - 1;
				w.wolfy = w.wolfy + 1;
			} else if (w.wolfx - closestrabbitx < 0 && w.wolfy - closestrabbity == 0) {
				w.tab[w.wolfx][w.wolfy] = 0;
				if (w.tab[w.wolfx + 1][w.wolfy] == 2)
					rest = 1;
				w.tab[w.wolfx + 1][w.wolfy] = 1;
				w.wolfx = w.wolfx + 1;
			} else if (w.wolfx - closestrabbitx == 0 && w.wolfy - closestrabbity < 0) {
				w.tab[w.wolfx][w.wolfy] = 0;
				if (w.tab[w.wolfx][w.wolfy + 1] == 2)
					rest = 1;
				w.tab[w.wolfx][w.wolfy + 1] = 1;
				w.wolfy = w.wolfy + 1;
			} else if (w.wolfx - closestrabbitx == 0 && w.wolfy - closestrabbity > 0) {
				w.tab[w.wolfx][w.wolfy] = 0;
				if (w.tab[w.wolfx][w.wolfy - 1] == 2)
					rest = 1;
				w.tab[w.wolfx][w.wolfy - 1] = 1;
				w.wolfy = w.wolfy - 1;
			} else if (w.wolfx - closestrabbitx > 0 && w.wolfy - closestrabbity == 0) {
				w.tab[w.wolfx][w.wolfy] = 0;
				if (w.tab[w.wolfx - 1][w.wolfy] == 2)
					rest = 1;
				w.tab[w.wolfx - 1][w.wolfy] = 1;
				w.wolfx = w.wolfx - 1;
			}
			if (rest == 1) {

				w.finish--;
				if (w.finish == 0) {
					w.repaint();
					JOptionPane.showMessageDialog(null, "Koniec!");
					try {
						this.finalize();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.exit(0);
				}
				try {
					sleep(5 * w.time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			w.repaint();
			Thread.yield();

		}
	}

}