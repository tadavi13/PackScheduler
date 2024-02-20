package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.Test;

/**
 * Tests the Linked List recursive class
 * 
 * @author Tyler Daivis
 */
public class LinkedListRecursiveTest {
	/**
	 * Tests the contains method
	 */
	@Test
	public void testContains() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add(0, "test");
		list.add(1, "test2");
		list.add(2, "test3");
		assertEquals("test", list.get(0));
		assertEquals("test2", list.get(1));
		assertEquals("test3", list.get(2));
		
		assertTrue(list.contains("test"));
		assertTrue(list.contains("test2"));
		assertTrue(list.contains("test3"));
		assertFalse(list.contains("test4"));
		assertFalse(list.contains(" "));
		assertFalse(list.contains(null));
	}
	
	/**
	 * Tests the get method
	 */
	@Test
	public void testGet() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		
		list.add(0, "test");
		list.add(1, "test2");
		list.add(2, "test3");
		assertEquals("test", list.get(0));
		assertEquals("test2", list.get(1));
		assertEquals("test3", list.get(2));
		list.add(1, "test4");
		assertEquals("test", list.get(0));
		assertEquals("test4", list.get(1));
		assertEquals("test2", list.get(2));
		assertEquals("test3", list.get(3));
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.add(999, "test"));
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.add(-1, "test"));
	}
	
	/**
	 * Tests the add method
	 */
	@Test
	public void testAdd() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.add(999, "test"));
		list.add(0, "test");
		list.add(1, "test2");
		list.add(2, "test3");
		assertEquals("test", list.get(0));
		assertEquals("test2", list.get(1));
		assertEquals("test3", list.get(2));
		assertEquals(3, list.size());
		assertThrows(NullPointerException.class,
				() -> list.add(2, null));
		assertThrows(IllegalArgumentException.class,
				() -> list.add(2, "test"));
		assertEquals(3, list.size());
		list.add(1, "test4");
		assertEquals("test", list.get(0));
		assertEquals("test4", list.get(1));
		assertEquals("test2", list.get(2));
		assertEquals("test3", list.get(3));
		assertEquals(4, list.size());
		list.add(0, "test5");
		assertEquals("test5", list.get(0));
		assertEquals("test", list.get(1));
		assertEquals("test4", list.get(2));
		assertEquals("test2", list.get(3));
		assertEquals("test3", list.get(4));
		assertEquals(5, list.size());
	}
	
	
	/**
	 * Tests the add method when adding an element without an index
	 */
	@Test
	public void testAddElement() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.add(999, "test"));
		list.add("test");
		list.add("test2");
		list.add("test3");
		assertEquals("test", list.get(0));
		assertEquals("test2", list.get(1));
		assertEquals("test3", list.get(2));
		assertEquals(3, list.size());
		assertThrows(NullPointerException.class,
				() -> list.add(null));
		assertThrows(IllegalArgumentException.class,
				() -> list.add("test"));
	}
	
	/**
	 * Tests the add method when adding to index 0. 
	 */
	@Test
	public void testAddAt0() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add(0, "test");
		list.add(0, "test2");
		assertEquals("test2", list.get(0));
		assertEquals("test", list.get(1));
		list.add(1, "test3");
		assertEquals("test2", list.get(0));
		assertEquals("test", list.get(2));
		assertEquals("test3", list.get(1));
	}
	
	/**
	 * Tests the remove method at index
	 */
	@Test
	public void testRemoveAtIdx() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(-999));
		list.add(0, "test");
		list.remove(0);
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
		list.add(0, "test");
		list.add(1, "test2");
		list.add(2, "test3");
		assertEquals("test2", list.remove(1));
		assertEquals("test", list.get(0));
		assertEquals("test3", list.get(1));
	}
	
	/**
	 * Tests remove method when given an element
	 */
	@Test
	public void testRemoveElement() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(-999));
		list.add(0, "test");
		list.remove("test");
		assertEquals(0, list.size());
		list.add(0, "test");
		list.add(1, "test2");
		list.add(2, "test3");
		assertTrue(list.remove("test2"));
		assertEquals("test", list.get(0));
		assertEquals("test3", list.get(1));
	}
	
	/**
	 * Tests the set method 
	 */
	@Test
	public void testSet() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add(0, "test");
		list.add(1, "test2");
		list.add(2, "test3");
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.set(999, "test4"));
		assertThrows(NullPointerException.class,
				() -> list.set(2, null));
		assertEquals(3, list.size());
		list.set(1, "test4");
		assertEquals(3, list.size());
		assertEquals("test4", list.get(1));
	}
}
