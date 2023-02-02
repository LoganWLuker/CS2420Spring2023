package class04;

import java.util.Comparator;

//Used to compare names without using the "natural ordering" which
//Name's compareTo method provides
public class FirstNameComparator implements Comparator<Name> {

    @Override
    public int compare(Name name1, Name name2) {
        return name1.first.compareTo(name2.first);
    }
}
