package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TesterSimple 
{
	
	private SimplePriorityQueue<Integer> iQueue;
	private SimplePriorityQueue<Point> pQueue;

	@BeforeEach
	void setup() 
	{
		iQueue = new SimplePriorityQueue<Integer>();
		pQueue = new SimplePriorityQueue<Point>();
	}
	//default constructor Tests ----------------------------------------------------------
	//insert Tests ---------------------------------------------
	@Test
	void testInsertInteger()
	{
		var insertTestQueue = new SimplePriorityQueue<Integer>();
		insertTestQueue.insert(4);
		insertTestQueue.insert(2);
		insertTestQueue.insert(8);
		insertTestQueue.insert(6);
		assertEquals(0,insertTestQueue.binSearch(2));
		assertEquals(1,insertTestQueue.binSearch(4));
		assertEquals(2,insertTestQueue.binSearch(6));
		assertEquals(3,insertTestQueue.binSearch(8));
	}
	@Test
	void testInsertString()
	{
		var insertTestQueue = new SimplePriorityQueue<String>();
		insertTestQueue.insert("ben");
		insertTestQueue.insert("ava");
		insertTestQueue.insert("dan");
		insertTestQueue.insert("connor");
		assertEquals(0,insertTestQueue.binSearch("ava"));
		assertEquals(1,insertTestQueue.binSearch("ben"));
		assertEquals(2,insertTestQueue.binSearch("connor"));
		assertEquals(3,insertTestQueue.binSearch("dan"));
	}
	@Test
	void testInsertIntegerFull()
	{
		var testQueue = new SimplePriorityQueue<Integer>();
		testQueue.insert(0);
		testQueue.insert(1);
		testQueue.insert(2);
		testQueue.insert(3);
		testQueue.insert(4);
		testQueue.insert(5);
		testQueue.insert(6);
		testQueue.insert(7);
		testQueue.insert(8);
		testQueue.insert(9);
		testQueue.insert(10);
		testQueue.insert(11);
		testQueue.insert(12);
		testQueue.insert(13);
		testQueue.insert(14);
		testQueue.insert(15);
		testQueue.insert(16);
		testQueue.insert(17);
		testQueue.insert(18);
		assertEquals(15,testQueue.binSearch(15));
		assertEquals(16,testQueue.binSearch(16));
		assertEquals(17,testQueue.binSearch(17));
		assertEquals(18,testQueue.binSearch(18));
	}
	@Test
	void testInsertStringFull()
	{
		var testQueue = new SimplePriorityQueue<String>();
		testQueue.insert("a");
		testQueue.insert("b");
		testQueue.insert("c");
		testQueue.insert("d");
		testQueue.insert("e");
		testQueue.insert("f");
		testQueue.insert("g");
		testQueue.insert("h");
		testQueue.insert("i");
		testQueue.insert("j");
		testQueue.insert("k");
		testQueue.insert("l");
		testQueue.insert("m");
		testQueue.insert("n");
		testQueue.insert("o");
		testQueue.insert("p");
		testQueue.insert("q");
		testQueue.insert("r");
		assertEquals(14,testQueue.binSearch("o"));
		assertEquals(15,testQueue.binSearch("p"));
		assertEquals(16,testQueue.binSearch("q"));
		assertEquals(17,testQueue.binSearch("r"));
	}
	//size Tests ---------------------------------------
	@Test
	void testSizeInteger()
	{
		var sizeTestQueue = new SimplePriorityQueue<Integer>();
		sizeTestQueue.insert(1);
		sizeTestQueue.insert(2);
		sizeTestQueue.insert(3);
		assertEquals(3,sizeTestQueue.size());
	}
	@Test
	void testSizeString()
	{
		var sizeTestQueue = new SimplePriorityQueue<String>();
		sizeTestQueue.insert("a");
		sizeTestQueue.insert("b");
		sizeTestQueue.insert("c");
		assertEquals(3,sizeTestQueue.size());
	}
	//clear Tests ----------------------------------------
	@Test
	void testClearInteger()
	{
		var testQueue = new SimplePriorityQueue<Integer>();
		testQueue.insert(1);
		testQueue.insert(2);
		testQueue.insert(3);
		testQueue.insert(4);
		testQueue.clear();
		assertEquals(testQueue.size(),0);
	}
	@Test
	void testClearString()
	{
		var testQueue = new SimplePriorityQueue<String>();
		testQueue.insert("a");
		testQueue.insert("b");
		testQueue.insert("c");
		testQueue.insert("d");
		testQueue.clear();
		assertEquals(testQueue.size(),0);
	}
	//contains Tests ---------------------------------------
	@Test
	void testContainsInteger()
	{
		var containsTestQueue = new SimplePriorityQueue<Integer>();
		containsTestQueue.insert(1);
		containsTestQueue.insert(2);
		containsTestQueue.insert(3);
		assertEquals(true,containsTestQueue.contains(2));
		assertEquals(true,containsTestQueue.contains(1));
		assertEquals(false,containsTestQueue.contains(4));
		assertEquals(false,containsTestQueue.contains(0));
	}
	@Test
	void testContainsString()
	{
		var containsTestQueue = new SimplePriorityQueue<String>();
		containsTestQueue.insert("a");
		containsTestQueue.insert("b");
		containsTestQueue.insert("c");
		assertEquals(true,containsTestQueue.contains("a"));
		assertEquals(true,containsTestQueue.contains("b"));
		assertEquals(false,containsTestQueue.contains("d"));
		assertEquals(false,containsTestQueue.contains(" "));
	}
	//findMax Tests ---------------------------------------
	@Test
	void testFindMaxInteger() 
	{
		var testQueue = new SimplePriorityQueue<Integer>();
		testQueue.insert(1);
		testQueue.insert(2);
		testQueue.insert(3);
		testQueue.insert(4);
		assertEquals(4,testQueue.findMax());
	}
	@Test
	void testFindMaxString() 
	{
		var testQueue = new SimplePriorityQueue<String>();
		testQueue.insert("b");
		testQueue.insert("a");
		testQueue.insert("d");
		testQueue.insert("c");
		assertEquals("d",testQueue.findMax());
	}
	//deleteMax Tests -----------------------------------
	@Test
	void testDeleteMaxInteger() 
	{
		var testQueue = new SimplePriorityQueue<Integer>();
		testQueue.insert(1);
		testQueue.insert(2);
		testQueue.insert(3);
		testQueue.insert(4);
		assertEquals(4,testQueue.deleteMax());
		assertEquals(3,testQueue.size());
	}
	@Test
	void testDeleteMaxString() 
	{
		var testQueue = new SimplePriorityQueue<String>();
		testQueue.insert("b");
		testQueue.insert("a");
		testQueue.insert("c");
		testQueue.insert("d");
		assertEquals("d",testQueue.deleteMax());
		assertEquals(3,testQueue.size());
	}
	//insertAll Tests -------------------------------------
	@Test
	void testInsertAllInteger() 
	{
		var testQueue = new SimplePriorityQueue<Integer>();
		testQueue.insertAll(new ArrayList<Integer>(Arrays.asList(5, 4, 3, 2, 1, 0)));
		assertEquals(0,testQueue.binSearch(0));
		assertEquals(3,testQueue.binSearch(3));
	}
	@Test
	void testInsertAllString() 
	{
		var testQueue = new SimplePriorityQueue<String>();
		testQueue.insertAll(new ArrayList<String>(Arrays.asList("b","e","d","c","a")));
		assertEquals(0,testQueue.binSearch("a"));
		assertEquals(3,testQueue.binSearch("d"));
	}
	@Test
	void testInsertAllIntegerFull() 
	{
		var testQueue = new SimplePriorityQueue<Integer>();
		testQueue.insertAll(new ArrayList<Integer>(Arrays.asList(18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0)));
		assertEquals(0,testQueue.binSearch(0));
		assertEquals(3,testQueue.binSearch(3));
	}
	@Test
	void testInsertAllStringFull() 
	{
		var testQueue = new SimplePriorityQueue<String>();
		testQueue.insertAll(new ArrayList<String>(Arrays.asList("r","q","p","o","n","m","l","k","j","i","h","g","f","e","d","c","b","a")));
		assertEquals(0,testQueue.binSearch("a"));
		assertEquals(3,testQueue.binSearch("d"));
	}
	//isEmpty Tests ---------------------------------------
	@Test
	void testIsEmptyInteger() 
	{
		var testQueue = new SimplePriorityQueue<Integer>();
		assertEquals(true,testQueue.isEmpty());
		testQueue.insert(1);
		assertEquals(false,testQueue.isEmpty());
	}
	@Test
	void testIsEmptyString() 
	{
		var testQueue = new SimplePriorityQueue<String>();
		assertEquals(true,testQueue.isEmpty());
		testQueue.insert("a");
		assertEquals(false,testQueue.isEmpty());
	}
	
	//Comparator Tests ---------------------------------------------------------------
	@Test
	void testInsertIntegerComparator() 
	{
		
	}
	
	
}
