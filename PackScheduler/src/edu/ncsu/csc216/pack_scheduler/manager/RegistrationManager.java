package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * A class utilizing the singleton pattern to manage registration. Contains the inner class Registrar that creates a registrar user using 
 * the parameters first name, last name, id, email, and password. Creates an instance of registration manager for use. Can get the instance, 
 * the course catalog, the student directory, and the current user. Lets you login and logout of the registrar. Can clear all data within the 
 * course catalog and student directory.
 * @author Christian Goga
 * @author Ken Ogura
 */
public class RegistrationManager {
	
	/** Represents the singleton for RegistrationManager */
	private static RegistrationManager instance;
	/** Represents the catalog for courses */
    private CourseCatalog courseCatalog = new CourseCatalog();
    /** Represents the directory for students */
	private StudentDirectory studentDirectory = new StudentDirectory();
	/** Represents the directory for faculty */
	private FacultyDirectory facultyDirectory;
	/** Represents a registrar that can look at course and student registration*/
	private User registrar;
	/** Represents the current user for the registration manager*/
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** The properties file for the registrar*/
	private static final String PROP_FILE = "registrar.properties";
	
	/**
	 * Represents the inner class for registrar and contains the constructor for registrar. 
	 */
	private static class Registrar extends User {
	    /**
	     * Create a registrar user.
	     * @param firstName first name of student
	     * @param lastName last name of student
	     * @param id id of student
	     * @param email email of student
	     * @param hashPW password of student
	     */
	    public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
	        super(firstName, lastName, id, email, hashPW);
	    }
	}

	/**
	 * Calls the createRegistrar method to create an instance of a registrar.
	 */
	private RegistrationManager() {
		createRegistrar();
		facultyDirectory = new FacultyDirectory();
	}
	
	/**
	 * Adds a course to the faculty's schedule if currentUser is not null and is the Registrar. 
	 * @param course the course that will be added to the faculty's schedule 
	 * @param faculty the faculty whose schedule will be modified by adding a course
	 * @return true if the faculty is added to the course 
	 * 
	 */
	public boolean addFacultyToCourse(Course course, Faculty faculty) {
		if(currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		if (currentUser != null && currentUser == registrar) {
			FacultySchedule schedule = faculty.getSchedule();
			return schedule.addCourseToSchedule(course);
			
		}
		return false; 
	}
	
	/**
	 * Removes the course from the faculty's schedule if currentUser is not null and is the Registrar.
	 * @param course the course that will be removed from the faculty's schedule
	 * @param faculty the faculty whose schedule will be modified by removing a course
	 * @return true if the course is removed from the faculty's schedule 
	 */
	public boolean removeFacultyFromCourse(Course course, Faculty faculty) {
		if(currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		if (currentUser != null && currentUser == registrar) {
			FacultySchedule schedule = faculty.getSchedule();
			return schedule.removeCourseFromSchedule(course);
		}
		return false;
	}
	
	/**
	 * Resets the faculty's schedule if currentUser is not null and is the Registrar.
	 * @param faculty the faculty whose schedule will be reset 
	 */
	public void resetFacultySchedule(Faculty faculty) {
		if(currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		if (currentUser != null && currentUser == registrar) {
			FacultySchedule schedule = faculty.getSchedule();
			schedule.resetSchedule();
		}
	}
	
	/**
	 * Creates an instance of a registrar. Throws an exception if the properties file contains invalid information.
	 * @throws IllegalArgumentException thrown if the properties file contains invalid information
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}
	
	/**
	 * Hashes a password to to encrypt it.
	 * @param pw the password to be hashed and encrypted 
	 * @throws IllegalArgumentException if password cannot be hashed
	 * @return a hashed version of the passed in password
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * Returns the single instance of RegistrationManager. If the current instance is null, a new
	 * instance of RegistrationManager is created.
	 * @return instance the single instance of RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * Returns the catalog for courses.
	 * @return courseCatalog the catalog for courses
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * Returns the directory for students.
	 * @return studentDirectory the directory for students
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/**
	 * Returns the directory for faculty.
	 * @return facultyDirectory the directory for faculty
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Logs a user into the registration manager by checking if the passed in id and password
	 * match the id and password of the registrar. Throws an IllegalArgumentException if they
	 * do not match.
	 * @param id the id of the user trying to login
	 * @param password the password of the user trying to login 
	 * @throws IllegalArgumentException if id or password is null or an empty string
	 * @throws IllegalArgumentException if the passed in id and password don't match the registrar's credentials
	 * @return true if the user successfully logins
	 */
	public boolean login(String id, String password) {
		String localHashPW = hashPW(password);
		Student s = studentDirectory.getStudentById(id);
		Faculty f = facultyDirectory.getFacultyById(id);
        if (registrar.getId().equals(id)) {
			if (registrar.getPassword().hashCode() == localHashPW.hashCode() && !(currentUser instanceof Student)) {
				currentUser = registrar;
				return true;
			} else {
				return false;
			}
		} else if (s != null && s.getId().equals(id)) {
			if (s.getPassword().hashCode() == localHashPW.hashCode() && !(currentUser instanceof Student)) {
				currentUser = s;
				return true;
			} else {
				return false;
			}
		} else if (f != null && f.getId().equals(id)) {
			if (f.getPassword().hashCode() == localHashPW.hashCode() && !(currentUser instanceof Faculty)) {
				currentUser = f;
				return true;
			} else {
				return false;
			}
		} else {
			System.out.println(registrar.getId());
			throw new IllegalArgumentException("User doesn't exist.");
		}
	}

	/**
	 * Lets the user logout by setting the current user to null. 
	 */
	public void logout() {
		currentUser = null; 
	}
	
	
	/**
	 * Returns the current user.
	 * @return currentUser the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Clears course and student data by resetting courseCatalog and studentDirectory.
	 */
	public void clearData() {
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}

}
