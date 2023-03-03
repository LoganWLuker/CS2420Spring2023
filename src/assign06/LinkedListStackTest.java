package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test the methods of the LinkedListStack class.
 * @author Bruce Crockett and Logan Luker
 */
public class LinkedListStackTest 
{
	SinglyLinkedList<Integer> list1, list2, list3, list4, list5;
	LinkedListStack<Integer> stack1, stack2, stack3, stack4;
	LinkedListStack<String> stringStack;
	
	@BeforeEach
	void setUp()
	{
		list1 = new SinglyLinkedList<Integer>();
		for(int i=0; i<5; i++)
			list1.insertFirst(i);
		
		// stack constructed manually
		stack1 = new LinkedListStack<Integer>();
		for(int i=0; i<5; i++)
			stack1.push(i);
		
		//stack constructed from list
		stack2 = new LinkedListStack<Integer>(list1);
		
		//empty stack
		stack3 = new LinkedListStack<Integer>();
		
		//string stack
		stringStack = new LinkedListStack<String>();
	}
	
	@Test
	void clearTest()
	{
		stack1.clear();
		assertThrows(NoSuchElementException.class, () -> stack1.peek());
		assertThrows(NoSuchElementException.class, () -> stack1.pop());
		assertEquals(0, stack1.size());
		assertTrue(stack1.isEmpty());
		
		stack2.clear();
		assertThrows(NoSuchElementException.class, () -> stack2.peek());
		assertThrows(NoSuchElementException.class, () -> stack2.pop());
		assertEquals(0, stack2.size());
		assertTrue(stack2.isEmpty());
	}
	
	@Test
	void isEmptyTest()
	{
		var emptyIntStack = new LinkedListStack<Integer>();
		var emptyStringStack = new LinkedListStack<String>();
		
		assertTrue(emptyIntStack.isEmpty());
		assertTrue(emptyStringStack.isEmpty());
		
		emptyIntStack.push(1);
		emptyStringStack.push("a");
		
		emptyIntStack.pop();
		emptyStringStack.pop();
		
		assertTrue(emptyIntStack.isEmpty());
		assertTrue(emptyStringStack.isEmpty());
		
		for(int i=0; i<5; i++)
			stack1.pop();
		
		assertTrue(stack1.isEmpty());
	}
	
	@Test
	void peekTest()
	{
		for(int i=4; i>=0; i--)
		{
			assertEquals(i, stack1.peek());
			stack1.pop();
		}
		
		assertThrows(NoSuchElementException.class, () -> stack3.peek());
	}
	
	@Test
	void popTest()
	{
		for(int i=4; i>=0; i--)
		{
			assertEquals(i, stack1.pop());
		}
		
		assertTrue(stack1.isEmpty());
		
		assertThrows(NoSuchElementException.class, () -> stack3.pop());
	}
	
	@Test
	void pushTest()
	{
		for(int i=0; i<5; i++)
		{
			stack3.push(i);
			assertEquals(i, stack3.peek());
		}
		
		assertFalse(stack3.isEmpty());
		
		stringStack.push("a");
		stringStack.push("b");
		stringStack.push("c");
		stringStack.push("d");
		stringStack.push("e");
		
		assertFalse(stringStack.isEmpty());
		
		assertEquals(stringStack.pop(), "e");
		assertEquals(stringStack.pop(), "d");
		assertEquals(stringStack.pop(), "c");
		assertEquals(stringStack.pop(), "b");
		assertEquals(stringStack.pop(), "a");
		
		assertTrue(stringStack.isEmpty());
	}
	
	@Test
	void sizeTest()
	{
		assertEquals(0, stack3.size());
		assertEquals(0, stringStack.size());
		
		assertEquals(5, stack1.size());
		assertEquals(5, stack2.size());
		
		stack1.push(1);
		
		assertEquals(6, stack1.size());
		
		stack1.pop();
		
		assertEquals(5, stack1.size());
		
		stringStack.push("A");
		
		assertEquals(1, stringStack.size());
		
		stringStack.pop();
		
		assertEquals(0, stringStack.size());
	}
}
