package nlognsort;


import java.util.Arrays;

/**
 * 自上而下的归并排序，采用递归
 * 难度⭐⭐⭐
 * 动画想象：要归并大区间，先将大区间分成两个小区间，分别归并这两个小区间;
 * 要归并小区间，先将小区间分成两个更小的区间，分别...如此操作直到最小单位
 * <p>
 * 复杂度低是因为将遍历时获取到的信息存起来了，利用空间换取时间，是out-place的排序算法
 */
public class MergeSorter {

    private static StringBuilder stringBuilder = new StringBuilder();

    public static <E extends Comparable<E>> void sort(E[] arr) {
        //合并过程中原数组数据会变动，用temp存下初始数据位置，处理好的数据放到arr
        E[] temp = Arrays.copyOfRange(arr, 0, arr.length);
        sort(arr, 0, arr.length - 1, temp);
    }

    //对数组的某一区间进行排序
    private static <E extends Comparable<E>> void sort(E[] arr, int start, int end, E[] temp) {
        //不同层级stringBuilder带有不同长度的“-”
//        String s = stringBuilder.append("----").toString();
//        System.out.printf("%s-before mergeSort: [%d - %d],%s \n",s,start, end , Arrays.toString(arr));
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(arr, start, mid, temp);
        sort(arr, mid + 1, end, temp);
        //如果左边最大值，小于右边最小值，那么数组已经排序完成，无需继续归并排序
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            mergeSort(arr, start, end, mid, temp);
        }
//        System.out.printf("%s-after mergeSort: [%d - %d],%s \n", s, start, end , Arrays.toString(arr));
    }

    //相当于合并两个数组 [start, mid],[mid+1, end]
    private static <E extends Comparable<E>> void mergeSort(E[] arr, int start, int end, int mid, E[] temp) {
        System.arraycopy(arr, start, temp, start, end + 1 - start);
        int left = start;
        int right = mid + 1;
        for (int i = start; i <= end; i++) {
            if (left > mid) { //左标记超出左数组
                arr[i] = temp[right++];
            } else if (right > end) { //右标记超出右数组
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