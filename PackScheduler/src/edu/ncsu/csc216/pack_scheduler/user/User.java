package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

/**
 * Constructs User object with the user's first name, last name, id, email, and password. Can edit and retrieve all of these parameters.
 * Can generate hash code and compare objects to see if they are equal.
 * @author Divya Srinivasan
 */
public abstract class User {

	/** String for the first name of the student */
	private String firstName;
	/** String for the last name of the student */
	private String lastName;
	/** String for the id of the student */
	private String id;
	/** String for the email of the student */
	private String email;
	/** String for hash PW */
	private String password;

	/**
	 * Constructs a user with the passed in parameters.
	 * @param firstName first name of the user
	 * @param lastName last name of the user
	 * @param id id of the user
	 * @param email email of the user
	 * @param password password of the user
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		super();
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Sets first name for the Student. If the first name is null or empty, throws
	 * IllegalArgumentException.
	 * 
	 * @param firstName String containing student first name.
	 * @throws IllegalArgumentException if the first name is null or empty.
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Sets last name for the Student. If the last name is null or empty, throws
	 * IllegalArgumentException.
	 * 
	 * @param lastName String containing student last name.
	 * @throws IllegalArgumentException if the last name is null or empty.
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Sets id for the Student. If the id is null or empty, throws
	 * IllegalArgumentException.
	 * 
	 * @param id String containing student id.
	 * @throws IllegalArgumentException if the id is null or empty.
	 */
	protected void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Sets email for the Student. If the email is null or empty, does not contain a
	 * '@', does not contain a '.', or has the last '.' before the first '@', throws
	 * IllegalArgumentException.
	 * 
	 * @param email String containing student email.
	 * @throws IllegalArgumentException if the email is null or empty, does not
	 *                                  contain a '@', does not contain a '.', or
	 *                                  has the last '.' before the first '@'.
	 */
	public void setEmail(String email) {
		int firstAt = -1;
		int lastDot = -1;
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		}
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '.') {
				lastDot = i;
			} else if (email.charAt(i) == '@') {
				firstAt = i;
			}
		}
		if (firstAt == -1) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (lastDot == -1) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (lastDot < firstAt) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Sets password for the Student. If the password is null or empty, throws
	 * IllegalArgumentException.
	 * 
	 * @param password String containing student password.
	 * @throws IllegalArgumentException if the password is null or empty.
	 */
	public void setPassword(String password) {
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * Returns student email.
	 * 
	 * @return String containing student email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the students first name.
	 * 
	 * @return firstName the students first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the students last name.
	 * 
	 * @return firstName the students first name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the students ID.
	 * 
	 * @return firstName the students first name
	 */
	public String getId() {
		return id;
	}

	/**
	 * gets the hashed password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Generates hashCode. Generates a hash code
	 * 
	 * @return the password
	 */
	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password);
	}

	/**
	 * Equals method compares this to object and returns true
	 * if they are the same
	 * returns false otherwise
	 * 
	 * @return the password
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}

}