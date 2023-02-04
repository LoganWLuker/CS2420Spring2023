package lab04;

import java.util.Arrays;
import java.util.Comparator;

public class MedianStuff
{
	public static <U extends Comparable<U>> U median(U[] arr)
	{
		//sort without Arrays.sort()
//		for(int j = 0; j < arr.length; j++)
//		{
//			for(int i = 0; i < arr.length; i++)
//			{
//				if(arr[j].compareTo(arr[i]) > 0)
//				{
//					U temp = arr[j];
//					arr[j] = arr[i];
//					arr[i] = temp;
//				}
//			}
//		}
		Arrays.sort(arr);
		return arr[arr.length/2];
	}
	public static <U> U medianComp(U[] arr,Comparator<? super U> cmp)
	{
		Arrays.sort(arr,cmp);
		return arr[arr.length/2];
	}
	public static void main(String[] args)
	{
		System.out.println(median(new Integer[]{6, 1, 3, 5, 4, 2}));

	}

}
