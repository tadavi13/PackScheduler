package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * Tests the LinkedStack class
 * @author Ken Ogura
 */
public class LinkedStackTest {
	
	/**
	 * Tests the push method
	 */
	@Test
	public void testPush() {
		LinkedStack<String> stack = new LinkedStack<String>(10);
		stack.push("test");
		assertEquals("test", stack.pop());
	}
	
	/**
	 * Tests the isEmpty method. 
	 */
	@Test
	public void testIsEmpty() {
		LinkedStack<String> stack = new LinkedStack<String>(10);
		assertTrue(stack.isEmpty());
		stack.push("test");
		assertFalse(stack.isEmpty());
		assertEquals("test", stack.pop());
		assertTrue(stack.isEmpty());
	}
	
}
