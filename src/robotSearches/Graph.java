package robotSearches;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import ilist.*;
import rp.util.*;
import maybe.*;

// We represent a graph as a set of nodes. 
// This is a minimal class so that a graph can be created.

/**The Graph class allows a graph of coordinates to be entered and then searched. Returning paths and nodes using Depth First Search, Breadth First Search and A* Search.
 * @author Kyle Allen-Taylor
 * @param <A> Element the graph will consist of.
 */
public class Graph<A> {

	// Keep the implementation of sets open, by using the Set interface:
	private SimpleSet<Node<A>> nodes;

	// Constructs the empty graph:
	/**
	 * Constructs the graph using a linkedHashSet to store the nodes.
	 */
	public Graph() {
		nodes = new SimpleSet<Node<A>>();
	}

	// Get method:
	/**Returns the nodes within the graph
	 * @return The nodes within the graph
	 */
	public SimpleSet<Node<A>> nodes() {
		return nodes;
	}

	// Finds or else creates a node with a given contents c:
	/** Returns the node with specified coordinates from the graph.
	 * @param c The coordinates of the node we wish to search for.
	 * @return The node with the coordinates we was searching for.
	 */
	public Node<A> nodeWith(A c) {
		for (Node<A> node : nodes) { // Inefficient for large graph.
			if (node.contentsEquals(c))
				return node; // Found.
		}
		// Not found, hence create it:
		Node<A> node = new Node<A>(c);
		nodes.add(node);
		return node;
	}
	
	/**
	 * Finds a path from a start node to a target one.
	 * 
	 * @param x The start node.
	 * @param p The predicate the end node must satisfy.
	 * @return The list of nodes to pass through to get to the target, or nothing if it can't be reached.
	 */
	public Maybe<IList<Node<A>>> findPathFrom(Node<A> x, Node<A> p) {
		Queue<Node<A>> frontier = new Queue<Node<A>>();
		SimpleSet<Node<A>> visited = new SimpleSet<Node<A>>();
		@SuppressWarnings("deprecation")
		Map<Node<A>,Node<A>> path = new HashMap<Node<A>,Node<A>>();
		
		frontier.addElement(x);;
		while (!frontier.isEmpty()) {
			@SuppressWarnings("unchecked")
			Node<A> y = (Node<A>) frontier.pop();
			if (!visited.contains(y)) {
				if (p.contents().equals(y.contents())) {
					IList<Node<A>> pathList = new Cons<Node<A>>(y, new Nil<Node<A>>());
					while(!path.get(y).equals(x)){
						pathList = pathList.append(path.get(y));
						y = path.get(y);
					}
					pathList = pathList.append(x);
					pathList = pathList.reverse();
					return new Just<IList<Node<A>>>(pathList);
				}
				
				visited.add(y);
				
				if(!y.successors().isEmpty()){
					for(Node<A> n : y.successors()){
						if(!visited.contains(n)){
							frontier.push(n);
							path.put(n, y);
						}
					}
				} else {
					path.remove(y);
				}
			}
		}

		return new Nothing<IList<Node<A>>>();
	}	

}
