package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * 
 * @author Bruce Crockett and Logan Luker
 * @version February 6th, 2023
 */
public class LNSTester 
{
	
	// Development Tests ----------------------------------------

	@Test
	void largestNumberBasicTest()
	{
		Integer[] arr1 = {54, 31, 39};
		Integer[] arr2 = {1, 1, 1, 9, 1};
		Integer[] arr3 = {11, 67, 79, 7, 22, 13};
		
		assertEquals(LargestNumberSolver.findLargestNumber(arr1), new BigInteger("543931"));
		assertEquals(LargestNumberSolver.findLargestNumber(arr2), new BigInteger("91111"));
		assertEquals(LargestNumberSolver.findLargestNumber(arr3), new BigInteger("79767221311"));
	}
	
	@Test
	void sumBasicTest()
	{
		Integer[] arr1 = {88, 51};
		Integer[] arr2 = {7, 42, 97};
		
		var arrays = new ArrayList<Integer[]>();
		arrays.add(arr1);
		arrays.add(arr2);
		
		assertEquals(LargestNumberSolver.sum(arrays), new BigInteger("106593"));
		
		Integer[] arr3 = {};
		arrays.add(arr3);
		
		assertEquals(LargestNumberSolver.sum(arrays), new BigInteger("106593"));
		
		Integer[] arr4 = {1};
		arrays.add(arr4);
		
		assertEquals(LargestNumberSolver.sum(arrays), new BigInteger("106594"));
		
	}
	
	@Test
	void findKthLargestBasicTest()
	{
		Integer[] arr1 = {88, 51};
		Integer[] arr2 = {7, 42, 87};
		Integer[] arr3 = {9, 11, 11};
		
		var arrays = new ArrayList<Integer[]>();
		arrays.add(arr1);
		arrays.add(arr2);
		arrays.add(arr3);
		
		assertEquals(LargestNumberSolver.findKthLargest(arrays, 0), arr3);
		assertEquals(LargestNumberSolver.findKthLargest(arrays, 1), arr2);
		assertEquals(LargestNumberSolver.findKthLargest(arrays, 2), arr1);
	}
	// Empty Tests -----------------------------------------------
	
	@Test
	void findLargestNumberEmptyTest() 
	{
		Integer[] emptyIntArr = {};
		assertEquals(LargestNumberSolver.findLargestNumber(emptyIntArr), BigInteger.ZERO);
		assertEquals(LargestNumberSolver.findLargestNumber(emptyIntArr).intValue(), 0);
	}
	
	@Test
	void findLargestIntEmptyTest()
	{
		Integer[] emptyIntArr = {};
		assertEquals(LargestNumberSolver.findLargestInt(emptyIntArr), 0);
	}
	
	@Test
	void findLargestLongEmptyTest()
	{
		Integer[] emptyIntArr = {};
		assertEquals(LargestNumberSolver.findLargestLong(emptyIntArr), 0);
	}
	
	@Test
	void insertionSortEmptyTest()
	{
		Integer[] emptyIntArr = {};
		String[] emptyStrArr = {};
		Double[] emptyDblArr = {};
		
		LargestNumberSolver.insertionSort(emptyIntArr, (i1, i2) -> i2.compareTo(i1));
		LargestNumberSolver.insertionSort(emptyStrArr, (s1, s2) -> s2.compareTo(s1));
		LargestNumberSolver.insertionSort(emptyDblArr, (n1, n2) -> n2.compareTo(n1));
		
		assertEquals(emptyIntArr.length, 0);
		assertEquals(emptyStrArr.length, 0);
		assertEquals(emptyDblArr.length, 0);
	}
	
	// insertionSort Tests ----------------------------------------
	@Test
	void insertionSortTypeTest()
	{
		Integer[] intArr = {4,5,2,3,0,1};
		String[] strArr = {"e","f","c","d","a","b"};
		Double[] dblArr = {4.1,5.2,2.3,3.4,0.5,1.6};
		
		LargestNumberSolver.insertionSort(intArr, (i1, i2) -> i1.compareTo(i2));
		LargestNumberSolver.insertionSort(strArr, (i1, i2) -> i1.compareTo(i2));
		LargestNumberSolver.insertionSort(dblArr, (i1, i2) -> i1.compareTo(i2));
		
		assertArrayEquals(new Integer[] {0,1,2,3,4,5}, intArr);
		assertArrayEquals(new String[] {"a","b","c","d","e","f"}, strArr);
		assertArrayEquals(new Double[] {0.5,1.6,2.3,3.4,4.1,5.2}, dblArr);
	}
	@Test
	void insertionSortVerySmallTest()
	{
		Integer[] smallArr = {1,0};
		Integer[] smallestArr = {1};
		
		LargestNumberSolver.insertionSort(smallestArr, (i1, i2) -> i1.compareTo(i2));
		LargestNumberSolver.insertionSort(smallArr, (i1, i2) -> i1.compareTo(i2));
		
		assertArrayEquals(new Integer[] {1}, smallestArr);
		assertArrayEquals(new Integer[] {0,1}, smallArr);
	}
	@Test
	void insertionSortComparatorNullTest()
	{
		Integer[] arr = {4,5,2,3,0,1};
		
		LargestNumberSolver.insertionSort(arr, null);
		
		assertArrayEquals(new Integer[] {0,1,2,3,4,5}, arr);
	}
	
	// findLargestNumber Tests ------------------------------------
	@Test
	void findLargestNumberTest()
	{
		Integer[] emptyArr = {};
		Integer[] arr1 = {1, 2, 3, 4, 5};
		Integer[] arr2 = {13, 14, 15, 16};
		Integer[] arr3 = {20, 200, 2000};
		Integer[] arr4 = {0, 0, 0, 0, 0, 1};
		
		assertEquals(LargestNumberSolver.findLargestNumber(emptyArr), BigInteger.ZERO);
		assertEquals(LargestNumberSolver.findLargestNumber(arr1), new BigInteger("54321"));
		assertEquals(LargestNumberSolver.findLargestNumber(arr2), new BigInteger("16151413"));
		assertEquals(LargestNumberSolver.findLargestNumber(arr3), new BigInteger("202002000"));
		assertEquals(LargestNumberSolver.findLargestNumber(arr4), new BigInteger("100000"));	
	}
	
	// findLargestInt Tests ---------------------------------------
	@Test
	void findLargestIntTest()
	{
		Integer[] emptyArr = {};
		Integer[] arr1 = {1, 2, 3, 4, 5};
		Integer[] arr2 = {13, 14, 15, 16};
		Integer[] arr3 = {20, 200, 2000};
		Integer[] arr4 = {0, 0, 0, 0, 0, 1};
		Integer[] tooBig = {1000000000, 1000000000};
		
		assertEquals(LargestNumberSolver.findLargestInt(emptyArr), 0);
		assertEquals(LargestNumberSolver.findLargestInt(arr1), 54321);
		assertEquals(LargestNumberSolver.findLargestInt(arr2), 16151413);
		assertEquals(LargestNumberSolver.findLargestInt(arr3), 202002000);
		assertEquals(LargestNumberSolver.findLargestInt(arr4), 100000);
		assertThrows(OutOfRangeException.class, () -> LargestNumberSolver.findLargestInt(tooBig));
	}
	
	// findLargestLong Tests --------------------------------------
	@Test
	void findLargestLongTest()
	{
		Integer[] emptyArr = {};
		Integer[] arr1 = {1, 2, 3, 4, 5};
		Integer[] arr2 = {13, 14, 15, 16};
		Integer[] arr3 = {20, 200, 2000};
		Integer[] arr4 = {0, 0, 0, 0, 0, 1};
		Integer[] tooBig = {1000000000, 1000000000, 1000000000, 1000000000, 100000000, 100000000};
		
		assertEquals(LargestNumberSolver.findLargestLong(emptyArr), 0);
		assertEquals(LargestNumberSolver.findLargestLong(arr1), 54321);
		assertEquals(LargestNumberSolver.findLargestLong(arr2), 16151413);
		assertEquals(LargestNumberSolver.findLargestLong(arr3), 202002000);
		assertEquals(LargestNumberSolver.findLargestLong(arr4), 100000);
		assertThrows(OutOfRangeException.class, () -> LargestNumberSolver.findLargestLong(tooBig));
	}
	
	// sum Tests --------------------------------------------------
	
	
	// findKthLargest Tests ---------------------------------------
	
	
	// readFile Tests ---------------------------------------------
	@Test
	void readFileTest()
	{
		List<Integer[]> fileList = LargestNumberSolver.readFile("src/assign04/integers.txt");
		
//		//print a line from the file
//		for(int i = 0; i < fileList.get(0).length; i++)
//			System.out.print(fileList.get(0)[i] + ",");
		
		//test first value
		assertArrayEquals(new Integer[] {410,21,93,80,69,379,20,60,432,13,72,62,70,83,9,3,14,11,62,
										 55,34,83,80,99,56,25,79,51,51,70,79,20,34,67,40,51,41,94,89,
										 116,874,554,137,371,17,77,97,58,83,97,26,17,54,96,33}, fileList.get(0));
		//test middle value
		assertArrayEquals(new Integer[] {88,51}, fileList.get(7));
		//test last value
		assertArrayEquals(new Integer[] {85, 35, 34, 52, 14, 92, 9, 79, 82, 83}, fileList.get(902));
	}
	
}
