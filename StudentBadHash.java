package assign09;

import java.text.DecimalFormat;

/**
 * This class provides a simple representation for a University of Utah student.
 * Object's hashCode method is overridden with a correct hash function for this
 * object, but one that does a poor job of distributing students in a hash
 * table.
 * 
 * @author CS 2420 course staff and Vany Nguyen and Brooke Griffin
 * @version 11-12-2026
 */
public class StudentBadHash {
	private int uid;
	private String firstName;
	private String lastName;

	/**
	 * Creates a new student with the specified uid, firstName, and lastName.
	 * 
	 * @param uid
	 * @param firstName
	 * @param lastName
	 */
	public StudentBadHash(int uid, String firstName, String lastName) {
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Getter for this Student's UID.
	 * 
	 * @return the UID for this object
	 */
	public int getUid() {
		return this.uid;
	}

	/**
	 * Getter for this Student's first name.
	 * 
	 * @return the first name for this object
	 */

	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Getter for this Student's last name.
	 * 
	 * @return the last name for this object
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Determines whether the given object is the same as this Student.
	 * 
	 * @return true if both objects have the same UID, first name, and last name;
	 *         false otherwise
	 */
	public boolean equals(Object other) {
		if (!(other instanceof StudentBadHash))
			return false;

		StudentBadHash rhs = (StudentBadHash) other;

		return this.uid == rhs.uid && this.firstName.equals(rhs.firstName) && this.lastName.equals(rhs.lastName);
	}

	/**
	 * Generates a textual representation of this Student.
	 * 
	 * @return a textual representation of this object
	 */
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("0000000");
		return firstName + " " + lastName + " (u" + formatter.format(uid) + ")";
	}

	/**
	 * Returns a hash code based only on the length of the first name.
	 * 
	 * @return the hash code for this student
	 */
	public int hashCode() {
		return firstName.length();
	}
}