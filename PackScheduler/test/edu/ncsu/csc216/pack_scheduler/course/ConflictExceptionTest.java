/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the ConflictException class
 * @author Kentaro Ogura
 */
class ConflictExceptionTest {

	/**
	 * Tests the ConflictException's parameterized constructor.
	 */
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}
	
	/**
	 * Tests the ConflictException's parameterless constructor.
	 */
	@Test
	public void testConflictExceptionParameterless() {
		ConflictException ce = new ConflictException();
	    assertEquals("Schedule conflict.", ce.getMessage());
	}
	

}
