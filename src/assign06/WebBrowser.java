package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser 
{
	LinkedListStack<URL> forward;
	LinkedListStack<URL> back;
	URL current;
	
	public WebBrowser()
	{
		forward = new LinkedListStack<URL>();
		back = new LinkedListStack<URL>();
	}
	
	public WebBrowser(SinglyLinkedList<URL> history)
	{
		forward = new LinkedListStack<URL>();
		back = new LinkedListStack<URL>(history);
		current = back.peek();
	}
	
	public void visit(URL webpage)
	{
		current = webpage;
		back.push(webpage);
		forward.clear();
	}
	
	public URL back() throws NoSuchElementException
	{
		if(back.isEmpty())
			throw new NoSuchElementException("This webpage does not exist");
		forward.push(current);
		back.pop();
		current = back.peek();
		return current;
	}
	
	public URL forward() throws NoSuchElementException
	{
		if(forward.isEmpty())
			throw new NoSuchElementException("This webpage does not exist");
		back.push(forward.peek());
		current = forward.pop();
		return current;
	}
	
	public SinglyLinkedList<URL> history()
	{
		var toReturn = new SinglyLinkedList<URL>();
		int i = 0;
		while(!back.isEmpty())
		{
			toReturn.insert(i, back.pop());
			i++;
		}
		return toReturn;
	}
}
