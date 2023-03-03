package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * This class simulates a web browser by implementing two stacks: One for the 'back' button, and one for the
 * 	'forward' button. 
 * The class implements a LinkedListStack of type URL, used for its more consistent runtime.
 * @author Logan Luker and Bruce Crockett
 *
 */
public class WebBrowser 
{
	LinkedListStack<URL> forward;
	LinkedListStack<URL> back;
	URL current;
	
	/**
	 * Default constructor that starts with empty forward and back buttons
	 */
	public WebBrowser()
	{
		forward = new LinkedListStack<URL>();
		back = new LinkedListStack<URL>();
	}
	
	/**
	 * Constructor that creates a browser with state equivalent to a browser
	 * 	with the given history list
	 * @param history : list of URLs to populate the 'back' stack with
	 */
	public WebBrowser(SinglyLinkedList<URL> history)
	{
		forward = new LinkedListStack<URL>();
		back = new LinkedListStack<URL>(history);
		current = back.peek();
	}
	
	/**
	 * Simulates a browser opening a webpage, and clears the forward stack as such
	 * @param webpage : URL to be passed to the back stack and current page parameter
	 */
	public void visit(URL webpage)
	{
		current = webpage;
		back.push(webpage);
		forward.clear();
	}
	
	/**
	 * Simulates a press of the 'back' button. Steps backward in the back stack and moves the current page
	 * 	parameter to the forward stack.
	 * @return current webpage after transition
	 * @throws NoSuchElementException if back is called on an 'original' page
	 */
	public URL back() throws NoSuchElementException
	{
		if(back.isEmpty())
			throw new NoSuchElementException("This webpage does not exist");
		forward.push(current);
		back.pop();
		current = back.peek();
		return current;
	}
	
	/**
	 * Simulates a press of the 'forward' button. Steps forward in the forward stack and moves the current
	 * 	page parameter to the back stack.
	 * @return current webpage after transition
	 * @throws NoSuchElementException if forward is called when back has not been pressed
	 */
	public URL forward() throws NoSuchElementException
	{
		if(forward.isEmpty())
			throw new NoSuchElementException("This webpage does not exist");
		back.push(forward.peek());
		current = forward.pop();
		return current;
	}
	
	/**
	 * Returns a list of all websites in the back stack, simulating browser history that doesn't
	 * 	include any forward webpages.
	 * @return list as above
	 */
	public SinglyLinkedList<URL> history()
	{
		var toReturn = new SinglyLinkedList<URL>();
		if(back.isEmpty())
			return toReturn;
		
		int i = 0;
		
		// Fill the list with the history, keeping track of how far we've gone
		
		// Insert the current URL
		toReturn.insert(i, current);
		
		while(!(back.size() <= 1))
		{
			i++;
			toReturn.insert(i, back());
		}

		// Return the browser to its initial state
		while(i>0)
		{
			forward();
			i--;
		}
		return toReturn;
	}
}
