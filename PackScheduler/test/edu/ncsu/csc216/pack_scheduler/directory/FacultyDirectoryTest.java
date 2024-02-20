/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Test class for FacultyDirectory class
 * @author Chris Brown
 */
class FacultyDirectoryTest {
	
	/** Valid records file */
	private final String validInFile = "test-files/expected_full_faculty_records.txt";
	/** Valid records file */
	private final String validOutFile = "test-files/faculty_records.txt";
	/** Faculty Correct first name */
	private static final String FIRST_NAME = "John";
	/** Faculty Correct last name */
	private static final String LAST_NAME = "Smith";
	/** Faculty Correct Email */
	private static final String EMAIL = "jsmith@ncsu.edu";
	/** Faculty Correct password */
	private static final String PASSWORD = "password123";
	/** Faculty Correct password */
	private static final String REPEAT_PASSWORD = "password123";
	/** Faculty Correct ID */
	private static final String ID = "jsmith";
	/** Faculty Correct max courses */
	private static final int MAX_COURSES = 3;

	/**
	 * Test method for FacultyDirectory objects
	 */
	@Test
	void testFacultyDirectory() {
		FacultyDirectory fd1 = new FacultyDirectory();
		
		String[][] expectedDirectory = new String[1][3];
		expectedDirectory[0][0] = FIRST_NAME;
		expectedDirectory[0][1] = LAST_NAME;
		expectedDirectory[0][2] = ID;
		
		assertTrue(fd1.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, REPEAT_PASSWORD, MAX_COURSES));
		assertArrayEquals(expectedDirectory, fd1.getFacultyDirectory());
		
		assertFalse(fd1.removeFaculty("invalid name"));
		assertTrue(fd1.removeFaculty(ID));
		assertEquals(null, fd1.getFacultyById(ID));
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> fd1.loadFacultyFromFile("invalid file name"));
		assertEquals("Unable to read file invalid file name", e.getMessage());
		fd1.loadFacultyFromFile(validInFile);
		fd1.saveFacultyDirectory(validOutFile);
		
		// Getting Faculty from valid id 
		FacultyDirectory fd2 = new FacultyDirectory();
		fd2.loadFacultyFromFile(validInFile);
		assertEquals(8, fd1.getFacultyDirectory().length);
		String[][] facultyDirectory = fd1.getFacultyDirectory();
		assertEquals("Fiona", facultyDirectory[1][0]);
		assertEquals("Meadows", facultyDirectory[1][1]);
		assertEquals("fmeadow", facultyDirectory[1][2]);
		Faculty f = fd2.getFacultyById("fmeadow");
		assertEquals("Fiona", f.getFirstName());
		assertEquals("Meadows", f.getLastName());
		assertEquals("fmeadow", f.getId());
						
		// Invalid id
		Faculty f2 = fd2.getFacultyById("hey");
		assertEquals(null, f2);
	}
}
