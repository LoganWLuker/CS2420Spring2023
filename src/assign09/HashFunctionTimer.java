package assign09;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class HashFunctionTimer
{

	public static void main(String[] args)
	{
		// Do 100000 lookups and use the average running time
		int timesToLoop = 10;
		Random rand = new Random();
		for(int n = 1; n <= 20001; n += 1000)
		{
			//setup
			HashMap<StudentGoodHash, Double> badTable = new HashMap<StudentGoodHash, Double>();
			var students = new ArrayList<StudentGoodHash>();
			for(int i = 0; i <= n; i++)
			{
				students.add(new StudentGoodHash(rand.nextInt(1000000),String.valueOf(rand.nextInt(99999999)),String.valueOf(rand.nextInt(99999999))));
			}
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
				badTable = new HashMap<StudentGoodHash, Double>();
				for(int i = 0; i < n; i++)
				{
					badTable.put(students.get(i), 0.5);
				}
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for(int j = 0; j < timesToLoop; j++)
			{
				badTable = new HashMap<StudentGoodHash, Double>();
				for(int i = 0; i < n; i++)
				{
					students.get(i);
				}
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / 
					(double)timesToLoop / (double) n;

			System.out.println(n-1 + "\t" + averageTime);

		}

	}

}
