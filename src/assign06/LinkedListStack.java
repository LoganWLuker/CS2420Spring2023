package assign06;

import java.util.NoSuchElementException;

public class LinkedListStack<E> implements Stack<E>
{
	private SinglyLinkedList<E> list;

	public LinkedListStack()
	{
		list = new SinglyLinkedList<E>();
	}
	
	public LinkedListStack(SinglyLinkedList<E> inpList)
	{
		this.list = inpList;
	}
	@Override
	public void clear()
	{
		list.clear();
	}

	@Override
	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	@Override
	public E peek() throws NoSuchElementException
	{
		return list.getFirst();
	}

	@Override
	public E pop() throws NoSuchElementException
	{
		return list.deleteFirst();
	}

	@Override
	public void push(E element)
	{
		list.insertFirst(element);
	}

	@Override
	public int size()
	{
		return list.size();
	}
	public void insert(int index, Object element)
	{
		list.insert(index, element);
	}
	public E get(int index) throws IndexOutOfBoundsException
	{
		return list.get(index);
	}

}
