package edu.ncsu.csc216.pack_scheduler.catalog;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;




/**
 * Tests the course catalog class.
 * @author Ken Ogura
 * @author Alex Jong
 * @author Zachary Orr
 */
public class CourseCatalogTest {
	/** File containing valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	
	/**
	 * Tests CourseCatalog(). Tests that the directory is initialized to an empty list and tries to remove a course from it.
	 */
	@Test
	public void testCourseCatalog() {

		CourseCatalog cc = new CourseCatalog();
		assertFalse(cc.removeCourseFromCatalog("CSC116", "003"));
		assertEquals(0, cc.getCourseCatalog().length);
	}
	
	/**
	 * Tests CourseCatalog.loadCoursesFromFile() with a valid file input.
	 * @throws FileNotFoundException if file is not found
	 */
	@Test
	public void testLoadCoursesFromFile() throws FileNotFoundException {
		CourseCatalog cc = new CourseCatalog();
				
		//Test valid file
		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);
	}
	
	
	/**
	 * Tests saveCourseCatalog()
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 10, "MW", 910, 1100);
		catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", 3, "spbalik", 10, "MW", 1120, 1310);
		catalog.addCourseToCatalog("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "TH", 1330, 1445);
		catalog.addCourseToCatalog("CSC216", "Software Development Fundamentals", "002", 3, "jtking", 10, "MW", 1330, 1445);
		catalog.addCourseToCatalog("CSC226", "Discrete Mathematics for Computer Scientists", "001", 3, "tmbarnes", 10, "MWF", 935, 1025);
		
		try {
			catalog.saveCourseCatalog("test-files/actual_course_catalog_records.txt");
		} catch (IllegalArgumentException e) {
			fail("Cannot write to course records file");
		}
		checkFiles("test-files/expected_course_catalog_records.txt", "test-files/actual_course_catalog_records.txt");
	}
	
	/**
	 * Tests CourseCatalog.removeCourseFromCatalog(). Also uses getCourseCatalog functionality.
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog catalog = new CourseCatalog();
				

		catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 10, "MW", 1250, 1440);
		catalog.addCourseToCatalog("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		catalog.addCourseToCatalog("CSC216", "Software Development Fundamentals", "601", 3, "jctetter", 10, "T", 1100, 1200);
		catalog.removeCourseFromCatalog("CSC216", "001");
		String[][] catalogArray = catalog.getCourseCatalog();
		assertEquals(catalogArray[0][0], "CSC116");
		assertEquals(catalogArray[1][0], "CSC216");
		
	}
	
	/**
	 * Tests getCourseFromCatalog()
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 10, "MW", 1250, 1440);
		catalog.addCourseToCatalog("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		catalog.addCourseToCatalog("CSC216", "Software Development Fundamentals", "601", 3, "jctetter", 10, "T", 1100, 1200);
		Course catalogCourse = catalog.getCourseFromCatalog("CSC116", "003");
		Course testCourse = new Course("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 10, "MW", 1250, 1440);
		assertTrue(catalogCourse.equals(testCourse));
	}
	
	/**
	 * Tests invalid inputs addCourseToCatalog()
	 */
	@Test
	public void testAddCourseFromCatalogInvalid() {
		
		CourseCatalog catalog = new CourseCatalog();
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog(null, "Title", "001", 3, "crg", 10, "MW", 1015, 1130); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("", "Title", "001", 3, "crg", 10, "MW", 1015, 1130); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("CSC 216", null, "001", 3, "crg", 10, "MW", 1015, 1130); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("CSC 216", "", "001", 3, "crg", 10, "MW", 1015, 1130); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("CSC 216", "Title", null, 3, "crg", 10, "MW", 1015, 1130); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("CSC 216", "Title", "01", 3, "crg", 10, "MW", 1015, 1130); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("CSC 216", "Title", "001", 3, null, 10, "MW", 1015, 1130); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("CSC 216", "Title", "001", 3, "", 10, "MW", 1015, 1130); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("CSC 216", "Title", "001", 3, "crg", 10, null, 1015, 1130); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("CSC 216", "Title", "001", 3, "crg", 10, "", 1015, 1130); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("CSC 216", "Title", "001", 3, "crg", 10, "MW", 1150, 1130); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("CSC 216", "Title", "001", 3, "crg", 10, "MW", 2415, 2500); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("CSC 216", "Title", "001", 3, "crg", 10, "MW", 1060, 1130); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("CSC 216", "Title", "001", 3, "crg", 10, "MW", 1015, 2430); });
		assertThrows(IllegalArgumentException.class, () -> {catalog.addCourseToCatalog("CSC 216", "Title", "001", 3, "crg", 10, "MW", 1015, 1160); });
		
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
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
