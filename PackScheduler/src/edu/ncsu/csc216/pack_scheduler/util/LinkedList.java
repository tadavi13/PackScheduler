package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Custom implementation of LinkedList 
 * @author Ken Ogura 
 * 
 * @param <E> generic type of the list
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	/** The number of elements in the list*/
	private int size;
	/** The front node of the list */
	private ListNode front;
	/** The back node of the list*/
	private ListNode back;
	
	
	/**
	 * Constructor for LinkedList
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0; 	
	}
		
	/**
	 * Returns the number of elements in the list.
	 * @return size then number of elements in the list 
	 */
	public int size() {
		return size; 
	}
	
	/**
	 * Checks for duplicates and adds element to the passed in index.
	 * Throws an IllegalArgumentException if the element to be added
	 * is already in the list. 
	 * @param index the index where the element will be added
	 * @param element the element that will be added
	 * @throws IllegalArgumentException if the element to be added is already in the list
	 */
	@Override
	public void add(int index, E element) {
		if (this.contains(element) && element != null) {
			throw new IllegalArgumentException(); 
		}
		super.add(index, element);
	}
	
	/**
	 * Sets the passed in element to the given index. Throws an IllegalArgumentException
	 * if the element to be set is already in the list. Returns the element that is replaced
	 * @param index the position where the element will be set
	 * @param element the element to be set 
	 * @throws IllegalArgumentException if the element to be set is already in the list. 
	 * @return replacedValue the element that is replaced 
	 */
	@Override
	public E set(int index, E element) {
		if (this.contains(element) && element != null) {
			throw new IllegalArgumentException();
		} else if (size() == 0) {
			throw new IndexOutOfBoundsException();
		}
		E replacedValue = super.set(index, element);
		return replacedValue;
	}
	
	/**
	 * List iterator constructs an iterator a certain index position.
	 * @param index of where the iterator should be placed
	 * @return listIterator with passed index of where it should be placed
	 */
	public ListIterator<E> listIterator(int index) {
		ListIterator<E> listIterator = new LinkedListIterator(index);
		return listIterator;
	}
	
	
	/**
	 * Inner class for LinkedList that serves as a iterator
	 */
	private class LinkedListIterator implements ListIterator<E> {
		
		/** Represents the ListNode that would be returned on a call to previous()*/
		public ListNode previous;
		/** represents the ListNode that would be returned on a call to next() */
		public ListNode next; 
		/** the index that would be returned on a call to previousIndex()*/
		public int previousIndex;
		/** the index that would be returned on a call to nextIndex()*/
		public int nextIndex;		
		/** Represents the ListNode that was returned on the last call */
		private ListNode lastRetrieved;
		
		/**
		 * Constructs the LinkedListIterator and throws an IndexOutOfBoundsException
		 * if index is invalid. If the index is within bounds, the previous ListNode
		 * points to the ListNode at index-1 and the next ListNode points to the ListNode
		 * at index. 
		 * @param index the index where the LinkedListIterator will be placed
		 * @throws IndexOutOfBoundsException if the index is invalid 
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size()) {
				throw new IndexOutOfBoundsException();
			}
			previous = front;
			next = previous.next;
			for (int i = 0; i < index; i++) {
				previous = next;
				next = next.next; 
			}
			previousIndex = index - 1;
			nextIndex = index; 
			
			lastRetrieved = null;
			
		}
		
		/**
		 * Modifies the element returned by the last call to previous()
		 * or next(). If lastRetrieved is null an IllegalStateException
		 * is thrown. If the element to be set is null, a NullPointerException
		 * is thrown. 
		 * @param element the element to be set 
		 * @throws IllegalStateException if lastRetrieved is null
		 */
		@Override
		public void set(E element) {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			} else if (element == null) {
				throw new NullPointerException();
			}
			lastRetrieved.data = element; 
		}
		
		/**
		 * Returns true if there is a next node in the list 
		 * @return true if there is a next node in the list
		 */
		public boolean hasNext() {
			return next.data != null;
		}
		
		/**
		 * Returns true if there is a previous node in the list
		 * @return true if there is a previous node in the list
		 */
		public boolean hasPrevious( ) {
			return previous.data != null;
		}
		
		/**
		 * Returns the element in the next node. Throws a NoSuchElementException
		 * if the next node has null data.
		 * @throws NoSuchElementException if the next node has null data. 
		 * @return lastRetrieved.data returns the element in the next node
		 */
		public E next() {
			if (next.data == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = next;
			next = next.next;
			previous = lastRetrieved;
			nextIndex++;
			previousIndex++;
			return lastRetrieved.data;
		}
		
		/**
		 * Returns the element in the previous node. Throws a NoSuchElementException
		 * if the next node has null data. 
		 * @throws NoSuchElementException if the previous node has null data. 
		 * @return lastRetrieved.data returns the element in the previous node
		 */
		public E previous() {
			if (previous == null) {
				throw new NoSuchElementException();
			}

			lastRetrieved = previous;
			previous = previous.prev;
			next = lastRetrieved;
			nextIndex--;
			previousIndex--;
			return lastRetrieved.data;
		}
		
		/**
		 * Returns the index of the next node.
		 * @return index index of the next node
		 */
		public int nextIndex() {
			return nextIndex;
		}
		
		/**
		 * Returns the index of the previous node.
		 * @return index index of the previous node 
		 */
		public int previousIndex() {
			return previousIndex;
		}
		
		/**
		 * Adds the passed in element to the list. If the element
		 * to be added is null, a NullPointerException is thrown.
		 * @throws NullPointerException if the element to be added is null. 
		 */
		@Override
		public void add(E element) {
			if (element == null) {
				throw new NullPointerException();
			}
			ListNode newNode = new ListNode(element, previous, next);
			previous.next = newNode;
			next.prev = newNode;
			
			size++;
			
			lastRetrieved = null;
		}
		
		/**
		 * If lastRetrieved is null, an IllegalStateException is thrown. 
		 * Otherwise, the lastRetrieved node is removed and size is decremented.
		 * @throws IllegalStateException if lastRetrieved in null. 
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			if (lastRetrieved == previous) {
				previous.prev.next = next;
				next.prev = previous.prev;
			} else if (lastRetrieved == next) {
				next.next.prev = previous;
				previous.next = next.next; 
			}
			size--;
			lastRetrieved = null; 
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Inner class of LinkedList representing a node in the list
	 */
	private class ListNode {
		/** Data in the node*/
		public E data;
		/** The next node in the list*/
		public ListNode next;
		/** The previous node in the list*/
		public ListNode prev;
		
		/**
		 * Constructor that sets the data field 
		 * @param data Data in the node
		 */
		public ListNode(E data) {
			this.data = data;
		}
		
		/**
		 * Constructor that sets all fields
		 * @param data Data in the node 
		 * @param prev The previous node in the list 
		 * @param next The next node in the list 
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next; 
		}
	}
}
