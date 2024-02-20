package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;
import java.util.Arrays;

/**
 * Custom implementation of an ArrayList 
 * @param <E> A generic type object that will be held in the ArrayList
 * @author Ken Ogura
 */
public class ArrayList<E> extends AbstractList<E> {
	/** Represents the initial size of the list*/
	private static final int INIT_SIZE = 10;
	/** The list where elements will be stored*/
	private E[] list;
	/** Represents the number of elements in the list*/
	private int size;
	
	/**
	 * Constructs the custom ArrayList object. 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
	}
	
	@Override
	public void add(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		} else if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}
		for (E eachElement : list) {
			if (eachElement != null && eachElement.equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		if (size() == list.length) {
			growArray(); 
		}
		if (idx != size) {
			for (int i = size - 1; i >= idx; i--) {
				list[i + 1] = list[i];
			}
			list[idx] = element;
			size++;
		} else {
			list[size++] = element;
		}
	}
	
	/**
	 * Helper method to double the length of the list when the adding elements
	 * at capacity. 
	 */
	private void growArray() {
		int newLength = list.length * 2;
		list = Arrays.copyOf(list, newLength);
	}
	
	/**
	 * Removes elements at the given index.
	 * @throws IllegalArgumentException if the index if less than 0 or greater than or equal to the number of elements in the list
	 * @return returnElement the element that is removed from the given index
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E returnElement = list[idx];
		if (idx != size - 1) {
			for (int i = idx; i < size - 1; i++) {
				list[i] = list[i + 1];
			}
			list[size - 1] = null;
			size--;
		} else {
			list[idx] = null;
			size -= 1;
		}
		return returnElement;
	}
	
	/**
	 * Replaces the element at the given index and returns the element that will be replaced. 
	 * @return returnElement E the element that the given index of the list 
	 * @throws IllegalArgumentException if the index is out of bounds or the passed in element is null or if the element is already in the list.
	 */
	@Override
	public E set(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		} else if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		for (E eachElement : list) {
			if (eachElement != null && eachElement.equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		E returnElement = list[idx];
		list[idx] = element; 
 		return returnElement;
	}
	
	/**
	 * Returns the element at the given index. 
	 * @param idx the index where the element will be retrieved 
	 * @return E an element present at the given index of the list
	 * @throws IllegalArgumentException if the index if less than 0 or greater than or equal to the number of elements in the list
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		} 
		return list[idx];
	}
	
	/**
	 * Returns the number of elements in the list
	 * @return size an integer representing the number of elements in the list
	 */
	public int size() {
		return size;
	}
}
