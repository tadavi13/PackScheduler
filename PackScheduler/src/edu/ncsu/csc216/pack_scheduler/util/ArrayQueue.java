/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * ArrayQueue implementation of the Queue interface. Contains methods to enqueue and 
 * dequeue elements, check is the array queue is empty, get the size of the array queue, and 
 * set the capacity of the queue. ArrayQueue is an array list based implementation
 * of a queue.
 * 
 * @author Tyler Davis
 * @param <E> generic type of list
 */
public class ArrayQueue<E> implements Queue<E> {
	/** ArrayList of the array queue */
	private ArrayList<E> arrayList;
	/** Capacity of the ArrayQueue */
	private int capacity;
	
	/**
	 * Constructor of the array queue.
	 * @param capacity max number of elements that can be in the queue
	 */
	public ArrayQueue(int capacity) {
		arrayList = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * Adds an element to the back of the queue.
	 * @param element to add
	 * @throws IllegalArgumentException if size is equal to capacity
	 */
	@Override
	public void enqueue(E element) {
		if (size() == capacity) {
			throw new IllegalArgumentException();
		}
		
		arrayList.add(size(), element);
	}

	/**
	 * Removes and element from the front of the queue.
	 * @return the element removed
	 */
	@Override
	public E dequeue() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		return arrayList.remove(0);
	}

	/**
	 * Returns if the array queue is empty.
	 * @return whether the array queue is empty or not
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Returns the size of the array queue.
	 * @return size of the array queue
	 */
	@Override
	public int size() {
		return arrayList.size();
	}

	/**
	 * Sets the capacity of the array queue.
	 * @throws IllegalArgumentException if the capacity is negative or less
	 * than size
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		
		this.capacity = capacity;
	}

}
