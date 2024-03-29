package assign08;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;

public class SpellCheckerTest
{
	private BinarySearchTree<String> emptyTree;
	private BinarySearchTree<String> smallTree;
	private BinarySearchTree<Integer> largeTree;
	@BeforeEach
	void setUp()
	{
		emptyTree = new BinarySearchTree<String>();
		smallTree = new BinarySearchTree<String>();
		smallTree.add("Ab");
		smallTree.add("Bc");
		smallTree.add("Cd");
		largeTree = new BinarySearchTree<Integer>();
		largeTree.addAll(Arrays.asList(8,6,10,5,7,9,11));
	}
	//BinarySearchTree Tests -----------------------------------
	@Test
	void addEmptyTreeTrueTest()
	{
		assertTrue(emptyTree.add("Hello"));
	}
	@Test
	void addEmptyTreeFalseTest()
	{
		emptyTree.add("Hello");
		assertFalse(emptyTree.add("Hello"));
	}
	@Test
	void addEmptyTreeFalseNullTest()
	{
		assertFalse(emptyTree.add(null));
	}
	@Test
	void addEmptyTreeContainsTest()
	{
		emptyTree.add("Hello");
		assertTrue(emptyTree.contains("Hello"));
	}
	@Test
	void addEmptyTreeSizeTest()
	{
		emptyTree.add("Hello");
		assertEquals(1,emptyTree.size());
	}
	@Test
	void addSmallTreeTrueTest()
	{
		assertTrue(smallTree.add("lol"));
	}
	@Test
	void addAllEmptyTrueTest()
	{
		assertTrue(emptyTree.addAll(Arrays.asList("a","b","c")));
	}
	@Test
	void addAllSmallTreeTest()
	{
		assertTrue(smallTree.addAll(Arrays.asList("a","b","Bc")));
	}
	@Test
	void addAllSmallTreeFalseTest()
	{
		assertFalse(smallTree.addAll(Arrays.asList("Ab","Bc","Cd")));
	}
	@Test
	void addAllEmptyFalseTest()
	{
		assertFalse(emptyTree.addAll(Arrays.asList()));
	}
	@Test
	void containsAllSmallTreeTest()
	{
		assertTrue(smallTree.containsAll(Arrays.asList("Ab","Bc","Cd")));
	}
	@Test
	void containsAllSmallTreeFalseTest()
	{
		assertFalse(smallTree.containsAll(Arrays.asList("Ab","Bc","Cd","De")));
	}
	@Test
	void containsAllSmallTreeEmptyFalseTest()
	{
		assertFalse(smallTree.containsAll(Arrays.asList()));
	}
	@Test
	void clearSmallTreeTest()
	{
		smallTree.clear();
		assertFalse(smallTree.contains("Ab"));
		assertFalse(smallTree.contains("Bc"));
		assertFalse(smallTree.contains("Cd"));
	}
	@Test
	void removeLargeTreeTest()
	{
		largeTree.remove(8);
		assertFalse(largeTree.contains(8));
		assertTrue(largeTree.containsAll(Arrays.asList(5,6,7,9,10,11)));
	}
	@Test
	void removeAllLargeTreeTest()
	{
		largeTree.removeAll(Arrays.asList(8,7,9));
		assertFalse(largeTree.isEmpty());
		assertFalse(largeTree.contains(8));
		assertFalse(largeTree.contains(7));
		assertFalse(largeTree.contains(9));
		assertTrue(largeTree.containsAll(Arrays.asList(5,6,10,11)));
	}
	@Test
	void removeAllTest()
	{
		largeTree.removeAll(Arrays.asList(8,7,9,5,6,10,11));
		assertFalse(largeTree.contains(8));
		assertFalse(largeTree.contains(7));
		assertFalse(largeTree.contains(9));
		assertFalse(largeTree.containsAll(Arrays.asList(5,6,10,11)));
		assertEquals(0, largeTree.size());
		assertTrue(largeTree.isEmpty());
	}
	@Test
	void sizeLargeTreeTest()
	{
		assertEquals(7,largeTree.size());
	}
	@Test
	void toArrayListTest()
	{
		largeTree.toDot();
		emptyTree.add("d");
		System.out.println(largeTree.toArrayList());
		System.out.println(smallTree.toArrayList());
		System.out.println(emptyTree.toArrayList());
	}
	@Test
	void firstTest()
	{
		assertEquals(5,largeTree.first());
	}
	
}
