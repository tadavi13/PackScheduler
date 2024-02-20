package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Represents an Activity for WolfScheduler and is a superclass of Course
 * @author Kentaro Ogura
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	/** The upper end of a Course's start or end hour */
	private static final int UPPER_HOUR = 23;
	/** The upper end of a Course's start or end minute */
	private static final int UPPER_MINUTE = 59;

	/**
	 * Constructs an Activity object with values for title, meetingDays, startTime, and endTime.
	 * @param title title of Course
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	/**
	 * Checks if two Activities have an overlapping day and time. A ConflictException is thrown if there is an overlap.
	 * @throws ConflictException if two Activities have overlapping days and times.
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		if (this.meetingDays.equals(Character.toString('A')) || possibleConflictingActivity.meetingDays.equals(Character.toString('A'))) {
			return;
		}
		for (char thisCharacter : this.meetingDays.toCharArray()) {
			for (char possibleCharacter : possibleConflictingActivity.meetingDays.toCharArray()) {
				if (thisCharacter == possibleCharacter) {
					if (this.startTime == possibleConflictingActivity.startTime ||  this.startTime == possibleConflictingActivity.endTime || this.endTime == possibleConflictingActivity.startTime || this.endTime == possibleConflictingActivity.endTime) {
						throw new ConflictException("Schedule conflict.");
					} else if (possibleConflictingActivity.startTime < this.endTime && possibleConflictingActivity.startTime > this.startTime || possibleConflictingActivity.endTime < this.endTime && possibleConflictingActivity.endTime > this.startTime) {
						throw new ConflictException("Schedule conflict.");
					} else if (this.startTime < possibleConflictingActivity.endTime && this.startTime > possibleConflictingActivity.startTime || this.endTime < possibleConflictingActivity.endTime && this.endTime > possibleConflictingActivity.startTime) {
						throw new ConflictException("Schedule conflict.");
					}
				}
			}
		}
		
		
	}

	/**
	 * Checks if an Activity is a duplicate of an existing Course or Event. 
	 * @param activity an Activity
	 * @return a boolean representing whether the passed in Activity is a duplicate of a Course or Event
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Gets an array used to populate the rows of the course catalog and student schedule.
	 * @return shortDisplayArray An array of length 4 containing Course name, section, title, and meeting string.
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Gets an array that displays the final schedule.
	 * @return longDisplayArray An array of length 7 containing Course name, section, title, credits, instructorId, meeting string, and empty string.
	 */
	public abstract String[] getLongDisplayArray();
	

	/**
	 * Returns the Course's title.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title. If the title is null, is an empty string,
	 * or has a length of 0, an IllegalArgumentException is thrown.
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title parameter is invalid
	 */
	public void setTitle(String title) {
		//Conditional 1:
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		//Conditional 2:
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		
		
		this.title = title;
	}

	/**
	 * Returns the Course's meeting days.
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Acts as a template method for Event and Course. If the Event and Course versions of the method do not throw exceptions,
	 * this method is called and the start and end time will be broken down into hours and minutes and an IllegalArgumentException 
	 * is thrown if they are not between 0 and 23 or between 0 and 59 respectively. The start time must also be less than the end time or else 
	 * an IllegalArgumentException is thrown. If everything is valid, the fields for meetingDays, startTime, and endTime are set 
	 * to the passed in parameters.
	 * @param meetingDays the meetingDays to set
	 * @param startTime the startTime to set
	 * @param endTime the endTime to set
	 * @throws IllegalArgumentException if meetingDays, startTime, or endTime are invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
			 if (startTime > endTime) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
	
			 }
			 
			 int startHour = startTime / 100;
			 int startMin = startTime % 100;
			 int endHour = endTime / 100;
			 int endMin = endTime % 100;
			 
			 if (startHour < 0 || startHour > UPPER_HOUR) { // not between 0 and 23, inclusive
				 throw new IllegalArgumentException("Invalid meeting days and times.");
			 }
			 
			 if (startMin < 0 || startMin > UPPER_MINUTE) { // not between 0 and 59, inclusive
				 throw new IllegalArgumentException("Invalid meeting days and times.");
			 }
			 
			 if (endHour < 0 || endHour > UPPER_HOUR) { // not between 0 and 23, inclusive
				 throw new IllegalArgumentException("Invalid meeting days and times.");
			 }
			 
			 if (endMin < 0 || endMin > UPPER_MINUTE) { // not between 0 and 59, inclusive
				 throw new IllegalArgumentException("Invalid meeting days and times.");
			 }
			 
			 
			//everything is valid and works together!
			 this.meetingDays = meetingDays;
			 this.startTime = startTime;
			 this.endTime = endTime; 			 
		}
		

	/**
	 * Formats a String into the format "D HH:MMPP-HH:MMPP" by converting military time to standard time.
	 * If meetingDays is equal to A, then "Arranged" is returned. 
	 * @return meeting a formatted string representing the days and meeting times of a Course
	 */
	public String getMeetingString() {
		 if ("A".equals(this.meetingDays)) {
			 return "Arranged";
		 }
		 String meeting = "";
		 String startPeriod = "AM";
		 String endPeriod = "AM";
		 int startHour = this.startTime / 100;
		 String startMin = Integer.toString(this.startTime % 100);
		 int endHour = this.endTime / 100;
		 String endMin = Integer.toString(this.endTime % 100);
		 for (char character : this.meetingDays.toCharArray()) {
			 meeting += character;
		 }
		 meeting += " ";
		 if (startHour > 12) {
			 startHour -= 12;
			 startPeriod = "PM";
		 }
		 if (endHour > 12) {
			 endHour -= 12;
			 endPeriod = "PM";
		 }
		 if (startHour == 12) {
			 startPeriod = "PM";
		 }
		 if (endHour == 12) {
			 endPeriod = "PM";
		 }
		 
		 if (startHour == 0) {
			 startHour = 12;
		 }
		 if (endHour == 0) {
			 endHour = 12;
		 }
		 if (Integer.parseInt(startMin) < 10) {
			 startMin = "0" + startMin;
		 }
		 if (Integer.parseInt(endMin) < 10) {
			 endMin = "0" + endMin;
		 }
		 String startHourString = Integer.toString(startHour);
		 String endHourString = Integer.toString(endHour);
		 meeting += startHourString + ":" + startMin + startPeriod;
		 meeting += "-" + endHourString + ":" + endMin + endPeriod;
		 return meeting;		 
	}

	/**
	 * Returns the Course's start time.
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time.
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}