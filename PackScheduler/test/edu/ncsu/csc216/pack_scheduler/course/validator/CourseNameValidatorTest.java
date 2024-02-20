package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the CourseNameValidator class
 * 
 * @author Christian Goga
 * @author Ken Ogura
 */
class CourseNameValidatorTest {
	
	/** CourseNameValidator object for method isValid to use */
	private CourseNameValidator validator;

	/**
	 * Resets CourseNameValidator object before each test
	 */
	@BeforeEach
	public void resetCourseNameValidator() {
		validator = new CourseNameValidator();
	}

	
	/**
	 * Test isValid in the initalState
	 */
	@Test
	void testIsValidInitialState() {
		try {
			validator.isValid("CSC116");
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		CourseNameValidator validator1 = new CourseNameValidator();
		
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> validator1.isValid("1SC116"));
		assertEquals("Course name must start with a letter.", e.getMessage(), "Incorrect exception thrown with invalid input");
	}
	
	/**
	 * Test isValid in the stateL
	 */
	@Test
	void testIsValidStateL() {
		try {
			validator.isValid("C116");
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/**
	 * Test isValid in the stateLL
	 */
	@Test
	void testIsValidStateLL() {
		try {
			validator.isValid("CS116");
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/**
	 * Test isValid in the stateLLL
	 */
	@Test
	void testIsValidStateLLL() {
		try {
			validator.isValid("CSCC116");
		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/**
	 * Test isValid in the stateLLLL
	 */
	@Test
	void testIsValidStateLLLL() {
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> validator.isValid("CSCCC116"));
		assertEquals("Course name cannot start with more than 4 letters.", e.getMessage(), "Incorrect exception thrown with invalid input");
	}
	
	/**
	 * Test isValid in the stateD
	 */
	@Test
	void testIsValidStateD() {
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> validator.isValid("CSC1C"));
		assertEquals("Course name must have 3 digits.", e.getMessage(), "Incorrect exception thrown with invalid input");
	}
	
	/**
	 * Test isValid in the stateDD
	 */
	@Test
	void testIsValidStateDD() {
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> validator.isValid("CSC11C"));
		assertEquals("Course name must have 3 digits.", e.getMessage(), "Incorrect exception thrown with invalid input");
	}
	
	/**
	 * Test isValid in the stateDDD
	 */
	@Test
	void testIsValidStateDDD() {
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> validator.isValid("CSC1111"));
		assertEquals("Course name can only have 3 digits.", e.getMessage(), "Incorrect exception thrown with invalid input");
	}
	
	/**
	 * Tests isValid for a valid string in the suffix state.
	 */
	@Test
	void testIsValidStateValidSuffix() {
		try {
			assertTrue(validator.isValid("CSC116C"));
		} catch (Exception e) {
			fail();
		}
	}
	
	/**
	 * Tests isValid for a invalid string in the suffix state with an extra character.
	 */
	@Test
	void testIsValidStateInvalidSuffixExtraLetter() {
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> validator.isValid("CSC116CS"));
		assertEquals("Course name can only have a 1 letter suffix.", e.getMessage(), "Incorrect exception thrown with invalid input");
	}
	
	/**
	 * Test isValid in the invalid string in the suffix state with an extra digit.
	 */
	@Test
	void testIsValidStateInvaliduffixExtraDigit() {
		Exception e1 = assertThrows(InvalidTransitionException.class,
				() -> validator.isValid("CSC116C1"));
		assertEquals("Course name cannot contain digits after the suffix.", e1.getMessage(), "Incorrect exception thrown with invalid input");
	}
	
	/**
	 * Test isValid if a character is not a letter or a digit
	 */
	@Test 
	void testNotLetterOrDigit() {
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> validator.isValid("CSC 116"));
		assertEquals("Course name can only contain letters and digits.", e.getMessage(), "Incorrect exception thrown with invalid input");
	}
	
	
	

}
