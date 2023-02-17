package lab06;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E>, Iterable<E>
{
	private E[] queue;
	private int currentSize;
	private Comparator<? super E> cmp;

	/**
	 * Default Constructor
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue()
	{
		queue = (E[]) new Object[32];
		currentSize = 0;
	}
	/**
	 * Constructor with Comparator
	 * @param cmp	the comparator to use for insertions
	 */
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp)
	{
		queue = (E[]) new Object[32];
		currentSize = 0;
		this.cmp = cmp;
	}
	/**
	 * 
	 * @param ob1
	 * @param ob2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int compare(E ob1, E ob2) 
	{
		if(cmp == null)
		{
			int toReturn;
			try
			{
				toReturn = ((Comparable<? super E>)ob1).compareTo(ob2);
			}
			catch(Exception e)
			{
				throw new NoSuchElementException("This object doesn't have a default comparison. Construct with a comparator");
			}
			return toReturn;
		}
		return cmp.compare(ob1, ob2);
	}
	@Override
	public E findMax() throws NoSuchElementException
	{
		// test for empty array
		if(queue[0] == null)
			throw new NoSuchElementException("Array is empty");
		
		return queue[currentSize-1];
	}

	@Override
	public E deleteMax() throws NoSuchElementException
	{
		
		// test for empty array
		if(queue[0] == null)
			throw new NoSuchElementException("Array is empty");
		
		E toReturn = queue[currentSize-1];
		queue[currentSize-1] = null;
		currentSize--;
		return toReturn;
	}

	
	
	@SuppressWarnings("unchecked")
	public void instantInsert(Integer[] arr)
	{
		queue = (E[])arr;
		currentSize = queue.length;
	}
	
	
	@Override
	public void insert(E item)
	{
		
		if(queue[0] == null)
		{
			queue[0] = item;
			currentSize ++;
			return;
		}
		int pos = binSearch(item);
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

		for (int i = queue.length-1; i > pos; i--) 
		{
			queue[i] = queue[i-1];
		}
		
		queue[pos] = item;
		currentSize ++;
		
	}
	
	public int binSearch(E item)
	{
		// determine the 'cutoff' position, pos
		int low = 0, high = queue.length - 1, mid = 0;
		while(low <= high)
		{
			mid = (low + high) / 2;
			if(item.equals(queue[mid]))
				return mid;
			if(queue[mid] == null || compare(item,queue[mid]) < 0)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return low;
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
		if(item == queue[binSearch(item)])
			return true;
		return false;
	}

	@Override
	public int size()
	{
		return this.currentSize;
	}

	@Override
	public boolean isEmpty()
	{
		if(currentSize == 0)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear()
	{
		currentSize = 0;
		queue = (E[]) new Object[32];
		
	}
	
	/**
	 * Retrieve an element from the queue
	 * @param pos	position of element in queue
	 * @return The element at given position
	 */
	public E getElement(int pos) 
	{
		return queue[pos];
	}
	public class FivePebbles implements Iterator<E> 
	{
		private int nextIndex;
		private boolean nextCalled;
		public FivePebbles() 
		{
			nextIndex = 1;
			nextCalled = false;
		}
		@Override
		public boolean hasNext()
		{
			if(currentSize > nextIndex)
				return true;
			return false;
		}

		@Override
		public E next()
		{
			if(!hasNext())
			    throw new NoSuchElementException();
			nextIndex++;
			nextCalled = true;
			return queue[nextIndex-1];
		}
		@Override
		public void remove()
		{
			if(!nextCalled)
				throw new IllegalStateException();
			nextCalled = false;
			for (int i = nextIndex-1; i < queue.length-1; i++) 
			{
				queue[i] = queue[i+1];
			}
			queue[queue.length-1] = null;
			currentSize--;
		}
		
	}
	@Override
	public Iterator<E> iterator()
	{
		return new FivePebbles();
	}
}
