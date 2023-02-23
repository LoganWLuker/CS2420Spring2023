package assign05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class provides extensive tests for the public methods in the ArrayListSorter class.
 * @author Bruce Crockett and Logan Luker
 * @version February 22nd, 2023
 */
public class ArrayListSorterTester 
{
	// Empty tests -------------------
	
	@Test
	void emptyArrayMergeTest()
	{
		ArrayList<Integer> empty = new ArrayList<Integer>();
		assertThrows(IllegalArgumentException.class,() -> ArrayListSorter.mergesort(empty));
	}
	
	@Test
	void emptyArrayQuickTest()
	{
		ArrayList<Integer> empty = new ArrayList<Integer>();
		assertThrows(IllegalArgumentException.class,() -> ArrayListSorter.quicksort(empty));
	}
	
	@Test
	void undersizedAscTest()
	{
		assertThrows(IllegalArgumentException.class,() -> ArrayListSorter.generateAscending(0));
		assertThrows(IllegalArgumentException.class,() -> ArrayListSorter.generateAscending(-1));
	}
	
	@Test
	void undersizedPermTest()
	{
		assertThrows(IllegalArgumentException.class,() -> ArrayListSorter.generatePermuted(0));
		assertThrows(IllegalArgumentException.class,() -> ArrayListSorter.generatePermuted(-1));
	}
	
	@Test
	void undersizedDescTest()
	{
		assertThrows(IllegalArgumentException.class,() -> ArrayListSorter.generateDescending(0));
		assertThrows(IllegalArgumentException.class,() -> ArrayListSorter.generateDescending(-1));
	}
	
	// mergesort tests ---------------
	
	@Test
	void mergeOneTest()
	{
		ArrayList<Integer> zero = new ArrayList<Integer>();
		ArrayList<Integer> one = new ArrayList<Integer>();
		ArrayList<Integer> minusOne = new ArrayList<Integer>();
		zero.add(0);
		one.add(1);
		minusOne.add(-1);
		
		ArrayListSorter.mergesort(zero);
		ArrayListSorter.mergesort(one);
		ArrayListSorter.mergesort(minusOne);
		
		assertEquals(one.get(0), 1);
		assertEquals(zero.get(0), 0);
		assertEquals(minusOne.get(0), -1);
	}
	
	@Test
	void mergeTwoTest()
	{
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		ArrayList<Integer> arr3 = new ArrayList<Integer>();
		ArrayList<Integer> arr4 = new ArrayList<Integer>();
		
		arr1.add(0);
		arr1.add(1);
		
		arr2.add(1);
		arr2.add(0);
		
		arr3.add(1);
		arr3.add(-1);
		
		arr4.add(-10);
		arr4.add(-11);
		
		ArrayListSorter.mergesort(arr1);
		ArrayListSorter.mergesort(arr2);
		ArrayListSorter.mergesort(arr3);
		ArrayListSorter.mergesort(arr4);
		
		assertEquals(arr1.get(0), 0);
		assertEquals(arr1.get(1), 1);
		
		assertEquals(arr2.get(0), 0);
		assertEquals(arr2.get(1), 1);
		
		assertEquals(arr3.get(0), -1);
		assertEquals(arr3.get(1), 1);
		
		assertEquals(arr4.get(0), -11);
		assertEquals(arr4.get(1), -10);
	}
	
	@Test
	void mergeTest()
	{
		ArrayList<Integer> arr1 = new ArrayList<Integer>(Arrays.asList(50, 0, 1, 3, 2, 49, 48));
		ArrayList<Integer> arr2 = new ArrayList<Integer>(Arrays.asList(3, 2, 1, 0, -1, -2, -3));
		ArrayList<Integer> arr3 = new ArrayList<Integer>(Arrays.asList(1, 0, 0, 0, 0));
		
		ArrayListSorter.mergesort(arr1);
		ArrayListSorter.mergesort(arr2);
		ArrayListSorter.mergesort(arr3);
		
		assertEquals(new ArrayList<Integer>(Arrays.asList(0,1,2,3,48,49,50)), arr1);
		assertEquals(new ArrayList<Integer>(Arrays.asList(-3,-2,-1,0,1,2,3)), arr2);
		assertEquals(new ArrayList<Integer>(Arrays.asList(0,0,0,0,1)), arr3);
	}
	
	@Test
	void largeMergeTest()
	{
		ArrayList<Integer> arr1 = ArrayListSorter.generateDescending(500);
		ArrayList<Integer> arr2 = ArrayListSorter.generatePermuted(500);
		ArrayList<Integer> arr3 = ArrayListSorter.generateAscending(500);
		
		ArrayListSorter.mergesort(arr1);
		ArrayListSorter.mergesort(arr2);
		ArrayListSorter.mergesort(arr3);
		
		assertEquals(ArrayListSorter.generateAscending(500), arr1);
		assertEquals(ArrayListSorter.generateAscending(500), arr2);
		assertEquals(ArrayListSorter.generateAscending(500), arr3);
	}
	
	// quicksort tests ---------------
	
	@Test
	void quickOneTest()
	{
		ArrayList<Integer> zero = new ArrayList<Integer>();
		ArrayList<Integer> one = new ArrayList<Integer>();
		ArrayList<Integer> minusOne = new ArrayList<Integer>();
		zero.add(0);
		one.add(1);
		minusOne.add(-1);
		
		ArrayListSorter.quicksort(zero);
		ArrayListSorter.quicksort(one);
		ArrayListSorter.quicksort(minusOne);
		
		assertEquals(one.get(0), 1);
		assertEquals(zero.get(0), 0);
		assertEquals(minusOne.get(0), -1);
	}
	
	@Test
	void quickTwoTest()
	{
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		ArrayList<Integer> arr3 = new ArrayList<Integer>();
		ArrayList<Integer> arr4 = new ArrayList<Integer>();
		
		arr1.add(0);
		arr1.add(1);
		
		arr2.add(1);
		arr2.add(0);
		
		arr3.add(1);
		arr3.add(-1);
		
		arr4.add(-10);
		arr4.add(-11);
		
		ArrayListSorter.quicksort(arr1);
		ArrayListSorter.quicksort(arr2);
		ArrayListSorter.quicksort(arr3);
		ArrayListSorter.quicksort(arr4);
		
		assertEquals(arr1.get(0), 0);
		assertEquals(arr1.get(1), 1);
		
		assertEquals(arr2.get(0), 0);
		assertEquals(arr2.get(1), 1);
		
		assertEquals(arr3.get(0), -1);
		assertEquals(arr3.get(1), 1);
		
		assertEquals(arr4.get(0), -11);
		assertEquals(arr4.get(1), -10);
	}
	
	@Test
	void quickTest()
	{
		ArrayList<Integer> arr1 = new ArrayList<Integer>(Arrays.asList(50, 0, 1, 3, 2, 49, 48));
		ArrayList<Integer> arr2 = new ArrayList<Integer>(Arrays.asList(3, 2, 1, 0, -1, -2, -3));
		ArrayList<Integer> arr3 = new ArrayList<Integer>(Arrays.asList(1, 0, 0, 0, 0));
		
		ArrayListSorter.quicksort(arr1);
		ArrayListSorter.quicksort(arr2);
		ArrayListSorter.quicksort(arr3);
		
		assertEquals(new ArrayList<Integer>(Arrays.asList(0,1,2,3,48,49,50)), arr1);
		assertEquals(new ArrayList<Integer>(Arrays.asList(-3,-2,-1,0,1,2,3)), arr2);
		assertEquals(new ArrayList<Integer>(Arrays.asList(0,0,0,0,1)), arr3);
	}
	
	@Test
	void largeQuickTest()
	{
		ArrayList<Integer> arr1 = ArrayListSorter.generateDescending(500);
		ArrayList<Integer> arr2 = ArrayListSorter.generatePermuted(500);
		ArrayList<Integer> arr3 = ArrayListSorter.generateAscending(500);
		
		ArrayListSorter.quicksort(arr1);
		ArrayListSorter.quicksort(arr2);
		ArrayListSorter.quicksort(arr3);
		
		assertEquals(ArrayListSorter.generateAscending(500), arr1);
		assertEquals(ArrayListSorter.generateAscending(500), arr2);
		assertEquals(ArrayListSorter.generateAscending(500), arr3);
	}
	
	// ascending tests ---------------
	
	@Test
	void ascendingSmallTest()
	{
		ArrayList<Integer> arr1 = ArrayListSorter.generateAscending(1);
		ArrayList<Integer> arr2 = ArrayListSorter.generateAscending(2);
		ArrayList<Integer> arr3 = ArrayListSorter.generateAscending(4);
		
		assertEquals(new ArrayList<Integer>(Arrays.asList(1)), arr1);
		assertEquals(new ArrayList<Integer>(Arrays.asList(1,2)), arr2);
		assertEquals(new ArrayList<Integer>(Arrays.asList(1,2,3,4)), arr3);
	}
	
	@Test
	void ascendingTest()
	{
		ArrayList<Integer> arr1 = ArrayListSorter.generateAscending(10);
		ArrayList<Integer> arr2 = ArrayListSorter.generateAscending(1000);
		
		assertEquals(new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10)), arr1);
		
		for(int i = 1; i <= 1000; i++)
		{
			assertEquals(i, arr2.get(i-1));
		}
	}
	
	// permuted tests ----------------
	
	@Test
	void permOneTest()
	{
		ArrayList<Integer> one = ArrayListSorter.generatePermuted(1);
		
		assertEquals(new ArrayList<Integer>(Arrays.asList(1)), one);
	}
	
	@Test
	void permBoundTest()
	{
		ArrayList<Integer> perm = ArrayListSorter.generatePermuted(500);
		
		for(Integer i : perm)
		{
			if(i.intValue() < 1 || i.intValue() > 500)
				fail("Out of bounds");
		}
	}
	// descending tests --------------

	@Test
	void descendingSmallTest()
	{
		ArrayList<Integer> arr1 = ArrayListSorter.generateDescending(1);
		ArrayList<Integer> arr2 = ArrayListSorter.generateDescending(2);
		ArrayList<Integer> arr3 = ArrayListSorter.generateDescending(4);
		
		assertEquals(new ArrayList<Integer>(Arrays.asList(1)), arr1);
		assertEquals(new ArrayList<Integer>(Arrays.asList(2,1)), arr2);
		assertEquals(new ArrayList<Integer>(Arrays.asList(4,3,2,1)), arr3);
	}
	
	@Test
	void descendingTest()
	{
		ArrayList<Integer> arr1 = ArrayListSorter.generateDescending(10);
		ArrayList<Integer> arr2 = ArrayListSorter.generateDescending(1000);
		
		assertEquals(new ArrayList<Integer>(Arrays.asList(10,9,8,7,6,5,4,3,2,1)), arr1);
		
		for(int i = 1000; i >= 1; i--)
		{
			assertEquals(i, arr2.get(1000-i));
		}
	}
}
