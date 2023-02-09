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
	void findKthLargestTest()
	{
		Integer[] arr1 = {88, 51};
		Integer[] arr2 = {7, 42, 97};
		
		var arrays = new ArrayList<Integer[]>();
		arrays.add(arr1);
		arrays.add(arr2);
		
		assertEquals(LargestNumberSolver.findKthLargest(arrays, 0), arr2);
		assertEquals(LargestNumberSolver.findKthLargest(arrays, 1), arr1);
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
	
	
	// findLargestNumber Tests ------------------------------------
	
	
	// findLargestInt Tests ---------------------------------------
	
	
	// findLargestLong Tests --------------------------------------
	
	
	// sum Tests --------------------------------------------------
	
}
