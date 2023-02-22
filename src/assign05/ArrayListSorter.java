package assign05;

import java.util.ArrayList;

public class ArrayListSorter<T>
{
	private static int threshold = 2;
	public ArrayListSorter()
	{
		
	}
	
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr)
	{
		ArrayList<T> out = new ArrayList<T>(arr.size());
		if(arr.size() <= 0)
			throw new IllegalArgumentException("Array to be sorted cannot be empty");
		
		mergesortRec(arr, out);
	}
	
	private static <T extends Comparable<? super T>> ArrayList<T> copyOfRangeAL(ArrayList<T> arr, int beg, int end)
	{
		ArrayList<T> out = new ArrayList<T>(end - beg);
		for(int i = beg; i <= end; i++)
		{
			out.set(i, arr.get(i));
		}
		
		return out;
	}
	
	private static <T extends Comparable<? super T>> void mergesortRec(ArrayList<T> arr, ArrayList<T> out)
	{
		if(arr.size() < threshold)
			return; //call insertion sort
		
		ArrayList<T> arr1 = copyOfRangeAL(arr, 0, arr.size()/2);
		ArrayList<T> arr2 = copyOfRangeAL(arr, arr.size()/2, arr.size()-1);
		
		
		mergesortRec(arr1, out);
		mergesortRec(arr2, out);
		merge(arr1, arr2, arr);
		//copy() ?
	}
	
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr1, ArrayList<T> arr2, ArrayList<T> out)
	{
		int i0 = 0, i1 = 0;
		for(int i = 0; i < out.size(); i++) //fix the stop condition
		{
			if (i0 == arr1.size())
                out.set(i, arr2.get(i1));
            else if (i1 == arr2.size())
                out.set(i, arr1.get(i0));
            else if(arr1.get(i0).compareTo(arr2.get(i1)) < 0)
			{
				out.set(i, arr1.get(i0));
				i0++;
			}
			else
			{
				out.set(i, arr2.get(i1));
				i1++;
			}
		}
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
