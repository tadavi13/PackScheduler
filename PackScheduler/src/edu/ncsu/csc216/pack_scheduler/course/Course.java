package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Represents a Course for WolfScheduler
 * @author Kentaro Ogura
 */

public class Course extends Activity implements Comparable<Course> {
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** The length of a Course section */
	private static final int SECTION_LENGTH = 3;
	/** The minimum credit hours of a Course */
	private static final int MIN_CREDITS = 1;
	/** The maximum credit hours of a Course */
	private static final int MAX_CREDITS = 5;
	/** Course roll for course */
	private CourseRoll roll;
	
	/**
	 * Constructs a Course object with values for all fields.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param enrollmentCap enrollment capacity of the course
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 * @throws IllegalArgumentException if enrollment cap is less than 10 or more than 250	
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	    roll = new CourseRoll(enrollmentCap, this);
	    roll.setEnrollmentCap(enrollmentCap);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param enrollmentCap enrollment capacity of the course
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, an
	 * IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		//Throw exception if the name is null
		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		CourseNameValidator validator = new CourseNameValidator();
		try {
			validator.isValid(name);
		} catch (InvalidTransitionException e){
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		this.name = name;
	}
	
	/**
	 * Returns the Course's section.
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * Sets the Course's section. If the section is null or a section's length
	 * is not equal to 3 or not all characters of a section are digits, an 
	 * IllegalArgumentException is thrown.
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section parameter is invalid
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH){
			throw new IllegalArgumentException("Invalid section.");
		}
		for (char character : section.toCharArray()) {
			if (!Character.isDigit(character)) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		
		this.section = section;
	}
	
	/**
	 * Returns the Course's credits.
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets the Course's credits. If the credit hours are less than 1 or greater than 5
	 * an IllegalArgumentException is thrown. 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if the credits parameter is invalid
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		
		this.credits = credits;
	}
	
	/**
	 * Returns the Course's instructor ID.
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * Sets the Course's instructor ID. If the instructorId parameter is null or is an
	 * empty string, an IllegalArgumentException is thrown.
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructorId parameter is invalid
	 */
	public void setInstructorId(String instructorId) {
		if ("".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		
		this.instructorId = instructorId;
	}
	
	/**
	 * Checks if the passed in Activity is a duplicate of the current Course object
	 * @param activity an Activity object
	 * @return isDuplicate a boolean representing whether the Activity is a duplicate of the current Course object. 
	 */
	public boolean isDuplicate(Activity activity) {
		return this.getName().equals(((Course) activity).getName());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		 if ("A".equals(getMeetingDays())) {
		        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays();
		    }
		 
		 return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}

	/**
	 * Gets an array used to populate the rows of the course catalog and student schedule.
	 * @return shortDisplayArray An array of length 4 containing Course name, section, title, and meeting string.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = new String[5];
		shortDisplayArray[0] = this.name;
		shortDisplayArray[1] = this.section;
		shortDisplayArray[2] = this.getTitle();
		shortDisplayArray[3] = this.getMeetingString();
		shortDisplayArray[4] = "" + this.roll.getOpenSeats();
		return shortDisplayArray;
	}

	/**
	 * Gets an array that displays the final schedule.
	 * @return longDisplayArray An array of length 7 containing Course name, section, title, credits, instructorId, meeting string, and empty string.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = new String[7];
		longDisplayArray[0] = this.name;
		longDisplayArray[1] = this.section;
		longDisplayArray[2] = this.getTitle();
		longDisplayArray[3] = Integer.toString(this.credits);
		longDisplayArray[4] = this.getInstructorId();
		longDisplayArray[5] = this.getMeetingString();
		longDisplayArray[6] = "";
		return longDisplayArray;
	}

	/**
	 * Sets the Course's meeting days, start time, and end time. If the meeting days is null or an
	 * empty string, an IllegalArgumentException is thrown. If the meeting days are arranged and the start time or
	 * end time is not 0, an IllegalArgumentException is thrown. Otherwise, meetingDays is set to the passed in parameter 
	 * and startTime and endTime are set to 0. If the meeting days are not arranged, the meeting days will be checked and
	 * an IllegalArgumentException is thrown if a day appears more than once or a character does not represent MTWHF. 
	 * If everything is valid, the superclass method from Activity is called and the fields for meetingDays, startTime, and endTime are set.
	 * @param meetingDays the meetingDays to set
	 * @param startTime the startTime to set
	 * @param endTime the endTime to set
	 * @throws IllegalArgumentException if meetingDays, startTime, or endTime are invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		else if ("A".equals(meetingDays)) { // Arranged
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, 0, 0);
		}
		
		else {
			 int monday = 0;
			 int tuesday = 0;
			 int wednesday = 0;
			 int thursday = 0;
			 int friday = 0;
			 for (char character : meetingDays.toCharArray()) {
				 switch (character) {
				 	case 'M':
				 		monday++;
				 		break;
				 	case 'T':
				 		tuesday++;
				 		break;
				 	case 'W':
				 		wednesday++;
				 		break;
				 	case 'H':
				 		thursday++;
				 		break;
				 	case 'F':
				 		friday++;
				 		break;
				 	default:
				 		throw new IllegalArgumentException("Invalid meeting days and times.");
				 }
			 }
			 if (monday > 1 || tuesday > 1 || wednesday > 1 || thursday > 1 || friday > 1) { // checks for duplicates
				 throw new IllegalArgumentException("Invalid meeting days and times.");
			 }
			 super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}

	@Override
	public int compareTo(Course c) {
		if (this.name.equals(c.name)) {
			if (this.section.compareTo(c.section) < 0) {
				return -1;
			} else if (this.section.compareTo(c.section) > 0) {
				return 1;
			} else {
				return 0;
			}
		} else if(this.name.compareTo(c.name) < 0) {
			return -1;
		} else if(this.name.compareTo(c.name) > 0) {
			return 1;
		}
		return 1;
	}
	
	/**
	 * Gets course roll
	 * @return roll course roll
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

}
