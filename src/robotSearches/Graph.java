package robotSearches;

import java.util.HashMap;
import java.util.Map;

import ilist.*;
import rp.util.*;

/**
 * The Graph class allows a graph of coordinates to be entered and then searched. Returning paths and nodes using Depth First Search, Breadth First Search and A* Search.
 * 
 * @author Kyle Allen-Taylor
 * @param <A> Element the graph will consist of.
 */
@SuppressWarnings("deprecation")
public class Graph<A> {

	private SimpleSet<Node<A>> nodes;

	/**
	 * Constructs the graph using a linkedHashSet to store the nodes.
	 */
	public Graph() {
		nodes = new SimpleSet<Node<A>>();
	}

	/**Returns the nodes within the graph
	 * @return The nodes within the graph
	 */
	public SimpleSet<Node<A>> nodes() {
		return nodes;
	}

	/** Returns the node with specified coordinates from the graph.
	 * @param c The coordinates of the node we wish to search for.
	 * @return The node with the coordinates we was searching for.
	 */
	public Node<A> nodeWith(A c) {
		for (Node<A> node : nodes) {
			if (node.contentsEquals(c))
				return node;
		}
		
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
	public IList<Node<A>> findPathFrom(Node<A> x, Node<A> p, IQueueContainer<A> frontier) {
		SimpleSet<Node<A>> visited = new SimpleSet<Node<A>>();
		@SuppressWarnings("deprecation")
		Map<Node<A>,Node<A>> path = new HashMap<Node<A>,Node<A>>();
		
		frontier.add(x);
		while (!frontier.isEmpty()) {
			Node<A> y = (Node<A>) frontier.poll();
			if (!visited.contains(y)) {
				if (p.contents().equals(y.contents())) {
					IList<Node<A>> pathList = new Cons<Node<A>>(y, new Nil<Node<A>>());
					while(!path.get(y).equals(x)){
						pathList = pathList.append(path.get(y));
						y = path.get(y);
					}
					pathList = pathList.append(x);
					pathList = pathList.reverse();
					//System.out.println("path = " + pathList);
					return pathList;
				}
				
				visited.add(y);
				
				if(!y.successors().isEmpty()){
					for(Node<A> n : y.successors()){
						if(!visited.contains(n)){
							frontier.add(n);
							path.put(n, y);
						}
					}
				} else {
					path.remove(y);
				}
			}
		}

		return new Nil<Node<A>>();
	}	

}
