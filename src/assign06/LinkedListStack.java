package assign06;

import java.util.NoSuchElementException;

/**
 * Class that implements the given Stack interface, backed by a SinglyLinkedList.
 * Overridden methods have contracts detailed in the given Stack interface.
 * @author Logan Luker and Bruce Crockett
 *
 * @param <E> : type of data to pass to backing linked list
 */
public class LinkedListStack<E> implements Stack<E>
{
	private SinglyLinkedList<E> list;

	/**
	 * Default constructor
	 */
	public LinkedListStack()
	{
		list = new SinglyLinkedList<E>();
	}
	
	/**
	 * Constructs a stack backed by a given SinglyLinkedList
	 * @param inpList : list to back stack
	 */
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
	
	/**
	 * (Unused) helper method to insert an element at a given position in the stack
	 * @param index : point in the backing list to insert the element
	 * @param element : data to be passed to backing list
	 */
	public void insert(int index, Object element)
	{
		list.insert(index, element);
	}
	
	/**
	 * (Unused) helper method to return a specific element at a given position in the stack
	 * @param index : point in the backing list to grab element
	 * @return element at the index
	 * @throws IndexOutOfBoundsException
	 */
	public E get(int index) throws IndexOutOfBoundsException
	{
		return list.get(index);
	}

}
