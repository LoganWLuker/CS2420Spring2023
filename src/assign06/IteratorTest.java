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
		assertEquals(2,list.get(1));
		
	}
}
