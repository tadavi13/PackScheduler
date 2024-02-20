/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for Faculty class
 * @author Chris Brown
 */
class FacultyTest {
	
	/** Faculty Correct first name */
	private static final String FIRST_NAME = "John";
	/** Faculty Correct last name */
	private static final String LAST_NAME = "Smith";
	/** Faculty Correct Email */
	private static final String EMAIL = "jsmith@ncsu.edu";
	/** Faculty Correct password */
	private static final String PASSWORD = "password123";
	/** Faculty Correct ID */
	private static final String ID = "jsmith";
	/** Faculty Correct max courses */
	private static final int MAX_COURSES = 3;

	/**
	 * Test method for Faculty User objects
	 */
	@Test
	void testFaculty() {
		Faculty f1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		Faculty f2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES - 1);
		assertEquals(3, f1.getMaxCourses());
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> f1.setMaxCourses(5));
		assertEquals("Invalid max courses", e.getMessage());
		
		assertNotEquals(f1, f2);
		assertEquals(f1, f1);
		assertNotEquals(f1, null);
		f2.setMaxCourses(3);
		assertEquals(f1, f2);
	}


	/**
	 * Test method for Faculty toString method
	 */
	@Test
	void testToString() {
		Faculty f1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
		String expected = "John,Smith,jsmith,jsmith@ncsu.edu,password123,3";
		assertEquals(expected, f1.toString());
	}

}
