package assign03;

import java.util.Random;

public class QueueTimer
{

	public static void main(String[] args) 
	{
		Random randomNumberGenerator = new Random();
		
		// Do 100000 lookups and use the average running time
		int timesToLoop = 100000;

		for(int n = 1; n <= 2000001; n += 100000)
		{
			
			var randomQueue = new SimplePriorityQueue<Integer>();
			Integer[] arr = new Integer[n];
			
			// Add the numbers to it
			for(int i = 0; i < n; i++)
			{
				arr[i] = i;
				//int randomIndex = randomNumberGenerator.nextInt(100000);
				//randomQueue.insert(randomIndex);
			}
			randomQueue.instantInsert(arr);

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();

			for(int i = 0; i < timesToLoop; i++)
				randomQueue.findMax();

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

			System.out.println(averageTime);

		}

	}
}