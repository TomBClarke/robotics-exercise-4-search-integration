package robotSearches;

import java.util.LinkedList;

/**
 * @author Kyle Allen-Taylor
 *
 * @param <E> The type of what the queue will consist of.
 */
public class MyQueue<E> implements Container<E> {
	private LinkedList<E> queue; 
	
	/**The constructor for the queue.
	 * 
	 */
	public MyQueue(){
		this.queue= new LinkedList<E>();
	}
	
	@Override
	public void add(E node) {
		queue.add(node);
	}

	@Override
	public E poll() {
		return queue.poll();
	}

	@Override
	public boolean contains(E node) {
		return queue.contains(node);
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

}
