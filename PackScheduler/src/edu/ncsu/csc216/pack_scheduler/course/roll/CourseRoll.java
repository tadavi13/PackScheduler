/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * Constructs CourseRoll object with parameter enrollmentCap. Can get and set enrollmentCap. Gets number of 
 * open seats in course. Enrolls and drops students in course. Determines whether a student can enroll within the course.
 */
public class CourseRoll {
	/** A custom LinkedAbsractList of Student s */
	LinkedAbstractList<Student> roll;
	
	/** Roll's enrollment capacity */
	private int enrollmentCap;
	
	/** Smallest Class Size */
	private static final int MIN_ENROLLMENT = 10;
	
	/** Largest Class Size */
	private static final int MAX_ENROLLMENT = 250;
	
	/** Waitlist object */
	private ArrayList<Student> waitlist;
	
	/** Largest Waitlist Size */
	private static final int WAITLIST_SIZE = 10;
	
	/** Course object */
	private Course course;
	
	/**
	 * Constructs CourseRoll object. Sets roll to a new LinkedAbstractList and sets enrollment cap.
	 * @param enrollmentCap enrollment capacity
	 * @param course to enroll in
	 */
	public CourseRoll(int enrollmentCap, Course course) {
		if(course == null) {
			throw new IllegalArgumentException();
		}
		roll = new LinkedAbstractList<Student>(enrollmentCap);
		setEnrollmentCap(enrollmentCap);
		waitlist = new ArrayList<Student>();
		this.course = course;
	}
	
	/**
	 * Sets enrollment cap
	 * @param enrollmentCap to set
	 * @throws IllegalArgumentException if enrollmentCap is less than 10 or more than 250
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("Invalid enrollment cap.");
		}
		
		if (enrollmentCap >= roll.size()) {
			this.enrollmentCap = enrollmentCap;
			roll.setCapacity(enrollmentCap);
		} else {
			throw new IllegalArgumentException();
		}	
	}

	/**
	 * Returns number of current open seats in a course
	 * @return number of open seats
	 */
	public int getOpenSeats() {
		
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Gets enrollmentCap
	 * @return enrollmentCap
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
		
	}
	
	/**
	 * Enrolls student in course
	 * @param s student being enrolled
	 * @throws IllegalArgumentException if student is null or the roll size is greater than 250
	 */
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		
		if (!canEnroll(s)) {
			throw new IllegalArgumentException();
		}
		
		if (roll.size() == enrollmentCap) {
			waitlist.add(waitlist.size(), s);
		} else {
			roll.add(s);
		}
	}
	
	/**
	 * Determines whether a student can enroll in a course
	 * @param s student wanting to be enrolled
	 * @return true if can enroll, false if not
	 */
	public boolean canEnroll(Student s) {
		if (roll.size() >= enrollmentCap) {
			if (waitlist.size() >= WAITLIST_SIZE) {
				return false;
			} else if (waitlist.contains(s)) {
				return false;
			}
		}
		
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(s)){
				return false;
			}
		}
	return true;
	}
	
	/**
	 * Drops student from course
	 * @param s student being dropped
	 * @throws IllegalArgumentException if student is null
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		
		if(roll.contains(s)) {
			roll.remove(s);
			for(int i = 0; i < getNumberOnWaitlist(); i++) {
				if(this.canEnroll(waitlist.get(i))) {
					waitlist.get(i).getSchedule().addCourseToSchedule(this.course);
					roll.add(waitlist.remove(i));
				}
			}
		} else if(waitlist.contains(s)) {
			waitlist.remove(s);
		}
		
		roll.remove(s);
	}
	
	/**
	 * Returns the number of Students on the Waitlist
	 * @return number of students on waitlist
	 */
	public int getNumberOnWaitlist() {
		return this.waitlist.size();
	}
	
}
