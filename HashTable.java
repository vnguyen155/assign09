package assign09;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a hash table with collisions resolved using quadratic probing.
 *
 * @author Vany Nguyen and Brooke Griffin
 * @version 11-12-2026
 */

public class HashTable<K, V> implements Map<K, V> {
	private Object[] table;
	private boolean[] deleted;
	private int size;
	private double maxLoadFactor;

	/**
	 * Casts the item at the given index of the backing array to a MapEntry.
	 *
	 * @param index - the index of the item to retrieve
	 * @return the MapEntry at the given index, or null if the cell is empty
	 */
	@SuppressWarnings("unchecked")
	private MapEntry<K, V> entryAt(int index) {
		return (MapEntry<K, V>) table[index];
	}

	/**
	 * Creates an empty hash table with the specified maximum load factor and an
	 * initial capacity of 101.
	 *
	 * @param maxLoadFactor - the maximum load factor, must be in (0.0, 1.0)
	 * @throws IllegalArgumentException if maxLoadFactor is not in (0.0, 1.0)
	 */
	public HashTable(double maxLoadFactor) {
		if (maxLoadFactor <= 0.0 || maxLoadFactor >= 1.0) {
			throw new IllegalArgumentException("Max load factor must be in (0.0, 1.0).");
		}
		this.maxLoadFactor = maxLoadFactor;
		this.table = new Object[101];
		this.deleted = new boolean[101];
		this.size = 0;
	}

	/**
	 * Creates an empty hash table with a maximum load factor of 0.5 and an initial
	 * capacity of 101.
	 */
	public HashTable() {
		this(0.5);
	}

	/**
	 * Removes all mappings from this map.
	 * <p>
	 * O(table length) for a quadratic-probing hash table
	 */
	public void clear() {
		table = new Object[table.length];
		deleted = new boolean[table.length];
		size = 0;
	}

	/**
	 * Determines whether this map contains the specified key.
	 * <p>
	 * O(1) for a quadratic-probing hash table
	 *
	 * @param key - the key being searched for
	 * @return true if this map contains the key, false otherwise
	 */
	public boolean containsKey(K key) {
		return findIndex(key) != -1;
	}

	/**
	 * Determines whether this map contains the specified value.
	 * <p>
	 * O(table length) for a quadratic-probing hash table
	 *
	 * @param value - the value being searched for
	 * @return true if this map contains one or more keys to the specified value,
	 *         false otherwise
	 */
	public boolean containsValue(V value) {
		// Values are not hashed, so check every cell
		for (int i = 0; i < table.length; i++) {
			MapEntry<K, V> entry = entryAt(i);

			// Skip empty and deleted cells. Compare values null-safely
			if (entry != null && !deleted[i] && Objects.equals(entry.getValue(), value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a list view of the mappings contained in this map, where the ordering
	 * of mapping in the list is insignificant.
	 * <p>
	 * O(table length) for a quadratic-probing hash table
	 *
	 * @return a List object containing all mapping (i.e., entries) in this map
	 */
	public List<MapEntry<K, V>> entries() {
		List<MapEntry<K, V>> list = new ArrayList<>();
		for (int i = 0; i < table.length; i++) {
			MapEntry<K, V> entry = entryAt(i);
			if (entry != null && !deleted[i]) {
				list.add(entry);
			}
		}
		return list;
	}

	/**
	 * Gets the value to which the specified key is mapped.
	 * <p>
	 * O(1) for a quadratic-probing hash table
	 *
	 * @param key - the key for which to get the mapped value
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 */
	public V get(K key) {
		int index = findIndex(key);
		if (index == -1) {
			return null;
		}
		return entryAt(index).getValue();
	}

	/**
	 * Determines whether this map contains any mappings.
	 * <p>
	 * O(1) for a quadratic-probing hash table
	 *
	 * @return true if this map contains no mappings, false otherwise
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Associates the specified value with the specified key in this map. (I.e., if
	 * the key already exists in this map, resets the value; otherwise adds the
	 * specified key-value pair.)
	 * <p>
	 * O(1) for a quadratic-probing hash table
	 *
	 * @param key   - the key for which to update the value (if exists) or to be
	 *              added to the table
	 * @param value - the value to be mapped to the key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V put(K key, V value) {
		int h = Math.floorMod(key.hashCode(), table.length);
		int firstAvailable = -1;

		for (int i = 0; i < table.length; i++) {
			int index = (h + i * i) % table.length;
			MapEntry<K, V> entry = entryAt(index);

			if (entry == null || deleted[index]) {
				if (firstAvailable == -1) {
					firstAvailable = index;
				}
				if (entry == null) {
					break;
				}
			} else if (key.equals(entry.getKey())) {
				V oldValue = entry.getValue();
				entry.setValue(value);
				return oldValue;
			}
		}

		if (firstAvailable == -1 || (size + 1) / (double) table.length > maxLoadFactor) {
			rehash();
			return put(key, value);
		}

		table[firstAvailable] = new MapEntry<>(key, value);
		deleted[firstAvailable] = false;
		size++;
		return null;
	}

	/**
	 * Removes the mapping for a key from this map if it is present.
	 * <p>
	 * O(1) for a quadratic-probing hash table
	 *
	 * @param key - the key to be removed
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V remove(K key) {
		int index = findIndex(key);
		if (index == -1) {
			return null;
		}
		V oldValue = entryAt(index).getValue();
		deleted[index] = true;
		size--;
		return oldValue;
	}

	/**
	 * Determines the number of mappings in this map.
	 * <p>
	 * O(1) for a quadratic-probing hash table
	 *
	 * @return the number of mappings in this map
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the current capacity (length of the backing array) of this hash
	 * table.
	 * 
	 * @return the capacity of this hash table
	 */
	public int capacity() {
		return table.length;
	}

	/**
	 * Finds the smallest prime number greater than or equal to num.
	 * 
	 * @param num - the starting number
	 * @return the smallest prime that is at least num
	 */
	private int nextPrime(int num) {
		if (num % 2 == 0) {
			num++;
		}
		while (!isPrime(num)) {
			num += 2;
		}
		return num;
	}

	/**
	 * Determines whether num is a prime number.
	 * 
	 * @param num - the number to check
	 * @return true if num is prime, false otherwise
	 */
	private boolean isPrime(int num) {
		if (num <= 1) {
			return false;
		}
		if (num <= 3) {
			return true;
		}

		if (num % 2 == 0 || num % 3 == 0) {
			return false;
		}

		for (int i = 5; i * i <= num; i += 6) {
			if (num % i == 0 || num % (i + 2) == 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Finds the index of the active entry with given key, using quadratic probing.
	 *
	 * @param key - the key to search for
	 * @return the index of the entry containing key, or -1 if the key is not in the
	 *         table
	 */
	private int findIndex(K key) {
		// starting index, floorMod keeps it non-negative
		int h = Math.floorMod(key.hashCode(), table.length);

		// stop after checking table.length cells (scanned full array)
		for (int i = 0; i < table.length; i++) {
			int index = (h + i * i) % table.length; // probe H + i^2, wrapping around the array
			MapEntry<K, V> entry = entryAt(index);

			// Empty cell: key is not in the table
			if (entry == null) {
				return -1;
			}

			// Found an active entry with a matching key
			if (!deleted[index] && key.equals(entry.getKey())) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * Grows the backing array to the next prime at least twice its current length,
	 * then re-inserts every active entry. Deleted entries are dropped.
	 */
	private void rehash() {
		// Save active entries before replacing the arrays (skip deleted cells)
		List<MapEntry<K, V>> oldEntries = entries();

		// New and larger arrays; both must have the same length
		int newCapacity = nextPrime(2 * table.length);
		table = new Object[newCapacity];
		deleted = new boolean[newCapacity];
		size = 0;

		// Re-insert so every key gets its index for the new capacity
		for (MapEntry<K, V> entry : oldEntries) {
			put(entry.getKey(), entry.getValue());
		}
	}
}
