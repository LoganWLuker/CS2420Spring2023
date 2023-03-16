package assign06;

import java.util.ArrayList;
import java.util.Random;

import assign05.ArrayListSorter;

public class StackTimer
{

	public static void main(String[] args) 
	{
		//Random randomNumberGenerator = new Random();
		
		// Do 100000 lookups and use the average running time
		int timesToLoop = 100000;
		
		for(int n = 1; n <= 30; n += 1)
		{
			var stack = new ArrayStack<Integer>();
			//var stack = new ArrayStack<Integer>();
			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();
			for(int j = 0; j < timesToLoop; j++)
			{
				stack = new ArrayStack<Integer>();
				//stack = new ArrayStack<Integer>();
				for(int i = 0; i < n; i++)
				{
					stack.push(10);
				}
				
				for(int i = 0; i < n; i++)
				{
					stack.pop();
					//stack.peek();
				}
				
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int j = 0; j < timesToLoop; j++)
			{
				stack = new ArrayStack<Integer>();
				//stack = new ArrayStack<Integer>();
				for(int i = 0; i < n; i++)
				{
					stack.push(10);
				}
				for(int i = 0; i < n; i++)
				{
					
				}
			}
			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = (((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) n) / (double) timesToLoop;

			System.out.println(n + "\t" + averageTime);

		}

	}
}