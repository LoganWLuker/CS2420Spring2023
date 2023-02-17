package lab06;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

/**
 * This class demonstrates how to use iterators, for Lab 6.
 * 
 * @author CS 2420 course staff
 * @version February 18, 2022
 */
public class IteratorDemo {
	public static void main(String[] args) {
		SortedSet<Integer> ss = new TreeSet<>();

		// How we like to see this
		for (int element : ss) {
			System.out.println(element);
		}

		// But this is what it is really doing
		Iterator<Integer> iter = ss.iterator();
		while (iter.hasNext()) {
			int element = iter.next();
			System.out.println(element);
		}

		// Same thing, different loop
		for (Iterator<Integer> it = ss.iterator(); it.hasNext();) {
			int element = it.next();
			System.out.println(element);
		}

		// this works for ANY collection:
		Collection<Integer> collection = new ArrayList<>();
		collection = new LinkedList<Integer>();
		collection = new HashSet<>();
		collection = new Vector<>();
		collection = new PriorityQueue<>();

		Queue<Integer> q = new LinkedList<>();
		q = new PriorityQueue<>();

		Stack<Integer> stack = new Stack<>();

		Iterator<Integer> iterator;
		iterator = collection.iterator();
		iterator = q.iterator();
		iterator = stack.iterator();

		for (int whatever : collection) {
			System.out.println(whatever);
		}

		for (int something : q) {
			System.out.println(q);
		}

		for (int nothing : stack) {
			System.out.println(stack);
		}
		var points = new ArrayList<Point>(Arrays.asList(new Point(0,0),new Point(1,2),new Point(3,4),new Point(5,6),new Point(7,8)));
		Iterator<Point> fivePebbles;
		fivePebbles = points.iterator();
		while (fivePebbles.hasNext()) {
			Point point = fivePebbles.next();
			System.out.println("("+point.x+","+point.y+")");
			point.translate(1, -1);
			System.out.println("Updated: ("+point.x+","+point.y+")");
		}
	}
}
