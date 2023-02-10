package assign04;

import java.util.ArrayList;
import java.util.Random;

public class FindKthLargestTimer
{

	public static void main(String[] args) 
	{
		Random randomNumberGenerator = new Random();
		
		// Do 100000 lookups and use the average running time
		int timesToLoop = 50;

		for(int n = 1; n <= 2000001; n += 100000)
		{
			var arrays = new ArrayList<Integer[]>();
			var array = new Integer[n/10000];
			int k = randomNumberGenerator.nextInt(n);
			// Add the numbers to it
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < array.length; j++)
					array[j] = randomNumberGenerator.nextInt(0,100);
				arrays.add(array);
			}

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();

			for(int i = 0; i < timesToLoop; i++)
				LargestNumberSolver.findKthLargest(arrays, k);

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

			System.out.println(n-1 + "\t" + averageTime);

		}

	}
}