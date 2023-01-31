package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E>
{
	private E[] queue;
	private int currentSize;

	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		queue = (E[]) new Object[32];
	}
	
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		
	}
	
	@Override
	public E findMax() throws NoSuchElementException {
		
		// test for empty array
		if(queue[0] == null)
			throw new NoSuchElementException("Array is empty");
		
		// loop through array looking for null elements,
		// if found, return previous entry
		for (int i = 1; i < queue.length; i++) {
			if (queue[i] == null)
				return queue[i-1];
		}
		
		// array is full, return largest value
		return queue[queue.length - 1];
	}

	@Override
	public E deleteMax() throws NoSuchElementException {
		
		// test for empty array
		if(queue[0] == null)
			throw new NoSuchElementException("Array is empty");
		
		// loop through array looking for null elements,
		// if found, return previous entry
		for (int i = 1; i < queue.length; i++) {
			if (queue[i] == null) {
				E toReturn = queue[i-1];
				queue[i-1] = null;
				return toReturn;
			}
		}
		
		return null;
	}

	@Override
	public void insert(E item) {
		
		if(queue[0] == null)
			queue[0] = item;
		int pos = binSearch(item)+1;
		// check to see if array is full, and create a doubled array
		// if so
		
		if(queue[queue.length-1] != null) {
			@SuppressWarnings("unchecked")
			E[] nextArray = (E[]) new Object[queue.length*2];
			
			for(int i = 0; i < queue.length; i++) {
				nextArray[i] = queue[i];
			}
			
			this.queue = nextArray;
		}
			
		
		// move everything >= pos up by 1
		@SuppressWarnings("unchecked")
		E[] placeArr = (E[]) new Object[queue.length];
		
		for (int i = 0; i < pos - 1; i++) {
			placeArr[i] = queue[i];
		}
		
		placeArr[pos-1] = item;
		
		for (int i = pos; i < queue.length; i++) {
			placeArr[i] = queue[i-1];	
		}
		
		queue = placeArr;
		
		
		/*
		// test for empty array
		if(queue[0] == null)
			queue[0] = item;
				
		// loop through array looking for null elements,
		// if found, insert item
		for (int i = 1; i < queue.length; i++) {
			if (queue[i] == null)
				queue[i] = item;
		}
		
		// create a new array that is double the size, and insert item
		// at the next element
		@SuppressWarnings("unchecked")
		E[] nextArray = (E[]) new Object[queue.length*2];
		
		for(int i = 0; i < queue.length; i++) {
			nextArray[i] = queue[i];
		}
		
		nextArray[queue.length] = item;
		this.queue = nextArray;
		*/
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	public int binSearch(E item)
	{
//		if(queue[0] == null)
//			return 0;
		
		// determine the 'cutoff' position, pos
		int low = 0, high = queue.length - 1, mid = 0;
		while(low <= high)
		{
			mid = (low + high) / 2;
			if(item.equals(queue[mid]))
				return mid;
			if(((Comparable<? super E>)item).compareTo(queue[mid]) < 0)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return mid;
	}

	@Override
	public void insertAll(Collection<? extends E> coll) 
	{
		for(E item : coll) 
		{
			insert(item);
		}
	}

	@Override
	public boolean contains(E item) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
