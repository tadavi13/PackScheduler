/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Represents a stack that uses a Linked List.
 * @author Ken Ogura
 * @param <E> generic type of list
 */
public class LinkedStack<E> implements Stack<E> {

	/** Represents a stack that uses a Linked List*/
	private LinkedAbstractList<E> list;
	/** The capacity of the stack*/
	private int capacity;
	
	
	/**
	 * Creates the LinkedStack object and sets the capacity of the stack
	 * @param capacity the capacity of the stack 
	 */
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}
	
	/**
	 * Pushes the passed in element to the top of the stack. Throws an
	 * IllegalArgumentException if there is no room.
	 * @param element the element of be added to the top of the stack.
	 * @throws IllegalArgumentException if capacity has been reached. 
	 */
	@Override
	public void push(E element) {
		if (list.size() == capacity) {
			throw new IllegalArgumentException("Capacity has been reached.");
		}
		list.add(element);
	}

	/**
	 * Removes and returns element from top of the stack. If the stack
	 * is empty an EmptyStackException is thrown.
	 * @return element the element that is removed from the top of the stack
	 * @throws EmptyStackException if the stack is empty  
	 */
	@Override
	public E pop() {
		if (size() == 0) {
			throw new EmptyStackException(); 
		}
		E element = list.remove(size() - 1);
		return element;
	}

	/**
	 * Returns true if the stack is empty. 
	 * @return true if the stack is empty. 
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Returns the number of elements in the stack.
	 * @return size the number of elements in the stack
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the stack's capacity. If the parameter is negative
	 * or less than the number of elements in the stack an IllegalArgumentException
	 * is thrown. 
	 * @throws IllegalArgumentException if the parameter is negative or less than the number of elements in the stack
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		this.capacity = capacity; 	}
}
