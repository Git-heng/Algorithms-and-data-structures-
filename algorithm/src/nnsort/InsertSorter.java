package nnsort;

import java.util.Arrays;


/**
 * 插入排序
 * 难度⭐⭐
 * 动画想象：把一个APP插入到一个位置，原位置和目标位置中间的APP依次右移，
 * 在目标位置腾出一块空间，然后将原位置的APP放到目标位置
 */
public class InsertSorter {

    public static <E extends Comparable<E>> void sort(E[] arr) {

        //对于第i个位置的元素，依次对比该元素左边的元素（已排序空间），任何一个大于他的值都应该往右移动一格
        for (int i = 0; i < arr.length; i++) {
            E tempE = arr[i];
            int j;
            for (j = i; j - 1 >= 0 && arr[j - 1].compareTo(tempE) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = tempE;
        }

    }



    public static void main(String[] args) {
        Integer[] a = {9, 4, 1, 6, 7, 3, 12, 4, 9, 3, -9};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
