package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Damian Borek
 * @version 0.99
 */
public class client extends JApplet {
	static JPanel rys = new JPanel();
	PrintStream p;
	Scanner sc;
	Graphics2D g2;

	/* (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		super.paint(((Graphics2D) g));
		g.clearRect(0, 0, rys.getWidth(), rys.getHeight());

	}

	/**
	 * @param pp PrintStream object
	 * @param s Scanner object
	 * 
	 * initialising applet
	 */
	public void init(PrintStream pp, Scanner s) {
		this.add(rys);
		p = pp;
		sc = s;
	}

	/**
	 *  cleaning panel
	 */
	public void clean() {
		Graphics g2 = rys.getGraphics();
		g2.clearRect(0, 0, rys.getWidth(), rys.getHeight());
	}

	/**
	 * @param tree Binary tree written in string 
	 * @param x coordinate
	 * @param y coordinate
	 * @param size lenght of line which is connecting circles
	 * @param step1 
	 * @param step2
	 *  painting tree
	 */
	public void painttree(String tree, int x, int y, int size, int step1, int step2) {

		Graphics g2 = rys.getGraphics();
		if (tree.length() > 1) {
			int width = 35;
			int height = 35;
			String temp;
			int left = 0;
			int right = 0;
			String key = "";
			while (tree.charAt(0) != '|') {
				key = key + tree.charAt(0);
				tree = tree.substring(1);
			}
			g2.setColor(Color.gray);
			p.println(69);
			p.println(key);

			temp = sc.nextLine();
			int temp2 = Integer.parseInt(temp) + 1;
			if (tree.charAt(1) == '1') {

				g2.drawLine(x, y, (int) (x - (30 * Math.pow(2, size - temp2))),
						(int) (y + (30 * Math.pow(2, size - temp2))));
				left = 1;
			}
			if (tree.charAt(2) == '1') {

				g2.drawLine(x, y, (int) (x + (30 * Math.pow(2, size - temp2))),
						(int) (y + (30 * Math.pow(2, size - temp2))));
				right = 1;
			}

			g2.fillOval(x - height / 2, y - width / 2, width, height);
			g2.setColor(Color.black);
			FontMetrics fm = g2.getFontMetrics();
			double textWidth = fm.getStringBounds("Bla", g2).getWidth();
			g2.drawString(key, (int) (x - textWidth / 2), (int) (y + fm.getMaxAscent() / 2));
			if (left == 1) {
				painttree(tree.substring(3), (int) (x - (30 * Math.pow(2, size - temp2))),
						(int) (y + (30 * Math.pow(2, size - temp2))), size, ++step1, step2);
			}
			if (right == 1) {
				int i = 0;
				int go = 0;
				while (true) {
					if (tree.charAt(i) == '|') {
						if (tree.charAt(i + 2) == '1') {
							go++;
						}
						if (tree.charAt(i + 1) == '0') {
							go--;
							if (go == 0) {
								painttree(tree.substring(i + 3), (int) (x + (30 * Math.pow(2, size - temp2))),
										(int) (y + (30 * Math.pow(2, size - temp2))), size, step1, ++step2);
								break;
							} else
								i++;
						} else
							i++;
					} else
						i++;
				}
			}
		}
	}

	/**
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 * main function
	 */
	public static void main(String args[]) throws UnknownHostException, IOException {

		int number;
		String temp;
		Scanner sc = new Scanner(System.in);
		Socket s = new Socket("127.0.0.1", 420);
		Scanner sc1 = new Scanner(s.getInputStream());
		PrintStream p = new PrintStream(s.getOutputStream());

		MyFrame frame1 = new MyFrame();
		JTextArea textarea = new JTextArea("Wynik");
		JButton insertButton = new JButton("Insert");
		JButton drawButton = new JButton("Draw");
		JButton searchButton = new JButton("Search");
		JButton stringButton = new JButton("String");
		JButton integerButton = new JButton("Integer");
		JButton doubleButton = new JButton("Double");
		JButton deleteButton = new JButton("Delete");
		client applet = new client();
		JTextField textfield = new JTextField("Enter number");
		JPanel pan = new JPanel();

		frame1.setLayout(new BorderLayout());
		frame1.add(textarea);
		frame1.add(rys);
		frame1.add(pan, BorderLayout.NORTH);
		textfield.setSize(200, 200);
		pan.add(textfield);
		pan.add(insertButton);
		pan.add(searchButton);
		pan.add(deleteButton);
		pan.add(drawButton);
		pan.add(integerButton);
		pan.add(stringButton);
		pan.add(doubleButton);
		frame1.add(applet);
		applet.init(p, sc1);
		frame1.setVisible(true);

		integerButton.addActionListener(new MyActionListenerType(7, p));
		doubleButton.addActionListener(new MyActionListenerType(8, p));
		stringButton.addActionListener(new MyActionListenerType(9, p));
		drawButton.addActionListener(new MyActionListenertest(p, sc1, textarea, textfield, applet));
		insertButton.addActionListener(new MyActionListener1(p, sc1, textarea, textfield, applet));
		searchButton.addActionListener(new MyActionListener2(p, sc1, textarea, textfield));
		deleteButton.addActionListener(new MyActionListener3(p, sc1, textarea, textfield, applet));
	}
}
