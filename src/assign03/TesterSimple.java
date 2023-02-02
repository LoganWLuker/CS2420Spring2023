package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import class04.Name;

class TesterSimple 
{
	
	private SimplePriorityQueue<Integer> iQueue;
	private SimplePriorityQueue<Point> pQueue;
	private boolean runtimeFlagMedium, runtimeFlagLarge;

	@BeforeEach
	void setup() 
	{
		iQueue = new SimplePriorityQueue<Integer>();
		pQueue = new SimplePriorityQueue<Point>();
		
		// Change this depending on if you want to run the longer tests, or more 'expensive' tests.
		// TRUE runs the expensive tests.
		runtimeFlagMedium = false;
		runtimeFlagLarge = false;
	}
	//Throw Tests ------------------------------------------
	@Test
	void testFindMaxEmptyInteger()
	{
		var testQueue = new SimplePriorityQueue<Integer>();
		assertThrows(NoSuchElementException.class, () -> testQueue.findMax());
	}
	@Test
	void testFindMaxEmptyString()
	{
		var testQueue = new SimplePriorityQueue<String>();
		assertThrows(NoSuchElementException.class, () -> testQueue.findMax());
	}
	@Test
	void testDeleteMaxEmptyInteger()
	{
		var testQueue = new SimplePriorityQueue<Integer>();
		assertThrows(NoSuchElementException.class, () -> testQueue.deleteMax());
	}
	@Test
	void testDeleteMaxEmptyString()
	{
		var testQueue = new SimplePriorityQueue<String>();
		assertThrows(NoSuchElementException.class, () -> testQueue.deleteMax());
	}
	//Throw Tests with Comparator ---------------------------
	@Test
	void testFindMaxEmptyIntegerComparator()
	{
		var testQueue = new SimplePriorityQueue<Integer>((o1,o2) -> o2.compareTo(o1));
		assertThrows(NoSuchElementException.class, () -> testQueue.findMax());
	}
	@Test
	void testFindMaxEmptyStringComparator()
	{
		var testQueue = new SimplePriorityQueue<String>((o1,o2) -> o2.compareTo(o1));
		assertThrows(NoSuchElementException.class, () -> testQueue.findMax());
	}
	@Test
	void testDeleteMaxEmptyIntegerComparator()
	{
		var testQueue = new SimplePriorityQueue<Integer>((o1,o2) -> o2.compareTo(o1));
		assertThrows(NoSuchElementException.class, () -> testQueue.deleteMax());
	}
	@Test
	void testDeleteMaxEmptyStringComparator()
	{
		var testQueue = new SimplePriorityQueue<String>((o1,o2) -> o2.compareTo(o1));
		assertThrows(NoSuchElementException.class, () -> testQueue.deleteMax());
	}
	
	//default constructor Tests -------------------------------------------------------------------
	//insert Tests ---------------------------------------------
	@Test
	void testInsertInteger()
	{
		var testQueue = new SimplePriorityQueue<Integer>();
		testQueue.insert(4);
		testQueue.insert(2);
		testQueue.insert(8);
		testQueue.insert(6);
		assertEquals(0,testQueue.binSearch(2));
		assertEquals(1,testQueue.binSearch(4));
		assertEquals(2,testQueue.binSearch(6));
		assertEquals(3,testQueue.binSearch(8));
	}
	@Test
	void testInsertString()
	{
		var testQueue = new SimplePriorityQueue<String>();
		testQueue.insert("ben");
		testQueue.insert("ava");
		testQueue.insert("dan");
		testQueue.insert("connor");
		assertEquals(0,testQueue.binSearch("ava"));
		assertEquals(1,testQueue.binSearch("ben"));
		assertEquals(2,testQueue.binSearch("connor"));
		assertEquals(3,testQueue.binSearch("dan"));
	}
	@Test
	void testInsertIntegerFull()
	{
		var testQueue = new SimplePriorityQueue<Integer>();
		testQueue.insert(0);
		testQueue.insert(1);
		testQueue.insert(2);
		testQueue.insert(3);
		testQueue.insert(11);
		testQueue.insert(12);
		testQueue.insert(13);
		testQueue.insert(14);
		testQueue.insert(4);
		testQueue.insert(5);
		testQueue.insert(6);
		testQueue.insert(7);
		testQueue.insert(8);
		testQueue.insert(9);
		testQueue.insert(10);
		testQueue.insert(15);
		testQueue.insert(16);
		testQueue.insert(17);
		testQueue.insert(18);
		assertEquals(11,testQueue.binSearch(11));
		assertEquals(12,testQueue.binSearch(12));
		assertEquals(13,testQueue.binSearch(13));
		assertEquals(14,testQueue.binSearch(14));
	}
	@Test
	void testInsertStringFull()
	{
		var testQueue = new SimplePriorityQueue<String>();
		testQueue.insert("d");
		testQueue.insert("e");
		testQueue.insert("f");
		testQueue.insert("g");
		testQueue.insert("h");
		testQueue.insert("i");
		testQueue.insert("j");
		testQueue.insert("k");
		testQueue.insert("l");
		testQueue.insert("a");
		testQueue.insert("b");
		testQueue.insert("c");
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
		assertEquals(testQueue.getElement(2), 3);
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
		
		//check to make sure it's referring to the right array
		containsTestQueue.clear();
		assertEquals(false, containsTestQueue.contains(2));
		assertEquals(false, containsTestQueue.contains(1));
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
		
		//check to make sure it's referring to the right array
		containsTestQueue.clear();
		assertEquals(false,containsTestQueue.contains("a"));
		assertEquals(false,containsTestQueue.contains("b"));
	}
	//findMax Tests ---------------------------------------
	@Test
	void testFindMaxInteger() 
	{
		var testQueue = new SimplePriorityQueue<Integer>();
		testQueue.insert(2);
		testQueue.insert(3);
		testQueue.insert(1);
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
		
		// here we test duplication as well
		testQueue.insert(4);
		testQueue.insert(1);
		testQueue.insert(2);
		testQueue.insert(3);
		testQueue.insert(4);
		assertEquals(4,testQueue.deleteMax());
		assertEquals(4,testQueue.size());
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
		assertEquals(0,testQueue.getElement(0));
		assertEquals(3,testQueue.binSearch(3));
		assertEquals(4,testQueue.getElement(4));
	}
	@Test
	void testInsertAllString() 
	{
		var testQueue = new SimplePriorityQueue<String>();
		testQueue.insertAll(new ArrayList<String>(Arrays.asList("b","e","d","c","a")));
		assertEquals(0,testQueue.binSearch("a"));
		assertEquals(3,testQueue.binSearch("d"));
		assertEquals(testQueue.getElement(1), "b");
	}
	@Test
	void testInsertAllIntegerFull() 
	{
		var testQueue = new SimplePriorityQueue<Integer>();
		testQueue.insertAll(new ArrayList<Integer>(Arrays.asList(18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0)));
		assertEquals(0,testQueue.binSearch(0));
		assertEquals(3,testQueue.binSearch(3));
		assertEquals(15,testQueue.getElement(15));
	}
	@Test
	void testInsertAllStringFull() 
	{
		var testQueue = new SimplePriorityQueue<String>();
		testQueue.insertAll(new ArrayList<String>(Arrays.asList("s","r","q","p","o","n","m","l","k","j","i","h","g","f","e","d","c","b","a")));
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
	
	//Comparator Constructor Tests ----------------------------------------------------------------------------
	//insert Tests ----------------------------------------
	@Test
	void testInsertIntegerComparator() 
	{
		var testQueue = new SimplePriorityQueue<Integer>((o1, o2) -> o2.compareTo(o1));
		testQueue.insert(4);
		testQueue.insert(2);
		testQueue.insert(8);
		testQueue.insert(6);
		assertEquals(3,testQueue.binSearch(2));
		assertEquals(2,testQueue.binSearch(4));
		assertEquals(1,testQueue.binSearch(6));
		assertEquals(0,testQueue.binSearch(8));
	}
	@Test
	void testInsertStringComparator()
	{
		var testQueue = new SimplePriorityQueue<String>((o1, o2) -> o2.compareTo(o1));
		testQueue.insert("ben");
		testQueue.insert("ava");
		testQueue.insert("dan");
		testQueue.insert("connor");
		assertEquals(3,testQueue.binSearch("ava"));
		assertEquals(2,testQueue.binSearch("ben"));
		assertEquals(1,testQueue.binSearch("connor"));
		assertEquals(0,testQueue.binSearch("dan"));
	}
	@Test
	void testInsertIntegerFullComparator()
	{
		var testQueue = new SimplePriorityQueue<Integer>((o1, o2) -> o2.compareTo(o1));
		testQueue.insertAll(new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18)));
		assertEquals(3,testQueue.binSearch(15));
		assertEquals(2,testQueue.binSearch(16));
		assertEquals(1,testQueue.binSearch(17));
		assertEquals(0,testQueue.binSearch(18));
	}
	@Test
	void testInsertStringFullComparator()
	{
		var testQueue = new SimplePriorityQueue<String>((o1, o2) -> o2.compareTo(o1));
		testQueue.insertAll(new ArrayList<String>(Arrays.asList("r","q","p","o","n","m","l","k","j","i","h","g","f","e","d","c","b","a")));
		assertEquals(3,testQueue.binSearch("o"));
		assertEquals(2,testQueue.binSearch("p"));
		assertEquals(1,testQueue.binSearch("q"));
		assertEquals(0,testQueue.binSearch("r"));
	}
	//size Tests -------------------------------
	@Test
	void testSizeIntegerComparator()
	{
		var sizeTestQueue = new SimplePriorityQueue<Integer>((o1, o2) -> o2.compareTo(o1));
		sizeTestQueue.insert(1);
		sizeTestQueue.insert(2);
		sizeTestQueue.insert(3);
		assertEquals(3,sizeTestQueue.size());
	}
	@Test
	void testSizeStringComparator()
	{
		var sizeTestQueue = new SimplePriorityQueue<String>((o1, o2) -> o2.compareTo(o1));
		sizeTestQueue.insert("a");
		sizeTestQueue.insert("b");
		sizeTestQueue.insert("c");
		assertEquals(3,sizeTestQueue.size());
	}
	//clear Tests ----------------------------------------
	@Test
	void testClearIntegerComparator()
	{
		var testQueue = new SimplePriorityQueue<Integer>((o1, o2) -> o2.compareTo(o1));
		testQueue.insert(1);
		testQueue.insert(2);
		testQueue.insert(3);
		testQueue.insert(4);
		testQueue.clear();
		assertEquals(testQueue.size(),0);
	}
	@Test
	void testClearStringComparator()
	{
		var testQueue = new SimplePriorityQueue<String>((o1, o2) -> o2.compareTo(o1));
		testQueue.insert("a");
		testQueue.insert("b");
		testQueue.insert("c");
		testQueue.insert("d");
		testQueue.clear();
		assertEquals(testQueue.size(),0);
	}
	//contains Tests ---------------------------------------
	@Test
	void testContainsIntegerComparator()
	{
		var containsTestQueue = new SimplePriorityQueue<Integer>((o1, o2) -> o2.compareTo(o1));
		containsTestQueue.insert(1);
		containsTestQueue.insert(2);
		containsTestQueue.insert(3);
		assertEquals(true,containsTestQueue.contains(2));
		assertEquals(true,containsTestQueue.contains(1));
		assertEquals(false,containsTestQueue.contains(4));
		assertEquals(false,containsTestQueue.contains(0));
	}
	@Test
	void testContainsStringComparator()
	{
		var containsTestQueue = new SimplePriorityQueue<String>((o1, o2) -> o2.compareTo(o1));
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
	void testFindMaxIntegerComparator() 
	{
		var testQueue = new SimplePriorityQueue<Integer>((o1, o2) -> o2.compareTo(o1));
		testQueue.insert(1);
		testQueue.insert(2);
		testQueue.insert(3);
		testQueue.insert(4);
		assertEquals(1,testQueue.findMax());
	}
	@Test
	void testFindMaxStringComparator() 
	{
		var testQueue = new SimplePriorityQueue<String>((o1, o2) -> o2.compareTo(o1));
		testQueue.insert("b");
		testQueue.insert("a");
		testQueue.insert("d");
		testQueue.insert("c");
		assertEquals("a",testQueue.findMax());
	}
	//deleteMax Tests -----------------------------------
	@Test
	void testDeleteMaxIntegerComparator() 
	{
		var testQueue = new SimplePriorityQueue<Integer>((o1, o2) -> o2.compareTo(o1));
		testQueue.insert(1);
		testQueue.insert(2);
		testQueue.insert(3);
		testQueue.insert(4);
		assertEquals(1,testQueue.deleteMax());
		assertEquals(3,testQueue.size());
	}
	@Test
	void testDeleteMaxStringComparator() 
	{
		var testQueue = new SimplePriorityQueue<String>((o1, o2) -> o2.compareTo(o1));
		testQueue.insert("b");
		testQueue.insert("a");
		testQueue.insert("c");
		testQueue.insert("d");
		assertEquals("a",testQueue.deleteMax());
		assertEquals(3,testQueue.size());
	}
	//insertAll Tests -------------------------------------
	@Test
	void testInsertAllIntegerComparator() 
	{
		var testQueue = new SimplePriorityQueue<Integer>((o1, o2) -> o2.compareTo(o1));
		testQueue.insertAll(new ArrayList<Integer>(Arrays.asList(5, 4, 3, 2, 1, 0)));
		assertEquals(0,testQueue.binSearch(5));
		assertEquals(3,testQueue.binSearch(2));
	}
	@Test
	void testInsertAllStringComparator() 
	{
		var testQueue = new SimplePriorityQueue<String>((o1, o2) -> o2.compareTo(o1));
		testQueue.insertAll(new ArrayList<String>(Arrays.asList("b","e","d","c","a")));
		assertEquals(0,testQueue.binSearch("e"));
		assertEquals(3,testQueue.binSearch("b"));
	}
	@Test
	void testInsertAllIntegerFullComparator() 
	{
		var testQueue = new SimplePriorityQueue<Integer>((o1, o2) -> o2.compareTo(o1));
		testQueue.insertAll(new ArrayList<Integer>(Arrays.asList(18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0)));
		assertEquals(18,testQueue.binSearch(0));
		assertEquals(15,testQueue.binSearch(3));
	}
	@Test
	void testInsertAllStringFullComparator() 
	{
		var testQueue = new SimplePriorityQueue<String>((o1, o2) -> o2.compareTo(o1));
		testQueue.insertAll(new ArrayList<String>(Arrays.asList("s","r","q","p","o","n","m","l","k","j","i","h","g","f","e","d","c","b","a")));
		assertEquals(18,testQueue.binSearch("a"));
		assertEquals(15,testQueue.binSearch("d"));
	}
	//isEmpty Tests ---------------------------------------
	@Test
	void testIsEmptyIntegerComparator() 
	{
		var testQueue = new SimplePriorityQueue<Integer>((o1, o2) -> o2.compareTo(o1));
		assertEquals(true,testQueue.isEmpty());
		testQueue.insert(1);
		assertEquals(false,testQueue.isEmpty());
	}
	@Test
	void testIsEmptyStringComparator() 
	{
		var testQueue = new SimplePriorityQueue<String>((o1, o2) -> o2.compareTo(o1));
		assertEquals(true,testQueue.isEmpty());
		testQueue.insert("a");
		assertEquals(false,testQueue.isEmpty());
	}
	
	//Miscellaneous Comprehensive Tests ----------------------------------------
	
	@Test
	void testMediumArray()
	{
		if(runtimeFlagMedium) {
			
		var testQueue = new SimplePriorityQueue<Integer>();
		//create an array from 'jumbled up' input
		for(int i = 250; i > 0; i--) {
			for(int j = 0; j < 3; j++) {
				testQueue.insert(i*j-1);
			}
		}
		assertEquals(testQueue.getElement(0), -1);
		assertEquals(testQueue.getElement(testQueue.getCurrentSize()-1), 499);
		assertEquals(testQueue.getElement(testQueue.getCurrentSize()-2), 497);
		assertEquals(testQueue.getElement(testQueue.getCurrentSize()-3), 495);
		assertEquals(testQueue.getElement(testQueue.getCurrentSize()-4), 493);
		}
	}
	
	@Test
	void testLargeArray()
	{
		if(runtimeFlagLarge) {
			
			var testQueue = new SimplePriorityQueue<Integer>();
			
			//create an array from 'jumbled up' input
			
			for(int i = 1000; i > 0; i--) {
				for(int j = 1; j < 3; j++) {
					for (int k = 4; k < 6; k++) {
						testQueue.insert(i + j + k);
					}
				}
			}
			
			assertEquals(testQueue.getElement(0), 6);
			assertEquals(testQueue.getElement(3999), 1007);
			assertEquals(testQueue.getElement(3998), 1006);
			assertEquals(testQueue.getElement(3997), 1006);
			
			testQueue.printContents();
			System.out.println("Array is of size " + testQueue.getCurrentSize());
		}
	}
	
	
	
}
