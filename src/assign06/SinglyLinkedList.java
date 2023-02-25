package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements List<T>
{
	Node head;
	int size;
	
	public SinglyLinkedList()
	{
		
	}
	
	private class Node {
		T data;
		Node next;
		public Node(T data, Node next)
		{
			this.data = data;
			this.next = next;
		}
	}
	
	public class SLLIterator implements Iterator<T> 
	{
		private int nextIndex;
		private boolean nextCalled;
		private Node here;
		public SLLIterator() 
		{
			nextIndex = 1;
			nextCalled = false;
			here = head;
		}
		@Override
		public boolean hasNext()
		{
			if(size > nextIndex)
				return true;
			return false;
		}

		@Override
		public T next()
		{
			if(!hasNext())
			    throw new NoSuchElementException();
			nextIndex++;
			nextCalled = true;
			return head.next.data;
			
		}
		@Override
		public void remove()
		{
			if(!nextCalled)
				throw new IllegalStateException();
			nextCalled = false;
			/*
			for (int i = nextIndex-1; i < queue.length-1; i++) 
			{
				queue[i] = queue[i+1];
			}
			queue[queue.length-1] = null;
			currentSize--;
			*/
			// TODO:
		}
		
	}
	
	
	@Override
	public void insertFirst(Object element) 
	{
		@SuppressWarnings("unchecked")
		Node newHead = new Node((T) element, head);
		head = newHead;
		size++;
		
	}

	@Override
	public void insert(int index, Object element) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T getFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T deleteFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T delete(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object element) {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}

	