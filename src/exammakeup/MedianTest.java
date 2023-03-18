package exammakeup;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;
/**
 * Tests for the Median method
 * @author Logan Luker
 *
 */
public class MedianTest
{
	/**
	 * Checks that everything before the median is less than it,
	 * and everything after the median is greater than it for a given array
	 * @param arr	the array to check
	 * @return	true if array is arranged correctly, false if it is not
	 */
	boolean isSortedToMedian(int[] arr)
	{
		for(int i=0; i<(arr.length/2);i++)
		{
			if(arr[i] > arr[arr.length/2])
				return false;
		}
		for(int i=arr.length/2; i<arr.length;i++)
		{
			if(arr[i] < arr[arr.length/2])
				return false;
		}
		return true;
	}
	@Test
	void singleMedianTest()
	{
		int[] arr = new int[] {5};
		assertEquals(5,Median.median(arr));
		assertTrue(isSortedToMedian(arr));
	}
	@Test
	void firstMedianTest()
	{
		int[] arr = new int[] {3,0,5,2,4,1};//0,1,2,3,4,5
		assertEquals(3,Median.median(arr));
		assertTrue(isSortedToMedian(arr));
	}
	@Test
	void lastMedianTest()
	{
		int[] arr = new int[] {1,0,5,2,4,3};//0,1,2,3,4,5
		assertEquals(3,Median.median(arr));
		assertTrue(isSortedToMedian(arr));
	}
	
	@Test
	void middleMedianTest()
	{
		int[] arr = new int[] {1,0,5,3,4,2};//0,1,2,3,4,5
		assertEquals(3,Median.median(arr));
		assertTrue(isSortedToMedian(arr));
	}
	@Test
	void repeatingMedianTest()
	{
		int[] arr = new int[] {1,3,3,1,4,2};//1,1,2,3,3,4
		assertEquals(3,Median.median(arr));
		assertTrue(isSortedToMedian(arr));
	}
	@Test
	void allTheSameMedianTest()
	{
		int[] arr = new int[] {6,6,6,6,6,6,6};
		assertEquals(6,Median.median(arr));
		assertTrue(isSortedToMedian(arr));
	}
	@Test
	void alternatingMedianTest()
	{
		int[] arr = new int[] {6,7,6,7,6,7,6};//6,6,6,6,7,7,7 - 6
		assertEquals(6,Median.median(arr));
		assertTrue(isSortedToMedian(arr));
	}
	@Test
	void verySmallMedianTest()
	{
		int[] arr = new int[] {2,1};
		assertEquals(2,Median.median(arr));
		assertTrue(isSortedToMedian(arr));
	}
	@Test
	void smallMedianTest()
	{
		int[] arr = new int[] {2,1,3};
		assertEquals(2,Median.median(arr));
		assertTrue(isSortedToMedian(arr));
	}
	@Test
	void bigArrayMedianTest()
	{
		Random rng = new Random();
		int[] arr = new int[1000];
		var arrList = new ArrayList<Integer>();
		for(int i = 0; i < 1000; i++)
		{
			int element = rng.nextInt();
			arr[i] = element;
			arrList.add(element);
		}
		arrList.sort(null);
		int expected = arrList.get(arrList.size()/2);
		int result = Median.median(arr);
		assertEquals(expected,result);
		assertTrue(isSortedToMedian(arr));
	}
	@Test
	void veryBigArrayMedianTest()
	{
		Random rng = new Random();
		int[] arr = new int[100000];
		var arrList = new ArrayList<Integer>();
		for(int i = 0; i < 100000; i++)
		{
			int element = rng.nextInt();
			arr[i] = element;
			arrList.add(element);
		}
		arrList.sort(null);
		int expected = arrList.get(arrList.size()/2);
		int result = Median.median(arr);
		assertEquals(expected,result);
		assertTrue(isSortedToMedian(arr));
	}
	
}