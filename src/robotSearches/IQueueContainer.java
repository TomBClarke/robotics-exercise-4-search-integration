package robotSearches;

/**
 * This is used to test the search on pc, and allow it to work on the robot without changing the code.
 * 
 * @author Tom
 *
 * @param <A>
 */
public interface IQueueContainer<A> {
	
	/**
	 * Add a node to the queue.
	 * 
	 * @param x The node
	 */
	public void add(Node<A> x);
	
	/**
	 * Gets and removes the first element in the queue.
	 * 
	 * @return The first node.
	 */
	public Node<A> poll();
	
	/**
	 * Checks if the queue is empty.
	 * 
	 * @return If the queue is empty.
	 */
	public boolean isEmpty();
}
