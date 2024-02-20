package edu.ncsu.csc216.pack_scheduler.directory;


import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests StudentDirectory.
 * @author Sarah Heckman
 * @author Zachary Orr
 * @author Alex Jong
 */
public class StudentDirectoryTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 15;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests StudentDirectory(). Tests that the directory is initialized to an empty list and tries to remove a student from it.
	 */
	@Test
	public void testStudentDirectory() {
		//Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("sesmith5"));
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.testNewStudentDirectory(). Checks that newStudentDirectory empties the directory.
	 */
	@Test
	public void testNewStudentDirectory() {
		//Test that if there are students in the directory, they 
		//are removed after calling newStudentDirectory().
		StudentDirectory sd = new StudentDirectory();
		
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		
		sd.newStudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile() with a valid file input.
	 */
	@Test
	public void testLoadStudentsFromFile() {
		StudentDirectory sd = new StudentDirectory();
				
		//Test valid file
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
	}
	
	/**
	 * Tests StudentDirectory.loadStudentsFromFile() with an invalid file input.
	 */
	@Test
	public void testLoadStudentsFromFileInvalid() {
		StudentDirectory sd = new StudentDirectory();
				
		//Test invalid file
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> sd.loadStudentsFromFile("invalid"));
		assertEquals("Unable to read file invalid", e1.getMessage());
	}

	/**
	 * Tests StudentDirectory.addStudent(). Tests adding a Student to the directory.
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();
		
		//Test valid Student
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
	}
	
	/**
	 * Tests StudentDirectory.addStudent() with invalid input and checks to see if an IllegalArgumentException is thrown.
	 */
	@Test
	public void testAddStudentInvalid() {
		StudentDirectory sd = new StudentDirectory();
		
		//Test invalid Student
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid password", e1.getMessage());
		
	}

	/**
	 * Tests StudentDirectory.removeStudent(). Also uses getStudentDirectory functionality.
	 * Tests for removing both existing and nonexisting students.
	 */
	@Test
	public void testRemoveStudent() {
		StudentDirectory sd = new StudentDirectory();
				
		//Tests adding and removing students
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("efrost"));
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);

		assertEquals("Zahir", studentDirectory[5][0]);
		assertEquals("King", studentDirectory[5][1]);
		assertEquals("zking", studentDirectory[5][2]);
		
		//Tests removing nonexistent students
		assertFalse(sd.removeStudent("notastudent"));
		//checking nothing was actually removed
		assertEquals(9, studentDirectory.length);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory(). Tests adding a student and saving the directory, 
	 * and tests if the IllegalArgumentException is thrown if studentRecordIO throws an IOException.
	 */
	@Test
	public void testSaveStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();
		
		//Add a student
		//sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
		
		//Test if the IOException thrown by StudentRecordIO is caught and an IllegalArgumentException thrown.
		Exception e = assertThrows(IllegalArgumentException.class, 
				() -> sd.saveStudentDirectory("/home/sesmith5/actual_student_records.txt"));
		assertEquals("Unable to write to file /home/sesmith5/actual_student_records.txt", e.getMessage());
	}
	
	/**
	 * Test StudentDirectory.getStudentById(). Tests getting a student from id and if returns null when student not found from id.
	 */
	@Test
	public void testGetStudentById() {
		
		// Getting student from valid id 
		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		String[][] studentDirectory = sd.getStudentDirectory();
		assertEquals("Zahir", studentDirectory[6][0]);
		assertEquals("King", studentDirectory[6][1]);
		assertEquals("zking", studentDirectory[6][2]);
		Student s = sd.getStudentById("zking");
		assertEquals("Zahir", s.getFirstName());
		assertEquals("King", s.getLastName());
		assertEquals("zking", s.getId());
		
		// Invalid id
		Student s1 = sd.getStudentById("hey");
		assertEquals(null, s1);
		
		
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
