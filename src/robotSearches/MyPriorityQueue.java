package robotSearches;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyPriorityQueue<E> implements Container<E> {

	private Queue<E> queue;
	public MyPriorityQueue(final AStarFunctions<E> functions, final E destination) {
		this.queue = new PriorityQueue<E>(new Comparator<E>() {

			@Override
			public int compare(E node1, E node2) {
				return (int) (functions.heuristicFunction(node1, destination) - functions.heuristicFunction(node2, destination));
			}
		});
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
