package assign05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ThresholdTimer
{
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
		
		
		// -----------------iterates over threshold in 200k-member array
		int timesToLoop = 500;
		
		// for each threshold n
		//for(int n = 2; n <= 30; n += 1)
		//{
			//double[] averages = new double[11];
			//System.out.println("Threshold: " + n);
			
			// finds average runtime for threshold n from arraylist sizes [2000, 5000] by increments of 200
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
					//int threshold = 2 + (int) ((n-1)*.001*testCopy.size());
					ArrayListSorter.mergesort(testCopy, 14); //threshold = .5%, 1%, 1.5% ... of length 2 + (int) 0.02 * testCopy.size()
				}

				midpointTime = System.nanoTime();

				// Run a loop to capture the cost of running the "timesToLoop" loop,
				// including the cost of copying the testList
				for(int i = 0; i < timesToLoop; i++) {
					var testCopyB = new ArrayList<Integer>(testList.size());
					for(int j = 0; j < testList.size(); j++)
						testCopyB.add(testList.get(j));
					//int threshold = 2 + (int) ((n-1)*.001*testCopyB.size());
				}

				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and doing the lookups
				// Average it over the number of runs
				double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

				//averages[k-15] = averageTime;
				System.out.println(averageTime);
			}
			/*
			// Compute the average running time for all arraylists given this threshold
			double sum = 0;
			for(int m = 0; m < 11; m++)
				sum += averages[m];
			double av = sum / 11;
			System.out.println(((n-1)*.1) + "%\t" + av);
			*/
		//}
		
		
		
		/* 
		// ------------iterates over arbitrary permuted array's size------------------------
		int timesToLoop = 50;

		for(int n = 1; n <= 10001; n += 100)
		{
			

			var testList = ArrayListSorter.generatePermuted(n);
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
				ArrayListSorter.mergesort(testCopy, 2); //manually change threshold \in {2, ...}
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
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) timesToLoop;

			System.out.println(n + "\t" + averageTime);

		}
		*/
	}
}