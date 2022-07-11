package search;

public class SearchUtils {

    public static void main(String[] args) {
        int n = 1000;
        int k = 55;
        Integer[] arr = new Integer[n];
        int[] arr1 = new int[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += i;
            arr[i] = total;
            arr1[i] = total;
        }
        double time = System.nanoTime() / 1000000.0;
        System.out.println("OrderSearch.find(arr, k): " + OrderSearch.find(arr, k));
        double time1 = System.nanoTime() / 1000000.0;
        System.out.println(time1 - time);

        System.out.println("BinarySearch.find(arr, k): " + BinarySearch.search(arr, k));
        double time2 = System.nanoTime() / 1000000.0;
        System.out.println(time2 - time1);

        System.out.println("BinarySearch.insertFind(arr, k): " + InsertSearch.insertFind(arr1, k));
        double time3 = System.nanoTime() / 1000000.0;
        System.out.println(time3 - time2);

        System.out.println("FibonacciSearch.find(arr, k): " + FibonacciSearch.find(arr1, k));
        double time4 = System.nanoTime() / 1000000.0;
        System.out.println(time4 - time3);
    }
}