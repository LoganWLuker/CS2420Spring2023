package assign08;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class SpellCheckerTest
{
	private BinarySearchTree<String> emptyTree;
	private BinarySearchTree<String> smallTree;
	private BinarySearchTree<String> largeTree;
	@BeforeEach
	void setUp()
	{
		emptyTree = new BinarySearchTree<String>();
		smallTree = new BinarySearchTree<String>();
		smallTree.add("Ab");
		smallTree.add("Bc");
		smallTree.add("Cd");
	}
	//BinarySearchTree Tests -----------------------------------
	@Test
	void addEmptyTreeContainsTest()
	{
		emptyTree.add("Hello");
		assertTrue(emptyTree.contains("Hello"));
	}
	@Test
	void addEmptyTreeSizeTest()
	{
		emptyTree.add("Hello");
		assertEquals(1,emptyTree.size());
	}
}
