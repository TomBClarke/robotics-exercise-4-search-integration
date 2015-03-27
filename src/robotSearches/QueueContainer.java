package robotSearches;

import java.util.Queue;

/**
 * Creates a queue that will work on the robot.
 * 
 * @author Tom
 *
 * @param <A>
 */
public class QueueContainer<A> implements IQueueContainer<A> {

	private Queue<Node<A>> queue;

	/**
	 * Creates a new queue.
	 */
	public QueueContainer() {
		queue = new Queue<Node<A>>();
	}

	public void add(Node<A> x) {
		queue.push(x);
	}

	public Node<A> poll() {
		Node<A> node = queue.elementAt(0);
		queue.removeElementAt(0);
		return node;
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
