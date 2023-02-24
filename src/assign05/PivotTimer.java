package assign05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PivotTimer{
	
	public static <T extends Comparable<? super T>> ArrayList<Integer> readFile(String filename, int line)
	{
		line--;
		var toReturn = new ArrayList<Integer>();
		try(Scanner fileScan = new Scanner(new File(filename));)
		{
			while(fileScan.hasNextLine() && line > 0)
			{
				fileScan.nextLine();
				line--;
			}
				String[] subStrArr = fileScan.nextLine().split(" ");
				Integer[] subArr = new Integer[subStrArr.length];
				for(int i = 0; i < subStrArr.length; i++)
					subArr[i] = Integer.parseInt(subStrArr[i]);
				
				toReturn = new ArrayList<Integer>(Arrays.asList(subArr));
		} catch (FileNotFoundException e)
		{
			System.out.println("file not found");
			return toReturn;
		}
		return toReturn;
	}

	public static void main(String[] args) 
	{
		int timesToLoop = 2000;
		for(int k = 10; k <= 25; k++)
		{
			var testList = readFile("src/assign05/integers.txt",k);

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
		
			// Now, run the test
			startTime = System.nanoTime();

			for(int i = 0; i < timesToLoop; i++)
			{
				var testCopy = new ArrayList<Integer>(testList.size());
				for(int j = 0; j < testList.size(); j++)
					testCopy.add(testList.get(j));
				ArrayListSorter.quicksort(testCopy);
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop,
			// including the cost of copying the testList
			for(int i = 0; i < timesToLoop; i++) {
				var testCopyB = new ArrayList<Integer>(testList.size());
				for(int j = 0; j < testList.size(); j++)
					testCopyB.add(testList.get(j));
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

			//averages[k-15] = averageTime;
			System.out.println((200*k) + "\t" + averageTime);
		}
		

	}

}
