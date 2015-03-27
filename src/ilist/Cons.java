package ilist;

/**
 * Implementation of a list that has a head and a tail
 * (using the "composite pattern").
 */

/**
 * @author Kyle Allen-Taylor
 */

public class Cons<E> implements IList<E> {

	private final E head;
	private final IList<E> tail;

	/**
	 * @param head
	 *            The head of the Cons, the first element in the IList
	 * @param tail
	 *            The rest of the elements in the IList excluding the head
	 */
	public Cons(E head, IList<E> tail) {
		assert (tail != null);

		this.head = head;
		this.tail = tail;
	}

	public boolean isEmpty() {
		return false;
	}

	public int size() {
		return 1 + tail.size(); // Is this a recursive call?
		// I prefer to call this "delegation" rather
		// than "recursion".
	}

	public String toString() {
		return "Cons(" + head + "," + tail.toString() + ")";
	}

	public IList<E> append(IList<E> l) {
		return new Cons<E>(head, tail.append(l));
	}

	public IList<E> append(E e) {
		return append(new Cons<E>(e, new Nil<E>()));
	}

	public IList<E> reverse() {
		return tail.reverse().append(head);
	}

	public boolean has(E e) {
		return (head.equals(e) || tail.has(e));
	}

	public E head() {
		return this.head;
	}

	public IList<E> tail() {
		return this.tail;
	}
}
