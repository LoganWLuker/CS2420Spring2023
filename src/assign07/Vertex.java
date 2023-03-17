package assign07;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * generic, assumes that a type T name is stored there.
 * 
 * @author Erin Parker, Bruce Crockett, and Logan Luker
 * @version March 14, 2023
 */
public class Vertex <T> {

	// used to id the Vertex
	private T name;
	public boolean visited;
	public Vertex<T> cameFrom;
	public int inDegree;

	// adjacency list
	private LinkedList<Edge<T>> adj;

	/**
	 * Creates a new Vertex object, using the given name.
	 * 
	 * @param name - type T used to identify this Vertex
	 */
	public Vertex(T name) {
		this.name = name;
		this.adj = new LinkedList<Edge<T>>();
		this.inDegree = 0;
	}

	/**
	 * @return the type T used to identify this Vertex
	 */
	public T getName() {
		return name;
	}

	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param otherVertex - the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex<T> otherVertex) {
		adj.add(new Edge<T>(otherVertex));
		otherVertex.inDegree += 1;
	}

	/**
	 * @return a iterator for accessing the edges for which this Vertex is the source
	 */
	public Iterator<Edge<T>> edges() {
		return adj.iterator();
	}

	/**
	 * Generates and returns a textual representation of this Vertex.
	 */
	public String toString() {
		String s = "Vertex " + name.toString() + " adjacent to vertices "; //toString might not be right
		Iterator<Edge<T>> itr = adj.iterator();
		while(itr.hasNext())
			s += itr.next() + "  ";
		return s;
	}
}