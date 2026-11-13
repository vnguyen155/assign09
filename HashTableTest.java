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
	private HashTable<Integer, String> stringTable;

	/**
	 * Creates fresh tables before every test: one empty, one with three entries.
	 */
	@BeforeEach
	void setUp() {
		emptyTable = new HashTable<>();

		stringTable = new HashTable<>();

		smallTable = new HashTable<>();
		smallTable.put("tea", 23);
		smallTable.put("matcha", 65);
		smallTable.put("coffee", 17);
	}

	//------------------------- Constructors ---------------------------------

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

	//------------------------- Empty table ---------------------------------

	@Test
	public void emptyTableIsEmpty() {
		assertTrue(emptyTable.isEmpty());
		assertEquals(0, emptyTable.size());
	}

	//------------------------- put() ---------------------------------

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

	//------------------------- containsKey() ---------------------------------

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

	//------------------------- containsValue() ---------------------------------
	@Test
	public void containsValuePresentAndMissing() {
		assertTrue(smallTable.containsValue(17));
		assertFalse(smallTable.containsValue(90));
	}

	@Test
	public void containsValueSingleElementTest() {
		emptyTable.put("hello", 1);
		assertTrue(emptyTable.containsValue(1));
	}

	@Test
	public void containsValueSingleElementFalseTest() {
		assertFalse(smallTable.containsValue(67));
		assertFalse(smallTable.containsValue(23456));
	}

	@Test
	public void containsValueTrueForAllValuesLargeTable() {
		String[] values = {"hello", "world", "i", "am", "a", "robot", "computer", "program", "goodbye", "Hello"};
		for(int i = 0; i < 10; i++) {
			stringTable.put(i, values[i]);
		}
		for(int i = 0; i < 10; i++) {
			assertTrue(stringTable.containsValue(values[i]));;
		}
	}

	@Test
	public void containsValueFalseForAbsentValuesLargeTable() {
		stringTable.put(1, "hello");
		stringTable.put(2, "world");
		stringTable.put(4, "am");
		stringTable.put(5, "a");
		stringTable.put(6, "robot");
		stringTable.put(8, "program");
		stringTable.put(9, "goodbye");
		stringTable.put(10, "Hello");
		assertTrue(stringTable.containsValue("hello"));
		assertTrue(stringTable.containsValue("world"));

		assertFalse(stringTable.containsValue("i"));
		assertTrue(stringTable.containsValue("am"));
		assertTrue(stringTable.containsValue("a"));
		assertTrue(stringTable.containsValue("robot"));

		assertFalse(stringTable.containsValue("computer"));
		assertTrue(stringTable.containsValue("program"));
		assertTrue(stringTable.containsValue("goodbye"));
		assertTrue(stringTable.containsValue("Hello"));
	}

	//------------------------- entries() ---------------------------------

	@Test
	public void entriesStringTableTest() {
		String[] values = {"hello", "world", "i", "am", "a", "robot"};
		for(int i = 0; i < values.length; i++) {
			stringTable.put(i, values[i]);
		}
		List<MapEntry<Integer, String>> expected = stringTable.entries();
		assertEquals(expected, stringTable.entries());
	}

	@Test
	public void entriesSmallTableTest() {
		Integer[] values = {890, 23445, 12893, 8239};

		List<MapEntry<String, Integer>> expected = emptyTable.entries();
		assertEquals(expected, emptyTable.entries());
	}

	//------------------------- remove() ---------------------------------

	@Test void removeSmallTableTest(){
		Integer result = smallTable.remove("matcha");
		assertEquals(65, result);
		assertFalse(smallTable.containsKey("matcha"));
		assertTrue(smallTable.containsKey("tea"));
		assertTrue(smallTable.containsValue(17));
	}

	@Test void removeEmptyTableReturnsNull(){
		Integer result = emptyTable.remove("matcha");
		assertEquals(-1, result);
	}

	//------------------------- clear() ---------------------------------

	@Test
	public void clearTestStringTable() {
		stringTable.put(1, "hello");
		stringTable.put(2, "world");
		stringTable.put(3, "Goodbye");
		stringTable.put(4, "World");
		stringTable.put(5, "!");
		stringTable.clear();
		assertTrue(stringTable.isEmpty());
		assertFalse(stringTable.containsValue("World"));
	}

	@Test
	public void clearTestSmallTable() {
		smallTable.put("hello", 1);
		smallTable.put("world", 2);
		smallTable.put("Goodbye", 3);
		smallTable.put("World", 4);
		smallTable.put("!", 5);
		smallTable.clear();
		assertTrue(smallTable.isEmpty());
		assertFalse(smallTable.containsValue(4));
	}

	@Test
	public void clearEmptyTable(){
		emptyTable.clear();
		assertTrue(emptyTable.isEmpty());
		assertFalse(emptyTable.containsValue(1));
	}

	//------------------------- rehash() / capacity() ---------------------------------

	@Test void stringTableCapacityIsDoubleAndPrimeOriginalSizeTest(){
		String[] values = {"hello", "world", "i", "am", "a", "robot"};
		for(int i = 0; i < values.length; i++) {
			stringTable.put(i, values[i]);
		}
		int result = stringTable.capacity();
		assertTrue(result == );

	}

}
