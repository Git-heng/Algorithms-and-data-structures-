package search;

public class OrderSearch {

    public static <E extends Comparable<E>> int find(E[] arr, E e) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(e) == 0) {
                return i;
            }
        }
        return -1;
    }

}

