package assign08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class TreesTimer
{

	public static void main(String[] args)
	{
		// Do 100000 lookups and use the average running time
		int timesToLoop = 1000;
		for(int n = 1000; n <= 20000; n += 1000)
		{
			var randList = new ArrayList<Integer>();
			for(int i = 0; i < n; i++)
			{
				randList.add(i);
			}
			Collections.shuffle(randList);
			
			if(n <= 3000)
			timesToLoop = 10000;
		else
			timesToLoop = 1000;
			//setup
			var setTree = new TreeSet<Integer>();
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
				setTree = new TreeSet<Integer>();
				for(int i = 0; i < n; i++)
					setTree.add(randList.get(i));
				
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int j = 0; j < timesToLoop; j++)
			{
				setTree = new TreeSet<Integer>();
				for(int i = 0; i < n; i++)
					randList.get(i);
				
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = (((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) timesToLoop)/(double) n;

			System.out.print(n + "\t" + averageTime + "\t");
			
			setTree.addAll(randList);
			var binTree = new BinarySearchTree<Integer>();
			
//			long startTime, midpointTime, stopTime;
			
			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			// Now, run the test
			startTime = System.nanoTime();
			
			for(int j = 0; j < timesToLoop; j++)
			{
				binTree = new BinarySearchTree<Integer>();
				for(int i = 0; i < n; i++)
					binTree.add(randList.get(i));
				
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int j = 0; j < timesToLoop; j++)
			{
				binTree = new BinarySearchTree<Integer>();
				for(int i = 0; i < n; i++)
					randList.get(i);
				
			}
			
			stopTime = System.nanoTime();
			
			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			averageTime = (((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) timesToLoop)/(double) n;
			
			System.out.print(n + "\t" + averageTime + "\t");
			
			binTree.addAll(randList);
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();
			
			for(int j = 0; j < timesToLoop; j++)
			{
				for(int i = 0; i < n; i++)
				{
					setTree.contains(i);
				}
				
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int j = 0; j < timesToLoop; j++)
			{
				for(int i = 0; i < n; i++)
				{
					
				}
				
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			averageTime = (((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) timesToLoop)/(double) n;

			System.out.print(n + "\t" + averageTime + "\t");
			
			startTime = System.nanoTime();
			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}
			
			// Now, run the test
			startTime = System.nanoTime();
			
			for(int j = 0; j < timesToLoop; j++)
			{
				for(int i = 0; i < n; i++)
				{
					binTree.contains(i);
				}
				
			}
			
			midpointTime = System.nanoTime();
			
			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int j = 0; j < timesToLoop; j++)
			{
				for(int i = 0; i < n; i++)
				{
					
				}
				
			}
			
			stopTime = System.nanoTime();
			
			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			averageTime = (((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double) timesToLoop)/(double) n;
			
			System.out.println(n + "\t" + averageTime);
		}
		//Binary Tree ------------------------------------------------
		
	}

}
