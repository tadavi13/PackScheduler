package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Test Schedule class
 */
class ScheduleTest {

	/**
	 * Test addCourseToSchedule method
	 */
	@Test
	void testAddCourseToSchedule() {
		Schedule schedule = new Schedule();
		Course course = new Course("CSC216", "Test", "101", 3, "crgoga", 10, "MW", 1015, 1130);
		schedule.addCourseToSchedule(course);
		
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> schedule.addCourseToSchedule(course));
		assertEquals("You are already enrolled in " + course.getName(), e.getMessage(), "Incorrect exception thrown with " + course.getName());
		
		Course course2 = new Course("CSC217", "Test", "102", 3, "crgoga", 10, "MW", 1015, 1130);
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> schedule.addCourseToSchedule(course2));
		assertEquals("The course cannot be added due to a conflict.", e1.getMessage(), "Incorrect exception thrown with " + course.getName());
	}
		
	/**
	 * Test removeCourseFromSchedule method
	 */
	@Test
	void testRemoveCourseFromSchedule() {
		Schedule schedule = new Schedule();
		Course course = new Course("CSC216", "Test", "101", 3, "crgoga", 10, "MW", 1015, 1130);
		Course course1 = new Course("CSC226", "Test", "100", 3, "crgoga", 10, "TH", 1015, 1130);
		schedule.addCourseToSchedule(course);
		schedule.addCourseToSchedule(course1);
		assertEquals(schedule.getScheduledCourses().length, 2);
		assertTrue(schedule.removeCourseFromSchedule(course));
		assertEquals(schedule.getScheduledCourses().length, 1);
		assertTrue(schedule.removeCourseFromSchedule(course1));
		assertEquals(schedule.getScheduledCourses().length, 0);
	}

	/**
	 * Test resetSchedule method
	 */
	@Test
	void testResetSchedule() {
		Schedule schedule = new Schedule();
		Course course = new Course("CSC216", "Test", "101", 3, "crgoga", 10, "MW", 1015, 1130);
		Course course1 = new Course("CSC226", "Test", "100", 3, "crgoga", 10, "TH", 1015, 1130);
		schedule.addCourseToSchedule(course);
		schedule.addCourseToSchedule(course1);
		schedule.resetSchedule();
		assertEquals(schedule.getScheduledCourses().length, 0);
	}

	/**
	 * Test setScheduleTitle method
	 */
	@Test
	void testSetScheduleTitle() {
		Schedule schedule = new Schedule();
		assertEquals(schedule.getTitle(), "My Schedule");
		schedule.setTitle("Test");
		assertEquals(schedule.getTitle(), "Test");
		
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> schedule.setTitle(null));
		assertEquals("Title cannot be null.", e.getMessage(), "Incorrect exception thrown with title");
	}
	
	/**
	 * Test getScheduleCredits method
	 */
	@Test
	void testGetScheduleCredits() {
		// Credits after construction
		Schedule schedule = new Schedule();
		assertEquals(0, schedule.getScheduleCredits());
		// Credits after adding one course
		Course course = new Course("CSC216", "Test", "101", 3, "crgoga", 10, "MW", 1015, 1130);
		schedule.addCourseToSchedule(course);
		assertEquals(3, schedule.getScheduleCredits());
		// Credits after adding another course
		Course course1 = new Course("CSC217", "Test", "101", 3, "crgoga", 10, "TH", 1015, 1130);
		schedule.addCourseToSchedule(course1);
		assertEquals(6, schedule.getScheduleCredits());
		// Credits after removing course
		schedule.removeCourseFromSchedule(course1);
		assertEquals(3, schedule.getScheduleCredits());
	}
	
	/**
	 * Test canAdd method
	 */
	@Test
	void testCanAdd() {
		Schedule schedule = new Schedule();
		Course course = new Course("CSC216", "Test", "101", 3, "crgoga", 10, "MW", 1015, 1130);
		assertTrue(schedule.canAdd(course));
		schedule.addCourseToSchedule(course);
		
		assertFalse(schedule.canAdd(course));
		assertFalse(schedule.canAdd(null));
		Course course1 = new Course("CSC217", "Test", "101", 3, "crgoga", 10, "MW", 1015, 1130);
		assertFalse(schedule.canAdd(course1));
	}

}
