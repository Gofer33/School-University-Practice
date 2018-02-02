/**
 * @author Damian Borek
 * Hare Thread
 */
public class Hare extends Thread {
	int x;
	int y;
	JWolf w;

	/**
	 * @param ww applet
	 * @param xx coordinate
	 * @param yy coordinate
	 */
	public Hare(JWolf ww, int xx, int yy) {
		x = xx;
		y = yy;
		w = ww;

	}

	/**
	 *  This method moves hare when it is in 0x0 corner
	 */
	public void corner00() {
		int move = 0;
		do {
			int ran = w.randomno.nextInt(3);
			if (ran == 0 && w.tab[x + 1][y] == 0) {
				w.tab[x][y] = 0;
				w.tab[x + 1][y] = 2;
				x = x + 1;
				move = 1;
			} else if (ran == 1 && w.tab[x][y + 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x][y + 1] = 2;
				y = y + 1;
				move = 1;
			} else if (ran == 2 && w.tab[x + 1][y + 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x + 1][y + 1] = 2;
				y = y + 1;
				x = x + 1;
				move = 1;
			}
		} while (move != 1);
	}

	/**
	 *  This method moves hare when it is in nxn corner
	 */
	public void cornernm() {
		int move = 0;
		do {
			int ran = w.randomno.nextInt(3);
			if (ran == 0 && w.tab[x - 1][y] == 0) {
				w.tab[x][y] = 0;
				w.tab[x - 1][y] = 2;
				x = x - 1;
				move = 1;
			} else if (ran == 1 && w.tab[x][y - 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x][y - 1] = 2;
				y = y - 1;
				move = 1;
			} else if (ran == 2 && w.tab[x - 1][y - 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x - 1][y - 1] = 2;
				y = y - 1;
				x = x - 1;
				move = 1;
			}
		} while (move != 1);
	}
	/**
	 *  This method moves hare when it is in 0xn corner
	 */
	public void corner0m() {
		int move = 0;
		do {
			int ran = w.randomno.nextInt(3);
			if (ran == 0 && w.tab[x + 1][y] == 0) {
				w.tab[x][y] = 0;
				w.tab[x + 1][y] = 2;
				x = x + 1;
				move = 1;
			} else if (ran == 1 && w.tab[x][y - 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x][y - 1] = 2;
				y = y - 1;
				move = 1;
			} else if (ran == 2 && w.tab[x + 1][y - 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x + 1][y - 1] = 2;
				y = y - 1;
				x = x + 1;
				move = 1;
			}
		} while (move != 1);
	}
	/**
	 *  This method moves hare when it is in nx0 corner
	 */
	public void cornern0() {
		int move = 0;
		do {
			int ran = w.randomno.nextInt(3);
			if (ran == 0 && w.tab[x - 1][y] == 0) {
				w.tab[x][y] = 0;
				w.tab[x - 1][y] = 2;
				x = x - 1;
				move = 1;
			} else if (ran == 1 && w.tab[x][y + 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x][y + 1] = 2;
				y = y + 1;
				move = 1;
			} else if (ran == 2 && w.tab[x - 1][y + 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x - 1][y + 1] = 2;
				y = y + 1;
				x = x - 1;
				move = 1;
			}
		} while (move != 1);
	}
	/**
	 *  This method moves hare when it is in near left edge
	 */
	public void x0y() {
		int move = 0;
		do {
			int ran = w.randomno.nextInt(5);
			if (ran == 0 && w.tab[x + 1][y] == 0) {
				w.tab[x][y] = 0;
				w.tab[x + 1][y] = 2;
				x = x + 1;
				move = 1;
			} else if (ran == 1 && w.tab[x][y + 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x][y + 1] = 2;
				y = y + 1;
				move = 1;
			} else if (ran == 2 && w.tab[x][y - 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x][y - 1] = 2;
				y = y - 1;
				move = 1;
			} else if (ran == 3 && w.tab[x + 1][y - 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x + 1][y - 1] = 2;
				x = x + 1;
				y = y - 1;
				move = 1;
			} else if (ran == 4 && w.tab[x + 1][y + 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x + 1][y + 1] = 2;
				x = x + 1;
				y = y + 1;
				move = 1;
			}
		} while (move != 1);
	}
	/**
	 *  This method moves hare when it is in near right edge
	 */
	public void xmaxy() {
		int move = 0;
		do {
			int ran = w.randomno.nextInt(5);
			if (ran == 0 && w.tab[x - 1][y] == 0) {
				w.tab[x][y] = 0;
				w.tab[x - 1][y] = 2;
				x = x - 1;
				move = 1;
			} else if (ran == 1 && w.tab[x][y + 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x][y + 1] = 2;
				y = y + 1;
				move = 1;
			} else if (ran == 2 && w.tab[x][y - 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x][y - 1] = 2;
				y = y - 1;
				move = 1;
			} else if (ran == 3 && w.tab[x - 1][y - 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x - 1][y - 1] = 2;
				x = x - 1;
				y = y - 1;
				move = 1;
			} else if (ran == 4 && w.tab[x - 1][y + 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x - 1][y + 1] = 2;
				x = x - 1;
				y = y + 1;
				move = 1;
			}
		} while (move != 1);

	}
	/**
	 *  This method moves hare when it is in near top edge
	 */
	public void xy0() {
		int move = 0;
		do {
			int ran = w.randomno.nextInt(5);
			if (ran == 0 && w.tab[x][y + 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x][y + 1] = 2;
				y = y + 1;
				move = 1;
			} else if (ran == 1 && w.tab[x + 1][y] == 0) {
				w.tab[x][y] = 0;
				w.tab[x + 1][y] = 2;
				x = x + 1;
				move = 1;
			} else if (ran == 2 && w.tab[x - 1][y] == 0) {
				w.tab[x][y] = 0;
				w.tab[x - 1][y] = 2;
				x = x - 1;
				move = 1;
			} else if (ran == 3 && w.tab[x + 1][y + 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x + 1][y + 1] = 2;
				x = x + 1;
				y = y + 1;
				move = 1;
			} else if (ran == 4 && w.tab[x - 1][y + 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x - 1][y + 1] = 2;
				x = x - 1;
				y = y + 1;
				move = 1;
			}
		} while (move != 1);

	}
	/**
	 *  This method moves hare when it is in near bottom edge
	 */
	public void xymax() {
		int move = 0;
		do {
			int ran = w.randomno.nextInt(5);
			if (ran == 0 && w.tab[x][y - 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x][y - 1] = 2;
				y = y - 1;
				move = 1;
			} else if (ran == 1 && w.tab[x + 1][y] == 0) {
				w.tab[x][y] = 0;
				w.tab[x + 1][y] = 2;
				x = x + 1;
				move = 1;
			} else if (ran == 2 && w.tab[x - 1][y] == 0) {
				w.tab[x][y] = 0;
				w.tab[x - 1][y] = 2;
				x = x - 1;
				move = 1;
			} else if (ran == 3 && w.tab[x + 1][y - 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x + 1][y - 1] = 2;
				x = x + 1;
				y = y - 1;
				move = 1;
			} else if (ran == 4 && w.tab[x - 1][y - 1] == 0) {
				w.tab[x][y] = 0;
				w.tab[x - 1][y - 1] = 2;
				x = x - 1;
				y = y - 1;
				move = 1;
			}
		} while (move != 1);

	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * this function works, when thread is started
	 * it moves hare
	 */
	public void run() {

		while (true) {
			double min = w.time * 0.5;
			double max = w.time * 1.5;
			try {
				double bla = w.randomno.nextInt((int) (max - min + 1)) + min;
				sleep((long) bla);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (w.tab[x][y] == 2) {
				if (x == 0 && y != 0 && y != w.m - 1) {
					x0y();
				} else if (x != 0 && y == 0 && x != w.n - 1) {
					xy0();
				} else if (x == w.n - 1 && y != 0 && y != w.m - 1) {
					xmaxy();
				} else if (x != 0 && y == w.m - 1 && x != w.n - 1) {
					xymax();
				} else if (x == 0 && y == 0) {
					corner00();
				} else if (x == w.n - 1 && y == w.m - 1) {
					cornernm();
				} else if (x == 0 && y == w.m - 1) {
					corner0m();
				} else if (x == w.n - 1 && y == 0) {
					cornern0();
				} else if (w.wolfx > x && w.wolfy > y) {

					if (w.tab[x - 1][y - 1] == 0) {
						w.tab[x][y] = 0;
						w.tab[x - 1][y - 1] = 2;
						x = x - 1;
						y = y - 1;
					}

				} else if (w.wolfx < x && w.wolfy < y) {

					if (w.tab[x + 1][y + 1] == 0) {
						w.tab[x][y] = 0;
						w.tab[x + 1][y + 1] = 2;
						x = x + 1;
						y = y + 1;
					}

				} else if (w.wolfx > x && w.wolfy < y) {
					if (w.tab[x - 1][y + 1] == 0) {
						w.tab[x][y] = 0;
						w.tab[x - 1][y + 1] = 2;
						x = x - 1;
						y = y + 1;
					}

				} else if (w.wolfx < x && w.wolfy > y) {

					if (w.tab[x + 1][y - 1] == 0) {
						w.tab[x][y] = 0;
						w.tab[x + 1][y - 1] = 2;
						y = y - 1;
						x = x + 1;
					}
				}

				else if (w.wolfx == x && w.wolfy > y) {

					if (w.tab[x][y - 1] == 0) {
						w.tab[x][y] = 0;
						w.tab[x][y - 1] = 2;
						y = y - 1;
					}

				} else if (w.wolfx > x && w.wolfy == y) {
					if (w.tab[x - 1][y] == 0) {
						w.tab[x][y] = 0;
						w.tab[x - 1][y] = 2;
						x = x - 1;
					}

				} else if (w.wolfx < x && w.wolfy == y) {
					if (w.tab[x + 1][y] == 0) {
						w.tab[x][y] = 0;
						w.tab[x + 1][y] = 2;
						x = x + 1;
					}

				} else if (w.wolfx == x && w.wolfy < y) {
					if (w.tab[x][y + 1] == 0) {
						w.tab[x][y] = 0;
						w.tab[x][y + 1] = 2;
						y = y + 1;
					}

				}

				w.repaint();
				Thread.yield();

			} else {
				try {
					this.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
}