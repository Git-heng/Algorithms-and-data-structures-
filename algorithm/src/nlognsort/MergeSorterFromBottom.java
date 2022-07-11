package nlognsort;

import java.util.Arrays;

/**
 * 自底向上的归并排序，采用for循环，
 * 难度⭐⭐⭐⭐
 * 动画想象：先将最碎的区间归并，再在稍大的区间将小空间归并...以此类推
 * （如果是10个元素，那么最大的区间相当于16，只是实际左边8个，右边2个）
 */
public class MergeSorterFromBottom {

    public static <E extends Comparable<E>> void sort(E[] arr) {
        E[] temp = Arrays.copyOfRange(arr, 0, arr.length);

        /*错误的思路（很绕，绕不出来）
          循环的条件，有时候很难把控边界，到底什么时候停下来，我目前觉得还是要把问题的本质转化成语言
          比如这里，到底还for循环的终止条件是什么呢？步长和数组长度成怎样的关系就可以了呢?
          step <= arr.length? step < arr.length*2? step <= arr.length*2?
          简化情景为一句话，直接写出公式
          循环的操作到哪停止：该步长分成两个空间，数组长度超出了其中一个继续操作，直到数组小于其中一个，那也不用归并了
          比如长度8step为8,一步走完可以停了；长度9step为8，一步没走完不能停；长度16step为8，一步没走完，执行依次，step16，一次走完停下
          arr.length - step > 0
        */

        /*设置要操作的变量，而不是设置操作后的变量。此处理解为设置要操作的小区间长度为step，而不是操作后的大区间长度为step
          所以，小区间的长度比数组小的时候就继续，等于数组长度时再操作相当于那数组与空数组归并，无意义
        */
        for (int step = 1; step < arr.length; step *= 2) {
            //i也就是两个小区间的长度,即2step（[i,i+step-1],[i+step,i+step+step-1]）的元素交给归并方法进行归并，
            //然后移动到下两个小区间的开始，即i+2step
            //两个区间两个区间进行操作，如果还剩下一个区间则无需进行操作。如何判断还有两个区间：即第二个区间的左边界还在数组范围内：i+step<arr.length
            for (int i = 0; i+step< arr.length; i = i + step * 2) {
                //对该步长的两个子区间进行归并，如果该步长的一步已跨出区间，传入最后的这一小段
                if (i + 2*step - 1 >= arr.length) {
                    mergeSort(arr, i, arr.length - 1, i+step-1, temp);
                } else {
                    mergeSort(arr, i, i + 2*step - 1, i+step-1, temp);
                }

            }
        }
    }

    //相当于合并两个数组 [start, mid],[mid+1, end]
    private static <E extends Comparable<E>> void mergeSort(E[] arr, int start, int end, int mid, E[] temp) {

        System.arraycopy(arr, start, temp, start, end + 1 - start);


        //temp数组相对于arr的index存在start的偏移量
        int left = start;
        int right = mid + 1;

        for (int i = start; i <= end; i++) {

            if (left > mid) { //左标记超出左数组
                arr[i] = temp[right];
                right++;
            } else if (right > end) {
                arr[i] = temp[left];
                left++;
            } else if (temp[left].compareTo(temp[right]) <= 0) {
                arr[i] = temp[left];
                left++;
            } else {
                arr[i] = temp[right];
                right++;
            }
        }

    }

    public static void main(String[] args) {
        Integer[] a = {9, 4, -1, 6, 7, 3, 12, 4, 9, 3};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
