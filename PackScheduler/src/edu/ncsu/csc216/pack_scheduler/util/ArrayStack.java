package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Implementation of Stack that uses an ArrayList
 * @author Ken Ogura 
 * @param <E> generic type of list
 */
public class ArrayStack<E> implements Stack<E> {
	/** Represents the stack that uses an ArrayList*/
	private ArrayList<E> list; 
	/** The capacity of the stack*/
	private int capacity;
	
	
	/**
	 * Constructs the ArrayStack object and sets the capacity
	 * @param capacity the capacity the stack will be set to 
	 */
	public ArrayStack(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * Pushes an element to the top of the stack. Throws IllegalArgumentException
	 * if there is no room
	 * @throws IllegalArgumentException if capacity is reached
	 */
	@Override
	public void push(E element) {
		if (list.size() == capacity) {
			throw new IllegalArgumentException("Capacity has been reached");
		}
		list.add(element);
	}

	/**
	 * Removes and returns an element from the top of the stack. Throws an EmptyStackException if
	 * the stack is empty.
	 * @return element the element that is removed from the top of the stack.  
	 * @throws EmptyStackException if the stack is empty 
	 */
	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		E element = list.remove(size() - 1);
		return element;
	}
	
	/**
	 * Returns true if the stack is empty
	 * @return true if the stack is empty 
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	/**
	 * Returns the number of elements in the stack. 
	 * @return size the number of elements in the stack. 
	 */
	@Override
	public int size() {
		return list.size();
	}
	
	/**
	 * Sets the capacity of the stack. If the parameter is negative
	 * or less than the number of elements in the stack an IllegalArgumentException
	 * is thrown. 
	 * @throws IllegalArgumentException if the parameter is negative or less than the number of elements in the stack
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		this.capacity = capacity; 
	}
	
}

