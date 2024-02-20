package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Base64;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Tests the StudentRecordIO class
 * @author Ken Ogura
 */
class StudentRecordIOTest {
	/** File path that does not exist*/
	private final String nonExistantFile = "test-files/not_found.txt";
	/** File path with invalid student records */
	private final String invalidTestFile = "test-files/invalid_student_records.txt";
	/** File path with valid student records */
	private final String validTestFile = "test-files/student_records.txt";
	/** String with first valid student */
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/** String with second valid student */
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** String with third valid student */
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/** String with fourth valid student */
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** String with fifth valid student */
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/** String with sixth valid student */
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/** String with seventh valid student */
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/** String with eighth valid student */
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/** String with ninth valid student */
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** String with tenth valid student */
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	/** String array with all valid students */
	private String [] validStudents = {validStudent0, validStudent1, validStudent2, validStudent3, validStudent4, validStudent5,
	        validStudent6, validStudent7, validStudent8, validStudent9};
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
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
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
	 * Tests that the correct number of students are read from student_records.txt and that
	 * each student is valid. 
	 */
	@Test
	public void testReadValidStudentRecords() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords(validTestFile);
			assertEquals(10, students.size());
			assertEquals(validStudents[3], students.get(0).toString());
			assertEquals(validStudents[6], students.get(1).toString());
			assertEquals(validStudents[4], students.get(2).toString());
			assertEquals(validStudents[5], students.get(3).toString());
			assertEquals(validStudents[2], students.get(4).toString());
			assertEquals(validStudents[8], students.get(5).toString());
			assertEquals(validStudents[0], students.get(6).toString());
			assertEquals(validStudents[9], students.get(7).toString());
			assertEquals(validStudents[1], students.get(8).toString());
			assertEquals(validStudents[7], students.get(9).toString());
			
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}
	
	/**
	 * Tests that no Students are read in from invalid_student_records.txt and that
	 * the created ArrayList is empty. 
	 */
	@Test
	public void testReadInvalidStudentRecords() {
		SortedList<Student> students;
		try {
			students = StudentRecordIO.readStudentRecords(invalidTestFile);
			assertEquals(0, students.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}
	
	/**
	 * Tests that a FileNotFoundException is thrown if a file that doesn't exist is passed in.
	 */
	@Test
	public void testReadStudentRecordsFileNotFound() {
		assertThrows(FileNotFoundException.class, () -> StudentRecordIO.readStudentRecords(nonExistantFile), 
				"FileNotFoundException not thrown");
	}

	/**
	 * Tests that writing a list of Students results in the expected output.
	 */
	@Test
	public void testWriteStudentRecordsValid() {
		SortedList<Student> students = new SortedList<Student>();
		for (String student : validStudents) {
			String[] split = student.split(",");
			students.add(new Student(split[0], split[1], split[2], split[3], split[4], Integer.parseInt(split[5])));
		}
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);
		} catch (IOException e) {
			fail("Cannot write to student records file");
		}
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
	}
	
	/**
	 * Tests that an IOException is thrown if there is no permission to write to the file. 
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		Exception exception = assertThrows(IOException.class, 
				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
		assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
	}

}
