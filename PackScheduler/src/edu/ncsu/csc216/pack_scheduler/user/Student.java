package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Student class, handles creation and content of Student objects
 * 
 * @author Ken Ogura
 * @author Zachary Orr
 * @author Alex Jong
 */
public class Student extends User implements Comparable<Student> {
	/** integer for maximum credits for the student */
	private int maxCredits;
	/** Maximum amount of credits possible */
	public static final int MAX_CREDITS = 18;
	/** Schedule object */
	private Schedule schedule = new Schedule();

	/**
	 * Sets maximum number of credits for the Student. If the maximum number of
	 * credits is less than three or more than eighteen, throws
	 * IllegalArgumentException.
	 * 
	 * @param maxCredits integer containing maximum number of credits for Student.
	 * @throws IllegalArgumentException if maxCredits is less than 3 or more than
	 *                                  18.
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > 18) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Constructs a Student with all fields.
	 * 
	 * @param firstName  the Student's first name
	 * @param lastName   the Student's last name
	 * @param id         the Student's id
	 * @param email      the Student's email
	 * @param password   the Student's hashed password
	 * @param maxCredits the Student's maximum number of credits
	 */

	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
		setMaxCredits(maxCredits);
	}

	/**
	 * Hash code method for a student object
	 * @return hash code value of a student
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maxCredits);
		return result;
	}

	/**
	 * Determines if two student objects are equal based on their max credits
	 * @return whether the two students are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}
	
	/**
	 * Creates a string in a comma separated value list, and returns it.
	 * 
	 * @return returns the values as a CSV string
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
	}


	/**
	 * Constructs a Student with the fields firstName, lastName, id, email, and
	 * password. The maximum number of credits defaults to 18.
	 * 
	 * @param firstName the Student's first name
	 * @param lastName  the Student's last name
	 * @param id        the Student's id
	 * @param email     the Student's email
	 * @param password  the Student's hashed password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}

	/**
	 * gets the max credits
	 * 
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}
	
	
	

	/**
	 * Compares the names of students alphabetically. If student names are equal,
	 * then the student id is used to compare two students alphabetically.
	 * 
	 * @return return -1, 0, or 1 if the student being compared is alphabetically
	 *         greater, equal or less than respectively
	 */
	@Override
	public int compareTo(Student s) {
		if (this.getFirstName().equals(s.getFirstName()) && this.getLastName().equals(s.getLastName())) {
			if (this.getId().compareTo(s.getId()) < 0) {
				return -1;
			} else if (this.getId().compareTo(s.getId()) > 0) {
				return 1;
			} else {
				return 0;
			}
		} else if (this.getLastName().compareTo(s.getLastName()) == 0) {
			if (this.getFirstName().compareTo(s.getFirstName()) < 0) {
				return -1;
			} else if (this.getFirstName().compareTo(s.getFirstName()) > 0) {
				return 1;
			}
		} else if (this.getLastName().compareTo(s.getLastName()) < 0) {
			return -1;
		} else if (this.getLastName().compareTo(s.getLastName()) > 0) {
			return 1;
		} else if (this.getFirstName().compareTo(s.getFirstName()) == 0) {
			return 0;
		} else if (this.getFirstName().compareTo(s.getFirstName()) < 0) {
			return -1;
		} else {
			return 1;
		}
		return 1;
	}
	
	/**
	 * Gets schedule
	 * @return schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Determines if you can add course given to the schedule
	 * @param course course wanting to be added
	 * @return true if can be added, false if not
	 */
	public boolean canAdd(Course course) {
		if (schedule.canAdd(course)) {
			return course.getCredits() + schedule.getScheduleCredits() <= maxCredits;
		}
		else {
			return false;
		}
	}
}
