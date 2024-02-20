package edu.ncsu.csc216.pack_scheduler.util;

/**
 * LinkedListRecursive class for a linked list of list nodes that are recursively implemented.
 * Contains the inner class for list nodes. Class itself contains methods for a linked list
 * such as adding, getting, setting, and removing elements, as well as methods to check
 * if the list already contains an element. A LinkedListRecursive keeps track of its size 
 * and a reference to its first node.
 * 
 * @author Tyler Davis
 * @author Ken Ogura
 * @param <E> Generic type for the linked recursive list
 */
public class LinkedListRecursive<E> {
	/** Size of the linked recursive list */
	private int size;
	/** List node reference of the first list node in the list */
	private ListNode front;
	
	/**
	 * Constructor for a LinkedListRecursive. Initializes fields, such as
	 * front to null and size of the list to 0.
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	/**
	 * Checks if the list is empty.
	 * @return true if size is 0 or false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Adds an element to the end of the list.
	 * @param element to add to the list
	 * @throws IllegalArgumentException if the element is already in the list
	 * @throws NullPointerException if the element to be added is null
	 * @return whether the element was added to the list
	 */
	public boolean add(E element) {
		if (front == null) {
			front = new ListNode(element, null);
			size++;
			return true;
		}
		if (element == null) {
			throw new NullPointerException();
		} else {
			if (front.contains(element)) {
				throw new IllegalArgumentException();
			}
			
			return front.add(element);
		}
	}
	
	/**
	 * Adds an element to a specified index in the list.
	 * @param idx the index to add an element at
	 * @param element to add to the list
	 * @throws NullPointerException if the element is null
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws IllegalArgumentException if the element is already in the list
	 */
	public void add(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (front == null) {
			front = new ListNode(element, null);
			size++;
		} else {
			if (front.contains(element)) {
				throw new IllegalArgumentException();
			}
			
			front.add(idx, element);
		}
	}
	
	/**
	 * Gets the data value of a node at a given index.
	 * @param idx of a node in the list to return the data of
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @return data of the list node
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		return front.get(idx);
	}
	
	/**
	 * Removes the passed in element from the list. Returns true if the
	 * element is removed and false otherwise.
	 * @param element the element to be removed
	 * @return true if the element is removed 
	 */
	public boolean remove(E element) {
		if (element == null) {
			return false;
		}
		if (size == 0) {
			return false;
		}
		if (element == front.data) {
			front = front.next; 
			size--;
			return true; 
		}
		
		return front.remove(element);
	}
	
	/**
	 * Removes an element from the list given an index and returns its value
	 * @param idx the index of the element to be removed
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws NullPointerException if the list is empty 
	 * @return E the value of the element that is removed
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		} 
		if (idx == 0) {
			E removed = front.data;
			front = front.next;
			size--;
			return removed; 
		}
		if (size == 0) {
			throw new NullPointerException();
		}
		return front.remove(idx);
	}
	
	/**
	 * Sets the passed in element at the passed in index. Returns
	 * the data that was previously in the ListNode. 
	 * @param idx the index where the element will be set
	 * @param element the element to be set 
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws NullPointerException if the passed in element is null 
	 * @throws IllegalArgumentException if the element is already in the list 
	 * @return E the data that was previously in the ListNode
	 */
	public E set(int idx, E element) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		} 
		if (element == null) {
			throw new NullPointerException();
		}
		
		if (front.contains(element)) {
			throw new IllegalArgumentException(); 
		}
		
		if (idx == 0) {
			E frontData = front.data;
			ListNode next = front.next;
			ListNode newFront = new ListNode(element, next);
			front = newFront;
			return frontData;
		}

		return front.set(idx, element);
	}
	
	/**
	 * Checks whether the list already contains the element passed.
	 * @param element to compare to the list to see if it is present
	 * @return true if the element is already in the list or false otherwise
	 */
	public boolean contains(E element) {
		if (front == null) {
			return false;
		} 
		return front.contains(element);
	}
	
	/**
	 * Inner class for ListNodes in a LinkedListRecursive. Contains private helper methods
	 * to execute recursive calls for the list public methods. A ListNode knows its data and
	 * holds a reference to the list node that comes next in the list.
	 * 
	 * @author Tyler Davis
	 * @author Ken Ogura
	 */
	private class ListNode {
		/** Data of the list node */
		public E data;
		/** Next list node in the list */
		public ListNode next;
		
		/**
		 * Constructor of a ListNode given its data and a reference to the next node
		 * in a list.
		 * @param data of the list node
		 * @param next reference for the next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * Helper add method to recursively call to add an element to the end
		 * of a list.
		 * @param element to add to the end of a list
		 * @return whether the element was added
		 */
		public boolean add(E element) {
			if (next == null) {
				next = new ListNode(element, null);
				size++;
				return true;
			} else {
				return next.add(element);
			}
		}
		
		/**
		 * Helper add method to recursively call to add an element at a specified
		 * index.
		 * @param idx to add an element at
		 * @param element to add to a list
		 */
		public void add(int idx, E element) {
			if (idx == 0) {
				ListNode temp = front;
				front = new ListNode(element, temp);
				size++;
			} else if (idx == 1) {
				ListNode temp = next;
				next = new ListNode(element, temp);
				size++;
			} else {
				next.add(idx - 1, element);
			}
		}
		
		/**
		 * Helper method to recursively call to get an element at a specified
		 * index.
		 * @param idx of an element to get
		 * @return data value of the node at the index
		 */
		public E get(int idx) {
			if (idx == 0) {
				return data;
			} else {
				return next.get(idx - 1);
			}
		}
		
		/**
		 * Removes an element from the list given an index
		 * @param idx the index of the element to be removed 
		 * @return removed the data of the element that is removed
		 */
		public E remove(int idx) {
			if (idx == 1) {
				E removed = next.data; 
				next = next.next;
				size--;
				return removed;
			}
			return next.remove(idx - 1);
		}
		
		/**
		 * Removes the passed in element from the list
		 * @param element the element to be removed
		 * @return true if the element is removed
		 */
		public boolean remove(E element) {
			if (next == null) {
				return false;
			}
			if (next.data == element) {
				next = next.next;
				size--;
				return true;
			}
			
			return next.remove(element);
		}
		
		/**
		 * Sets the passed in element at the passed in index. Returns
		 * the data that was previously in the ListNode. 
		 * @param idx the index where the element will be set
		 * @param element the element to be set  
		 * @return E the data that was previously in the ListNode
		 */
		public E set(int idx, E element) {
			if (idx == 1) {
				E removed = next.data; 
				next.data = element;
				return removed;
			}
			return next.set(idx - 1, element);
		}
		
		/**
		 * Helper method to check if a list contains a specified element.
		 * @param element to check to see if it is present in the list
		 * @return true if the element is already in the list or false otherwise
		 */
		public boolean contains(E element) {
			if (element == this.data) {
				return true;
			} else if (next == null) {
				return false;
			} else {
				return next.contains(element);
			}
		}
	}
}