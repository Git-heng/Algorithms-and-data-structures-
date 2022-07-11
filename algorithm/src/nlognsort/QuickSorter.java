package nlognsort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;


/**
 * 快速排序
 * 难度⭐⭐⭐⭐
 * 动画想象：
 * 备注：带了标记的是优化点
 */

public class QuickSorter {

    public <E extends Comparable<E>> void quickSort(E[] arr) {
        Random random = new Random();
        if (arr != null) {
            quickSort(arr, 0, arr.length - 1, random);
        }
    }

    //先将第一个元素放到正确的位置，然后分别对[start, p-1],[p+1, end]进行quickSort
    private <E extends Comparable<E>> void quickSort(E[] arr, int start, int end, Random random) {
        if (start >= end) {
            return;
        }
        int randomNum = random.nextInt(end+1-start);
        swap(arr, start, randomNum);
//        swap(arr, start, (start+end)/2);
        int p = start;
        int left = start;
        //遍历数组[start, end],小于p的值放在区间[start+1, left],大于p的值放在[left+1, right],当前点right+1
        //当前要处理的元素在大值区间的右1个，即right+1
        for (int right = start; right + 1 <= end; right++) {
            //大于p处元素，大值区间扩展即可，right++,只有当小于p处元素时，才特别处理:
            // 把这个小值和大值区间区间左边界元素交换，这样right+1位置是大值,left+1位置是小值，进行left++，right++后维护成功
            if (arr[right + 1].compareTo(arr[p]) < 0) {
                swap(arr, left+1, right+1);
                left++;
            }
        }
        //遍历完之后，将p元素的位置与小值区间的最右边元素交换（p小大->小p大），记录p值
        swap(arr, left, start);
        p = left;

        quickSort(arr, start, p - 1, random);
        quickSort(arr, p + 1, end, random);
    }


    public <E extends Comparable<E>> void swap(E[] arr, int a, int b) {
        E tempE = arr[a];
        arr[a] = arr[b];
        arr[b] = tempE;
    }

    public static void main(String[] args) {
        QuickSorter quickSorter = new QuickSorter();
        Integer[] a = new Integer[2];
        for (int i = 0; i < 2; i++) {
            a[i] = i;
        }
//        quickSorter.beSpecialArray(a, 0, 50000-1);
//        System.out.println(Arrays.toString(a));

        System.out.println(Arrays.toString(a));
        quickSorter.quickSort(a);
    }

    public void beSpecialArray(Integer[] arr, int start, int end){
        if (start>=end){
            return;
        }
        beSpecialArray(arr, start+1, end);
        int mid = (end+start)/2;
        swap(arr, start, mid);
    }


}
