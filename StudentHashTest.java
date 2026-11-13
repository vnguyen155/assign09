package assign09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * For testing 3 classes: StudentBadHash, StudentGoodHash and StudentMediumHash.
 * 
 * @author Vany Nguyen and Brooke Griffin
 * @version 11-12-2026
 */
public class StudentHashTest {
	//-------------------------- StudentBadHash ---------------------------------
	@Test
	public void badHashEqualsStudentsSameHash() {
		StudentBadHash s1 = new StudentBadHash(1019999, "Alan", "Turing");
		StudentBadHash s2 = new StudentBadHash(1019999, "Alan", "Turing");

		assertTrue(s1.equals(s2));
		assertEquals(s1.hashCode(), s2.hashCode());
	}

	@Test
	public void badHashDifferentUidNotEqual() {
		StudentBadHash s1 = new StudentBadHash(1019999, "Alan", "Turing");
		StudentBadHash s2 = new StudentBadHash(1602056, "Alan", "Turing");

		assertFalse(s1.equals(s2));
	}

	@Test
	public void badHashCollidesOnSameLength() {
		StudentBadHash s1 = new StudentBadHash(1019999, "Alan", "Turing");
		StudentBadHash s2 = new StudentBadHash(1602056, "John", "Smith");

		assertEquals(s1.hashCode(), s2.hashCode());
	}
	
	//-------------------------- StudentMediumHash ---------------------------------
	
	
	
	//-------------------------- StudentGoodHash ---------------------------------
}
