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
		//Random randomNumberGenerator = new Random();
		
		// Do 100000 lookups and use the average running time
		int timesToLoop = 50;

		for(int n = 2; n <= 5000; n += 1)
		{
			
			var testList = readFile("src/assign05/integers.txt",1);

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();

			for(int i = 0; i < timesToLoop; i++)
				ArrayListSorter.mergesort(testList, n);

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int i = 0; i < timesToLoop; i++) { // empty block
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) timesToLoop;

			System.out.println(n + "\t" + averageTime);

		}

	}
}