package assign08;

import java.util.ArrayList;
import java.util.Collections;

public class ContainsTimer
{

	public static void main(String[] args)
	{
		// Do 100000 lookups and use the average running time
		int timesToLoop = 10000;
		for(int n = 1000; n <= 20000; n += 1000)
		{
//			if(n == 1000)
//				timesToLoop = 10000;
//			else
//				timesToLoop = 10000;
			//setup
			//var sortedTree = new BinarySearchTree<Integer>();
			var randomTree = new BinarySearchTree<Integer>();
			var randList = new ArrayList<Integer>();
			for(int i = 0; i < n; i++)
			{
				//sortedTree.add(i);
				randList.add(i);
			}
			Collections.shuffle(randList);
			randomTree.addAll(randList);
			
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
				for(int j = 0; j < n; j++)
				{
					//sortedTree.contains(j);
					randomTree.contains(j);					
				}
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int i = 0; i < timesToLoop; i++)
			{
				for(int j = 0; j < n; j++)
				{
					
				}
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = (((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) timesToLoop)/(double) n;

			System.out.println(averageTime);
			//System.out.println(n + "\t" + averageTime);
		}

	}

}
