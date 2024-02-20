package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Queue interface for the methods of a queue. Contains methods to enqueue and 
 * dequeue elements, check is the queue is empty, get the size of the queue, and 
 * set the capacity of the queue. Queue interface contains methods for
 * ArrayQueue and LinkedQueue implementation.
 * 
 * @author Tyler Davis
 * @param <E> generic type of list
 */
public interface Queue<E> {
	/**
	 * Adds an element to the back of the queue.
	 * @param element to add to the queue
	 * @throws IllegalArgumentException if the size of the queue is equal to the capacity
	 */
	void enqueue (E element);
	
	/**
	 * Removes and returns the element at the front of the queue.
	 * @return element at the front of the queue that was removed
	 */
	E dequeue();
	
	/**
	 * Returns if the queue is empty.
	 * @return whether the queue is empty or not
	 */
	boolean isEmpty();
	
	/**
	 * Returns the number of elements in the queue.
	 * @return number of elements in the queue
	 */
	int size();
	
	/**
	 * Sets the queue capacity.
	 * @param capacity to set
	 * @throws IllegalArgumentException if the param is negative or less than the
	 * number of elements in the queue
	 */
	void setCapacity(int capacity);
}
