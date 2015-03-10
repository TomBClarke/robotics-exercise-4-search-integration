package robotSearches;

/**This is an interface which is used to represent the types of containers  which can be used in the graph searches.
 * This includes stacks, queues and priority queues. 
 * This allows for a general algorithm to be used to search the graph for all of the different methods of searching the graph.
 * @author Kyle Allen-Taylor
 *
 * @param <E> The type of the object which the container holds.
 */
public interface Container<E>{
	
	/**Adds the object parameter to the container.
	 * @param node The object we wish to add to the container.
	 */
	void add(E node);
	/**Returns the next object from the container.
	 * @return The next object from the container.
	 */
	E poll();
	/**Returns whether or not the container contains an object.
	 * @param node The object we want to check if the container contains.
	 * @return Whether or not the container contains the object.
	 */
	boolean contains(E node);
	/**Returns if the container is empty or not.
	 * @return Whether or not the container is empty.
	 */
	boolean isEmpty();

}
