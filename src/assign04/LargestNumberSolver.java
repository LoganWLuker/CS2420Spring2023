package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
	@SuppressWarnings("unchecked")
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp)
	{
		if(cmp == null)
		{
			try
			{
				cmp = (o1,o2) -> ((Comparable<? super T>)o1).compareTo(o2);
			}
			catch(Exception e)
			{
				throw new NoSuchElementException("This Type doesn't have a default comparison. Don't put null as the comparator");
			}
		}
		
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
	 * @return the largest number
	 */
	public static BigInteger findLargestNumber(Integer[] arr)
	{
		// Return 0 if array is empty, as instructed
		
		if(arr.length == 0) 
			return BigInteger.ZERO;
		
		// Construct a proper comparator object, then feed arr and that comparator to insertionSort
		
		// Here the Comparator is constructed from two Integers, which are converted to strings, concatenated,
		// 	   then compared.
		insertionSort(arr, (i1, i2) -> 
				(
					i2.toString().concat(i1.toString())
				.compareTo(
					i1.toString().concat(i2.toString())
						  )
				));
		
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
		// Create data structures to keep track of calculated BigIntegers and their respective Integer[] arrays
		BigInteger[] bigs = new BigInteger[list.size()];
		//List<Integer[]> tempBig = new ArrayList<Integer[]>();
		Map<BigInteger, Integer[]> bigMap = new HashMap<>();
		
		for(int i = 0; i < list.size(); i++)
		{
			BigInteger big = findLargestNumber(list.get(i));
			bigs[i] = big;
			bigMap.put(bigs[i], list.get(i));
		}
		
		
		
		insertionSort(bigs, (b1, b2) -> (b2.compareTo(b1)));
//		insertionSort(bigs, new Comparator<BigInteger>() 
//		{
//			@Override
//			public int compare(BigInteger b1, BigInteger b2)
//			{
//				
//				int result = b2.compareTo(b1);
//				if(result > 0)
//				{
//					
//				}
//				return result;
//			}
//		});
		
		BigInteger target = bigs[k];
		
		return bigMap.get(target);
	}
	
	/**
	 * This method generates list of integer arrays from an input file, such that each line corresponds to 
	 * 	   one array of integers separated by blank spaces, and returns an empty list if the file does not exist.
	 * @param filename
	 * @return
	 */
	public static List<Integer[]> readFile(String filename)
	{
		var toReturn = new ArrayList<Integer[]>();
		//File intFile = new File(filename);
		try(Scanner fileScan = new Scanner(new File(filename));)
		{
			while(fileScan.hasNextLine())
			{
				String[] subStrArr = fileScan.nextLine().split(" ");
				Integer[] subArr = new Integer[subStrArr.length];
				for(int i = 0; i < subStrArr.length; i++)
					subArr[i] = Integer.parseInt(subStrArr[i]);
				toReturn.add(subArr);
			}
		} catch (FileNotFoundException e)
		{
			System.out.println("file not found");
			return toReturn;
		}
		return toReturn;
	}
}
