package robotSearches;

/**An interface for the functions which the A* search will use.
 * @author Kyle Allen-Taylor
 */
public interface AStarFunctions<A> {
	
	/**Creates the distance function which calculates the distance between two nodes' contents i.e. coordinates
	 * @param node1 The first node.
	 * @param node2 The second node.
	 * @return The value which the function returns when the two nodes are input.  
	 */
	double distanceFunction(A node1, A node2);
	
	/**
	 * @param node1
	 * @param node2
	 * @return
	 */
	double heuristicFunction(A node1, A node2);
}
