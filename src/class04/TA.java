package class04;

public class TA extends Student {
    //extra TA member vars + constructor
    private String courseAssignment;
    public TA(String name, String uid, String courseAssignment) {
        super(name, uid);
        this.courseAssignment = courseAssignment;
    }
    //a TA-only method
    public void runLabSection(int section){
        System.out.println("running lab " + section);
    }

    public double getTuitionCost(){
        //call the Student version, then take off 10%
        //without super, this is infinite recursion and causes a stack overflow
        return super.getTuitionCost()*0.9;
    }

}
