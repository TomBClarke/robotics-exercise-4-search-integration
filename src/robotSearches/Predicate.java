package robotSearches;

/**
 * Used to test if the node in question in the target node.
 * 
 * @author Tom
 *
 * @param <A>
 */
public interface Predicate<A> {
	boolean holds(A a);
}
