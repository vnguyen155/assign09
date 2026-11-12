package assign09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * For testing HashTable class.
 * 
 * @author Vany Nguyen and Brooke Griffin
 * @version 11-12-2026
 */
public class HashTableTest {
	private HashTable<String, Integer> emptyTable;
	private HashTable<String, Integer> smallTable;

	/**
	 * Creates fresh tables before every test: one empty, one with three entries.
	 */
	@BeforeEach
	void setUp() {
		emptyTable = new HashTable<>();

		smallTable = new HashTable<>();
		smallTable.put("tea", 23);
		smallTable.put("matcha", 65);
		smallTable.put("coffee", 17);
	}
	
	/*
	 * Constructors
	 */ 
	
	@Test
	public void constructorRejectsZero() {
		assertThrows(IllegalArgumentException.class, () -> new HashTable<String, Integer>(0.0));
	}
	
	@Test
	public void constructorRejectsOne() {
		assertThrows(IllegalArgumentException.class, () -> new HashTable<String, Integer>(1.0));
	}
	
	@Test
	public void constructorAcceptsValidFactor() {
		HashTable<String, Integer> table = new HashTable<>(0.75);
		assertTrue(table.isEmpty());
		assertEquals(101, table.capacity());
	}
	
	@Test
	public void newTableCapacity() {
		assertEquals(101, emptyTable.capacity());
	}
	
	/*
	 * Empty table
	 */ 
	
	@Test
	public void emptyTableIsEmpty() {
		assertTrue(emptyTable.isEmpty());
		assertEquals(0, emptyTable.size());
	}
	
	/*
	 * put()
	 */
	
	@Test
	public void putNewKeyReturnsNull() {
		assertNull(smallTable.put("water", 5));
		assertEquals(4, smallTable.size());
		assertEquals(5, smallTable.get("water"));
	}
	
	@Test
	public void putExistingKeyReplacesValue() {
		assertEquals(23, smallTable.put("tea", 30));
		assertEquals(30, smallTable.get("tea"));
		assertEquals(3, smallTable.size());
	}
	
	/*
	 * containsKey()
	 */
	
	@Test
	public void containsKeyMissingKeyReturnsFalse() {
		assertFalse(smallTable.containsKey("utah"));
	}
	
	@Test 
	public void containsKeyPresentKeyReturnsTrue() {
		assertTrue(smallTable.containsKey("coffee"));
	}
	
	@Test
	public void getPresentKey() {
		assertEquals(65, smallTable.get("matcha"));
	}
	
	/*
	 * containsValue()
	 */
	@Test
	public void containsValuePresentAndMissing() {
		assertTrue(smallTable.containsValue(17));
		assertFalse(smallTable.containsValue(90));
	}
	
	
}
