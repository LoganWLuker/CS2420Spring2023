package assign05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayListSorterTester 
{
	//if testing, make merge() public instead of private
	@Test
	void mergeDevTest()
	{
		ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(4,8,11,25,2,3,17,20,30));
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < arr.size(); i++)
			temp.add(i,-1);
		
		ArrayListSorter.merge(arr, 0,4,9, temp);
		arr = temp;
		assertEquals(new ArrayList<Integer>(Arrays.asList(2,3,4,8,11,17,20,25,30)), arr);
	}
	
	
//	@Test
//	void mergesortDevTest()
//	{
////		ArrayList<Integer> arr1 = new ArrayList<Integer>();
////		arr1.add(4);
////		arr1.add(2);
////		arr1.add(5);
////		arr1.add(1);
////		arr1.add(3);
////		arr1.add(0);
//		ArrayList<Integer> arr1 = new ArrayList<Integer>(Arrays.asList(510510,5131,6848,565,15,210515,451,4,8));
//		ArrayList<Integer> out = new ArrayList<Integer>();
//		
//		for(int i = 0; i < 6; i++)
//		{
//			out.add(-1);
//		}
//		
//		
//		ArrayListSorter.mergesort(arr1);
//		
//		for(Integer e : arr1)
//		{
//			System.out.print(e + ",");
//		}
//		
//	}
	@Test
	void quicksortTest()
	{
		ArrayList<Integer> arr2 = new ArrayList<Integer>(Arrays.asList(510510,5131,6848,565,15,210515,451,4,8));
		ArrayListSorter.quicksort(arr2);
		//System.out.println("\t");
		for(Integer e : arr2)
		{
			System.out.print(e + ",");
		}
	}
}
