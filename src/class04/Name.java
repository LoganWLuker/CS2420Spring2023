package class04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//Making this implement Comparable<Name> mean's there's an "obvious natural ordering"
//of Name objects that's a good default when sorting them
public class Name implements Comparable<Name>{
    public String first, last;

    public Name(String first, String last){
        this.first = first;
        this.last = last;
    }


    @Override
    public String toString(){
        return first + " " + last;
    }


    @Override
    public boolean equals(Object other){
        if(! (other instanceof Name) ){
            return false;
        }
        Name otherName = (Name)other;
        return first.equals(otherName.first) && last.equals(otherName.last);
    }


    public static void main(String[] args){
        ArrayList<Name> names = new ArrayList<>(Arrays.asList(new Name("Ben", "Jones"),
                new Name("Andrew", "Jones"),
                new Name("Erin", "Parker"),
                new Name("Danny", "Kopta"),
                new Name("Peter", "Jensen")));

        //sorts using the Name.compareTo, because Name implements Comparable<Name>
        Collections.sort(names);
        for(var name : names){
            System.out.println(name);
        }

        System.out.println();

        //calls below use an explicit Comparator object to sort by first name only

        //use a Comparator defined in a class "like normal"
        //Collections.sort(names, new FirstNameComparator());

        //anonymous, inline class. This defines a nameless class and creates
        //an object of that type
        Collections.sort(names, new Comparator<Name>(){
            @Override
            public int compare(Name o1, Name o2) {
                return o1.first.compareTo(o2.first);
            }
        });

        //same as previous but uses the "lambda function" syntax
        //The compiler figures out what interface this should be and
        //what method is being implemented by looking at the type
        //of the 2nd parameter to Sort.  In this case it infers that the
        //lambda method here is Comparator<Name>.compare(Name, Name)
        Collections.sort(names, (Name n1, Name n2) -> {
            return n1.first.compareTo(n2.first);
        });

        //same as previous but the compiler infers that n1 and n2 parameters
        //are both of type Name.  The expression after the -> is the return
        //value of the method
        Collections.sort(names, (n1, n2) -> n1.first.compareTo(n2.first));

        //names sorted by first name only, using the Comparator object
        for(var name : names){
            System.out.println(name);
        }

    }

    @Override
    public int compareTo(Name other) {
        int lastComparison = last.compareTo(other.last);
        if(lastComparison == 0) { //last names are the same
            return first.compareTo(other.first);
        } else {
            return lastComparison;
        }
    }
}
