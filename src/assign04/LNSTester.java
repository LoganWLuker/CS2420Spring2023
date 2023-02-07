package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LNSTester 
{

	public static void main(String[] args) 
	{

	}
	
	@BeforeEach
	void setup()
	{
		
	}
	
	// Tests -----------------

	@Test
	void largestNumberSimpleTest()
	{
		Integer[] arr1 = {54, 31, 39};
		Integer[] arr2 = {1, 1, 1, 9, 1};
		
		assertEquals(LargestNumberSolver.findLargestNumber(arr1), new BigInteger("543931"));
		assertEquals(LargestNumberSolver.findLargestNumber(arr2), new BigInteger("91111"));
	}
}
