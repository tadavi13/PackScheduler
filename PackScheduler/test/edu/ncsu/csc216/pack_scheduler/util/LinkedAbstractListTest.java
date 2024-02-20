/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * Tests LinkedAbstractList
 * @author Ken Ogura
 */
public class LinkedAbstractListTest {

	
	/**
	 * Tests the add method when adding to the front and middle of the list. 
	 */
	@Test
	public void testAdd() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(100);
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.add(999, "test"));
		list.add(0, "test");
		list.add(1, "test2");
		list.add(2, "test3");
		assertThrows(IllegalArgumentException.class,
				() -> list.add(2, "test"));
		list.add(1, "test4");
		assertEquals("test4", list.get(1));
		assertEquals("test2", list.get(2));
		assertEquals("test3", list.get(3));
		list.add(0, "test5");
		assertEquals("test5", list.get(0));
		assertEquals("test3", list.get(4));
	}
	
	/**
	 * Tests the add method when adding to index 0. 
	 */
	@Test
	public void testAddAt0() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(100);
		list.add(0, "test");
		list.add(0, "test2");
		list.add(1, "test3");
		assertEquals("test3", list.get(1));

	}
	
	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(100);
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(-999));
		list.add(0, "test");
		list.remove(0);
		assertEquals(0, list.size());
		list.add(0, "test");
		list.add(1, "test2");
		list.add(2, "test3");
		list.remove(1);
		assertEquals("test", list.get(0));
		assertEquals("test3", list.get(1));
	}
	
	/**
	 * Tests the set method. 
	 */
	@Test
	public void testSet() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(100);
		list.add(0, "test");
		assertThrows(NullPointerException.class,
				() -> list.set(0, null));
		list.add(1, "test2");
		list.add(2, "test3");
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.set(999, "test4"));
		assertEquals(3, list.size());
		list.set(1, "test4");
		assertEquals(3, list.size());
		assertEquals("test4", list.get(1));
	}
	
	/**
	 * Tests the set method when setting at size. 
	 */
	@Test
	public void testSetAtSize() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(100);
		list.add(0, "test");
		list.add(1, "test2");
		list.add(2, "test3");
		assertEquals(3, list.size());
		list.add(3, "test4");
		assertEquals("test4", list.set(3, "test5"));
		assertEquals("test5", list.get(3));
	}
	
	/**
	 * Tests the set method when setting multiple elements 
	 */
	@Test
	public void testSetMultipleElements() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(100);
		list.add(0, "test0");
		list.add(1, "test1");
		list.add(2, "test2");
		list.add(3, "test3");
		assertEquals("test1", list.set(1, "test11"));
		assertEquals("test11", list.get(1));
		assertEquals("test0", list.set(0, "test00"));
		assertEquals("test00", list.get(0));
		assertEquals("test3", list.set(3, "test33"));
		assertEquals("test33", list.get(3));
		assertEquals("test2", list.get(2));
	}
}
