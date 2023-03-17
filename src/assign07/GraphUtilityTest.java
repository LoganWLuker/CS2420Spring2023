package assign07;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GraphUtilityTest{

	@Test
	void areConnectedTest()
	{
		ArrayList<String> sources = new ArrayList<String>();
		ArrayList<String> destinations = new ArrayList<String>();
		GraphUtility.buildListsFromDot("lab08.tenVertices", sources, destinations);
		
		assertTrue(GraphUtility.areConnected(sources, destinations, "v0", "v9"));
		assertTrue(GraphUtility.areConnected(sources, destinations, "v1", "v5"));
		assertTrue(GraphUtility.areConnected(sources, destinations, "v2", "v4"));
		assertTrue(GraphUtility.areConnected(sources, destinations, "v3", "v8"));
		assertTrue(GraphUtility.areConnected(sources, destinations, "v4", "v8"));
		assertTrue(GraphUtility.areConnected(sources, destinations, "v5", "v5"));
		assertTrue(GraphUtility.areConnected(sources, destinations, "v6", "v7"));
		assertTrue(GraphUtility.areConnected(sources, destinations, "v7", "v9"));
		assertTrue(GraphUtility.areConnected(sources, destinations, "v8", "v8"));
		
		assertTrue(GraphUtility.areConnected(sources, destinations, "v2", "v8"));
		assertTrue(GraphUtility.areConnected(sources, destinations, "v6", "v9"));
		
		assertFalse(GraphUtility.areConnected(sources, destinations, "v0", "v1"));
		assertFalse(GraphUtility.areConnected(sources, destinations, "v1", "v6"));
		assertFalse(GraphUtility.areConnected(sources, destinations, "v1", "v7"));
		assertFalse(GraphUtility.areConnected(sources, destinations, "v1", "v9"));
		assertFalse(GraphUtility.areConnected(sources, destinations, "v2", "v3"));
		assertFalse(GraphUtility.areConnected(sources, destinations, "v2", "v9"));
	}
	@Test
	void shortestPathTest()
	{
		ArrayList<String> sources = new ArrayList<String>();
		ArrayList<String> destinations = new ArrayList<String>();
		GraphUtility.buildListsFromDot("lab08.tenVertices", sources, destinations);
		//System.out.println(GraphUtility.shortestPath(sources, destinations, "v0", "v2"));
	}
	
	@Test
	void sortTest()
	{
		ArrayList<String> sources = new ArrayList<String>();
		ArrayList<String> destinations = new ArrayList<String>();
		GraphUtility.buildListsFromDot("DAG1.txt", sources, destinations);
		System.out.println(GraphUtility.sort(sources, destinations));
	}
}
