package robotSearches;

import java.util.Stack;

/**
 * @author Kyle Allen-Taylor
 *
 * @param <E> The type of what the stack will consist of.
 */
public class MyStack<E> implements Container<E> {
	private Stack<E> stack;
	/**Constructs the stack with different methods.
	 * 
	 */
	public MyStack() {
		this.stack =new Stack<E>();
	}

	@Override
	public void add(E node) {
		stack.push(node);
	}

	@Override
	public E poll() {
		return stack.pop();
	}

	@Override
	public boolean contains(E node) {
		return stack.contains(node);
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

}
