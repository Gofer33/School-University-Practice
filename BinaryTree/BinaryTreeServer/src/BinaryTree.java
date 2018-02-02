import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 * 
 * @author Damian Borek
 * @version 0.99
 * @param <T>
 */
class BST<T extends Comparable<T>> {
	public static String drzewo = "";
	public Node root = null;
	public PrintStream p;
	/**
	 * 
	 * @param pp PrintStream object
	 * constructor
	 */
	public BST(PrintStream pp) {
		p = pp;
	}
	/**
	 * 
	 * @author Damian Borek
	 * class node
	 */
	public class Node {
		public T key;
		Node left, right, parent = null;

		Node(T key) {
			this.key = key;
		}
	}
	/**
	 * 
	 * @param key value of node which we want insert to binary tree
	 * inserts a node to binary tree
	 */
	public void insert(T key) {
		if (root == null)
			root = new Node(key);
		else {
			if (search(key) == false) {
				Node actual = root;
				Node parent = null;
				while (actual != null) {
					parent = actual;
					compareTo(actual.key, key);
					if (compareTo(actual.key, key) > 0) {
						actual = actual.left;
					} else
						actual = actual.right;
				}
				if (compareTo(parent.key, key) > 0) {//
					parent.left = new Node(key);
					parent.left.parent = parent;
				} else {
					parent.right = new Node(key);
					parent.right.parent = parent;
				}
			} else {

			}
		}
	}
	/**
	 * 
	 * @param aNode node which height we want know
	 * @return value of height of aNode
	 */
	int findHeight(Node aNode) {
		if (aNode == null) {
			return -1;
		}

		int lefth = findHeight(aNode.left);
		int righth = findHeight(aNode.right);

		if (lefth > righth) {
			return lefth + 1;
		} else {
			return righth + 1;
		}
	}
	/**
	 * 
	 * @param key value of node we want to find
	 * @return true of that node is in tree or false if it isnt
	 */
	public boolean search(T key) {
		Node actual = root;
		while (actual != null && compareTo(actual.key, key) != 0)// actual.key
																	// != key
			if (compareTo(actual.key, key) > 0) {
				actual = actual.left;
			} else
				actual = actual.right;

		if (actual == null)
			return false;
		return true;
	}
	/**
	 * 
	 * @param key value of key which depth we want know
	 * @return depth of that node
	 */
	public int getDepth(T key) {
		Node actual = root;
		int i = 0;
		while (actual != null && compareTo(actual.key, key) != 0) {
			if (compareTo(actual.key, key) > 0) {
				actual = actual.left;
			} else
				actual = actual.right;
			i++;
		}

		return i;
	}
	/**
	 * 
	 * @param key value of node which successor we want to know
	 * @return succesor of that node
	 */
	private Node successor(T key) {
		Node node = this.search1(key);
		if (node.right != null) {
			node = node.right;
			while (node.left != null)
				node = node.left;
			return node;
		} else if (node.right == null && node != root) {
			Node parent = node.parent;
			while (parent != root && compareTo(parent.key, node.key) < 0)
				parent = parent.parent;
			return parent;
		}
		return node;
	}
	/**
	 * 
	 * @param key value of key which we want find
	 * @return node that we are looking for
	 */
	public Node search1(T key) {
		Node actual = root;
		while (actual != null && compareTo(actual.key, key) != 0)
			if (compareTo(actual.key, key) > 0) {
				actual = actual.left;
			} else
				actual = actual.right;

		return actual;
	}
	/**
	 * 
	 * @param a first value
	 * @param b second value
	 * @return negative integer if a>b, positive integer if b>a, 0 if a==b
	 */
	public int compareTo(T a, T b) {
		return a.compareTo(b);
	}
	/**
	 * 
	 * @param node node which we start from
	 * creates string of binary tree
	 */
	public void preOrderMod(Node node) {

		if (node != null) {
			drzewo = drzewo + node.key + "|"; // Integer.toString(node.key)
			if (node.left != null)
				drzewo = drzewo + "1";
			else
				drzewo = drzewo + "0";
			if (node.right != null)
				drzewo = drzewo + "1";
			else
				drzewo = drzewo + "0";

			preOrderMod(node.left);
			preOrderMod(node.right);
		}
	}
	/**
	 * 
	 * @param key value of node which we want to delete
	 * @return node which suposed to be deleted
	 */
	public Node delete(T key) {
		if (search(key) == false)
			return null;
		else {
			Node node = search1(key);
			Node parent = node.parent;
			Node tmp;
			if (node.left != null && node.right != null) {
				tmp = delete(successor(key).key);
				tmp.left = node.left;
				if (tmp.left != null)
					tmp.left.parent = tmp;
				tmp.right = node.right;
				if (tmp.right != null)
					tmp.right.parent = tmp;
			} else {
				if (node.left != null)
					tmp = node.left;
				else
					tmp = node.right;
			}
			if (tmp != null)
				node.parent = parent;
			if (parent == null)
				root = tmp;
			else if (parent.left == node)
				parent.left = tmp;
			else
				parent.right = tmp;
			return node;
		}
	}

}
/**
 * 
 * @author Damian Borek
 *
 */
public class BinaryTree {

	public static void main(String[] args) throws IOException {
		int temp;
		String number;
		ServerSocket s1 = new ServerSocket(420);
		Socket ss = s1.accept();
		Scanner sc = new Scanner(ss.getInputStream());
		PrintStream p = new PrintStream(ss.getOutputStream());
		int flaga = 2;
		BST<String> drzewo1 = new BST<String>(p);
		BST<Integer> drzewo2 = new BST<Integer>(p);
		BST<Double> drzewo3 = new BST<Double>(p);
		while (true) {
			number = sc.nextLine();

			if (number.equals("7") == true) {
				flaga = 1;
			} else if (number.equals("8") == true) {
				flaga = 2;
			} else if (number.equals("9") == true) {
				flaga = 3;
			}

			else if (number.equals("1") == true) {
				if (flaga == 3) {
					drzewo1.preOrderMod(drzewo1.root);
					p.println(BST.drzewo);
					p.println(drzewo1.findHeight(drzewo1.root));
					BST.drzewo = "";
				}
				if (flaga == 2) {
					drzewo3.preOrderMod(drzewo3.root);
					p.println(BST.drzewo);
					p.println(drzewo3.findHeight(drzewo3.root));
					BST.drzewo = "";
				}
				if (flaga == 1) {
					drzewo2.preOrderMod(drzewo2.root);
					p.println(BST.drzewo);
					p.println(drzewo2.findHeight(drzewo2.root));
					BST.drzewo = "";
				}
			} else if (number.equals("2") == true) {
				if (flaga == 1) {
					int number1;
					number1 = sc.nextInt();
					drzewo2.insert(number1);
					drzewo2.preOrderMod(drzewo2.root);
					p.println(BST.drzewo);
					p.println(drzewo2.findHeight(drzewo2.root));
				}
				if (flaga == 3) {
					String number1;
					number1 = sc.nextLine();
					drzewo1.insert(number1);
					drzewo1.preOrderMod(drzewo1.root);
					p.println(BST.drzewo);
					p.println(drzewo1.findHeight(drzewo1.root));
				}
				if (flaga == 2) {
					double number1;
					number1 = sc.nextDouble();
					drzewo3.insert(number1);
					drzewo3.preOrderMod(drzewo3.root);
					p.println(BST.drzewo);
					p.println(drzewo3.findHeight(drzewo3.root));
				}

				BST.drzewo = "";
			} else if (number.equals("69") == true) {
				if (flaga == 1) {
					int number1;
					number1 = sc.nextInt();
					p.println(drzewo2.getDepth(number1));
				}
				if (flaga == 3) {
					String number1;
					number1 = sc.nextLine();
					p.println(drzewo1.getDepth(number1));
				}
				if (flaga == 2) {
					Double number1;
					number1 = sc.nextDouble();
					p.println(drzewo3.getDepth(number1));
				}

			} else if (number.equals("3") == true) {
				if (flaga == 1) {
					int number1;
					number1 = sc.nextInt();
					if (drzewo2.search(number1)) {
						p.println("FOUND!");
					} else
						p.println("NOT FOUND!");
				}
				if (flaga == 3) {
					String number1;
					number1 = sc.nextLine();
					if (drzewo1.search(number1)) {
						p.println("FOUND!");
					} else
						p.println("NOT FOUND!");
				}
				if (flaga == 2) {
					Double number1;
					number1 = sc.nextDouble();
					if (drzewo3.search(number1)) {
						p.println("FOUND!");
					} else
						p.println("NOT FOUND!");
				}

			} else if (number.equals("4") == true) {
				if (flaga == 1) {
					int number1;
					number1 = sc.nextInt();
					if (drzewo2.delete(number1) == null) {
						drzewo2.preOrderMod(drzewo2.root);
						p.println(BST.drzewo);
						p.println(drzewo2.findHeight(drzewo2.root));
					} else {
						drzewo2.preOrderMod(drzewo2.root);
						p.println(BST.drzewo);
						p.println(drzewo2.findHeight(drzewo2.root));
					}
				}
				if (flaga == 3) {
					String number1;
					number1 = sc.nextLine();
					if (drzewo1.delete(number1) == null) {
						drzewo1.preOrderMod(drzewo1.root);
						p.println(BST.drzewo);
						p.println(drzewo1.findHeight(drzewo1.root));
					} else {
						drzewo1.preOrderMod(drzewo1.root);
						p.println(BST.drzewo);
						p.println(drzewo1.findHeight(drzewo1.root));
					}

				}
				if (flaga == 2) {
					Double number1;
					number1 = sc.nextDouble();
					if (drzewo3.delete(number1) == null) {
						drzewo3.preOrderMod(drzewo3.root);
						p.println(BST.drzewo);
						p.println(drzewo3.findHeight(drzewo3.root));
					} else {
						drzewo3.preOrderMod(drzewo3.root);
						p.println(BST.drzewo);
						p.println(drzewo3.findHeight(drzewo3.root));
					}

				}

				BST.drzewo = "";
			}

		}
	}

}
