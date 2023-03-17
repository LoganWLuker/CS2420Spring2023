package exammakeup;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class MedianTest
{
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
	void medianTest()
	{
//		int[] arr = new int[]{684,891,5,98,561,81261,6842,651};
//		System.out.println(Median.median(arr));	//0,1,2,3,4,5
//		for(int i : arr)
//			System.out.print(i + ", ");
//		System.out.println("\n");
//		ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(684,891,5,98,561,81261,6842,651));
//		array.sort(null);
//		System.out.println(array);
//		System.out.println(array.get(array.size()/2));
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
		//prints the results
//		for(int i : arr)
//			System.out.print(i + ", ");
//		
//		System.out.println("\n");
//		System.out.println(arrList);
		assertEquals(expected,result);
		assertTrue(isSortedToMedian(arr));
	}
	
}
//5,98,561,651,684,891,6842,81261