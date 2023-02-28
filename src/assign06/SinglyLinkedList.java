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
	public void setLinkNext(Node next)
	{
		head.next = next;
	}
	public Node getLinkNext()
	{
		return head.next;
	}
	public void setData(T d)
	{
		head.data = d;
	}
	public T getData()
	{
		return head.data;
	}
	
//	private class Node {
//		T data;
//		Node next;
//		public Node()
//		{
//			next = null;
//			data = null;
//		}
//		public Node(T data, Node next)
//		{
//			this.data = data;
//			this.next = next;
//		}
//	}
	
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
		@SuppressWarnings("unchecked")
		Node newNode = new Node((T) element, head.next);
		head.next = newNode;	
	}

	@Override
	public T getFirst() throws NoSuchElementException 
	{
		return head.data;
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException 
	{
		T toReturn = head.data;
		Iterator<T> getIterator = this.iterator();
		for(int i = 0; i < index; i++)
			toReturn = getIterator.next();
		return toReturn;
	}

	@Override
	public T deleteFirst() throws NoSuchElementException 
	{
		Node temp = head;
		head = head.next;
		return temp.data;
	}

	@Override
	public T delete(int index) throws IndexOutOfBoundsException 
	{
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
		//TODO finish this
		T toReturn = head.data;
		Iterator<T> delIterator = this.iterator();
		return 0;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public T[] toArray() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> iterator() 
	{
		return new SLLIterator();
	}

}

	