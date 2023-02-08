package assign04;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

/**
 * This class, given an array of integers, provides tools to find the largest stringwise combination of
 * the integers in the array.  
 * @author Bruce Crockett and Logan Luker
 * @version February 6th, 2023
 */
public class LargestNumberSolver 
{
	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * @param <T>
	 * @param arr <- input array
	 * @param cmp <- comparator passed to the method
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp)
	{
		for(int i = 0; i < arr.length; i++)
		{
			for(int j = i; j > 0 && (cmp.compare(arr[j], arr[j-1]) < 0); j--)
			{
				var temp = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] = temp;
			}
		}
	}
	
	/**
	 * This method returns the largest number that can be formed by arranging the integers of the given array,
	 *     in any order.   If the array is empty, the largest number that can be formed is 0.
	 * @param arr
	 * @return
	 */
	public static BigInteger findLargestNumber(Integer[] arr)
	{
		// Return 0 if array is empty, as instructed
		
		if(arr.length == 0) 
			return BigInteger.ZERO;
		
		// Construct a proper comparator object, then feed arr and that comparator to insertionSort
		
		// Here the Comparator is constructed from two Integers, which are converted to strings, concatenated,
		// 	   then converted back to Integers and compared. TODO: It's possible that the compareTo method would work fine even if
		// 	   we didn't convert the Strings back into Integers. 
		insertionSort(arr, (i1, i2) -> (Integer.valueOf(i2.toString().concat(i1.toString())).compareTo(Integer.valueOf(i1.toString().concat(i2.toString())))));
		
		// Concatenate and return the result
		
		StringBuilder bigNumber = new StringBuilder();
		for(Integer intg : arr)
		{
			bigNumber.append(intg.toString());
		}
		
		return new BigInteger(bigNumber.toString());
	}
	
	/**
	 * This method returns the largest int value that can be formed by arranging the integers of the given array, in any order.  
	 *     An OutOfRangeExceptionis thrown if the largest number that can be formed is too large for the int data type.
	 * @param arr
	 * @return
	 * @throws OutOfRangeException
	 */
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException
	{
		int num;
		try 
		{
			num = findLargestNumber(arr).intValue();
		}
		catch(Exception e)
		{
			throw new OutOfRangeException("Largest number is too large for data type.");
		}
		
		return num;
	}
	
	/**
	 * This method behaves the same as the previous method, but for data type long instead of data type int.
	 * @param arr
	 * @return
	 * @throws OutOfRangeException
	 */
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException
	{
		long num;
		try 
		{
			num = findLargestNumber(arr).longValue();
		}
		catch(Exception e)
		{
			throw new OutOfRangeException("Largest number is too large for data type.");
		}
		
		return num;
	}
	
	/**
	 * This method sums the largest numbers that can be formed by each array in the given list.
	 * @param list
	 * @return
	 */
	public static BigInteger sum(List<Integer[]> list)
	{
		// Fill an array with each value to be summed
		
		BigInteger[] bigs = new BigInteger[list.size()];
		int count = 0;
		
		for(Integer[] arr : list)
		{
			bigs[count] = findLargestNumber(arr);
			count++;
		}
		
		// Compute the sum
		
		BigInteger sum = BigInteger.ZERO;
		
		for(BigInteger big : bigs)
		{
			sum = sum.add(big);
		}
		
		
		return sum;
	}
	
	/**
	 * This method determines the kth largest number that can be formed by each array in the given list.
	 * @param list
	 * @param k
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException
	{
		// TODO: Use insertionSort with an appropriate comparator. Read BigInteger documentation to see how to compare
		return null;
	}
	
	/**
	 * This method generates list of integer arrays from an input file, such that each line corresponds to 
	 * 	   one array of integers separated by blank spaces, and returns an empty list if the file does not exist.
	 * @param filename
	 * @return
	 */
	public static List<Integer[]> readFile(String filename)
	{
		// TODO: refer to assign02.CS2420Class.java
		return null;
	}
}
