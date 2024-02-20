/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test InvalidTransitionException class
 */
class InvalidTransitionExceptionTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException#InvalidTransitionException(java.lang.String)}.
	 */
	@Test
	public void testConflictExceptionString() {
		  InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
		  assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException#InvalidTransitionException()}.
	 */
	@Test
	public void testConflictExceptionInvalid() {
		InvalidTransitionException c2 = new InvalidTransitionException();
//		assertEquals("Schedule Conflict", new ConflictException("Schedule Conflict"));
		assertEquals("Invalid FSM Transition.", c2.getMessage());
	}

}
