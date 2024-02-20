package edu.ncsu.csc216.pack_scheduler.io;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.io.File;


/**
 * Reads Student records from text file. Writes a set of StudentRecords to a file.
 * @author Ken Ogura
 * @author Zachary Orr
 * @author Alex Jong
 */
public class StudentRecordIO {
	
	/**
	 * Reads student records from a file and generates a list of valid Students. Any invalid
	 * Students are ignored. If the file to read cannot be found a FileNotFoundException is thrown.
	 * @param fileName file to read Student records from
	 * @return a list of valid Students
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> records = new SortedList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				records.add(student);
			} catch (IllegalArgumentException IAE) {
				// Invalid line, skip it.
			}
		}
		fileReader.close();
		return records;
	}
	
	/**
	 * Reads a line from a file to gather information about a Student and return it.
	 * Throws an IllegalArgumentException if a line in the file is invalid.
	 * @param line a line separated by commas containing details of the Student
	 * @return Student a new Student object that contains all details found in line
	 * @throws IllegalArgumentException if the line is invalid
	 */
	private static Student processStudent(String line) {
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter(",");
		try {
			String firstName = scanner.next();
			String lastName = scanner.next();
			String id = scanner.next();
			String email = scanner.next();
			String password = scanner.next();
			int maxCredits = Integer.parseInt(scanner.next());    
			scanner.close();
			return new Student(firstName, lastName, id, email, password, maxCredits);
			
		} catch (Exception e) {
			scanner.close();
			throw new IllegalArgumentException("Invalid line.");
		}
	}

	/**
	 * Writes the given list of Students to a file
	 * @param fileName file to write records of Students to
	 * @param studentDirectory list of Students to write
	 * @throws IOException if unable to write to file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}
		fileWriter.close();
	}

}
