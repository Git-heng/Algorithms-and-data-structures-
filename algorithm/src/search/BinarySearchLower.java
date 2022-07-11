package search;

/**
 * 在数组中寻找小于target的...
 * 在从小到大排序的数组中，需要往左边找
 * 小区间从右边往左边扫，[-1, arr.length-1], mid需要取偏后的位置
 */
public class BinarySearchLower {

    public static <E extends Comparable<E>> int search(E[] arr, E target) {
        if (arr != null) {
            return search(arr, target, -1, arr.length - 1);
        }
        return -1;
    }

    //
    public static <E extends Comparable<E>> int search(E[] arr, E target, int start, int end) {
        if (start > end) {
            return -1;
        }
        //对于左端来说，使用start + (end - start) / 2,有一步会在[-1,0]区间得知mid = -1
        // 但实际上我们关心的并不是左端,而是此时右端点的情况，所以end+1,中值取右端点
        /**
         * 这里的定义实际上是根据数组的方向来的，如果你想往目标值左边找元素，那么中点（当前要处理的点 ）应该取后面的值
         */
        int mid = start + (end + 1 - start) / 2;
        if (start == end) {
            return start;
        }

        if (arr[mid].compareTo(target) < 0) {
            return search(arr, target, mid, end); //左端点会保留
        } else {
            return search(arr, target, start, mid - 1); //右端点会丢弃
        }
    }


    public static void main(String[] args) {
        Integer[] integer = {1, 1, 3, 3, 5, 5, 7, 7};
        System.out.println(search(integer, 100));
    }
}