/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Represents an abstract linked list that extends AbstractList.
 * @param <E> element in list
 * 
 * @author Ken Ogura 
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** The number of elements in the list*/
	private int size;
	/** The total capacity of the list*/
	private int capacity;
	/** The first node in the linked list*/
	private ListNode front;
	/** The last node in the linked list*/
	private ListNode back;
	
	/**
	 * Constructor for LinkedAbtractList that sets size, capacity, and front.
	 * Throws an IllegalArgumentException if capacity is less than 0 or less than
	 * size. 
	 * @param capacity the capacity of the list
	 * @throws IllegalArgumentException if capacity is less than 0 or less than size
	 */
	public LinkedAbstractList(int capacity) {
		setCapacity(capacity);
		size = 0;
		front = null; 
	}
	
	/**
	 * Helper method to set the capacity of the list. Throws an IllegalArgumentException if capacity is less than 0 
	 * or less than size.  
	 * @param capacity the capacity of the list
	 * @throws IllegalArgumentException if capacity is less than 0 or less than size
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity less than 0");
		} else if (capacity < size) {
			throw new IllegalArgumentException("Capacity less than size");
		}
		this.capacity = capacity;
	}
	
	/**
	 * Adds an element to the specified index of the list. Throws an IllegalArgumentException
	 * if size is equal to capacity. If the element to be added is null a NullPointerException is
	 * thrown. If the element to add is a duplicate of an element in the list, than the method 
	 * throws an IllegalArgumentException. If the index is out of range, an IndexOutOfBoundsException is 
	 * thrown. 
	 * @param idx the index where the element will be added  
	 * @param element the element to be added 
	 * @throws NullPointerException if the element to be added is null 
	 * @throws IllegalArgumentException if size equals capacity or the element is a duplicate.
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 */
	@Override
	public void add(int idx, E element) {
		if (size == capacity) {
			throw new IllegalArgumentException("Size equals capacity");
		} else if (element == null) {
			throw new NullPointerException("Element to be added is null");
		} else if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException("Index out of range");
		}
		if (size == 0) {
			front = new ListNode(element);
			size++;
			return;
		}
		ListNode current = front;
		while (current != null) {
			if (current.data.equals(element)) {
				throw new IllegalArgumentException("Element is already in the list");
			}
			current = current.next;
		}
		if (idx == 0) {
			ListNode newElement = new ListNode(element, front);
			front = newElement;
			
			// Sets back field 
			current = front;
			while (current.next != null) {
				current = current.next;
			}
			back = current;
		} else if (idx == size() - 1) {
			int currentIndex = 0;
			current = front;
			while (current != null) {
				if (currentIndex == idx - 1) {
					current.next = new ListNode(element, back);
					break;
				}
				current = current.next;
				currentIndex++;
			}
			
			// Sets back field
			current = front;
			while (current.next != null) {
				current = current.next;
			}
			back = current;
		} else {
			int currentIndex = 0;
			current = front;
			while (current != null) {
				if (currentIndex == idx - 1) {
					ListNode tmp = current.next;
					current.next = new ListNode(element, tmp);
					break;
				}
				current = current.next;
				currentIndex++;
			}
			
			// Sets back field
			current = front;
			while (current.next != null) {
				current = current.next;
			}
			back = current;
		}
		size++;
	}
	
	/**
	 * Removes and returns the element at the given index. Throws
	 * an IndexOutOfBoundsException if the index is out of range.
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Remove element's index is out of range");
		}
		E value = null;
		if (idx == 0) {
			value = front.data;
			front = front.next;
		} else {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			value = current.next.data;
			current.next = current.next.next;
			
			
			// Sets back field
			current = front;
			while (current.next != null) {
				current = current.next;
			}
			back = current;
		}
		size--;
		return value; 
	}
	
	/**
	 * Returns the number of elements in the list. 
	 * @return size number of elements in listx
	 */
	public int size() {
		return size; 
	}
	
	/**
	 * Replaces and returns the element at the given index. If the element is null
	 * a NullPointerException is thrown. If an element is a duplicate of an element
	 * already in the list a IllegalArgumentException is thrown. If the index is out of
	 * range, a IndexOutOfBoundsException is thrown. 
	 * @param idx the index of the element to be set
	 * @param element the element to be set 
	 * @throws NullPointerException if the element to be added is null
	 * @throws IllegalArgumentException if the element to be set is already in the list 
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 * @return E the element that is replaced 
	 */
	@Override 
	public E set(int idx, E element) {
		if (element == null) {
			throw new NullPointerException("Element to be set is null");
		} else if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Set element index out of range");
		}
		ListNode current = front;
		while (current != null) {
			if (current.data.equals(element)) {
				throw new IllegalArgumentException("Element is already in the list");
			}
			current = current.next; 
		}
		current = front;
		int currentIndex = 0;
		if (idx == size - 1) {
			for (int i = 0; i < size - 1; i++) {
				current = current.next;
			}
			E returnData = current.data;
			current.data = element;
			return returnData;
		}
		while (current.next != null) {
			if (currentIndex == idx) {
				E returnData = current.data;
				current.data = element;
				return returnData;
			}
			currentIndex++;
			current = current.next; 
		}
		return current.data; 
	}
	
	/**
	 * Returns the element at the given index. Throws an IndexOutOfBoundsException
	 * if the index is out of range. 
	 * @param idx index given
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the index is out of range 
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Get element index out of range");
		}
		ListNode current = front;
		int currentIndex = 0;
		while (current.next != null) {
			if (currentIndex == idx) {
				return current.data; 
			}
			currentIndex++;
			current = current.next; 
		}
		return current.data; 
	}
	
	
	/**
	 * Inner class representing a node in the linked list. 
	 */
	private class ListNode {
		/** Represents the data held in the node*/
		private E data;
		/** Represents the next node of the current node*/
		private ListNode next;
		
		/**
		 * Constructs a ListNode given data. 
		 * @param data the data of the ListNode
		 */
		public ListNode(E data) {
			this.data = data;
		}
		
		/**
		 * Constructs a ListNode given data and the next node
		 * @param data the data of the ListNode 
		 * @param next the next node of the current node 
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next; 
		}
	}
}
