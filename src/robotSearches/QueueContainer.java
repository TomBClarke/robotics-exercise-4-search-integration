package robotSearches;

import java.util.Queue;

public class QueueContainer<A> implements IQueueContainer<A> {
	
	private Queue<Node<A>> queue;

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
