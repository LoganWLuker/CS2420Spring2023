package assign07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set of edges). 
 * The graph is generic and assumes that a type T is stored at each vertex.
 * 
 * @author Erin Parker, Bruce Crockett, & Logan Luker
 * @version March 14, 2023
 */
public class Graph <T> {

	// the graph -- a set of vertices (T name mapped to Vertex instance)
	private HashMap<T, Vertex<T>> vertices;
	//public ArrayList<Vertex<T>> vertList; //may contain repeat vertices
	//public ArrayList<Edge<T>> edgeList;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<T, Vertex<T>>();
		//vertList = new ArrayList<Vertex<T>>();
		//edgeList = new ArrayList<Edge<T>>();
	}
	/**
	 * Constructs a graph containing edges based on the specified sources and destinations
	 * @throws	IllegalArgumentException if the parameters are different sizes
	 * @param sources
	 * @param destinations
	 */
	public Graph(List<T> sources, List<T> destinations)
	{
		if(sources.size() != destinations.size())
			throw new IllegalArgumentException("Sources and Destinations are of different sizes");
		vertices = new HashMap<T, Vertex<T>>();
		for(int i = 0; i < sources.size(); i++)
		{
			this.addEdge(sources.get(i), destinations.get(i));
		}
	}
	/**
	 * Adds to the graph a directed edge from the vertex with name "name1" 
	 * to the vertex with name "name2".  (If either vertex does not already 
	 * exist in the graph, it is added.)
	 * 
	 * @param name1 - type T name for source vertex
	 * @param name2 - type T name for destination vertex
	 */
	public void addEdge(T name1, T name2) {
		Vertex<T> vertex1;
		// if vertex already exists in graph, get its object
		if(vertices.containsKey(name1)) {
			vertex1 = vertices.get(name1);
		}
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex<T>(name1);
			vertices.put(name1, vertex1);
		}

		Vertex<T> vertex2;
		if(vertices.containsKey(name2)) {
			vertex2 = vertices.get(name2);
		}
		else {
			vertex2 = new Vertex<T>(name2);
			vertices.put(name2, vertex2);
		}

		// add new directed edge from vertex1 to vertex2
		vertex1.addEdge(vertex2);
	}
	/**
	 * Prepares to Depth First Search for a path to given target from given source
	 * Sets the vertices to visited = false, and calls the recursive DFS method
	 * with the vertices attached to the data in the hash map
	 * @param sourceKey
	 * @param targetKey
	 * @return
	 */
	public boolean DFSPrep(T sourceKey, T targetKey)
	{
		if(!vertices.containsKey(sourceKey) || !vertices.containsKey(targetKey))
			throw new IllegalArgumentException("Source or Target Vertex does not exist");
		Vertex<T> source = vertices.get(sourceKey);
		Vertex<T> target = vertices.get(targetKey);
		vertices.forEach((key,value) -> {
			value.visited = false;
		});
		return DFS(source,target);
	}
	/**
	 * Recursive method to depth first search for a path from source to target
	 * 
	 * @param source	starting vertex
	 * @param target	target vertex
	 * @return true if path exists, false if no path exists
	 */
	public boolean DFS(Vertex<T> source, Vertex<T> target) 
	{
		source.visited = true;
		if(source.equals(target))
			return true;
		
		Iterator<Edge<T>> iter = source.edges();
		while(iter.hasNext())
		{
			Vertex<T> neighbor = iter.next().getOtherVertex();
			if(neighbor.visited == false)
			{
				boolean result = DFS(neighbor, target);
				if(result)
					return true;
			}
		}
		return false;
	}
	/**
	 * Driver method for BFS
	 * Prepares to run a BFS search from source to target
	 * @param sourceKey
	 * @param targetKey
	 * @return
	 */
	public List<T> BFSPrep(T sourceKey, T targetKey)
	{
		if(!vertices.containsKey(sourceKey) || !vertices.containsKey(targetKey))
			throw new IllegalArgumentException("Source or Target Vertex does not exist");
		Vertex<T> source = vertices.get(sourceKey);
		Vertex<T> target = vertices.get(targetKey);
		vertices.forEach((key,value) -> {
			value.visited = false;
			value.cameFrom = null;
		});
		
		return BFS(source, target);
	}
	/**
	 * Runs a BFS from source to target, then returns the shortest path
	 * @param source
	 * @param target
	 * @return
	 */
	public List<T> BFS(Vertex<T> source, Vertex<T> target) {
		Queue<Vertex<T>> verticesToVisit = new LinkedList<Vertex<T>>();
		verticesToVisit.offer(source);
		
		while(!verticesToVisit.isEmpty())
		{
			var v = verticesToVisit.poll();
			v.visited = true;
			if(v.equals(target))
				return reconstructPath(target, source); //source might be wrong
			
			Iterator<Edge<T>> iter = v.edges();
			iter.forEachRemaining((edge) -> {
				var neighbor = edge.getOtherVertex();
				if(neighbor.visited == false)
				{
					neighbor.cameFrom = v;
					neighbor.visited = true;
					verticesToVisit.offer(neighbor);
				}
			});
		}
		throw new IllegalArgumentException("no path exists");
	}
	/**
	 * Reconstructs the path taken from the source to the target
	 * to return it to the user
	 * @param target
	 * @param start
	 * @return	linked list containing the path
	 */
	public LinkedList<T> reconstructPath(Vertex<T> target, Vertex<T> start) {
		LinkedList<T> path = new LinkedList<T>();
		for(Vertex<T> v = target; !v.equals(start); v = v.cameFrom)
		{
			path.addFirst(v.getName());
		}
		path.addFirst(start.getName());
		return path;
	}
	/**
	 * Performs a topological sort on the graph
	 * @return	A sorted linked list
	 */
	public LinkedList<T> topoSort()
	{
		var output = new LinkedList<T>();
		
		Queue<Vertex<T>> doableTasks = new LinkedList<Vertex<T>>();
		vertices.forEach((key,value) -> {
			if(value.inDegree == 0)
				doableTasks.offer(value);
		});
		
		while(!doableTasks.isEmpty())
		{
			var task = doableTasks.poll();
			output.add(task.getName());
			Iterator<Edge<T>> iter = task.edges();
			iter.forEachRemaining((edge) -> {
				var neighbor = edge.getOtherVertex();
				neighbor.inDegree--;
				if(neighbor.inDegree==0)
					doableTasks.offer(neighbor);
			});
		}
		if(output.size() == vertices.size())
			return output;
		else
			throw new IllegalArgumentException("Graph cannot contain a loop");
	}
	
	/**
	 * Generates the DOT encoding of this graph as string, which can be 
	 * pasted into http://www.webgraphviz.com to produce a visualization.
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");
		
		// for every vertex 
		for(Vertex<T> v : vertices.values()) {
			// for every edge
			Iterator<Edge<T>> edges = v.edges();
			while(edges.hasNext()) 
				dot.append("\t\"" + v.getName() + "\" -> \"" + edges.next() + "\"\n");
			
		}
		
		return dot.toString() + "}";
	}
	
	/**
	 * Generates a simple textual representation of this graph.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		for(Vertex<T> v : vertices.values()) 
			result.append(v + "\n");
		
		return result.toString();
	}
}