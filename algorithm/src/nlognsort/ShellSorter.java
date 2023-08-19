package nlognsort;


import java.util.Arrays;
import java.util.Random;

/**
 * todo:不是很理解
 */
public class ShellSorter {

    public static <E extends Comparable<E>> void sort(E[] arr) {
        int gap = arr.length / 2;
        while (gap >= 1) {
            /* 例如步长gap = 4: [a1, b1, c1, d1]、[a2, b2, c2, d2]、[a3]
                参考插入排序，从gap开始， a2、b2、c2、d2、a3的处理，实际上是处理了a1、a2、a3插入排序，b1、b2插入排序...
             */
            for (int i = gap; i < arr.length; i++) {
                E tempE = arr[i];
                int cur = i; //从i开始，是因为把0到i-1的元素看成了有序区间（它们是各数组的第一个元素）
                //当前位置元素往前插
                while (cur - gap >= 0 && arr[cur - gap].compareTo(tempE) > 0) {
                    arr[cur] = arr[cur - gap];
                    cur -= gap;
                }
                arr[cur] = tempE;
            }
            gap = gap / 2;
        }
    }


    public static void main(String[] args) {
        int n = 20;
        Integer[] integer = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            integer[i] = random.nextInt(n);
        }
        System.out.println(Arrays.toString(integer));

        sort(integer);
        System.out.println(Arrays.toString(integer));
        for (int i = 0; i + 1 < integer.length; i++) {
            if (integer[i] > integer[i + 1]) {
                System.out.println("error: " + i);
            }
        }
    }

}