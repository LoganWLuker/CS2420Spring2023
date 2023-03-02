package assign06;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WebBrowserTest 
{
	private URL URL1=null, URL2=null, URL3=null, URL4=null, URL5=null;
	private WebBrowser defBrowser;
	@BeforeEach
	void setUp()
	{
		try {
			URL1 = new URL("https://a");
			URL2 = new URL("https://b");
			URL3 = new URL("https://c");
			URL4 = new URL("https://d");
			URL5 = new URL("https://e");
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL");
		}
		
		defBrowser = new WebBrowser();
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

}
