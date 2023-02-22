package assign05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ArrayListSorter<T>
{
	private static int threshold = 2;
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
	public static <T extends Comparable<? super T>> void mergesortRec(ArrayList<T> arr, int beg, int end, ArrayList<T> temp)
	{
		//check if we should run insertion Sort
		if(end - beg < threshold)
		{
			insertionSort(arr);
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
	public static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr)
	{
		for(int i = 0; i < arr.size(); i++)
		{
			for(int j = i; j > 0 && (arr.get(j).compareTo(arr.get(j-1)) < 0); j--)
			{
				var temp = arr.get(j);
				arr.set(j, arr.get(j-1));
				arr.set(j-1, temp);
			}
		}
	}
	
	public static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, int beg, int mid, int end, ArrayList<T> temp)
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
	
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr)
	{
		
	}
	
	public static ArrayList<Integer> generateAscending(int size)
	{
		return null;
	}
	
	public static ArrayList<Integer> generatePermuted(int size)
	{
		return null;
	}
	
	public static ArrayList<Integer> generateDescending(int size)
	{
		return null;
	}
}
