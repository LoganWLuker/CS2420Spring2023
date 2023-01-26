package assign02;
import java.util.ArrayList;
public class CS2420Student extends UofUStudent
{
	private EmailAddress email;
	private ArrayList<Double> assignmentScores = new ArrayList<Double>();
	private ArrayList<Double> examScores = new ArrayList<Double>();
	private ArrayList<Double> labScores = new ArrayList<Double>();
	private ArrayList<Double> quizScores = new ArrayList<Double>();
	public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo)
	{
		super(firstName,lastName,uNID);
		this.email = contactInfo;
	}
	public EmailAddress getContactInfo()
	{
		return this.email;
	}
	/**
	 * add a score to the student
	 * @param score
	 * @param category
	 */
	public void addScore(double score, String category)
	{
		switch (category) {
			case "assignment": this.assignmentScores.add(score);
			break;
			case "exam" : this.examScores.add(score);
			break;
			case "lab" : this.labScores.add(score);
			break;
			case "quiz" : this.quizScores.add(score);
			break;
			default: break;
		}
	}
	/**
	 * compute the final score based on the scores and types
	 * @return final score as a double
	 */
	public double computeFinalScore() 
	{
		if(assignmentScores.size() == 0 || examScores.size() == 0 || labScores.size() == 0 || quizScores.size() == 0)
			return 0.0;
		double assignAv = 0;
		double examAv = 0;
		double labAv = 0;
		double quizAv = 0;
		double totalScore = 0;
		
		for(int i = 0; i < assignmentScores.size(); i++)
			assignAv += assignmentScores.get(i);
		assignAv = assignAv/assignmentScores.size();
		
		for(int i = 0; i < examScores.size(); i++)
			examAv += examScores.get(i);
		examAv = examAv/examScores.size();
		if(examAv < 65)
			return examAv;
		
		for(int i = 0; i < labScores.size(); i++)
			labAv += labScores.get(i);
		labAv = labAv/labScores.size();
		
		for(int i = 0; i < quizScores.size(); i++)
			quizAv += quizScores.get(i);
		quizAv = quizAv/quizScores.size();
		
		totalScore = (0.40*assignAv) + (0.40*examAv) + (0.10*labAv) + (0.10*quizAv);
		return totalScore;
	}
	/**
	 * computes the final grade based on the syllabus
	 * @return the final letter grade as a string
	 */
	public String computeFinalGrade()
	{
		if(assignmentScores.size() == 0 || examScores.size() == 0 || labScores.size() == 0 || quizScores.size() == 0)
			return "N/A";
		double totalScore = this.computeFinalScore();
		int[] gradeScores = {60,63,67,70,73,77,80,83,87,90,93,101};
		String[] gradeLetters = {"E","D-","D","D+","C-","C","C+","B-","B","B+","A-","A"};
		int i;
		for(i = 0; i < gradeScores.length; i++)
		{
			if(totalScore < gradeScores[i])
				break;
		}
		return gradeLetters[i];
	}
}
