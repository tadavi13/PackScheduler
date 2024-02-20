package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Test class for FacultyRecordIO
 * @author Tyler Davis
 */
public class FacultyRecordIOTest {
	/** File path that does not exist*/
	private final String nonExistantFile = "test-files/not_found.txt";
	/** File path with invalid faculty records */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";
	/** File path with valid faculty records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** String with first valid faculty */
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	/** String with second valid faculty */
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	/** String with third valid faculty */
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	/** String array with all valid faculty */
	private String [] validFaculty = {validFaculty0, validFaculty1, validFaculty2};
	/** Hashed version of the password */
	private String hashPW;
	/** Algorithm for hashing */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Helper method ran before each test. Replaces "pw" with its hashed value.
	 */
	@BeforeEach
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = Base64.getEncoder().encodeToString(digest.digest());
	        
	        for (int i = 0; i < validFaculty.length; i++) {
	            validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act, "Expected: " + exp + " Actual: " + act); 
				//The third argument helps with debugging!
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * Tests that the correct number of faculty are read from faculty_records.txt and that
	 * each faculty memeber is valid. 
	 */
	@Test
	public void testReadValidFacultyRecords() {
		try {
			LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, faculty.size());
			assertEquals(validFaculty[0], faculty.get(0).toString());
			assertEquals(validFaculty[1], faculty.get(1).toString());
			assertEquals(validFaculty[2], faculty.get(2).toString());
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}
	
	/**
	 * Tests that no Faculty are read in from invalid_faculty_records.txt and that
	 * the created LinkedList is empty. 
	 */
	@Test
	public void testReadInvalidFacultyRecords() {
		LinkedList<Faculty> faculty;
		try {
			faculty = FacultyRecordIO.readFacultyRecords(invalidTestFile);
			assertEquals(0, faculty.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}
	
	/**
	 * Tests that a FileNotFoundException is thrown if a file that doesn't exist is passed in.
	 */
	@Test
	public void testReadFacultyRecordsFileNotFound() {
		assertThrows(FileNotFoundException.class, () -> FacultyRecordIO.readFacultyRecords(nonExistantFile), 
				"FileNotFoundException not thrown");
	}

	/**
	 * Tests that writing a list of Faculty results in the expected output.
	 */
	@Test
	public void testWriteFacultyRecordsValid() {
		LinkedList<Faculty> facultyList = new LinkedList<Faculty>();
		for (String faculty : validFaculty) {
			String[] split = faculty.split(",");
			facultyList.add(new Faculty(split[0], split[1], split[2], split[3], split[4], Integer.parseInt(split[5])));
		}
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", facultyList);
		} catch (IOException e) {
			fail("Cannot write to student records file");
		}
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}
	
	/**
	 * Tests that an IOException is thrown if there is no permission to write to the file. 
	 */
	@Test
	public void testWriteFacultyRecordsNoPermissions() {
		LinkedList<Faculty> faculty = new LinkedList<Faculty>();
		faculty.add(new Faculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 1));
		Exception exception = assertThrows(IOException.class, 
				() -> FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_student_records.txt", faculty));
		assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
	}
}
