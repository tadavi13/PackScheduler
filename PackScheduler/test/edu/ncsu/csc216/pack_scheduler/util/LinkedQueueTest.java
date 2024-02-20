/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class contains test methods for LinkedQueue
 * @author Chris Brown
 * @author Tyler Davis
 * @author Ken Ogura
 */
class LinkedQueueTest {

	/** Linked Queue 1 for use in test methods */
	private LinkedQueue<String> q1;
	/** Linked Queue 2 for use in test methods */
	private LinkedQueue<String> q2;
	/** Linked Queue 2 for use in test methods */
	private LinkedQueue<String> q3;
	
	/**
	 * Set up before each test
	 */
	@BeforeEach
	public void setUp() {
		q1 = new LinkedQueue<String>(10);
		q2 = new LinkedQueue<String>(10);
		q3 = new LinkedQueue<String>(10);
	}
	
	/**
	 * Test method for enqueue and dequeue
	 */
	@Test
	void testEnqueueAndDequeue() {
		assertTrue(q1.isEmpty());
		q1.enqueue("test");
		assertFalse(q1.isEmpty());
		assertEquals("test", q1.dequeue());
		assertTrue(q1.isEmpty());
	}

	/**
	 * Test method for insertions and removals
	 */
	@Test
	void testInsertAndRemove() {
		//insert single element
		assertTrue(q1.isEmpty());
		q1.enqueue("first");
		assertFalse(q1.isEmpty());
		assertEquals(1, q1.size());
		
		//insert multiple elements
		assertTrue(q2.isEmpty());
		q2.enqueue("first");
		q2.enqueue("second");
		q2.enqueue("third");
		assertEquals(3, q2.size());
				
		//remove single element
		assertEquals("first", q1.dequeue());
		assertTrue(q1.isEmpty());
		assertEquals(0, q1.size());
				
		//remove multiple elements
		assertEquals("first", q2.dequeue());
		assertEquals("second", q2.dequeue());
		assertFalse(q2.isEmpty());
		assertEquals(1, q2.size());
				
		//remove last element
		assertEquals("third", q2.dequeue());
		assertTrue(q2.isEmpty());
		assertEquals(0, q2.size());
		
		//interleaved insertions and removals
		assertTrue(q3.isEmpty());
		q3.enqueue("first");
		q3.enqueue("second");
		assertEquals(2, q3.size());
		assertEquals("first", q3.dequeue());
		assertEquals(1, q3.size());
		q3.enqueue("third");
		assertEquals(2, q3.size());
		assertEquals("second", q3.dequeue());
		assertEquals("third", q3.dequeue());
		assertTrue(q3.isEmpty());
	}
	
	/**
	 * Test method for removing from empty queue
	 */
	@Test
	void testRemoveFromEmpty() {
		//attempt to remove element from empty queue
		assertTrue(q1.isEmpty());
		
		Exception e1 = assertThrows(NoSuchElementException.class, () -> q1.dequeue());
		assertEquals(null, e1.getMessage());
	}


	/**
	 * Test method for setCapacity
	 */
	@Test
	void testSetCapacity() {
		//set capacity to size
		assertTrue(q1.isEmpty());
		q1.enqueue("first");
		q1.enqueue("second");
		assertEquals(2, q1.size());
		assertDoesNotThrow(() -> q1.setCapacity(2));
		
		//set capacity to less than size
		assertTrue(q2.isEmpty());
		q2.enqueue("first");
		q2.enqueue("second");
		assertEquals(2, q2.size());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> q2.setCapacity(1));
		assertEquals("Capacity less than size", e2.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> q2.setCapacity(-1));
		assertEquals("Capacity less than 0", e3.getMessage());
	}
}
