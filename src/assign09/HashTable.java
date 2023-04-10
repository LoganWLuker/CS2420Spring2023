package assign09;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class HashTable <K, V> implements Map<K, V>
{
	private BigInteger capacity = new BigInteger("7");
	private ArrayList<MapEntry<K, V>> table;
	private int size;
	
	/**
	 * Default Constructor
	 */
	public HashTable() {
		size = 0;
		table = new ArrayList<MapEntry<K, V>>(capacity.intValue());
		for(int i = 0; i < capacity.intValue(); i++)
			table.add(null);
	}
	@Override
	public V remove(K key)
	{
	    int index = hash(key); // calculate the hash index for the key
	    int i = 0;
	    while (table.get(index) != null) { // loop until an empty slot is reached
	        if (table.get(index).getKey() != null && table.get(index).getKey().equals(key)) { // check if the entry matches the key
	            V value = table.get(index).getValue(); // get the value of the entry
	            table.set(index, new MapEntry<K, V>(null, null)); // set the entry to a new MapEntry with null key and value (lazy deletion)
	            size--; // decrement the size of the hash table
	            return value; // return the value of the removed entry
	        }
	        i++;
	        index = (hash(key) + i * i) % capacity.intValue(); // calculate the next index to check
	    }
	    return null; // key not found, return null
	}
	@Override
	public V put(K key, V value)
	{
		int index = hash(key);
		// Get the entry at the index.
	    MapEntry<K, V> entry = table.get(index);
	    
	    // Keep track of the number of collisions.
	    int i = 0;
	    // Make sure It's not already in there
	    while (entry != null && entry.getKey() != null && !entry.getKey().equals(key)) {
	        i++;
	        index = (hash(key) + i * i) % capacity.intValue();
	        entry = table.get(index);
	    }

	    // If an entry with the same key or tomb stone already exists, update its value.
	    if (entry != null) {
	        V oldValue = entry.getValue();
	        entry.setValue(value);
	        return oldValue;
	    } 
	    // Otherwise, add a new entry to the table.
	    else {
	        // Create a new entry with the given key and value.
	        entry = new MapEntry<K, V>(key, value);
	        
	        // Add the entry to the table.
	        table.set(index, entry);

	        // Increment the size of the table.
	        size++;

	        // Check if the load factor exceeds 0.5 and rehash the table if it does.
	        if(size > capacity.intValue() * 0.5) 
	        	rehash();

	        // Return null to indicate that no value was previously associated with the key.
	        return null;
	    }
	}
	/**
	 * Method to rehash and double size if load factor exceeds 0.5
	 */
	private void rehash() {
		//copy the old table
		ArrayList<MapEntry<K, V>> oldTable = table;
		//find the next prime number after doubling
		capacity = capacity.multiply(new BigInteger("2")).nextProbablePrime();
		//increase the size of the table and fill it
	    table = new ArrayList<MapEntry<K, V>>(capacity.intValue());
	    for (int i = 0; i < capacity.intValue(); i++) {
	        table.add(null);
	    }
	    size = 0;
	    //add the entries from the old table to the new one
	    for (MapEntry<K, V> entry : oldTable) {
	    	if(entry != null)
	    		put(entry.getKey(), entry.getValue());
	    }
	}
	@Override
	public void clear()
	{
		table.clear();
		size = 0;
	}

	@Override
	public boolean containsKey(K key)
	{
		int index = hash(key);
        MapEntry<K, V> entry = table.get(index);
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return true;
            }
            entry = getNextEntry(index, key);
        }
        return false;
	}

	@Override
	public boolean containsValue(V value)
	{
		for (int i = 0; i < table.size(); i++) {
            MapEntry<K, V> entry = table.get(i);
            if (entry != null && entry.getValue().equals(value)) {
            	return true;
            }
        }
        return false;
	}

	@Override
	public List<MapEntry<K, V>> entries()
	{
		List<MapEntry<K, V>> result = new ArrayList<>();
        for (int i = 0; i < table.size(); i++) {
            MapEntry<K, V> entry = table.get(i);
            if(entry != null) {
            	result.add(entry);
            }
        }
        return result;
	}

	@Override
	public V get(K key)
	{
		if(size == 0)
			return null;
		int index = hash(key);
        MapEntry<K, V> entry = table.get(index);
        while (entry != null) {
            if (entry.getKey() != null && entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = getNextEntry(index, key);
        }
        return null;
	}
	/**
	 * method to find the key or the next null value
	 * @param index		starting index
	 * @param key	key to check for
	 * @return	the entry
	 */
	private MapEntry<K, V> getNextEntry(int index, K key) {
        index = index % table.size();
		int newIndex = index;
        for(int i = 1; i < table.size(); i++)
        {
        	newIndex = (index + i*i) % table.size();
        	if(table.get(newIndex) == null || (table.get(newIndex).getKey() != null && table.get(newIndex).getKey().equals(key)))
        		break;
        	
        }
        return table.get(newIndex);
    }
	
	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	
	
	private int hash(K key)
	{
		return key.hashCode() % table.size();
	}

	@Override
	public int size()
	{
		return size;
	}
	

}