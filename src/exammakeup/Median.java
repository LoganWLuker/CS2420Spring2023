package exammakeup;

import java.util.Collections;
import java.util.Random;


/**
 * Method for computing the median of an int[], along with some other helpers
 * Author: Ben Jones and Logan Luker
 **/
public class Median {
	/**
	 * Driver method for median
	 * Calculates the end and uses 0 as the beginning
	 * @param arr	The array to find the median of
	 * @return	the result of the main median() method
	 */
	public static int median(int[] arr)
	{
		int begin = 0;
		int end = arr.length;
		return median(arr,begin,end);
	}
	/**
	 * Finds the median for a given array using the QuickSelect algorithm
	 * @param arr	the array to find the median of
	 * @param begin	the place to start from; changes within the recursion
	 * @param end	the place to end; changes within the recursion
	 * @return	the median of the array/sub-array as an integer
	 */
	private static int median(int[] arr, int begin, int end)
	{
		int pivotIndex = getPivotIndex(begin,end);
		int updatedPivotIndex = partition(arr, begin, end,pivotIndex);
		int middleIndex = arr.length/2;
		if(updatedPivotIndex == middleIndex)
		{
			return arr[middleIndex];
		}else if(updatedPivotIndex < middleIndex)
		{
			//test upper half
			return median(arr,updatedPivotIndex,end);
		}else
		{
			//test lower half
			return median(arr,begin,updatedPivotIndex);
		}
	}

    /**
     * Driver for quicksort
     * @param arr the array to sort
     */
    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length);
    }

    /**
     * Recursive quicksort method
     * @param arr
     * @param begin inclusive index
     * @param end exclusive index
     */
    private static void quickSort(int[] arr, int begin, int end){
        if((end - begin) < 2) return;
        var pivot = partition(arr, begin, end, getPivotIndex(begin, end));
        quickSort(arr, begin, pivot);
        quickSort(arr, pivot + 1, end);
    }


    /**
     * Partition the sub-array so all elements left of the pivot are less than or equal to it
     * and all elements to its right are greater than it
     * @param arr the array to partition
     * @param begin first element in the range to be partitioned
     * @param end one-past the end of the range to be partitioned (ie end is EXclusive)
     * @param pivotIndex index of element to use as the pivot
     * @return index of the pivot element after partitioning
     */
    public static int partition(int[] arr, int begin, int end, int pivotIndex){
        int left = begin;
        int right = end  -1;
        var pivot = arr[pivotIndex];

        //move pivot out of the way
        swap(arr, pivotIndex, right);
        right--;

        while(left <= right){
            while(left < end -1 && arr[left] <= pivot){ left++; }
            while(right >= begin && arr[right] > pivot){ right--;}
            if(left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        swap(arr, left, end -1);
        return left;
    }
    /**
     * swap 2 elements of an array
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j){
        var tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    /**
     * shuffle the elements of a given array
     * @param arr	array to shuffle
     */
    public static void shuffle(int[] arr){
        Random r = new Random();
        for(var i = arr.length - 1; i > 0; i--){
            var j = r.nextInt(i + 1);
            swap(arr, i, j);
        }
    }


    /// use the middle element, which will usually be fine.
    private static int getPivotIndex(int begin, int end){
        return (begin + end)/2;
    }
}
