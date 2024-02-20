/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * LinkedQueue implementation of the Queue interface. Contains methods to enqueue and 
 * dequeue elements, check is the array queue is empty, get the size of the linked queue, and 
 * set the capacity of the linked queue. LinkedQueue is a linked abstract list based implementation
 * of a queue.
 * 
 * @author Tyler Davis
 * @param <E> generic type of list
 */
public class LinkedQueue<E> implements Queue<E> {
	/** List for the LinkedAbstractList */
	private LinkedAbstractList<E> list;
	/** Capacity of the list */
	private int capacity;
	
	/**
	 * Constructor of the linked queue.
	 * @param capacity max elements a linked queue can have
	 */
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}
	
	/**
	 * Adds an element to the queue at the back.
	 * @param element to add
	 */
	@Override
	public void enqueue(E element) {
		if (size() == capacity) {
			throw new IllegalArgumentException();
		}
		
		list.add(size(), element);
	}

	/**
	 * Removes the first element from the queue.
	 * @return the element that was removed
	 */
	@Override
	public E dequeue() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		return list.remove(0);
	}

	/**
	 * Returns whether the queue is empty or not.
	 * @return whether the queue is empty or not
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the size of the linked queue.
	 * @return number of elements in the linked queue
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the capacity of the linked queue by delegating to the
	 * setCapacity method in LinkedAbstractList.
	 */
	@Override
	public void setCapacity(int capacity) {
		list.setCapacity(capacity);
		this.capacity = capacity;
	}

}
