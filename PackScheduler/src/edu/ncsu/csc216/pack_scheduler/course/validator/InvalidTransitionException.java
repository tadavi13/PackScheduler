/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Public class Invalid Transition Exception
 * that contains constructors for the exception
 * @author Divya Srinivasan
 */
public class InvalidTransitionException extends Exception {
	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor with parameter that returns a custom message if exception if caught
	 * in activity
	 * @param message custom message
	 */
	public InvalidTransitionException(String message) {
		super(message);
		
	}
	
	/**
	 * Parameterless constructor that returns default message if exception is caught
	 * in activity
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
	

}
