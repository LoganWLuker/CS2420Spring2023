package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements List<T>
{
	Node head;
	int size;
	
	private class Node
	{
		T data;
		Node next;
		public Node(T data, Node next)
		{
			this.data = data;
			this.next = next;
		}
	}
	
	public SinglyLinkedList()
	{
		this.head = null;
		this.size = 0;
		
	}
	public SinglyLinkedList(T data, Node next)
	{
		head.data = data;
		head.next = next;
	}
	
	public class SLLIterator implements Iterator<T>
	{
		private Node next;
		private Node lastReturned;
		private Node prev;
		public SLLIterator()
		{
			next = head.next;
		}
		@Override
		public boolean hasNext()
		{
			return next != null;
		}

		@Override
		public T next()
		{
			if(next == null)
			    throw new NoSuchElementException();
			lastReturned = next;
			next = next.next;
			if(prev == null)
				prev = head;
			else
				prev = prev.next;
			
			return lastReturned.data;
			
		}
		@Override
		public void remove()
		{
			if(lastReturned == null)
				throw new IllegalStateException();
			prev.next = lastReturned.next;
			lastReturned = null;
		}
		public void insert(Object element)
		{
			if(lastReturned == null)
				lastReturned = head;
			@SuppressWarnings("unchecked")
			Node newNode = new Node((T) element, lastReturned.next);
			lastReturned.next = newNode;
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
	public void insert(int index, Object element) throws IndexOutOfBoundsException 
	{
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index is out of range");
		if(index == 0)
		{
			insertFirst(element);
			return;
		}
		Iterator<T> insIterator = this.iterator();
		for(int i = 0; i < index-1; i++)
			insIterator.next();
		((SLLIterator)(insIterator)).insert(element);
		size++;
	}

	@Override
	public T getFirst() throws NoSuchElementException 
	{
		if(size == 0)
			throw new NoSuchElementException("List is empty");
		return head.data;
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException 
	{
		if(index < 0 || index >= size) // Note: if it breaks then change this back from >= to >
			throw new IndexOutOfBoundsException("Index is out of range");
		T toReturn = head.data;
		Iterator<T> getIterator = this.iterator();
		for(int i = 0; i < index; i++)
			toReturn = getIterator.next();
		return toReturn;
	}

	@Override
	public T deleteFirst() throws NoSuchElementException 
	{
		if(size == 0)
			throw new NoSuchElementException("List is empty");
		Node temp = head;
		head = head.next;
		return temp.data;
	}

	@Override
	public T delete(int index) throws IndexOutOfBoundsException 
	{
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index is out of range");
		T toReturn = head.data;
		Iterator<T> delIterator = this.iterator();
		for(int i = 0; i < index; i++)
			toReturn = delIterator.next();
		delIterator.remove();
		size--;
		return toReturn;
	}

	@Override
	public int indexOf(Object element) 
	{
		int toReturn = 0;
		Iterator<T> indIterator = this.iterator();
		T current = head.data;
		while(!current.equals(element))
		{
			current = indIterator.next();
			toReturn++;
		}
		return toReturn;
	}

	@Override
	public int size() 
	{
		return this.size;
	}

	@Override
	public boolean isEmpty() 
	{
		if(size == 0)
			return true;
		return false;
	}

	@Override
	public void clear() 
	{
		head = null;
		size = 0;
	}

	@Override
	public Object[] toArray()  throws NoSuchElementException
	{
		if(size == 0)
			throw new NoSuchElementException("List is empty");
		Iterator<T> toArrIterator = this.iterator();
		Object[] arr = new Object[size];
		arr[0] = head.data;
		for(int i = 1; i < size; i++)
		{
			arr[i] = toArrIterator.next();
		}
		return arr;
	}

	@Override
	public Iterator<T> iterator() 
	{
		return new SLLIterator();
	}

}

	