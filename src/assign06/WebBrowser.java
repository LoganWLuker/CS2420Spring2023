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
	
	public WebBrowser(LinkedListStack<URL> history)
	{
		forward = new LinkedListStack<URL>();
		back = history;
		current = history.peek();
	}
	
	public void visit(URL webpage)
	{
		current = webpage;
		back.push(webpage);
		forward.clear();
	}
	
	public URL back() throws NoSuchElementException
	{
		forward.push(current);
		back.pop();
		current = back.peek();
		return current;
	}
	
	public URL forward() throws NoSuchElementException
	{
		return null;
	}
	
	public SinglyLinkedList<URL> history()
	{
		return null;
	}
}
