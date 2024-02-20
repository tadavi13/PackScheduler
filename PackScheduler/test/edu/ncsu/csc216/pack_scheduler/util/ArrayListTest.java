package edu.ncsu.csc216.pack_scheduler.util;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.Test;

/**
 * Tests the custom array list implementation.
 * @author Ken Ogura
 */
public class ArrayListTest {
	
	/**
	 * Tests the get method.
	 */
	@Test
	public void testGet() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "test");
		assertEquals("test", list.get(0));
	}
	/**
	 * Tests the add method when adding to the front and middle of the list. 
	 */
	@Test
	public void testAdd() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "test");
		list.add(1, "test2");
		list.add(2, "test3");
		list.add(1, "test4");
		assertEquals("test4", list.get(1));
		assertEquals("test2", list.get(2));
		assertEquals("test3", list.get(3));
		list.add(0, "test5");
		assertEquals("test5", list.get(0));
		assertEquals("test3", list.get(4));
	}
	
	/**
	 * Tests the growArray method by creating an ArrayList with a size greater than 10
	 */
	@Test
	public void testGrowArray() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i <= 10; i++) {
			list.add(i, "test" + i);
		}
		assertEquals(11, list.size());
	}
	
	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		ArrayList<String> list = new ArrayList<String>();
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
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "test");
		list.add(1, "test2");
		list.add(2, "test3");
		list.set(1, "test4");
		assertEquals(3, list.size());
	}
	
	
}
