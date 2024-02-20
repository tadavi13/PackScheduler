package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the Student class
 * 
 * @author Ken Ogura
 * @author Zachary Orr
 * @author Alex Jong
 */
class StudentTest {

	/** Student Correct first name */
	private static final String FIRST_NAME = "John";
	/** Student Correct last name */
	private static final String LAST_NAME = "Smith";
	/** Student Correct Email */
	private static final String EMAIL = "jsmith@ncsu.edu";
	/** Student Correct password */
	private static final String PASSWORD = "password123";
	/** Student Correct ID */
	private static final String ID = "jsmith";
	/** Student Correct max credits */
	private static final int MAX_CREDITS = 15;

	/**
	 * Tests the full student constructor with correct inputs
	 */
	@Test
	void testStudentStringStringStringStringStringInt() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals("John", s1.getFirstName());
		assertEquals("Smith", s1.getLastName());
		assertEquals("jsmith", s1.getId());
		assertEquals("jsmith@ncsu.edu", s1.getEmail());
		assertEquals("password123", s1.getPassword());
		assertEquals(15, s1.getMaxCredits());

	}

	/**
	 * Tests the student constructor with no set max credits and with correct input
	 */
	@Test
	void testStudentStringStringStringStringString() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		assertEquals("John", s1.getFirstName());
		assertEquals("Smith", s1.getLastName());
		assertEquals("jsmith", s1.getId());
		assertEquals("jsmith@ncsu.edu", s1.getEmail());
		assertEquals("password123", s1.getPassword());
		assertEquals(18, s1.getMaxCredits());
	}

	/**
	 * Tests the incorrect student ID through creation of a student object
	 */
	@Test
	void testSetIdIncorrect() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD));
		assertEquals("Invalid id", e1.getMessage()); // Check correct exception message
	}

	/**
	 * Tests the setFirstName method with correct input
	 */
	@Test
	void testSetFirstName() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		s1.setFirstName("Steve");
		assertEquals("Steve", s1.getFirstName()); // Check that first name changed
	}

	/**
	 * Tests the setFirstName method with incorrect input
	 */
	@Test
	void testSetFirstNameIncorrect() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s1.setFirstName(null));
		assertEquals("Invalid first name", e1.getMessage()); // Check correct exception message
		assertEquals("John", s1.getFirstName()); // Check that first name didn't change
	}

	/**
	 * Tests the setLastName method with correct input
	 */
	@Test
	void testSetLastName() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		s1.setFirstName("Brown");
		assertEquals("Brown", s1.getFirstName()); // Check that last name changed
	}

	/**
	 * Tests the setLastName method with incorrect input
	 */
	@Test
	void testSetLastNameIncorrect() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s1.setLastName(null));
		assertEquals("Invalid last name", e1.getMessage()); // Check correct exception message
		assertEquals("Smith", s1.getLastName()); // Check that last name didn't change
	}

	/**
	 * Tests the setEmail method with correct input
	 */
	@Test
	void testSetEmail() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		s1.setEmail("email@ncsu.edu");
		assertEquals("email@ncsu.edu", s1.getEmail()); // Check that email changed
	}

	/**
	 * Tests the setEmail method with incorrect input
	 */
	@Test
	void testSetEmailIncorrect() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s1.setEmail(null));
		assertEquals("Invalid email", e1.getMessage()); // Check correct exception message
		assertEquals("jsmith@ncsu.edu", s1.getEmail()); // Check that email didn't change
	}

	/**
	 * Tests the setPassword method with correct input
	 */
	@Test
	void testSetPassword() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		s1.setPassword("newpassword");
		assertEquals("newpassword", s1.getPassword()); // Check that password changed
	}

	/**
	 * Tests the setPassword method with incorrect input
	 */
	@Test
	void testSetPasswordIncorrect() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s1.setPassword(null));
		assertEquals("Invalid password", e1.getMessage()); // Check correct exception message
		assertEquals("password123", s1.getPassword()); // Check that password didn't change
	}

	/**
	 * Tests the setMaxCredits method with correct input
	 */
	@Test
	void testSetMaxCredits() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		s1.setMaxCredits(12);
		assertEquals(12, s1.getMaxCredits()); // Check that max credits have changed
	}

	/**
	 * Tests the setMaxCredits method with incorrect input
	 */
	@Test
	void testSetMaxCreditsIncorrect() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s1.setMaxCredits(2));
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s1.setMaxCredits(19));
		assertEquals("Invalid max credits", e1.getMessage()); // Check correct exception message
		assertEquals("Invalid max credits", e2.getMessage()); // Check correct exception message
		assertEquals(15, s1.getMaxCredits()); // Check that max credits have not changed
	}

	/**
	 * Tests the hashCode method for same and different objects
	 */
	@Test
	void testHashCode() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s3 = new Student("different", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s4 = new Student(FIRST_NAME, "different", ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s5 = new Student(FIRST_NAME, LAST_NAME, "different", EMAIL, PASSWORD, MAX_CREDITS);
		User s6 = new Student(FIRST_NAME, LAST_NAME, ID, "different@ncsu.edu", PASSWORD, MAX_CREDITS);
		User s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "different", MAX_CREDITS);
		User s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 12);
		assertEquals(s1.hashCode(), s2.hashCode()); // two Students with the same fields should have the same hashcode
		assertNotEquals(s1.hashCode(), s3.hashCode()); // two Students with different fields should have different
														// hashcodes
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}

	/**
	 * Tests the equalsObject method for same and different objects
	 */
	@Test
	void testEqualsObject() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s3 = new Student("different", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s4 = new Student(FIRST_NAME, "different", ID, EMAIL, PASSWORD, MAX_CREDITS);
		User s5 = new Student(FIRST_NAME, LAST_NAME, "different", EMAIL, PASSWORD, MAX_CREDITS);
		User s6 = new Student(FIRST_NAME, LAST_NAME, ID, "different@ncsu.edu", PASSWORD, MAX_CREDITS);
		User s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "different", MAX_CREDITS);
		User s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 12);
		// Students with the same fields should be equal to each other
		assertEquals(s1, s2);
		assertEquals(s2, s1);

		// Students with different fields should not be equal
		assertNotEquals(s1, s3);
		assertNotEquals(s1, s4);
		assertNotEquals(s1, s5);
		assertNotEquals(s1, s6);
		assertNotEquals(s1, s7);
		assertNotEquals(s1, s8);
	}

	/**
	 * Tests the toString method for both constructors
	 */
	@Test
	void testToString() {
		User s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		String t1 = "John,Smith,jsmith,jsmith@ncsu.edu,password123,15";
		assertEquals(t1, s1.toString());

		User s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		String t2 = "John,Smith,jsmith,jsmith@ncsu.edu,password123,18";
		assertEquals(t2, s2.toString());
	}
	
	/**
	 * Tests the compareTo method
	 */
	@Test
	void testCompareTo() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s2 = new Student("Adam", LAST_NAME, "asmith", EMAIL, PASSWORD, MAX_CREDITS);
		Student s3 = new Student("Wanda", "Williams", "wwilliams", EMAIL, PASSWORD, MAX_CREDITS);
		Student s1Copy = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s23 = new Student(FIRST_NAME, "a", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s33 = new Student(FIRST_NAME, "b", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s43 = new Student(FIRST_NAME, "a", ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s24 = new Student("a", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s34 = new Student("b", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s44 = new Student("a", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s25 = new Student(FIRST_NAME, LAST_NAME, "a", EMAIL, PASSWORD, MAX_CREDITS);
		Student s35 = new Student(FIRST_NAME, LAST_NAME, "b", EMAIL, PASSWORD, MAX_CREDITS);
		Student s45 = new Student(FIRST_NAME, LAST_NAME, "a", EMAIL, PASSWORD, MAX_CREDITS);
		
		
		// tests different students placements against each other
		assertEquals(1, s1.compareTo(s2));
		assertEquals(-1, s1.compareTo(s3));		
		assertEquals(0, s1.compareTo(s1Copy));
		assertEquals("a".compareTo("b"), s23.compareTo(s33));
		assertEquals("a".compareTo("a"), s43.compareTo(s23));
		assertEquals("b".compareTo("a"), s33.compareTo(s23));
		assertEquals("a".compareTo("b"), s24.compareTo(s34));
		assertEquals("a".compareTo("a"), s44.compareTo(s24));
		assertEquals("b".compareTo("a"), s34.compareTo(s24));
		assertEquals("a".compareTo("b"), s25.compareTo(s35));
		assertEquals("a".compareTo("a"), s45.compareTo(s25));
		assertEquals("b".compareTo("a"), s35.compareTo(s25));
		
	}
	
	/**
	 * Test canAdd method
	 */
	@Test
	void testCanAdd() {
		Student student = new Student("c", "g", "crg", "crgoga@ncsu.edu", "pass", 4);
		Course course = new Course("CSC216", "Test", "101", 3, "crgoga", 10, "MW", 1015, 1130);
		assertTrue(student.canAdd(course));
		student.getSchedule().addCourseToSchedule(course);
		
		assertFalse(student.canAdd(course));
		assertFalse(student.canAdd(null));
		Course course1 = new Course("CSC217", "Test", "101", 3, "crgoga", 10, "MW", 1015, 1130);
		assertFalse(student.canAdd(course1));
	}

	
}
