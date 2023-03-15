package assign07;

/**
 * Demonstrates how to use the Graph class.
 * 
 * @author Erin Parker, Bruce Crockett, & Logan Luker
 * @version March 14, 2022
 */
public class GraphDemo {

	public static void main(String[] args) {
		
		// build a sample directed graph
		Graph<String> sample = new Graph<String>();
		sample.addEdge("a", "b");
		sample.addEdge("b", "c");
		sample.addEdge("c", "d");
		sample.addEdge("b", "d");
		sample.addEdge("d", "a");

		// print textual representation of sample graph
		System.out.println(sample);
		
		// print DOT representation of sample graph (paste into http://www.webgraphviz.com)
		System.out.println(sample.generateDot());
		
		Graph<Integer> sample2 = new Graph<Integer>();
		sample2.addEdge(1, 2);
		sample2.addEdge(2, 3);
		sample2.addEdge(2, 4);
		sample2.addEdge(3, 5);
		
		System.out.println(sample2);
		
		// print DOT representation of sample2 graph (paste into http://www.webgraphviz.com)
		System.out.println(sample2.generateDot());
	}
}