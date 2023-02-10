package assign04;

import java.util.ArrayList;
import java.util.Random;

/**
 * Timer for graphing
 * @author Bruce Crockett and Logan Luker
 * @version February 9th, 2023
 */
public class FindKthLargestTimer
{

	public static void main(String[] args) 
	{
		Random randomNumberGenerator = new Random();
		
		// Do 100000 lookups and use the average running time
		int timesToLoop = 10;
		
		for(int n = 1; n <= 200001; n += 10000)
		{
			var arrays = new ArrayList<Integer[]>();
			int k = randomNumberGenerator.nextInt(n);
			// Add the numbers to it
			for(int i = 0; i < n; i++)
			{
				arrays.add(new Integer[] {randomNumberGenerator.nextInt(1000)});
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
			arrays.clear();
			
		}

	}
}