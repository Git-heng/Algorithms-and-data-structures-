package test;

public class ArrayGenerator {

    public static Integer[] getRandomArray(int n){
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int)(Math.random()*100000);
        }
        return arr;
    }
}
