package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface for ArrayStack and LinkedStack
 * @author Ken Ogura
 * @param <E> generic type of list
 */
public interface Stack<E> {
	
	/**
	 * Method to push elements to the top of the stack. 
	 * @param element the element to be pushed to the top of the stack.
	 * @throws IllegalArgumentException if capacity is reached  
	 */ 
	void push (E element);
	
	/**
	 * Removes and returns the element at the top of the stack
	 * @return E an element found at the top of the stack that is removed.
	 */
	E pop();
	
	/**
	 * Returns true if the stack is empty
	 * @return true if the stack is empty 
	 */
	boolean isEmpty();
	
	/**
	 * Returns the number of elements in the stack 
	 * @return size the number of elements in the stack. 
	 */
	int size(); 
	
	/**
	 * Set's the stack's capacity. 
	 * @param capacity the capacity that the stack will be set to 
	 * @throws IllegalArgumentException if the parameter is negative or less than the number of elements in stack 
	 */
	void setCapacity(int capacity);
	
}
