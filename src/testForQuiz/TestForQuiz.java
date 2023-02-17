package testForQuiz;

public class TestForQuiz {

	private static void updateArray(int[] arr) 
	{
	   arr = new int[arr.length];
	   for(int i = 0; i < arr.length; i++)
	      arr[i]++;
	}

	public static void main(String[] args) 
	{
	   int[] values = { 5, 2, 3, 4, 5 };
	   updateArray(values);
	   System.out.print(values[0]);
	}
}