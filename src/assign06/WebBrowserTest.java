package assign06;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class to test the methods of the WebBrowser class.
 * @author Bruce Crockett and Logan Luker
 */
public class WebBrowserTest 
{
	private URL URL1=null, URL2=null, URL3=null, URL4=null, URL5=null,
				URL6=null, URL7=null, URL8=null, URL9=null, URL10=null;
	private WebBrowser defBrowser;
	private SinglyLinkedList<URL> defList;
	@BeforeEach
	void setUp()
	{
		try {
			URL1 = new URL("https://a");
			URL2 = new URL("https://b");
			URL3 = new URL("https://c");
			URL4 = new URL("https://d");
			URL5 = new URL("https://e");
			URL6 = new URL("https://f");
			URL7 = new URL("https://g");
			URL8 = new URL("https://h");
			URL9 = new URL("https://i");
			URL10 = new URL("https://j");
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL");
		}
		
		defBrowser = new WebBrowser();
		defList = new SinglyLinkedList<URL>();
		defList.insertFirst(URL1);
		defList.insertFirst(URL2);
		defList.insertFirst(URL3);
	}
	@Test
	void backInvalidTestOne()
	{
		assertThrows(NoSuchElementException.class, () -> defBrowser.back());
	}
	
	@Test
	void backInvalidTestTwo()
	{
		defBrowser.visit(URL1);
		assertThrows(NoSuchElementException.class, () -> defBrowser.back());
	}
	
	@Test
	void backInvalidTestThree()
	{
		defBrowser.visit(URL1);
		defBrowser.visit(URL2);
		defBrowser.back();
		assertThrows(NoSuchElementException.class, () -> defBrowser.back());
	}
	
	@Test
	void backTestOne()
	{
		defBrowser.visit(URL1);
		defBrowser.visit(URL2);
		assertEquals("https://a", defBrowser.back().toString());
		defBrowser.visit(URL3);
		assertEquals("https://a", defBrowser.back().toString());
		defBrowser.visit(URL4);
		assertEquals("https://a", defBrowser.back().toString());
		defBrowser.visit(URL5);
		assertEquals("https://a", defBrowser.back().toString());
	}
	
	@Test
	void backTestTwo()
	{
		defBrowser.visit(URL1);
		defBrowser.visit(URL2);
		defBrowser.visit(URL3);
		defBrowser.visit(URL4);
		defBrowser.visit(URL5);
		assertEquals("https://d", defBrowser.back().toString());
		assertEquals("https://c", defBrowser.back().toString());
		assertEquals("https://b", defBrowser.back().toString());
		assertEquals("https://a", defBrowser.back().toString());
	}
	
	@Test
	void forwardInvalidTestOne()
	{
		assertThrows(NoSuchElementException.class, () -> defBrowser.forward());
	}
	
	@Test
	void forwardInvalidTestTwo()
	{
		defBrowser.visit(URL1);
		assertThrows(NoSuchElementException.class, () -> defBrowser.forward());
		defBrowser.visit(URL2);
		assertThrows(NoSuchElementException.class, () -> defBrowser.forward());
		defBrowser.visit(URL3);
		assertThrows(NoSuchElementException.class, () -> defBrowser.forward());
		defBrowser.visit(URL4);
		assertThrows(NoSuchElementException.class, () -> defBrowser.forward());
		defBrowser.visit(URL5);
		assertThrows(NoSuchElementException.class, () -> defBrowser.forward());
	}
	
	@Test
	void forwardInvalidTestThree()
	{
		defBrowser.visit(URL1);
		defBrowser.visit(URL2);
		defBrowser.back();
		defBrowser.forward();
		assertThrows(NoSuchElementException.class, () -> defBrowser.forward());
	}
	
	@Test
	void forwardTestOne()
	{
		defBrowser.visit(URL1);
		defBrowser.visit(URL2);
		defBrowser.back();
		assertEquals("https://b", defBrowser.forward().toString());
	}
	
	@Test
	void forwardTestTwo()
	{
		defBrowser.visit(URL1);
		defBrowser.visit(URL2);
		defBrowser.visit(URL3);
		defBrowser.visit(URL4);
		defBrowser.visit(URL5);
		defBrowser.back();
		defBrowser.back();
		defBrowser.back();
		defBrowser.back();
		assertEquals("https://b", defBrowser.forward().toString());
		assertEquals("https://c", defBrowser.forward().toString());
		assertEquals("https://d", defBrowser.forward().toString());
		assertEquals("https://e", defBrowser.forward().toString());
	}
	
	@Test
	void historyEmptyTest()
	{
		var list = defBrowser.history();
		assertEquals(0, list.size());
		assertThrows(NoSuchElementException.class, () -> list.getFirst());
	}
	
	@Test
	void historyTestOne()
	{
		defBrowser.visit(URL1);
		defBrowser.visit(URL2);
		var history = defBrowser.history();
		assertEquals(history.get(0).toString(), "https://b");
		assertEquals(history.get(1).toString(), "https://a");
	}
	
	@Test
	void historyTestTwo()
	{
		defBrowser.visit(URL1);
		defBrowser.visit(URL2);
		defBrowser.visit(URL3);
		defBrowser.visit(URL4);
		defBrowser.visit(URL5);
		var history = defBrowser.history();
		assertEquals(history.get(0).toString(), "https://e");
		assertEquals(history.get(1).toString(), "https://d");
		assertEquals(history.get(2).toString(), "https://c");
		assertEquals(history.get(3).toString(), "https://b");
		assertEquals(history.get(4).toString(), "https://a");
	}
	
	/*
	 * This test ensures that history() does not modify the back and forward stacks
	 */
	@Test
	void historyCriterionTest()
	{
		defBrowser.visit(URL1);
		defBrowser.visit(URL2);
		defBrowser.visit(URL3);
		defBrowser.visit(URL4);
		defBrowser.visit(URL5);
		defBrowser.history();
		
		assertEquals("https://d", defBrowser.back().toString());
		assertEquals("https://c", defBrowser.back().toString());
		assertEquals("https://b", defBrowser.back().toString());
		assertEquals("https://a", defBrowser.back().toString());
	}

	@Test
	void historyConstructorTest()
	{
		defBrowser.visit(URL1);
		defBrowser.visit(URL2);
		defBrowser.visit(URL3);
		defBrowser.visit(URL4);
		defBrowser.visit(URL5);
		defBrowser.visit(URL6);
		defBrowser.visit(URL7);
		defBrowser.visit(URL8);
		defBrowser.visit(URL9);
		defBrowser.visit(URL10);
		var newBrowser = new WebBrowser(defBrowser.history());
		
		assertEquals("https://i", newBrowser.back().toString());
		assertEquals("https://h", newBrowser.back().toString());
		assertEquals("https://g", newBrowser.back().toString());
		assertEquals("https://f", newBrowser.back().toString());
		assertEquals("https://e", newBrowser.back().toString());
	}
}
