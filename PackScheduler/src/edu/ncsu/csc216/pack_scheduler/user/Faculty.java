/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Faculty user class
 * @author Chris Brown
 * @author Tyler
 * @author Ken Ogura
 */
public class Faculty extends User {

	/** current max courses for Faculty User */
	private int maxCourses;
	/** minimum number of courses a faculty member can teach */
	public static final int MIN_COURSES = 1;
	/** maximum number of courses a faculty member can teach */
	public static final int MAX_COURSES = 3;
	/** schedule of faculty users*/
	private FacultySchedule schedule;
	
	/**
	 * Faculty constructor
	 * @param firstName name of faculty user to add
	 * @param lastName of faculty user to add
	 * @param id of faculty user to add
	 * @param email of faculty user to add
	 * @param password of faculty user to add
	 * @param maxCourses max courses of faculty to add
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
		setMaxCourses(maxCourses);
		schedule = new FacultySchedule(id);
	}
	
	/**
	 * Returns the Faculty Schedule
	 * @return schedule the schedule of faculty users
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Checks if the number of scheduled course is overloaded for a Faculty user 
	 * @return true if the number of scheduled courses is greater than the Faculty's maxCourses
	 */
	public boolean isOverloaded() {
		return schedule.getNumScheduledCourses() > maxCourses; 
	}
	
	/** 
	 * setter for maxCourses field 
	 * @param maxCourses max courses of a faculty to set
	 * @throws IllegalArgumentException if maxCourses is less than 1 or greater than 3
	 */
	public void setMaxCourses(int maxCourses) {
		if(maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}
	
	/** 
	 * Getter for maxCourses field 
	 * @return maxCourses of this faculty
	 */
	public int getMaxCourses() {
		return this.maxCourses;
	}

	/**
	 * Hash code method for a faculty member
	 * @return hash code value for a faculty member
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maxCourses);
		return result;
	}

	/**
	 * Determines if two faculty objects are equal based on their max courses.
	 * @return whether the faculty are equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		return maxCourses == other.maxCourses;
	}

	/**
	 * Creates a string in a comma separated value list and returns it.
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + getMaxCourses();
	}
	
	
}
