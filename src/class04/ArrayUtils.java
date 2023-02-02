package class04;

import java.util.Arrays;

public class ArrayUtils {

    //Generic method, works arrays of any kind of object
    public static <Type> void reverse(Type[] arr){
        for(int i = 0; i < arr.length/2; i++){
            Type tmp = arr[i]; //Type gets filled with whatever the "base type" of the array you pass is
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;
        }
    }


    public static void main(String[] args){

        //here Type is Double inside the method
        Double[] arr = new Double[]{1.0,2.0,3.0,4.0};
        reverse(arr);
        System.out.println(Arrays.toString(arr));

        //here Type is String inside the method
        String[] strings = new String[]{"hello", "2420"};
        reverse(strings);
        System.out.println(Arrays.toString(strings));

    }
}
