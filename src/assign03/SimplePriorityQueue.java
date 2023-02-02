package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E>
{
	private E[] queue;
	private int currentSize;
	private Comparator<? super E> cmp;

	@SuppressWarnings("unchecked")
	public SimplePriorityQueue()
	{
		queue = (E[]) new Object[32];
		currentSize = 0;
	}
	
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp)
	{
		queue = (E[]) new Object[32];
		currentSize = 0;
		this.cmp = cmp;
	}
	@SuppressWarnings("unchecked")
	private int compare(E ob1, E ob2) 
	{
		if(cmp == null)
		{
			return ((Comparable<? super E>)ob1).compareTo(ob2);
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

		// move everything >= pos up by 1
		@SuppressWarnings("unchecked")
		E[] placeArr = (E[]) new Object[queue.length];
		
		for (int i = 0; i < pos; i++)
		{
			placeArr[i] = queue[i];
		}
		
		placeArr[pos] = item;
		
		for (int i = pos+1; i < queue.length; i++) 
		{
			placeArr[i] = queue[i-1];
		}
		
		queue = placeArr;
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
	
	// Helper method for running tests; in the real world this
	// would definitely be deleted.
	public E getElement(int pos) {
		return queue[pos];
	}
	
	// Helper method for running tests; in the real world this
	// would definitely be deleted.
	public int getCurrentSize() {
		return this.currentSize;
	}
	
	// Helper method for running tests; in the real world this
	// would definitely be deleted.
	// assumes E is of the Integer type
	public void printContents() {
		for(int i=0; i < queue.length; i++) {
			if(!(queue[i] == null))
				System.out.println(((Integer) queue[i]).intValue());
		}
	}

}
