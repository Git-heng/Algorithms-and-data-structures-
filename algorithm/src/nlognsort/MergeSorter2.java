package nlognsort;

import java.util.Arrays;

public class MergeSorter2 {


    private static StringBuilder stringBuilder = new StringBuilder();

    public static <E extends Comparable<E>> void sort(E[] arr) {
        //合并过程中原数组数据会变动，用temp存下初始数据位置，处理好的数据放到arr
        E[] temp = Arrays.copyOfRange(arr, 0, arr.length);
        sort(arr, 0, arr.length, temp);
    }

    //排序[start, end)
    private static <E extends Comparable<E>> void sort(E[] arr, int start, int end, E[] temp) {
//        不同层级stringBuilder带有不同长度的“-”
        String s = stringBuilder.append("----").toString();
        System.out.printf("%s-before mergeSort: [%d - %d],%s \n",s,start, end , Arrays.toString(arr));
        if (start >= end - 1) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(arr, start, mid, temp);
        sort(arr, mid, end, temp);
        //如果左边最大值，小于右边最小值，那么数组已经排序完成，无需继续归并排序
        if (arr[mid-1].compareTo(arr[mid ]) > 0) {
            mergeSort(arr, start, end, mid, temp);
        }
        System.out.printf("%s-after mergeSort: [%d - %d],%s \n", s, start, end , Arrays.toString(arr));
    }

    //相当于合并两个数组 [start, mid),[mid, end)
    private static <E extends Comparable<E>> void mergeSort(E[] arr, int start, int end, int mid, E[] temp) {
        System.arraycopy(arr, start, temp, start, end - start);
        int left = start;
        int right = mid;
        for (int i = start; i < end; i++) {
            if (left >= mid) { //左标记超出左数组
                arr[i] = temp[right++];
            } else if (right >= end) { //右标记超出右数组
                arr[i] = temp[left++];
            } else if (temp[left].compareTo(temp[right]) <= 0) { //左边元素小于等于右边，拿取左边
                arr[i] = temp[left++];
            } else { //拿取右边元素
                arr[i] = temp[right++];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {9, 4, -1, 6, 7, 3, 12, 4, 9, 3};
        sort(a);
        System.out.println(Arrays.toString(a));

    }

}
