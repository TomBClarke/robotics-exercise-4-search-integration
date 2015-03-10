package robotSearches;

import ilist.Cons;
import ilist.IList;
import ilist.Nil;

import java.util.*;

import maybe.Just;
import maybe.Maybe;
import maybe.Nothing;
import maybe.Predicate;

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
		// Choose any implementation of sets you please, but you need to
		// choose one.
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
	//Finds a node from a certain point which satisfies the predicate p using a depth first search approach.
	/**Finds a node from a starting node to another node which satisfies a predicate using a depth first search approach.
	 * @param x The node we start from
	 * @param p The predicate which needs to be satisfied
	 * @return The node which was found from the starting node and satisfied the predicate. If no node was found then nothing is returned.
	 */
	public Maybe<Node<A>> findNodeFromDFS(Node<A> x, Predicate<A> p) {
		Stack<Node<A>> stack = new Stack<Node<A>>();
		Set<Node<A>> visited = new HashSet<Node<A>>();
		stack.add(x);

		while (!stack.empty()) {
			Node<A> node = stack.pop();
			if (!visited.contains(node)) {
				if (p.holds(node.contents())) {
					System.out.println("visited: " + visited);
					return new Just<Node<A>>(node);
				}
				visited.add(node);
				Set<Node<A>> successors = node.successors();
				stack.addAll(successors);

			}
		}
		return new Nothing<Node<A>>();
	}
	//Returns a path from a certain point to another which satisfies the predicate p using the depth first search approach.
	/**	Returns a path from a starting node to another node which satisfies a predicate using the depth first search approach.
	 * @param x The node we start from
	 * @param p The predicate which needs to be satisfied
	 * @return A list of nodes which is the path to be taken from the starting node to the node which satisfies the predicate. 
	 * If no node can be found from the starting node which satisfies the predicate then nothing is returned.
	 */
	public Maybe<IList<Node<A>>> findPathFromDFS(Node<A> x, Predicate<A> p){
		Stack<Node<A>> stack = new Stack<Node<A>>();
		Set<Node<A>> visited = new HashSet<Node<A>>();
		Hashtable<Node<A>, Node<A>> hash = new Hashtable<Node<A>, Node<A>>();
		stack.add(x);
		IList<Node<A>> path = new Nil<Node<A>>();
			while (!stack.empty()) { //Run the following code whilst the stack isn't empty 
			Node<A> node = stack.pop(); //get the first node from the stack.
				if (p.holds(node.contents())) { //if you have found the node which fulfils the predicate do the following code.
					System.out.println("found the Node, calculating path...");
					path = new Cons<Node<A>>(node,new Nil<Node<A>>());
					while (hash.containsKey(node)) {
						path = new Cons<Node<A>>((hash.get(node)),path);
						node = hash.get(node);						
					} 
					//finally return the list of nodes, which is the path to follow to get from one node to another.
					return new Just<IList<Node<A>>>(path);
				}
				//otherwise continue searching for the node, add the successors of the current node to the stack.
				visited.add(node); //add the node we just found to the visited set
				Set<Node<A>> successors = node.successors();
				stack.addAll(successors);
				//check the next successor
				Node<A> node2 = stack.pop();
				//if we have already visited that successor then continue to pop from the stack until it hasn't been visited.
				while(visited.contains(node2)){
					if(stack.empty()){
						//if the stack is empty then we have visited all of the possible nodes 
						//and the node has not been found which fulfils the predicate.
						return new Nothing<IList<Node<A>>>();
					}
					node2 = stack.pop();
				}
				
				//once we  have found a successor which hasn't been visited we add it to the hashMap.
				//the key of the hashMap represents the next node which we have found and the value of the key is the node which we came from.
				hash.put(node2, node);
				//push that value back onto the stack so that the algorithm will work when we repeat the while loop.
				stack.push(node2);
			}
		//if the stack is empty then we have visited all of the possible nodes 
		//and the node has not been found which fulfils the predicate.
		return new Nothing<IList<Node<A>>>();
	}
	//Finds a node from a certain point which satisfies the predicate p using a breadth first search approach.	
	/**Finds a node from a starting node to another node which satisfies a predicate using a breadth first search approach.
	 * @param x The node we start from
	 * @param p The predicate which needs to be satisfied
	 * @return The node which was found from the starting node and satisfied the predicate. If no node was found then nothing is returned.
	 */
	public Maybe<Node<A>> findNodeFromBFS(Node<A> x, Predicate<A> p) {

		LinkedList<Node<A>> queue = new LinkedList<Node<A>>();
		Set<Node<A>> visited = new HashSet<Node<A>>();
		queue.add(x);

		while (!queue.isEmpty()) {
			Node<A> node = queue.pop();
			if (!visited.contains(node)) {
				if (p.holds(node.contents())) {
					System.out.println("visited: " + visited);
					return new Just<Node<A>>(node);
				}
				visited.add(node);
				Set<Node<A>> successors = node.successors();
				queue.addAll(successors);

			}
		}
		return new Nothing<Node<A>>();
	}
	//Returns a path from a certain point to another which satisfies the predicate p using the breadth first search approach.
	/**	Returns a path from a starting node to another node which satisfies a predicate using the breadth first search approach.
	 * @param x The node we start from
	 * @param p The predicate which needs to be satisfied
	 * @return A list of nodes which is the path to be taken from the starting node to the node which satisfies the predicate. 
	 * If no node can be found from the starting node which satisfies the predicate then nothing is returned.
	 */
	public Maybe<IList<Node<A>>> findPathFromBFS(Node<A> x, Predicate<A> p){
		LinkedList<Node<A>> queue = new LinkedList<Node<A>>();
		Set<Node<A>> visited = new HashSet<Node<A>>();
		Hashtable<Node<A>, Node<A>> hash = new Hashtable<Node<A>, Node<A>>();
		queue.add(x);
		IList<Node<A>> path = new Nil<Node<A>>();
			while (!queue.isEmpty()) { //Run the following code whilst the queue isn't empty 
			Node<A> node = queue.pop(); //get the first node from the queue.
			
				if (p.holds(node.contents())) { //if you have found the node which fulfils the predicate do the following code.
					System.out.println("found the Node, calculating path...");
					path = new Cons<Node<A>>(node,new Nil<Node<A>>());
					//hash.put()
					while (hash.containsKey(node)) {
						path = new Cons<Node<A>>((hash.get(node)),path);
						node = hash.get(node);						
					} 
					//finally return the list of nodes, which is the path to follow to get from one node to another.
					return new Just<IList<Node<A>>>(path);
				}
				//otherwise continue searching for the node, add the successors of the current node to the queue.
				visited.add(node); //add the node we just found to the visited set
				Set<Node<A>> successors = node.successors();
				queue.addAll(successors);
				//check the next successor
				Node<A> node2 = queue.pop();
				//if we have already visited that successor then continue to pop from the queue until it hasn't been visited.
				while(visited.contains(node2)){
					//if the queue is empty then we have visited all of the possible nodes 
					//and the node has not been found which fulfils the predicate.
					if(queue.isEmpty()){
						return new Nothing<IList<Node<A>>>();
					}
					node2 = queue.pop();
				}
				
				//once we  have found a successor which hasn't been visited we add it to the hashMap.
				//the key of the hashMap represents the next node which we have found and the value of the key is the node which we came from.
				hash.put(node2, node);
				//push that value back onto the queue so that the algorithm will work when we repeat the while loop.
				queue.push(node2);
			}
		//if the queue is empty then we have visited all of the possible nodes 
		//and the node has not been found which fulfils the predicate.
		return new Nothing<IList<Node<A>>>();
	}	
	//Returns a path from a certain point to another using the A star search approach.
	/**Returns a path from a starting node to another node using the A* search approach.
	 * @param origin The node we start the search from
	 * @param destination The node we wish to find a path to
	 * @param functions Holds both the heuristic function and the distance function
	 * @return The path which we need to take from the origin to reach the destination node
	 */
	public Maybe<IList<Node<A>>> findPathFromAStar(Node<A> origin, final Node<A> destination, final AStarFunctions<Node<A>> functions){
		Set<Node<A>> visited = new HashSet<Node<A>>();
		Hashtable<Node<A>, Double> estimatedTotalCost = new Hashtable<Node<A>, Double>();
		Queue<Node<A>> pending = new PriorityQueue<Node<A>>(new Comparator<Node<A>>() {

			@Override
			public int compare(Node<A> node1, Node<A> node2) {
				return (int) (functions.heuristicFunction(node1, destination) - functions.heuristicFunction(node2, destination));
			}

		});
		Hashtable<Node<A>, Node<A>> predecessors = new Hashtable<Node<A>, Node<A>>();
		Hashtable<Node<A>, Double> Di = new Hashtable<Node<A>, Double>();
		Di.put(origin, 0.0);
		estimatedTotalCost.put(origin, functions.heuristicFunction(origin, destination));
		pending.add(origin);
		
		while(!pending.isEmpty()){
			Node<A> node = pending.poll();
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
			
			visited.add(node);//We add that node to the visited set
			Set<Node<A>> successors = node.successors();//Get the successors of that node
			for (Iterator<Node<A>> iterator = successors.iterator(); iterator.hasNext();) {//iterate through the successors
				Node<A> node2 = (Node<A>) iterator.next();
				if(!visited.contains(node2)){//if we have visited that successor already then we don't do anything with it.
					Double cost = Di.get(node) + functions.distanceFunction(node, node2);//The cost to get to this successor is
																						//The previous cost plus the distance 
																						//from the original node to the successor.					
					if(!pending.contains(node2) || cost<Di.get(node2)){//if the pending queue doesn't have the successor in
																//or if the cost to get to the successor is less than the current cost to get to that successor then...
						predecessors.put(node2, node);//update the predecessors so that the original node is a predecessor of the successor
						Di.put(node2, cost);//update the distance to get to the successor with the cost.
						
						//update the estimated total cost to get to the destination for the successor
						estimatedTotalCost.put(node2, cost + functions.heuristicFunction(node2, destination)); 
						if(!pending.contains(node2))//if the successor isn't in the pending queue already, add it.
							pending.add(node2);
					}
				}
				
			}
		}
		return new Nothing<IList<Node<A>>>();//otherwise we haven't found any destination node, so return no path.	
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
	
	// Builds sample graph for testing:
	public static void main(String args []) {    

	//write the graph here
    int [] [] nick = {
      {0,0,1,0,0,1}, 
      {0,1,0,0,1,1,0,2}, 
      {0,2,0,3,0,1}, 
      {0,3,0,2,0,4}, 
      {0,4,0,3,0,5}, 
      {0,5,0,6,1,5,0,4}, 
      {0,6,1,6,0,5}, 
      {1,0,0,0,1,1,2,0}, 
      {1,1,1,2,2,1,1,0,0,1}, 
      {1,2,2,2,1,1,1,3}, 
      {1,3,1,2,1,4,2,3}, 
      {1,4,2,4,1,5,1,3}, 
      {1,5,1,4,2,5,1,6,0,5}, 
      {1,6,0,6,1,5,2,6}, 
      {2,0,3,0,2,1,1,0}, 
      {2,1,2,2,1,1,2,0,3,1}, 
      {2,2,1,2,2,1,2,3,3,2}, 
      {2,3,2,2,2,4,3,3,1,3}, 
      {2,4,1,4,2,5,2,3,3,4}, 
      {2,5,2,4,1,5,2,6,3,5}, 
      {2,6,3,6,2,5,1,6}, 
      {3,0,2,0,3,1}, 
      {3,1,3,0,4,1,2,1,3,2}, 
      {3,2,2,2,4,2,3,1}, 
      {3,3,2,3,3,4}, 
      {3,4,2,4,3,3}, 
      {3,5,3,6,2,5,4,5}, 
      {3,6,2,6,3,5}, 
      {4,0}, 
      {4,1,4,2,5,1,3,1}, 
      {4,2,4,1,5,2,3,2}, 
      {4,3}, 
      {4,4}, 
      {4,5,5,5,3,5}, 
      {4,6}, 
      {5,0}, 
      {5,1,4,1,5,2,6,1}, 
      {5,2,4,2,5,1,6,2}, 
      {5,3}, 
      {5,4}, 
      {5,5,4,5,6,5}, 
      {5,6}, 
      {6,0,7,0,6,1}, 
      {6,1,6,0,5,1,6,2,7,1}, 
      {6,2,5,2,6,1,7,2}, 
      {6,3,7,3,6,4}, 
      {6,4,6,3,7,4}, 
      {6,5,5,5,6,6,7,5}, 
      {6,6,7,6,6,5}, 
      {7,0,6,0,7,1,8,0}, 
      {7,1,8,1,7,0,6,1,7,2}, 
      {7,2,7,3,8,2,6,2,7,1}, 
      {7,3,6,3,7,2,7,4,8,3}, 
      {7,4,7,3,8,4,6,4,7,5}, 
      {7,5,8,5,7,6,7,4,6,5}, 
      {7,6,6,6,7,5,8,6}, 
      {8,0,8,1,7,0,9,0}, 
      {8,1,8,2,9,1,7,1,8,0}, 
      {8,2,8,1,7,2,8,3}, 
      {8,3,8,2,7,3,8,4}, 
      {8,4,8,5,8,3,7,4}, 
      {8,5,9,5,8,4,7,5,8,6}, 
      {8,6,8,5,7,6,9,6}, 
      {9,0,9,1,8,0}, 
      {9,1,8,1,9,2,9,0}, 
      {9,2,9,1,9,3}, 
      {9,3,9,2,9,4}, 
      {9,4,9,5,9,3}, 
      {9,5,8,5,9,4,9,6}, 
      {9,6,9,5,8,6} 
    };

    Graph<Coordinate> nicksGraph = new Graph<Coordinate>();

    for (int i = 0; i < nick.length; i++) {
      // What we are going to do relies on the two following facts
      // about nick:
      assert(nick[i].length >= 2);       // (1)
      assert(nick[i].length % 2 == 0);   // (2)

      int x = nick[i][0]; // Can't get array out of bounds 
      int y = nick[i][1]; // because of assertion (1).
      Coordinate c = new Coordinate(x, y);
      Node<Coordinate> node = nicksGraph.nodeWith(c);

      // And next we add its successors. We rely on assertion (2)
      // again to avoid array out of bounds. Now we start from
      // position 2, as positions 0 and 1 have already been looked at
      // (they are x and y). Notice that we need to increment by 2.

      for (int j = 2; j < nick[i].length; j=j+2) {
        int sx = nick[i][j];   
        int sy = nick[i][j+1]; 
        Coordinate sc = new Coordinate(sx, sy);
        Node<Coordinate> s = nicksGraph.nodeWith(sc);
        node.addSuccessor(s);
      }
    }
    // Done. We have the graph. Now we print it back to be sure this worked:
    for (Node<Coordinate> node : nicksGraph.nodes()) {
      System.out.print("(" + node.contents().x + "," + node.contents().y + "): ");
      for(Node<Coordinate> s : node.successors()) {
        System.out.print("(" + s.contents().x + "," + s.contents().y + "), ");
      }
      System.out.println(); 
    }
    
    //Tests
      
    AStarFunctions<Node<Coordinate>> functions = new AStarFunctions<Node<Coordinate>>() {

		@Override
		public double distanceFunction(Node<Coordinate> node1,
				Node<Coordinate> node2) {
			if (node1.successors().contains(node2))
			return 1.0;
			else
			return Double.POSITIVE_INFINITY;		
		}

		@Override
		public double heuristicFunction(Node<Coordinate> node1,
				Node<Coordinate> node2) {
			Coordinate c1= (Coordinate) node1.contents();
			Coordinate c2= (Coordinate) node2.contents();
			
			return Math.sqrt(Math.pow((c1.x()-c2.x()), 2)+Math.pow(c1.y()-c2.y(), 2));
		}
	};
	
	Coordinate origin = new Coordinate(0, 0);
	Coordinate destination = new Coordinate(9, 3);
	
	System.out.println(nicksGraph.findPathFromGeneralisation(nicksGraph.nodeWith(origin), nicksGraph.nodeWith(destination), functions, new MyStack<Node<Coordinate>>()));
	System.out.println(nicksGraph.findPathFromGeneralisation(nicksGraph.nodeWith(origin), nicksGraph.nodeWith(destination), functions, new MyQueue<Node<Coordinate>>()));
	System.out.println(nicksGraph.findPathFromGeneralisation(nicksGraph.nodeWith(origin), nicksGraph.nodeWith(destination), functions, new MyPriorityQueue<Node<Coordinate>>(functions,nicksGraph.nodeWith(new Coordinate(9,0)))));
	System.out.println(nicksGraph.findPathFromAStar(nicksGraph.nodeWith(origin), nicksGraph.nodeWith(destination), functions));
	
  }
}
