package assign05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayListSorterTester 
{
	/* if testing, make merge() public instead of private
	@Test
	void mergeDevTest()
	{
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		ArrayList<Integer> out = new ArrayList<Integer>(6);
		arr1.add(0);
		arr1.add(2);
		arr1.add(4);
		
		arr2.add(1);
		arr2.add(3);
		arr2.add(5);
		
		for(int i = 0; i < 6; i++)
		{
			out.add(-1);
		}
		
		ArrayListSorter.merge(arr1, arr2, out);
	}
	*/
	
	@Test
	void mergesortDevTest()
	{
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		ArrayList<Integer> out = new ArrayList<Integer>();
		
		for(int i = 0; i < 6; i++)
		{
			out.add(-1);
		}
		
		arr1.add(4);
		arr1.add(2);
		arr1.add(5);
		arr1.add(1);
		arr1.add(3);
		arr1.add(0);
		
		ArrayListSorter.mergesort(arr1);
		
		for(Integer e : arr1)
		{
			System.out.println(e);
		}
		
	}
}
