package class04;

public class Student {
    private String name, uid;
    private double costPerCredit = 500, numCredits = 12; //default values

    public Student(String name, String uid){
        this.name = name;
        this.uid = uid;
    }

    public double getTuitionCost(){
        return costPerCredit*numCredits;
    }
    public void printTranscript(){
        System.out.println("Transcript for student: " + name + " " + uid + " ...");
    }
}
