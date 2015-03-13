package robotSearches;

import ilist.*;

import java.util.*;

import maybe.*;

// We represent a graph as a set of nodes. 
// This is a minimal class so that a graph can be created.

/**The Graph class allows a graph of coordinates to be entered and then searched. Returning paths and nodes using Depth First Search, Breadth First Search and A* Search.
 * @author Kyle Allen-Taylor
 * @param <A> Element the graph will consist of.
 */
public class Graph<A> {

	// Keep the implementation of sets open, by using the Set interface:
	private Set<Node<A>> nodes;

	// Constructs the empty graph:
	/**
	 * Constructs the graph using a linkedHashSet to store the nodes.
	 */
	public Graph() {
		nodes = new LinkedHashSet<Node<A>>();
	}

	// Get method:
	/**Returns the nodes within the graph
	 * @return The nodes within the graph
	 */
	public Set<Node<A>> nodes() {
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
	
	/**Returns a path from a starting node to another node using a generalised approach.
	 * This generalisation will perform a DFS when a MyStack object is passed as the container, 
	 * a BFS when a MyQueue object is passed, and an A* search when a MyPriorityQueue object is passed.
	 * @param origin The node we start our search from
	 * @param destination The node we wish to find a path to
	 * @param functions Holds both the heuristic function and the distance function
	 * @param container The container for the path and therefore the type of search we make, DFS, BFS or A*
	 * @return The path which we need to take from the origin to reach the destination node
	 */
	public Maybe<IList<Node<A>>> findPathFromGeneralisation(Node<A> origin, Node<A> destination, AStarFunctions<Node<A>> functions, Container<Node<A>> container)
	{
		Set<Node<A>> visited = new HashSet<Node<A>>();
		Hashtable<Node<A>, Double> estimatedTotalCost = new Hashtable<Node<A>, Double>();
		Hashtable<Node<A>, Node<A>> predecessors = new Hashtable<Node<A>, Node<A>>();
		Hashtable<Node<A>, Double> Di = new Hashtable<Node<A>, Double>();
		Di.put(origin, 0.0);
		estimatedTotalCost.put(origin, functions.heuristicFunction(origin, destination));
		container.add(origin);
		
		while(!container.isEmpty()){
			Node<A> node = container.poll();
			//if the node from the queue has the same coordinates as the destination node, 
			//then we have found our destination and we return the path.
			if(node.contentsEquals(destination.contents())){
				System.out.println("found the Node, calculating path...");
				IList<Node<A>> path = new Cons<Node<A>>(node,new Nil<Node<A>>());
				while (predecessors.containsKey(node)) {
					path = new Cons<Node<A>>((predecessors.get(node)),path);
					node = predecessors.get(node);						
				} 
				//finally return the list of nodes, which is the path to follow to get from one node to another.
				return new Just<IList<Node<A>>>(path);
			}
			
			visited.add(node);//We add that node to the visited set.
			Set<Node<A>> successors = node.successors();
			for (Iterator<Node<A>> iterator = successors.iterator(); iterator.hasNext();) {
				Node<A> node2 = (Node<A>) iterator.next();
				if(!visited.contains(node2)){
					Double cost = Di.get(node) + functions.distanceFunction(node, node2);
					
					if(!container.contains(node2) || cost<Di.get(node2)){
						predecessors.put(node2, node);
						Di.put(node2, cost);
						estimatedTotalCost.put(node2, cost + functions.heuristicFunction(node2, destination));
						if(!container.contains(node2))
							container.add(node2);
					}
				}
				
			}
		}
		return new Nothing<IList<Node<A>>>();		
	}

}
