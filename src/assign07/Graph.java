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
	public ArrayList<Vertex<T>> vertList; //may contain repeat vertices
	//public ArrayList<Edge<T>> edgeList;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<T, Vertex<T>>();
		vertList = new ArrayList<Vertex<T>>();
		//edgeList = new ArrayList<Edge<T>>();
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
			vertList.add(vertex1);
		}
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex<T>(name1);
			vertices.put(name1, vertex1);
			vertList.add(vertex1);
		}

		Vertex<T> vertex2;
		if(vertices.containsKey(name2)) {
			vertex2 = vertices.get(name2);
			vertList.add(vertex2);
		}
		else {
			vertex2 = new Vertex<T>(name2);
			vertices.put(name2, vertex2);
			vertList.add(vertex2);
		}

		// add new directed edge from vertex1 to vertex2
		vertex1.addEdge(vertex2);
	}
	
	public boolean DFS(Graph<T> G, Vertex<T> source, Vertex<T> target) {
		
		source.visited = true;
		if(source.equals(target))
			return true;
		
		Iterator<Edge<T>> iter = source.edges();
		while(iter.hasNext())
		{
			Vertex<T> neighbor = iter.next().getOtherVertex();
			if(neighbor.visited == false)
			{
				boolean result = DFS(G, neighbor, target);
				if(result)
					return true;
			}
		}
		
		return false;
	}
	
	public List<T> BFS(Graph<T> G, Vertex<T> source, Vertex<T> target) {
		Queue<Vertex<T>> verticesToVisit = new LinkedList<Vertex<T>>();
		verticesToVisit.offer(source);
		
		while(!verticesToVisit.isEmpty())
		{
			Vertex<T> v = verticesToVisit.poll();
			v.visited = true;
			if(v.equals(target))
				return reconstructPath(G, target, source); //source might be wrong
			
			Iterator<Edge<T>> iter = v.edges();
			while(iter.hasNext())
			{
				Vertex<T> neighbor = iter.next().getOtherVertex();
				if(neighbor.visited == false)
				{
					neighbor.cameFrom = v;
					neighbor.visited = true;
					verticesToVisit.offer(neighbor);
				}	
			}	
		}
		
		return null;
	}
	
	public LinkedList<T> reconstructPath(Graph<T> G, Vertex<T> target, Vertex<T> start) {
		LinkedList<T> path = new LinkedList<T>();
		for(Vertex<T> v = target; !v.equals(start); v = v.cameFrom)
		{
			path.add(v.getName());
		}
		path.add(start.getName());
		
		//TODO: reverse path
		return path;
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