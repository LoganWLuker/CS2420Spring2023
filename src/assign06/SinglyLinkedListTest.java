package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

/**
 * Class to test the methods of the SinglyLinkedList class.
 * @author Bruce Crockett and Logan Luker
 */
public class SinglyLinkedListTest
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
	
	@Test
	public void iteratorTest()
	{
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertFirst(4);
		list.insertFirst(3);
		list.insertFirst(2);
		list.insertFirst(1);
		SinglyLinkedList<Integer>.SLLIterator iter = (SinglyLinkedList<Integer>.SLLIterator) list.iterator();
		
		assertTrue(iter.hasNext());
		assertEquals(2, iter.next());
		
		assertTrue(iter.hasNext());
		assertEquals(3, iter.next());
		
		assertTrue(iter.hasNext());
		assertEquals(4, iter.next());
		
		assertFalse(iter.hasNext());
		
		iter.remove();
		
		assertEquals(list.get(0), 1);
		assertEquals(list.get(1), 2);
		assertEquals(list.get(2), 3);
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
		assertEquals(list.size(), 3);
		
		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<Integer>();
		list2.insertFirst(4);
		list2.insertFirst(3);
		list2.insertFirst(2);
		list2.insertFirst(1);
		SinglyLinkedList<Integer>.SLLIterator iter2 = (SinglyLinkedList<Integer>.SLLIterator) list2.iterator();
		
		assertTrue(iter2.hasNext());
		assertEquals(2, iter2.next());
		
		iter2.remove();
		
		assertTrue(iter2.hasNext());
		assertEquals(3, iter2.next());
		
		assertTrue(iter2.hasNext());
		assertEquals(4, iter2.next());
		
		assertFalse(iter2.hasNext());
		
		assertEquals(list2.get(0), 1);
		assertEquals(list2.get(1), 3);
		assertEquals(list2.get(2), 4);
		assertThrows(IndexOutOfBoundsException.class, () -> list2.get(3));
		
		SinglyLinkedList<Integer> list3 = new SinglyLinkedList<Integer>();
		list3.insertFirst(4);
		list3.insertFirst(3);
		list3.insertFirst(2);
		list3.insertFirst(1);
		SinglyLinkedList<Integer>.SLLIterator iter3 = (SinglyLinkedList<Integer>.SLLIterator) list3.iterator();
		
		assertTrue(iter3.hasNext());
		assertEquals(2, iter3.next());
		
		assertTrue(iter3.hasNext());
		assertEquals(3, iter3.next());
		
		iter3.remove();
		
		assertTrue(iter3.hasNext());
		assertEquals(4, iter3.next());
		
		assertFalse(iter3.hasNext());
		
		assertEquals(list3.get(0), 1);
		assertEquals(list3.get(1), 2);
		assertEquals(list3.get(2), 4);
		assertThrows(IndexOutOfBoundsException.class, () -> list3.get(3));
	}
	
	@Test
	public void iteratorTestTwo()
	{
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertFirst(4);
		list.insertFirst(3);
		list.insertFirst(2);
		list.insertFirst(1);
		SinglyLinkedList<Integer>.SLLIterator iter = (SinglyLinkedList<Integer>.SLLIterator) list.iterator();
		
		assertThrows(IllegalStateException.class, () -> iter.remove());
		
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		
		assertEquals(1, list.get(0));
		assertEquals(1, list.size());
		
		SinglyLinkedList<Integer> list2 = new SinglyLinkedList<Integer>();
		list2.insertFirst(4);
		list2.insertFirst(3);
		list2.insertFirst(2);
		list2.insertFirst(1);
		SinglyLinkedList<Integer>.SLLIterator iter2 = (SinglyLinkedList<Integer>.SLLIterator) list2.iterator();
		
		assertEquals(2, iter2.next());
		assertEquals(3, iter2.next());
		assertEquals(4, iter2.next());
	}
	
	@Test
	void iteratorTestThree()
	{
		var list = new SinglyLinkedList<String>();
		list.insert(0, "a");
		list.insert(1, "b");
		list.insert(2, "c");
		list.insert(3, "d");
		list.insert(4, "e");
		list.insert(5, "f");
		
		SinglyLinkedList<String>.SLLIterator iter = (SinglyLinkedList<String>.SLLIterator) list.iterator();
		
		while(iter.hasNext())
		{
			iter.next();
		}
	}
}
