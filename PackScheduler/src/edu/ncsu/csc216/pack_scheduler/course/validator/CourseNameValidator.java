/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * A class that utilizes the FSM pattern to determine whether a course name is valid.
 * @author Ken Ogura
 */
public class CourseNameValidator {
	/** Represents whether the end state is reached*/
	private boolean validEndState; 
	/** The number of letters that have been entered*/
	private int letterCount;
	/** The number of digits that have been entered*/
	private int digitCount; 
	/** Represents the current state of the FSM*/
	private State currentState;
	
	/**
	 * Constructor for CourseNameValidator.
	 */
	public CourseNameValidator() {
		// Constructor object for use when using method isValid
	}
	
	/**
	 * Sets the first state as InitialState and sets letter and digit counts to 0. 
	 * Valid end state is initially set to false. Checks if a course name is valid. 
	 * Returns true if valid and false if invalid.
	 * @param courseName The name of the course. 
	 * @return true if course name is valid and false if it is invalid
	 * @throws InvalidTransitionException If a character in courseName is not a letter or digit.
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		this.currentState = new InitialState();
		this.letterCount = 0;
		this.digitCount = 0;
		this.validEndState = false;
		char[] characters = courseName.toCharArray();
		for (char c : characters) {
			if (Character.isAlphabetic(c)) {
				currentState.onLetter();
			} else if (Character.isDigit(c)) {
				currentState.onDigit();
			} else {
				currentState.onOther();
			}
		}
		if (digitCount != 3) {
			throw new InvalidTransitionException("Course name must have 3 digits.");
		}
		return this.validEndState;
	}
	
	/**
	 * An abstract class representing the overall structure of a state class with methods for dealing with letter and digit characters
	 * as well as invalid characters. 
	 */
	private abstract class State {
		
		/**
		 * Abstract method dealing with letter characters
		 * @throws InvalidTransitionException if a letter character is inappropriate at the current state. 
		 */
		public abstract void onLetter() throws InvalidTransitionException;
		
		/**
		 * Abstract method dealing with digit characters
		 * @throws InvalidTransitionException if a digit character is inappropriate at the current state. 
		 */
		public abstract void onDigit() throws InvalidTransitionException;
		
		/**
		 * Concrete method dealing with characters that are not letters or digits. 
		 * @throws InvalidTransitionException if the method is called due a non letter or digit character. 
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * A concrete class extending the state class that deals with characters when previous characters
	 * were letters. 
	 */
	private class LetterState extends State {
		/** Represents the maximum number of prefix letters allowed in a course name */
		private static final int MAX_PREFIX_LETTERS = 4;
		
		/**
		 * Empty constructor for the letter state class. 
		 */
		private LetterState() {
			
		}
		
		/**
		 * Concrete method dealing with digit characters. Increments digitCount and sets currentState to
		 * number state. 
		 */
		public void onDigit() {
			digitCount++;
			currentState = new NumberState();
		}
		
		/**
		 * Abstract method dealing with letter characters
		 * @throws InvalidTransitionException if the letter count reaches a number greater than the maximum number of prefix letters. 
		 */
		public void onLetter() throws InvalidTransitionException {
			if (letterCount == MAX_PREFIX_LETTERS) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
			letterCount++;
			
		}
	}
	
	/**
	 * A concrete class extending the state class that deals with the optional character for the course suffix.
	 */
	private class SuffixState extends State  {
		/**
		 * Constructor for the suffix state that sets validEndState to true. 
		 */
		private SuffixState() {
			validEndState = true;
		}
		
		
		/**
		 * Concrete method dealing with unnecessary digit characters. 
		 * @throws InvalidTransitionException if a digit is passed in when state is in the suffix state. 
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
		
		/**
		 * Concrete method dealing with unnecessary letter characters. 
		 * @throws InvalidTransitionException if a letter is passed in when state is in the suffix state. 
		 */
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}
	}
	
	/**
	 * A concrete class extending the state class that deals the first character in courseName. 
	 */
	private class InitialState extends State {
		
		/**
		 * Empty constructor for the initial state class. 
		 */
		private InitialState() {
			
		}
		
		/**
		 * Concrete method dealing with unnecessary digit characters. 
		 * @throws InvalidTransitionException if a digit is passed in when state is in the initial state. 
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
		
		/**
		 * Concrete method dealing with letter characters. Increments letter count and sets current state
		 * to letter state. 
		 */
		public void onLetter() {
			letterCount++;
			currentState = new LetterState();
		}
		
	}
	
	/**
	 * A concrete class extending the state class that deals with characters after the course prefix characters
	 * have been entered. 
	 */
	private class NumberState extends State {
		/** Represents the maximum number of digits a course name can have*/
		private static final int COURSE_NUMBER_LENGTH = 3;
		
		/**
		 * Empty constructor for the number state class. 
		 */
		private NumberState() {
			
		}
		
		/**
		 * Concrete method dealing with digit characters. If a digit character is passed in when digitCount is already
		 * at the maximum number of course name digits, an InvalidTransitionException is thrown. Otherwise, digitCount is
		 * incremented and validEndState is set to true if the increment led to digitCount being equal to the maximum number 
		 * of course name digits.  
		 * @throws InvalidTransitionException if a digit character is passed in when digitCount is already at the maximum number of course name digits
		 */
		public void onDigit() throws InvalidTransitionException {
			if (digitCount == COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
			digitCount++;
			if (digitCount == COURSE_NUMBER_LENGTH) {
				validEndState = true;
			}
		}
		
		/**
		 * Concrete method dealing with letter characters. Throws an InvalidTransitionException if a letter is passed in
		 * and digit count is not equal to COURSE_NUMBER_LENGTH. Otherwise increments letterCount and the current state is set to 
		 * suffix state. 
		 * @throws InvalidTransitionException if a letter is passed in and digit count is not equal to COURSE_NUMBER_LENGTH
		 */
		public void onLetter() throws InvalidTransitionException {
			if (digitCount != COURSE_NUMBER_LENGTH) { 
				throw new InvalidTransitionException("Course name must have 3 digits.");
			} 
			letterCount++;
			currentState = new SuffixState();
		}
	}
	
}
