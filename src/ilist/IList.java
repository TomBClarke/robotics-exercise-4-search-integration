package ilist;

/**
 * Interface for immutable lists using the "composite pattern".
 */
public interface IList<E> {
	
	/**
	 * Checks if the list is empty.
	 * 
	 * @return If the list if empty.
	 */
	public boolean isEmpty();

	/**
	 * Gets the size of the list.
	 * 
	 * @return The size of the list.
	 */
	public int size();

	/**
	 * Reverses the list.
	 * 
	 * @return The list reversed.
	 */
	public IList<E> reverse();

	/**
	 * Appends a list to the current list.
	 * 
	 * @param l The list to add.
	 * @return The new combined list.
	 */
	public IList<E> append(IList<E> l);

	/**
	 * Appends an element to the list.
	 * 
	 * @param e The element to add.
	 * @return The new combined list.
	 */
	public IList<E> append(E e);

	/**
	 * Checks if a list has a element.
	 * 
	 * @param e The element we're looking for.
	 * @return Whether it is found.
	 */
	public boolean has(E e);

	/**
	 * Returns the head of the list.
	 * 
	 * @return The first item.
	 */
	public E head();

	/**
	 * Returns the list without the head.
	 * 
	 * @return The list. without a head.
	 */
	public IList<E> tail();
}