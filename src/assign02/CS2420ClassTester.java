package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for CS2420Class.
 * 
 * @author Erin Parker and ??
 * @version January 20, 2022
 */
public class CS2420ClassTester {

	private CS2420Class emptyClass, verySmallClass, smallClass, largeClass;
	private CS2420Student testStudent;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		emptyClass = new CS2420Class();
		
		verySmallClass = new CS2420Class();
		verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Drew", "Hall", 2323232, new EmailAddress("howdy", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Riley", "Nguyen", 4545454, new EmailAddress("hello", "gmail.com")));

		smallClass = new CS2420Class();
		smallClass.addAll("src/assign02/a_small_2420_class.txt");
		
		largeClass = new CS2420Class();
		testStudent = new CS2420Student("Logan","Luker", 12345678,new EmailAddress("feefoo","gmail.com"));
		String randFirst,randLast;
		int randUNID;
		EmailAddress randEmail;
		
		for(int i = 0; i<1000; i++)
		{
			if(i == 250)
			{
				largeClass.addStudent(testStudent);
				continue;
			}
			randFirst = ((Double)(Math.random()*9999999)).toString();
			randLast = ((Double)(Math.random()*9999999)).toString();
			randUNID = (int)(Math.random()*9999999);
			randEmail = new EmailAddress(((Double)(Math.random()*9999999)).toString(), "gmail.com");
			largeClass.addStudent(new CS2420Student(randFirst,randLast,randUNID,randEmail));
		}
	}
	
	// Empty CS 2420 class tests --------------------------------------------------------------------------

	@Test
	public void testEmptyLookupUNID() {
		assertNull(emptyClass.lookup(1234567));
	}
	
	@Test
	public void testEmptyLookupContactInfo() {
		ArrayList<CS2420Student> students = emptyClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(0, students.size());
	}
	
	@Test
	public void testEmptyAddScore() {
		// ensure no exceptions thrown
		emptyClass.addScore(1234567, 100, "assignment");
	}

	@Test
	public void testEmptyClassAverage() {
		assertEquals(0, emptyClass.computeClassAverage(), 0);
	}
	
	@Test
	public void testEmptyContactList() {
		ArrayList<EmailAddress> contactList = emptyClass.getContactList();
		assertEquals(0, contactList.size());
	}

	// Very small CS 2420 class tests --------------------------------------------------------------------

	@Test
	public void testVerySmallLookupUNID() {
		UofUStudent expected = new UofUStudent("Drew", "Hall", 2323232);
		CS2420Student actual = verySmallClass.lookup(2323232);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testVerySmallLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
		ArrayList<CS2420Student> actualStudents = verySmallClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}
	
	@Test
	public void testVerySmallAddDuplicateStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, 
				new EmailAddress("hi", "gmail.com")));
		assertFalse(actual);
	}
	
	@Test
	public void testVerySmallAddNewStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010100, 
				new EmailAddress("hi", "gmail.com")));
		assertTrue(actual);		
	}

	@Test
	public void testVerySmallStudentFinalScore0() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}
	
	@Test
	public void testVerySmallStudentFinalGradeNA() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		assertEquals("N/A", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalScore() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(55, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals(55, student.computeFinalScore(), 0.001);
	}
	
	@Test
	public void testVerySmallStudentFinalGrade() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals("B", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentComputeScoreTwice() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		student.computeFinalScore();   
		student.addScore(70, "lab");
		student.addScore(54.5, "exam");				
		assertEquals(64.75, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testVerySmallUpdateName() {
		verySmallClass.lookup(1010101).updateName("John", "Doe");
		ArrayList<CS2420Student> students = verySmallClass.lookup(new EmailAddress("hi", "gmail.com"));
		assertEquals("John", students.get(0).getFirstName());
		assertEquals("Doe", students.get(0).getLastName());
	}	
	
	// Small CS 2420 class tests -------------------------------------------------------------------------

	@Test
	public void testSmallLookupContactInfo() {
		UofUStudent expectedStudent1 = new UofUStudent("Kennedy", "Miller", 888888);
		UofUStudent expectedStudent2 = new UofUStudent("Taylor", "Miller", 999999);

		ArrayList<CS2420Student> actualStudents = smallClass.lookup(new EmailAddress("we_love_puppies", "geemail.com"));
		assertEquals(2, actualStudents.size());
		assertTrue(actualStudents.contains(expectedStudent1));
		assertTrue(actualStudents.contains(expectedStudent2));
	}
	
	@Test
	public void testSmallGetContactList() {
		ArrayList<EmailAddress> actual = smallClass.getContactList();
		assertEquals(9, actual.size());
	}
		
	@Test
	public void testSmallStudentFinalScore() {
		CS2420Student student = smallClass.lookup(333333);
		assertEquals(95.5345, student.computeFinalScore(), 0.001);
	}
		
	@Test
	public void testSmallComputeClassAverage() {
		assertEquals(78.356, smallClass.computeClassAverage(), 0.001);
	}
	// More Tests --------------------------------------------------------------------------------------
	
	@Test
	public void testVerySmallStudentFinalGradeEdgeCase1() 
	{
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(0, "assignment");
		student.addScore(75, "exam");
		student.addScore(0, "lab");
		student.addScore(0, "quiz");
		student.addScore(0, "assignment");
		student.addScore(0, "lab");
		student.addScore(0, "quiz");
		assertEquals("E", student.computeFinalGrade());
	}
	@Test
	public void testVerySmallStudentFinalGradeEdgeCase2() 
	{
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(100, "assignment");
		student.addScore(100, "exam");
		student.addScore(100, "lab");
		student.addScore(100, "quiz");
		student.addScore(100, "assignment");
		student.addScore(100, "lab");
		student.addScore(100, "quiz");
		assertEquals("A", student.computeFinalGrade());
	}
	@Test
	public void testSmallStudentFinalGrade() 
	{
		CS2420Student student = smallClass.lookup(111111);
		assertEquals("B", student.computeFinalGrade());
	}
	// Large CS 2420 class tests -------------------------------------------------------------------------
	@Test
	public void testLargeStudentFinalScore() 
	{
		CS2420Student student = largeClass.lookup(12345678);
		student.addScore(86.5, "assignment");
		student.addScore(55, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");	
		assertEquals(55, student.computeFinalScore(), 0.001);
	}
	@Test
	public void testLargeStudentFinalScore0() 
	{
		CS2420Student student = largeClass.lookup(12345678);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}
	@Test
	public void testLargeLookupUNID() 
	{
		UofUStudent expected = new CS2420Student("Logan","Luker", 12345678,new EmailAddress("feefoo","gmail.com"));
		CS2420Student actual = largeClass.lookup(12345678);
		assertEquals(expected, actual);
	}
}