package assign07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import assign06.ArrayStack;

public class GraphUtilityTimer {

	public static void main(String[] args) 
	{
		//Random randomNumberGenerator = new Random();
		
		// Do 100000 lookups and use the average running time
		int timesToLoop = 2000;
		
		for(int n = 100; n <= 5000; n += 100)
		{
			Random rng = new Random();
			String[] vertex = new String[n];
		    for(int i = 0; i < n; i++)
		    	vertex[i] = "v" + i;

		    ArrayList<String> sources = new ArrayList<String>();
		    ArrayList<String> destinations = new ArrayList<String>();
		    for(int i = 0; i < 2 * n; i++) {
			    sources.add(vertex[rng.nextInt(n)]);
			    destinations.add(vertex[rng.nextInt(n)]);
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
				GraphUtility.areConnected(sources, destinations, sources.get(rng.nextInt(n)), destinations.get(rng.nextInt(n)));
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			// and generating two random integers
			for(int j = 0; j < timesToLoop; j++)
			{
				sources.get(rng.nextInt(n));
				destinations.get(rng.nextInt(n));
			}
			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

			System.out.println(n + "\t" + averageTime);

		}

	}
	
	public static void generateRandomDotFile(String filename, int vertexCount) {
	    PrintWriter out = null;
	    try {
		   out = new PrintWriter(filename);
	    } 
	    catch (IOException e) {
	      System.out.println(e);
	    }

	    Random rng = new Random();

	    // randomly construct either a graph or a digraph
	    
	    String edgeOp = "->";
	    out.print("di");
	    
	    out.println("graph G {");

	    // generate a list of vertices
	    String[] vertex = new String[vertexCount];
	    for(int i = 0; i < vertexCount; i++)
	      vertex[i] = "v" + i;

	    
	    // randomly connect the vertices using 2 * |V| edges
	    for(int i = 0; i < 2 * vertexCount; i++)
	      out.println("\t" + vertex[rng.nextInt(vertexCount)] + edgeOp + vertex[rng.nextInt(vertexCount)]);
		
	    
	    /* DAGs -------------------------------------
	 // NOTE: Repeat this loop as needed for denser graphs.
	    for(int i = 0; i < vertexCount - 1; i++)
	      out.println("\t" + vertex[i] + "->" + vertex[i + rng.nextInt(vertexCount - (i))]);

	    out.println("}");
	    out.close();
	    */
	  }
}
