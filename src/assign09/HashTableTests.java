package assign09;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class HashTableTests {

    private HashTable<String, Integer> hashTable;
    private HashTable<String, Integer> map;
    StudentBadHash alan = new StudentBadHash(1, "Alan", "Turing");
	StudentBadHash ada = new StudentBadHash(1, "Ada", "Lovelace");
	StudentBadHash edsger = new StudentBadHash(2, "Edsger", "Dijkstra");
	StudentBadHash grace = new StudentBadHash(3, "Grace", "Hopper");
	HashTable<StudentBadHash, Double> students = new HashTable<StudentBadHash, Double>();
    @Before
    public void setUp() {
    	students = new HashTable<StudentBadHash, Double>();
    	students.put(alan, 3.2);
    	students.put(edsger, 3.8);
    	students.put(grace, 4.0);
    	students.put(ada, 3.5);
    	map = new HashTable<String, Integer>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        hashTable = new HashTable<String, Integer>();
    }

    @Test
    public void testPutAndGet() {
        hashTable.put("a", 1);
        hashTable.put("b", 2);
        hashTable.put("c", 3);
        hashTable.put("d", 4);
        hashTable.put("e", 5);

        assertEquals(Integer.valueOf(1), hashTable.get("a"));
        assertEquals(Integer.valueOf(2), hashTable.get("b"));
        assertEquals(Integer.valueOf(3), hashTable.get("c"));
        assertEquals(Integer.valueOf(4), hashTable.get("d"));
        assertEquals(Integer.valueOf(5), hashTable.get("e"));
    }

    @Test
    public void testRemove() {
        hashTable.put("a", 1);
        hashTable.put("b", 2);
        hashTable.put("c", 3);

        assertEquals(Integer.valueOf(2), hashTable.remove("b"));
        assertNull(hashTable.get("b"));

        assertEquals(Integer.valueOf(1), hashTable.remove("a"));
        assertNull(hashTable.get("a"));

        assertEquals(Integer.valueOf(3), hashTable.remove("c"));
        assertNull(hashTable.get("c"));
    }

    @Test
    public void testContainsKey() {
        hashTable.put("a", 1);
        hashTable.put("b", 2);

        assertTrue(hashTable.containsKey("a"));
        assertTrue(hashTable.containsKey("b"));
        assertFalse(hashTable.containsKey("c"));
    }

    @Test
    public void testContainsValue() {
        hashTable.put("a", 1);
        hashTable.put("b", 2);

        assertTrue(hashTable.containsValue(1));
        assertTrue(hashTable.containsValue(2));
        assertFalse(hashTable.containsValue(3));
    }

    @Test
    public void testEntries() {
        hashTable.put("a", 1);
        hashTable.put("b", 2);
        hashTable.put("c", 3);

        List<MapEntry<String, Integer>> entries = hashTable.entries();

        assertEquals(3, entries.size());
        assertTrue(entries.contains(new MapEntry<>("a", 1)));
        assertTrue(entries.contains(new MapEntry<>("b", 2)));
        assertTrue(entries.contains(new MapEntry<>("c", 3)));
    }

    @Test
    public void testClear() {
        hashTable.put("a", 1);
        hashTable.put("b", 2);

        hashTable.clear();

        assertEquals(0, hashTable.size());
        assertNull(hashTable.get("a"));
        assertNull(hashTable.get("b"));
    }

    @Test
    public void testSize() {
        assertEquals(0, hashTable.size());

        hashTable.put("a", 1);
        hashTable.put("b", 2);

        assertEquals(2, hashTable.size());

        hashTable.remove("a");

        assertEquals(1, hashTable.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(hashTable.isEmpty());

        hashTable.put("a", 1);

        assertFalse(hashTable.isEmpty());

        hashTable.remove("a");

        assertTrue(hashTable.isEmpty());
    }
    @Test
    public void testRemoveMore() {
        // Test removal of existing key
        assertEquals((Integer)2, map.remove("B"));
        assertEquals(null, map.get("B"));
        assertEquals(3, map.size());
        
        // Test removal of non-existent key
        assertEquals(null, map.remove("E"));
        assertEquals(3, map.size());
    }
    
    @Test
    public void testLazyDeletion() {
        // Test that lazy deletion is functioning properly
    	//alan,ada,edsger,grace
    	//alan,edsger,grace,ada
        students.remove(edsger);
        students.remove(ada);
        assertEquals(2, students.size());
    }
    @Test
    public void getTest() {
    	assertEquals((Double)3.5,students.get(ada));
    }
    @Test
    public void rehashTest() {
    	hashTable.put("a", 1);
        hashTable.put("b", 2);
        hashTable.put("c", 3);
        hashTable.put("d", 4);
        hashTable.put("e", 5);
        hashTable.put("f", 5);
        hashTable.put("g", 5);
        hashTable.put("h", 5);
        hashTable.put("i", 5);
        assertEquals(9,hashTable.size());
        
    }
}