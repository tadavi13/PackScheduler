package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Reads Faculty records from text file. Writes a set of FacultyRecords to a file.
 * @author Tyler Davis
 */
public class FacultyRecordIO {
	/**
	 * Reads faculty records from a file and generates a list of valid Faculty. Any invalid
	 * faculty are ignored. If the file to read cannot be found a FileNotFoundException is thrown.
	 * @param fileName file to read Faculty records from
	 * @return a list of valid Faculty
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> records = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {
				Faculty faculty = processFaculty(fileReader.nextLine());
				records.add(faculty);
			} catch (IllegalArgumentException IAE) {
				// Invalid line, skip it.
			}
		}
		fileReader.close();
		return records;
	}
	
	/**
	 * Reads a line from a file to gather information about a Faculty and return it.
	 * Throws an IllegalArgumentException if a line in the file is invalid.
	 * @param line a line separated by commas containing details of the Faculty
	 * @return Faculty a new Faculty object that contains all details found in line
	 * @throws IllegalArgumentException if the line is invalid
	 */
	private static Faculty processFaculty(String line) {
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter(",");
		try {
			String firstName = scanner.next();
			String lastName = scanner.next();
			String id = scanner.next();
			String email = scanner.next();
			String password = scanner.next();
			int maxCourses = Integer.parseInt(scanner.next());    
			scanner.close();
			return new Faculty(firstName, lastName, id, email, password, maxCourses);
			
		} catch (Exception e) {
			scanner.close();
			throw new IllegalArgumentException("Invalid line.");
		}
	}

	/**
	 * Writes the given list of Students to a file
	 * @param fileName file to write records of Students to
	 * @param facultyDirectory list of Students to write
	 * @throws IOException if unable to write to file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < facultyDirectory.size(); i++) {
			fileWriter.println(facultyDirectory.get(i).toString());
		}
		fileWriter.close();
	}
}
