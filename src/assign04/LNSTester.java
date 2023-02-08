package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * 
 * @author Bruce Crockett and Logan Luker
 * @version February 6th, 2023
 */
public class LNSTester 
{
	
	@BeforeEach
	void setup()
	{
		Integer[] emptyArr = {};
	}
	
	// Development Tests ----------------------------------------

	@Test
	void largestNumberBasicTest()
	{
		Integer[] arr1 = {54, 31, 39};
		Integer[] arr2 = {1, 1, 1, 9, 1};
		Integer[] arr3 = {11, 67, 79, 7, 22, 13};
		
		assertEquals(LargestNumberSolver.findLargestNumber(arr1), new BigInteger("543931"));
		assertEquals(LargestNumberSolver.findLargestNumber(arr2), new BigInteger("91111"));
		assertEquals(LargestNumberSolver.findLargestNumber(arr3), new BigInteger("79767221311"));
	}
	
	// Empty Tests -----------------------------------------------
	
	@Test
	void findLargestNumberEmptyTest() 
	{
		
	}
	
	@Test
	void findLargestIntEmptyTest()
	{
		
	}
	
	@Test
	void findLargestLongEmptyTest()
	{
		
	}
}
