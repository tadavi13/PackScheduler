package edu.ncsu.csc216.pack_scheduler.util;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * Tests the ArrayStack class
 * @author Ken Ogura
 */
public class ArrayStackTest {

	/**
	 * Tests the push method
	 */
	@Test
	public void testPush() {
		ArrayStack<String> stack = new ArrayStack<String>(10);
		stack.push("test");
		assertEquals("test", stack.pop());
	}
	
	/**
	 * Tests the isEmpty method. 
	 */
	@Test
	public void testIsEmpty() {
		ArrayStack<String> stack = new ArrayStack<String>(10);
		assertTrue(stack.isEmpty());
		stack.push("test");
		assertFalse(stack.isEmpty());
		assertEquals("test", stack.pop());
		assertTrue(stack.isEmpty());
	}
	
	
}
