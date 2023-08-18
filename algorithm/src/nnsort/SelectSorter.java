package nnsort;

import java.util.Arrays;


/**
 * 选择排序
 * 难度⭐
 * 动画想象：找到最小的数据放在第一位，找到第二小的数据放在第二位...
 * 稳定性，是n²复杂度排序中唯一不稳定的，例如8、8、4，第一轮8与4交换，导致两个8的前后顺序变了
 */
public class SelectSorter {

    public static <E extends Comparable<E>> void sort(E[] arr) {

        //i为当前处理位置，j为对比元素的位置
        for (int i = 0; i + 1 < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].compareTo(arr[j]) > 0) {
                    E tempE = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tempE;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {9, 4, -1, 6, 7, 3, 12, 4, 9, 3};
        sort(a);
        System.out.println(Arrays.toString(a));

    }

}
