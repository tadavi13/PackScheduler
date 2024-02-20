/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * A custom checked exception thrown in the Conflict class.
 * @author Kentaro Ogura
 */
public class ConflictException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L; 
	
	/**
	 * Parameterized constructor that passes in the parameter to the parent constructor.
	 * @param message a string that will be passed to the parent constructor
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * A parameterless constructor that calls the parameterized constructor
	 * with the default message "Schedule conflict.".
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
