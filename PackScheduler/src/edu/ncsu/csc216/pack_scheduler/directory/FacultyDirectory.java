/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Class for maintaining directory of Faculty Users
 * @author Chris Brown
 * @author Tyler
 * @author Ken Ogura
 */
public class FacultyDirectory {
	
	/** constant string for directory hash algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Doubly Linked List for storing faculty users */
	private LinkedList<Faculty> facultyDirectory;
	
	/**
	 * FacultyDirectory constructor
	 */
	public FacultyDirectory(){
		newFacultyDirectory();
	}
	
	/**
	 * creates a new faculty directory list
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * loads a faculty directory from an external file
	 * @param fileName name of input file
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Hashes a String according to the SHA-256 algorithm, and outputs the digest in base64 encoding.
	 * This allows the encoded digest to be safely copied, as it only uses [a-zA-Z0-9+/=].
	 * 
	 * @param toHash the String to hash 
	 * @throws IllegalArgumentException if the password given cannot be hashed
	 * @return the encoded digest of the hash algorithm in base64
	 */
	private static String hashString(String toHash) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(toHash.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * adds a faculty user to directory
	 * @param firstName name of faculty user to add
	 * @param lastName of faculty user to add
	 * @param id of faculty user to add
	 * @param email of faculty user to add
	 * @param password of faculty user to add
	 * @param repeatPassword of faculty user to add
	 * @param maxCourses max courses of faculty to add
	 * @return true if user is added, false otherwise
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}
		
		hashPW = hashString(password);
		repeatHashPW = hashString(repeatPassword);
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		//If an IllegalArgumentException is thrown, it's passed up from Student
		//to the GUI
		Faculty faculty = null;
		if (maxCourses < 3 || maxCourses > Faculty.MAX_COURSES) {
			faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		} else {
			faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		}
		
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(faculty.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(faculty);
	}
	
	/**
	 * removes a faculty user from directory
	 * @param facultyId ID of user to remove
	 * @return true if user is removed, false otherwise
	 */
	public boolean removeFaculty(String facultyId) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User f = facultyDirectory.get(i);
			if (f.getId().equals(facultyId)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * returns faculty directory as a 2d array of Strings
	 * @return directory as 2d array
	 */
	public String[][] getFacultyDirectory(){
		String[][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User f = facultyDirectory.get(i);
			directory[i][0] = f.getFirstName();
			directory[i][1] = f.getLastName();
			directory[i][2] = f.getId();
		}
		return directory;
	}
	
	/**
	 * output faculty directory to file
	 * @param outputName name of output file
	 */
	
	public void saveFacultyDirectory(String outputName) {
		try {
			FacultyRecordIO.writeFacultyRecords(outputName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + outputName);
		}
	}

	/**
	 * retrieves faculty with given id from directory
	 * @param facultyId id of desired faculty
	 * @return faculty user with given id
	 */
	public Faculty getFacultyById(String facultyId) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			if (facultyDirectory.get(i).getId().equals(facultyId)) {
				return facultyDirectory.get(i);
			}
		}
		return null;
	}
}
