package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;



/**
 * Tests adding to SortedList
 */
public class SortedListTest {

	/**
	 * Tests the construction of a sorted list
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		//Remember the list's initial capacity is 10
		int testNumber = 0;
		for (int i = 0; i < 11; i++) {
			list.add(Integer.toString(testNumber));
			testNumber++;
		}
		assertEquals(11, list.size());
		
		
	}

	/**
	 * Tests the add method of SortedList
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		// adds to front
		assertTrue(list.add("a"));
		assertEquals(2, list.size());
		assertEquals("a", list.get(0));
		assertEquals("banana", list.get(1));
		
		// adds to back
		assertTrue(list.add("z"));
		assertEquals(3, list.size());
		assertEquals("a", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("z", list.get(2));
		
		// adds to middle
		assertTrue(list.add("m"));
		assertEquals(4, list.size());
		assertEquals("a", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("m", list.get(2));
		assertEquals("z", list.get(3));
		
		// checks for exception with adding null
	assertThrows(NullPointerException.class,
				() -> list.add(null));
		
	// checks for exception with adding a duplicate
	assertThrows(IllegalArgumentException.class,
			() -> list.add("banana"));
	}
	
	/**
	 * Tests the get method of SortedList 
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//Test getting an element from an empty list
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(0));
		
		
		//Add some elements to the list
		list.add("a");
		list.add("b");
		
		//Test getting an element at an index < 0 (-1)
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(-1));
		
		
		//Test getting an element at the size of list
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(list.size()));
		
	}
	
	/**
	 * Test the remove method of SortedList
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		// Test removing from an empty list
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(0));
		
		//Add some elements to the list - at least 4
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		//TODO Test removing an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(-1));
		
		
		// Test removing an element at size
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(list.size()));
		
		// Test removing a middle element
		assertEquals("c", list.remove(2));
		assertEquals(3, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("d", list.get(2));
		
		// Test removing the last element
		assertEquals("d", list.remove(2));
		assertEquals(2, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		
		// Test removing the first element
		assertEquals("a", list.remove(0));
		assertEquals(1, list.size());
		assertEquals("b", list.get(0));
		
		// Test removing the last element
		assertEquals("b", list.remove(0));
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests the indexOf method for sortedList
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		assertEquals(-1, list.indexOf("a"));
		assertEquals(0, list.size());
		
		//Add some elements
		list.add("a");
		list.add("b");
		
		//Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(0, list.indexOf("a"));
		assertEquals(1, list.indexOf("b"));
		assertEquals(-1, list.indexOf("c"));
		assertEquals(2, list.size());
		
		//Test checking the index of null
		assertThrows(NullPointerException.class,
				() -> list.indexOf(null));
		
	}
	
	/**
	 * Tests the clear method in SortedList
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("a");
		list.add("b");
		
		// Clear the list
		list.clear();
		
		// Test that the list is empty
		assertEquals(0, list.size());
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(0));
		
	}

	/**
	 * Tests the isEmpty method of SortedList
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list starts empty
		assertTrue(list.isEmpty());
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(0));
		
		//Add at least one element
		list.add("a");
		assertEquals("a", list.get(0));
		
		//Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests the contains method of SortedList
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertFalse(list.contains("a"));
		
		//Add some elements
		list.add("a");
		list.add("b");
		
		//Test some true and false cases
		assertTrue(list.contains("a"));
		assertFalse(list.contains("c"));
		assertTrue(list.contains("b"));
	}
	
	
	/**
	 * Tests the equals method of SortedList
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("a");
		list1.add("b");
		list1.add("c");
		
		list2.add("a");
		list2.add("b");
		list2.add("c");
		
		list3.add("a");
		list3.add("b");
		list3.add("d");
		
		// Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertFalse(list1.equals(list3));
		assertFalse(list2.equals(list3));
		
	}
	
	/**
	 * Tests the HashCode method of SortedList
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("a");
		list1.add("b");
		list1.add("c");
		
		list2.add("a");
		list2.add("b");
		list2.add("c");
		
		list3.add("a");
		list3.add("b");
		list3.add("d");
		
		// Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
		
	}

}
 