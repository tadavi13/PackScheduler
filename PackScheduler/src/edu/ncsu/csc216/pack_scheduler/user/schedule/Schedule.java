/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
/**
 * Constructs schedule object. Can add and remove courses from the schedule. A 2D String array can be made and retruned.
 * Can reset the schedule which removes every course. Sets and gets the title of the schedule.
 */
public class Schedule {
	
	
	/** Custom ArrayList of Courses called Schedule */
	ArrayList<Course> schedule = new ArrayList<Course>();
	
	/** Schedule's title */
	private String title;
	
	/**
	 * Creates schedule object by setting schedule to a new Array List with course and setting the title to My Schedule
	 */
	public Schedule(){
		schedule = new ArrayList<Course>();
		setTitle("My Schedule");
		
		
	}
	
	/**
	 * Adds course given to the schedule
	 * @param course course being added
	 * @return true if added, false if not
	 */
	public Boolean addCourseToSchedule(Course course) {
		//Added abstract isDuplicate in activity and implemented those methods in Course and Event
		if (schedule.size() == 0) {
			schedule.add(course);
			return true;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(course)){
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			}
			
			try {
				schedule.get(i).checkConflict(course);
			} catch (ConflictException c) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
			
			if (i == schedule.size() - 1) {
				schedule.add(course);
				return true;
			}
		}
		
		
		
		return false;
	}
	
	/**
	 * Removes Course from Schedule
	 * @param course course being removed
	 * @return Boolean true if successful false if not
	 */
	public Boolean removeCourseFromSchedule(Course course) {
		if (course == null) {
			return false;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(course)) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Resets schedule
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Course>();
		setTitle("My Schedule");
		
	}
	
	/**
	 * Returns a 2D String Array of the schedule
	 * @return schedulerArray
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduleArray = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
            Course c = schedule.get(i);
            scheduleArray[i] = c.getShortDisplayArray();
        }
		
		return scheduleArray;
	}
	
	/**
	 * Set's schedule title
	 * @param title title of schedule
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		
		this.title = title;
		
	}
	
	/**
	 * Get schedule title
	 * @return schedule title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Returns cumulative sum of credits
	 * @return sum cumulative sum of credits
	 */
	public int getScheduleCredits() {
		int sum = 0;
		for (Course course: schedule) {
			sum += course.getCredits();
		}
		return sum;
	}
	
	/**
	 * Sees if you can add the course given to the schedule
	 * @param course course wanted to be added
	 * @return true if can be added, false if not
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		}
		if (schedule.size() == 0) {
			return true;
		}
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(course)){
				return false;
			}
			
			try {
				schedule.get(i).checkConflict(course);
			} catch (ConflictException c) {
				return false;
			}
			
			if (i == schedule.size() - 1) {
				return true;
			}
		}
		return false;
	}

}
