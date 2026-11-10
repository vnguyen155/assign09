package assign09;

import java.util.List;

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
	 * 
	 * O(table length) for a quadratic-probing hash table
	 */
	public void clear() {
		table = new Object[table.length];
		deleted = new boolean[table.length];
		size = 0;
	}

	/**
	 * Determines whether this map contains the specified key.
	 * 
	 * O(1) for a quadratic-probing hash table
	 * 
	 * @param key - the key being searched for
	 * @return true if this map contains the key, false otherwise
	 */
	public boolean containsKey(K key) {

	}

	/**
	 * Determines whether this map contains the specified value.
	 * 
	 * O(table length) for a quadratic-probing hash table
	 * 
	 * @param value - the value being searched for
	 * @return true if this map contains one or more keys to the specified value,
	 *         false otherwise
	 */
	public boolean containsValue(V value) {

	}

	/**
	 * Returns a list view of the mappings contained in this map, where the ordering
	 * of mapping in the list is insignificant.
	 * 
	 * O(table length) for a quadratic-probing hash table
	 * 
	 * @return a List object containing all mapping (i.e., entries) in this map
	 */
	public List<MapEntry<K, V>> entries() {

	}

	/**
	 * Gets the value to which the specified key is mapped.
	 * 
	 * O(1) for a quadratic-probing hash table
	 * 
	 * @param key - the key for which to get the mapped value
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 */
	public V get(K key) {

	}

	/**
	 * Determines whether this map contains any mappings.
	 * 
	 * O(1) for a quadratic-probing hash table
	 * 
	 * @return true if this map contains no mappings, false otherwise
	 */
	public boolean isEmpty() {

	}

	/**
	 * Associates the specified value with the specified key in this map. (I.e., if
	 * the key already exists in this map, resets the value; otherwise adds the
	 * specified key-value pair.)
	 * 
	 * O(1) for a quadratic-probing hash table
	 * 
	 * @param key   - the key for which to update the value (if exists) or to be
	 *              added to the table
	 * @param value - the value to be mapped to the key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V put(K key, V value) {

	}

	/**
	 * Removes the mapping for a key from this map if it is present.
	 * 
	 * O(1) for a quadratic-probing hash table
	 *
	 * @param key - the key to be removed
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V remove(K key) {

	}

	/**
	 * Determines the number of mappings in this map.
	 * 
	 * O(1) for a quadratic-probing hash table
	 * 
	 * @return the number of mappings in this map
	 */
	public int size() {
		return size;
	}
}
