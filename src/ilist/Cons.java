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
  private final IList<E> tail; // Reference to another list 
                              // (not a list itself).

  /**
 * @param head The head of the Cons, the first element in the IList
 * @param tail The rest of the elements in the IList excluding the head
 */
public Cons(E head,  IList<E> tail) {
    assert(tail != null);  // Tail should NOT be null. Use Nil instead.
    // See http://docs.oracle.com/javase/8/docs/technotes/guides/language/assert.html

    this.head = head;      // The usual stuff now.
    this.tail = tail;
  }



/**
 * @return Whether or not the list is empty
 */
public boolean isEmpty() { 
   return false; 
  }

/**
 * @return the size of the list
 */
public int size() {
    return 1 + tail.size(); // Is this a recursive call?
    // I prefer to call this "delegation" rather
    // than "recursion".
  }

  public String toString() {
    return "Cons("  +  head + "," + tail.toString()  +  ")";
  }
    
  public IList<E> append(IList<E> l) {
    return new Cons<E>(head, tail.append(l));
  }

  public IList<E> append(E e) {
    return append(new Cons<E>(e , new Nil<E>()));
  }

  public IList<E> reverse() {
    return tail.reverse().append(head);

    // // Equivalently:
    // IList <E> r = tail.reverse();
    // IList <E> s = r.append(head);
    // return s;
  }

  public boolean has(E e) {
    return (head.equals(e) || tail.has(e)); 
    // Short-circuit evaluation of "||" makes this efficient.
    // Search for "short-circuit evaluation" in the internet.
  }
  
  
  /**
 * @return The head of the list.
 */
public E head(){
	  return head;
  }
}
