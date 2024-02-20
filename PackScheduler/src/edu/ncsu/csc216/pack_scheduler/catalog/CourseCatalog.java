package edu.ncsu.csc216.pack_scheduler.catalog;

import edu.ncsu.csc217.collections.list.SortedList;


import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;


/**
 * Represents a course catalog for PackScheduler
 * @author Ken Ogura
 * @author Alex Jong
 * @author Zachary Orr
 */
public class CourseCatalog {
	/** A SortedList representing a catalog for courses */
	private SortedList<Course> catalog;
	
	/**
	 * Constructor that constructs an empty catalog
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}
	
	/**
	 * Constructs an empty catalog
	 */
	public void newCourseCatalog() {
		this.catalog = new SortedList<Course>();
	}
	
	/**
	 * Loads courses from a file into catalog.
	 * @param fileName String representing a filename that courses will be loaded from
	 * @throws IllegalArgumentException if the file cannot be found
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			this.catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a course to the course catalog. 
	 * @param name name of the course 
	 * @param title title of the course
	 * @param section section of the course
	 * @param credits credit number of the course
	 * @param instructorId instructor id of the instructor for the course
	 * @param enrollmentCap enrollment capacity of the course
	 * @param meetingDays the meeting days of the course
	 * @param startTime the start time of the course
	 * @param endTime the end time of the course 
	 * @throws IllegalArgumentException if any of the String parameters are null or invalid
	 * @throws IllegalArgumentException if meeting days is invalid
	 * @throws IllegalArgumentException if times is invalid
	 * @return true if the passed in course details are already in catalog and false otherwise 
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
			 
		Course newCourse = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		for(int i = 0; i < catalog.size(); i++) {
			if(catalog.get(i).getName().equals(newCourse.getName()) && catalog.get(i).getSection().equals(newCourse.getSection())) {
				return false;
			}
		}
		this.catalog.add(new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime));
		return true;
	}
	
	/**
	 * Removes a course from the course catalog 
	 * @param name the name of the course
	 * @param section the section of the course
	 * @return true if a course is in the catalog and false otherwise 
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < this.catalog.size(); i++) {
			Course course = this.catalog.get(i);
			if (course.getName().equals(name) && course.getSection().equals(section)) {
				this.catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets a course from the course catalog 
	 * @param name the name of the course to be retrieved
	 * @param section the section of the course to be retrieved 
	 * @return course if it is present in catalog and returns null otherwise  
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < this.catalog.size(); i++) {
			Course course = this.catalog.get(i);
			if (course.getName().equals(name) && course.getSection().equals(section)) {
				return this.catalog.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Gets the entire course catalog with information about course names, sections, and
	 * titles.
	 * @return courseCatalog a 2d array that contains courses in catalog and course name, section, and title.
	 */
	public String[][] getCourseCatalog() {
		String[][] courseCatalog = new String[this.catalog.size()][5];
		for (int i = 0; i < this.catalog.size(); i++) {
			Course course = this.catalog.get(i);
			courseCatalog[i][0] = course.getName();
			courseCatalog[i][1] = course.getSection();
			courseCatalog[i][2] = course.getTitle();
			courseCatalog[i][3] = course.getMeetingString();
			courseCatalog[i][4] = "" + course.getCourseRoll().getOpenSeats();
			}	
		return courseCatalog;
	}
	
	/**
	 * Saves the course catalog to a given file.
	 * @param filename the file where the course catalog will be saved
	 * @throws IllegalArgumentException if unable to save file
	 */
	public void saveCourseCatalog(String filename) {
		try {
			CourseRecordIO.writeCourseRecords(filename, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + filename);
		}
	}

}
	
	
	

