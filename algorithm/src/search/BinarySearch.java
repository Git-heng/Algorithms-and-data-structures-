package search;

public class BinarySearch {
//git
    public static <E extends Comparable<E>> int search(E[] arr, E target) {
        if (arr == null) {
            return -1;
        }
        return search(arr, target, 0, arr.length - 1);
//        return searchWithCirculation(arr, target, 0, arr.length - 1);
    }

    //在[start, end]中区间中递归查找k
    public static <E extends Comparable<E>> int search(E[] arr, E target, int start, int end) {

        if (start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (target.compareTo(arr[mid]) == 0) {
            return mid;
        } else if (target.compareTo(arr[mid]) > 0) { //[start,       mid,    (target)      end]
            return search(arr, target, mid + 1, end);
        } else {                                     //[start,   (target)    mid,          end]
            return search(arr, target, start, mid - 1);
        }
    }

    //在[start, end]中区间中循环查找k
    public static <E extends Comparable<E>> int searchWithCirculation(E[] arr, E target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target.compareTo(arr[mid]) == 0) {
                return mid;
            } else if (target.compareTo(arr[mid]) > 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        Integer[] integers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(search(integers, 9));
    }
}
