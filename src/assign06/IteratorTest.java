package assign06;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class IteratorTest
{
	@Test
	public void getTest() 
	{
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertFirst(3);
		list.insertFirst(2);
		list.insertFirst(1);
		assertEquals(0,list.indexOf(1));
		
	}
	@Test
	public void insertTest() 
	{
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertFirst(3);
		list.insertFirst(2);
		list.insertFirst(1);
		list.insert(1, 4);
		assertEquals(4,list.get(1));
		assertEquals(1,list.get(0));
		assertEquals(2,list.get(2));
		assertEquals(3,list.get(3));
	}
	@Test
	public void indexOfTest() 
	{
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertFirst(3);
		list.insertFirst(2);
		list.insertFirst(1);
		list.insert(1, 4);
		assertEquals(4,list.get(1));
		assertEquals(1,list.get(0));
		assertEquals(2,list.get(2));
		assertEquals(3,list.get(3));
	}
	@Test
	public void sizeTest()
	{
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertFirst(4);
		list.insertFirst(3);
		list.insertFirst(2);
		list.insertFirst(1);
		assertEquals(4,list.size());
	}
	
	@Test
	public void clearTest()
	{
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertFirst(4);
		list.insertFirst(3);
		list.insertFirst(2);
		list.insertFirst(1);
		assertEquals(false, list.isEmpty());
		assertEquals(4, list.size());
		list.clear();
		assertEquals(true, list.isEmpty());
		assertEquals(0, list.size());
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
	}
	
	@Test
	public void toArrayTest()
	{
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertFirst(4);
		list.insertFirst(3);
		list.insertFirst(2);
		list.insertFirst(1);
		Object[] arr = list.toArray();
		Object[] test = {1, 2, 3, 4};
		assertArrayEquals(arr, test);
	}
}