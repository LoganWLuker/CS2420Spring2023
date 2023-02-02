package class04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


//Generic class that holds an array of T's (T can be any object type)
public class GrowableArray<T> {
    //Due to how generics are implemented, you cannot create T objects
    //or T array directly.  We must create an array of Objects and cast
    //it to array of Ts.  This is about the only time I'll ever encourage you
    //to ignore/suppress warnings
    T[] arr =  (T[])(new Object[10]); //new T[10]; //not allowed
    //T var = new T(); not allowed
    int size = 0;
    void add(T obj){ //The compiler will check to make sure we pass appropriate types
        arr[size] = obj;
        size++;
    }

    public T get(int index){
        return arr[index]; //the compiler checks to make sure we really return a T
    }


    public static void main(String[] args){


        var arr = new GrowableArray<Integer>();
        arr.add(1); //int isn't object? Integer class autoboxing/auto-unboxing
        arr.add(2);
        arr.add(3);
        //arr.add("hello");
        arr.add(3);
        System.out.println(arr.get(2));

    }
}
