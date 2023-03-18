package exammakeup;

import java.util.Random;

public class MedianTimer
{
	public static void main(String[] args) 
	{
		Random rng = new Random();
		// Do 100000 lookups and use the average running time
		int timesToLoop = 1000;

		for(int n = 1; n <= 1500001; n += 50000)
		{
//			if(n == 1)
//				timesToLoop = 10000000;
//			else
//				timesToLoop = 1000;
			//setup
			int[] inputArr = new int[n];
			for(int i = 0; i < n; i++)
				inputArr[i] = rng.nextInt(n);
			int[] arr = new int[n];

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
				arr = new int[n];
				for(int j = 0; j < n; j++)
					arr[j] = inputArr[j];
				//code to time
				Median.quickSort(arr);
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int i = 0; i < timesToLoop; i++) //empty block *
			{
				arr = new int[n];
				for(int j = 0; j < n; j++)
					arr[j] = inputArr[j]; 
				//*any other code ran with the test goes here
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) timesToLoop;

			System.out.println(n-1 + "\t" + averageTime);

		}

	}
}
