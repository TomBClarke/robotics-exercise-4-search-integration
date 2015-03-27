package robotSearches;

import rp.util.SimpleSet;

/**
 * Creates a node, each node can store content of any element, and each node may
 * also have successors which are the other nodes that node is connected to.
 * 
 * @author Kyle Allen-Taylor
 *
 * @param <A>
 *            The element of the contents of the node.
 */
public class Node<A> {

	private A contents;
	private SimpleSet<Node<A>> successors;

	/**
	 * Constructs the node, contents are required here.
	 * 
	 * @param contents
	 *            The contents of the node which is being constructed.
	 */
	public Node(A contents) {
		this.contents = contents;
		this.successors = new SimpleSet<Node<A>>();
	}

	public String toString() {
		return "Node: (" + ((Coordinate) contents()).x() + ","
				+ ((Coordinate) contents()).y() + ")";
	}

	/**
	 * Sets the successors of a particular node.
	 * 
	 * @param s
	 *            The node to be added as a successor.
	 */
	public void addSuccessor(Node<A> s) {
		successors.add(s);
	}

	/**
	 * Returns whether or not the contents of a node is equal to other content.
	 * 
	 * @param c
	 *            The content we wish to compare the current node's content
	 *            with.
	 * @return Whether or not the contents are equal.
	 */
	public boolean contentsEquals(A c) {
		return contents.equals(c);
	}

	/**
	 * Returns the contents of the node.
	 * 
	 * @return The contents of the node.
	 */
	public A contents() {
		return contents;
	}

	/**
	 * Returns the successors of the node.
	 * 
	 * @return The successors of the node.
	 */
	public SimpleSet<Node<A>> successors() {
		return successors;
	}
}
