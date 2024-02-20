/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests CourseRoll method
 */
public class CourseRollTest {
	
	/** Course for test */
	Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
	/** Course roll for test */
	CourseRoll roll = c.getCourseRoll();
	
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
	 * Tests CourseRoll constructor
	 */
	@Test
	public void testConstructor() {
		assertEquals(10, roll.getEnrollmentCap());
		assertEquals(10, roll.getOpenSeats());
		
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> new CourseRoll(30, null));
		assertEquals(null, e.getMessage(), "Incorret message");
	}
	
	/**
	 * Tests setEnrollmentCap
	 */
	@Test
	public void testSetter() {
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> roll.setEnrollmentCap(5));
		assertEquals("Invalid enrollment cap.", e.getMessage(), "Incorrect exception thrown with enrollment cap");
		roll.setEnrollmentCap(20);
		assertEquals(20, roll.getEnrollmentCap());
	}
	
	/**
	 * Tests enroll
	 */
	@Test
	public void testEnroll() {
		assertThrows(IllegalArgumentException.class,
				() -> roll.enroll(null));
		
		Student s1 = new Student(FIRST_NAME, LAST_NAME, "1", EMAIL, PASSWORD, MAX_CREDITS);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, "2", EMAIL, PASSWORD, MAX_CREDITS);
		Student s3 = new Student(FIRST_NAME, LAST_NAME, "3", EMAIL, PASSWORD, MAX_CREDITS);
		Student s4 = new Student(FIRST_NAME, LAST_NAME, "4", EMAIL, PASSWORD, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, "5", EMAIL, PASSWORD, MAX_CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, "6", EMAIL, PASSWORD, MAX_CREDITS);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, "7", EMAIL, PASSWORD, MAX_CREDITS);
		Student s8 = new Student(FIRST_NAME, LAST_NAME, "8", EMAIL, PASSWORD, MAX_CREDITS);
		Student s9 = new Student(FIRST_NAME, LAST_NAME, "9", EMAIL, PASSWORD, MAX_CREDITS);
		Student s10 = new Student(FIRST_NAME, LAST_NAME, "10", EMAIL, PASSWORD, MAX_CREDITS);
		Student s11 = new Student(FIRST_NAME, LAST_NAME, "11", EMAIL, PASSWORD, MAX_CREDITS);
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		assertEquals(10, roll.roll.size());
		roll.enroll(s11);
		assertEquals(10, roll.roll.size());
		assertEquals(1, roll.getNumberOnWaitlist());
	}
	
	/**
	 * Tests drop
	 */
	@Test
	public void testDrop() {
		assertThrows(IllegalArgumentException.class,
				() -> roll.drop(null));
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		roll.drop(s1);
		assertEquals(0, roll.roll.size());
		
		Student s2 = new Student(FIRST_NAME, LAST_NAME, "2", EMAIL, PASSWORD, MAX_CREDITS);
		Student s3 = new Student(FIRST_NAME, LAST_NAME, "3", EMAIL, PASSWORD, MAX_CREDITS);
		Student s4 = new Student(FIRST_NAME, LAST_NAME, "4", EMAIL, PASSWORD, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, "5", EMAIL, PASSWORD, MAX_CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, "6", EMAIL, PASSWORD, MAX_CREDITS);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, "7", EMAIL, PASSWORD, MAX_CREDITS);
		Student s8 = new Student(FIRST_NAME, LAST_NAME, "8", EMAIL, PASSWORD, MAX_CREDITS);
		Student s9 = new Student(FIRST_NAME, LAST_NAME, "9", EMAIL, PASSWORD, MAX_CREDITS);
		Student s10 = new Student(FIRST_NAME, LAST_NAME, "10", EMAIL, PASSWORD, MAX_CREDITS);
		Student s11 = new Student(FIRST_NAME, LAST_NAME, "11", EMAIL, PASSWORD, MAX_CREDITS);
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		roll.enroll(s11);
		roll.drop(s6);
		assertEquals(10, roll.roll.size());
		assertEquals(0, roll.getNumberOnWaitlist());
	}
	
	/**
	 * Tests canEnroll
	 */
	@Test
	public void testCanEnroll() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertTrue(roll.canEnroll(s1));
		
		Student s2 = new Student(FIRST_NAME, LAST_NAME, "2", EMAIL, PASSWORD, MAX_CREDITS);
		Student s3 = new Student(FIRST_NAME, LAST_NAME, "3", EMAIL, PASSWORD, MAX_CREDITS);
		Student s4 = new Student(FIRST_NAME, LAST_NAME, "4", EMAIL, PASSWORD, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, "5", EMAIL, PASSWORD, MAX_CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, "6", EMAIL, PASSWORD, MAX_CREDITS);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, "7", EMAIL, PASSWORD, MAX_CREDITS);
		Student s8 = new Student(FIRST_NAME, LAST_NAME, "8", EMAIL, PASSWORD, MAX_CREDITS);
		Student s9 = new Student(FIRST_NAME, LAST_NAME, "9", EMAIL, PASSWORD, MAX_CREDITS);
		Student s10 = new Student(FIRST_NAME, LAST_NAME, "10", EMAIL, PASSWORD, MAX_CREDITS);
		Student s11 = new Student(FIRST_NAME, LAST_NAME, "11", EMAIL, PASSWORD, MAX_CREDITS);
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		assertTrue(roll.canEnroll(s11));
		roll.enroll(s11);
		assertFalse(roll.canEnroll(s11));
	}
	
	/**
	 * Tests getting the number of students on the waitlist
	 */
	@Test
	public void testGetNumberOnWaitlist() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, "1", EMAIL, PASSWORD, MAX_CREDITS);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, "2", EMAIL, PASSWORD, MAX_CREDITS);
		Student s3 = new Student(FIRST_NAME, LAST_NAME, "3", EMAIL, PASSWORD, MAX_CREDITS);
		Student s4 = new Student(FIRST_NAME, LAST_NAME, "4", EMAIL, PASSWORD, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, "5", EMAIL, PASSWORD, MAX_CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, "6", EMAIL, PASSWORD, MAX_CREDITS);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, "7", EMAIL, PASSWORD, MAX_CREDITS);
		Student s8 = new Student(FIRST_NAME, LAST_NAME, "8", EMAIL, PASSWORD, MAX_CREDITS);
		Student s9 = new Student(FIRST_NAME, LAST_NAME, "9", EMAIL, PASSWORD, MAX_CREDITS);
		Student s10 = new Student(FIRST_NAME, LAST_NAME, "10", EMAIL, PASSWORD, MAX_CREDITS);
		Student s11 = new Student(FIRST_NAME, LAST_NAME, "11", EMAIL, PASSWORD, MAX_CREDITS);
		Student s12 = new Student(FIRST_NAME, LAST_NAME, "12", EMAIL, PASSWORD, MAX_CREDITS);
		Student s13 = new Student(FIRST_NAME, LAST_NAME, "13", EMAIL, PASSWORD, MAX_CREDITS);
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		assertEquals(0, roll.getNumberOnWaitlist());
		assertEquals(10, roll.roll.size());
		roll.enroll(s11);
		assertEquals(1, roll.getNumberOnWaitlist());
		assertEquals(10, roll.roll.size());
		roll.enroll(s12);
		assertEquals(2, roll.getNumberOnWaitlist());
		assertEquals(10, roll.roll.size());
		roll.enroll(s13);
		assertEquals(3, roll.getNumberOnWaitlist());
		assertEquals(10, roll.roll.size());
	}
}
