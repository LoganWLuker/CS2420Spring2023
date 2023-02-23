package assign05;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class, given a generic array, can perform a mergesort or a quicksort, and can generate sorted or permuted ArrayLists given a size.
 * @author Bruce Crockett and Logan Luker
 * @version February 22nd, 2023
 */
public class ArrayListSorter<T>
{
	private static int threshold = 3;
	/**
	 * Merge Sort driver method and setup
	 * @param <T>	type of array
	 * @param arr	array to sort
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr)
	{
		//check if empty
		if(arr.size() <= 0)
			throw new IllegalArgumentException("Array to be sorted cannot be empty");
		//temp array for storage
		ArrayList<T> temp = new ArrayList<T>(arr.size());
		for(int i = 0; i < arr.size(); i++)		//fill temp array with null
			temp.add(i,null);
		mergesortRec(arr, 0, arr.size(), temp); //call the recursive method
	}
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, int threshold)
	{
		ArrayListSorter.threshold = threshold;
		//check if empty
		if(arr.size() <= 0)
			throw new IllegalArgumentException("Array to be sorted cannot be empty");
		//temp array for storage
		ArrayList<T> temp = new ArrayList<T>(arr.size());
		for(int i = 0; i < arr.size(); i++)		//fill temp array with null
			temp.add(i,null);
		mergesortRec(arr, 0, arr.size(), temp); //call the recursive method
	}
	/**
	 * Recursive mergesort routine
	 * @param <T>	type of array
	 * @param arr	array to sort
	 * @param beg	beginning of sorting range
	 * @param end	end of sorting range
	 * @param temp	temporary array for storage
	 */
	private static <T extends Comparable<? super T>> void mergesortRec(ArrayList<T> arr, int beg, int end, ArrayList<T> temp)
	{
		//check if we should run insertion Sort
		if(end - beg < threshold)
		{
			insertionSort(arr, beg, end);
			return;
		}
		//find midpoint
		int mid = (beg + end) / 2;
		//sort first half
		mergesortRec(arr, beg, mid, temp);
		//sort second half
		mergesortRec(arr, mid, end, temp);
		//merge
		merge(arr, beg, mid, end, temp);
		
	}
	/**
	 * Insertion Sort to sort if array size is less than threshold
	 * @param <T>	Type of ArrayList
	 * @param arr	Array to be Sorted
	 */
	private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int beg, int end)
	{
		for(int i = beg; i < end; i++)
		{
			for(int j = i; j > beg && (arr.get(j).compareTo(arr.get(j-1)) < 0); j--)
			{
				var temp = arr.get(j);
				arr.set(j, arr.get(j-1));
				arr.set(j-1, temp);
			}
		}
	}
	
	/**
	 * Merge routine, merges the two subarrays given by [beg, mid) and [mid, end)
	 * @param <T>  type of array
	 * @param arr  sup array containing subarrays to be merged
	 * @param beg  first element of subarray to be merged
	 * @param mid  last element of first subarray to be merged
	 * @param end  last element of second subarray to be merged
	 * @param temp storage for operations, copied to 'arr' when routine completes
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, int beg, int mid, int end, ArrayList<T> temp)
	{
		//Set up pointers
		int i = beg, j = mid, k = beg;
		//compare and increment pointers while they are in their halves
		while (i < mid && j < end)
		{
			if (arr.get(i).compareTo(arr.get(j)) < 0)	//store the smaller value and increment It's pointer
				temp.set(k++, arr.get(i++));
			else
				temp.set(k++, arr.get(j++));
		}
		//After a pointer has moved out of range, store the rest of the other pointer's values
		while (i < mid)
			temp.set(k++, arr.get(i++));
		while (j < end)
			temp.set(k++, arr.get(j++));
		//Copy everything back to the main array within the bounds beg <= x < k
		for(int copy = beg; copy < k; copy++)
			arr.set(copy, temp.get(copy));
	}
	
	/**
	 * Quick Sort driver method and setup
	 * @param <T>    type of array
	 * @param arr    array to be sorted
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr)
	{
		//check if empty
		if(arr.size() <= 0)
			throw new IllegalArgumentException("Array to be sorted cannot be empty");
		
		quicksortRec(arr,0,arr.size()-1);
	}
	
	/**
	 * Recursive quicksort routine, driven by quicksort()
	 * @param <T>     type of array
	 * @param arr     array to be sorted
	 * @param begin   first index of subarray to be sorted
	 * @param end     last index of subarray to be sorted
	 */
	private static <T extends Comparable<? super T>> void quicksortRec(ArrayList<T> arr, int begin, int end)
	{
		if(begin < end)
		{
			int partitionIndex = pivotA(arr, begin, end);	//random pivot
			//int partitionIndex = pivotB(arr, begin, end);	//center pivot
			//int partitionIndex = pivotC(arr, begin, end);	//beginning pivot
			//int partitionIndex = partition(arr, begin, end);	//end (default) pivot
			quicksortRec(arr, begin, partitionIndex-1);
			quicksortRec(arr, partitionIndex+1, end);			
		}
	}
	
	/**
	 * Partitions the given array by the last element in the array. Assumes
	 * 	that the last element in the array is the pivot
	 * @param <T>    type of ArrayList
	 * @param arr    array to be partitioned
	 * @param begin  first element of subarray to be partitioned
	 * @param end    last element of subarray to be partitioned
	 * @return
	 */
	private static <T extends Comparable<? super T>> int partition(ArrayList<T> arr, int begin, int end) 
	{
	    T pivot = arr.get(end);
	    int i = (begin-1);

	    for (int j = begin; j <= end-1; j++) {
	        if (arr.get(j).compareTo(pivot) < 0) {
	            i++;

	            T swapTemp = arr.get(i);
	            arr.set(i,arr.get(j));
	            arr.set(j, swapTemp);
	        }
	    }
	    
	    T swapTemp = arr.get(i+1);
	    arr.set(i+1, arr.get(end));
	    arr.set(end, swapTemp);

	    return i+1;
	}
	/**
	 * Generates a pivot for partition by picking a random index
	 * @param <T>   type of ArrayList
	 * @param arr   array to be partitioned
	 * @param begin first index of subarray to be partitioned
	 * @param end   last index of subarray to be partitioned
	 * @return
	 */
	private static <T extends Comparable<? super T>> int pivotA(ArrayList<T> arr, int begin, int end)
	{
		int r = (int)(Math.random()*(end-begin))+begin;	//pivot at a random point in the range
		T swapTemp = arr.get(r);
	    arr.set(r, arr.get(end));
	    arr.set(end, swapTemp);
		return partition(arr,begin,end);
	}
	
	/**
	 * Generates a pivot for partition by picking the center index
	 * @param <T>   type of ArrayList
	 * @param arr   array to be partitioned
	 * @param begin first index of subarray to be partitioned
	 * @param end   last index of subarray to be partitioned
	 * @return
	 */
	private static <T extends Comparable<? super T>> int pivotB(ArrayList<T> arr, int begin, int end)
	{
		int r = (begin+end)/2;				//pivot the middle of the range
		T swapTemp = arr.get(r);
	    arr.set(r, arr.get(end));
	    arr.set(end, swapTemp);
		return partition(arr,begin,end);	
	}
	
	/**
	 * Generates a pivot for partition by picking the first index of the ArrayList
	 * @param <T>   type of ArrayList
	 * @param arr   array to be partitioned
	 * @param begin first index of subarray to be partitioned
	 * @param end   last index of subarray to be partitioned
	 * @return
	 */
	private static <T extends Comparable<? super T>> int pivotC(ArrayList<T> arr, int begin, int end)
	{
		int r = begin;	//pivot at the first point in the range
		T swapTemp = arr.get(r);
	    arr.set(r, arr.get(end));
	    arr.set(end, swapTemp);
		return partition(arr,begin,end);
	}
	
	/**
	 * Generates an ArrayList of integers from 1...size in ascending order
	 * @param size   size of ArrayList to be generated
	 * @return       generated ArrayList
	 */
	public static ArrayList<Integer> generateAscending(int size)
	{
		if(size<=0)
			throw new IllegalArgumentException("Array to be generated cannot be of size 0 or below");
		
		ArrayList<Integer> toReturn = new ArrayList<Integer>(size);
		for(int i = 1; i <= size; i++)
		{
			toReturn.add(i);
		}
		
		return toReturn;
	}
	
	/**
	 * Generates an ArrayList of integers from 1...size in ascending order,
	 * 	then permutes the ArrayList using java.Collections.shuffle().
	 * @param size   size of ArrayList to be generated
	 * @return       generated ArrayList
	 */
	public static ArrayList<Integer> generatePermuted(int size)
	{
		if(size<=0)
			throw new IllegalArgumentException("Array to be generated cannot be of size 0 or below");
		
		ArrayList<Integer> toReturn = new ArrayList<Integer>(size);
		for(int i = 1; i <= size; i++)
		{
			toReturn.add(i);
		}
		Collections.shuffle(toReturn);
		return toReturn;
	}
	
	/**
	 * Generates an ArrayList of integers from 1...size in descending order
	 * @param size   size of ArrayList to be generated
	 * @return       generated ArrayList
	 */
	public static ArrayList<Integer> generateDescending(int size)
	{
		if(size<=0)
			throw new IllegalArgumentException("Array to be generated cannot be of size 0 or below");
		
		ArrayList<Integer> toReturn = new ArrayList<Integer>(size);
		for(int i = size; i >= 1; i--)
		{
			toReturn.add(i);
		}
		
		return toReturn;
	}
}
