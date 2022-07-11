package search;


/**
 * 在数组中寻找大于target的...
 * 小区间左边往右边扫,[0, arr.length],mid默认
 */
public class BinarySearchUpper {


    public static <E extends Comparable<E>> int search(E[] arr, E target) {
        if (arr == null) {
            return -1;
        }
        return search(arr, target, 0, arr.length);
    }

    //由于数字可以大于有序数组的最大值，所以原数组区间[start, end-1]，虚拟数组区间[[start, end-1] ---- end]
    //这样大于最大值时，可以返回end+1,
    public static <E extends Comparable<E>> int search(E[] arr, E target, int start, int end) {
        //start == end 时说明已找到该点
        if (start >= end) {
            return start;
        }
        int mid = start + (end - start) / 2;
        //[start, mid, end],大于target时，该值可能是最小值，带上该点继续往左找大于target的最小值
        if (arr[mid].compareTo(target) > 0) {
            return search(arr, target, start, mid);
        } else {
            return search(arr, target, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        Integer[] integer = {0, 1, 2, 3, 4, 4, 6, 7, 8, 8};
        System.out.println(search(integer, 3));

    }

}
