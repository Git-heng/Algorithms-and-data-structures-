package nlognsort;


import java.util.Arrays;
import java.util.Random;

public class ShellSorter {

    public static <E extends Comparable<E>> void sort(E[] arr) {
        int n = arr.length / 2;
        while (n >= 1) {
            /* 例如步长n = 4: [a1, b1, c1, d1]、[a2, b2, c2, d2]、[a3, b3, c3, d3]
             * 从n开始，也就是a2对比a1，b2对比b1,...a3对比a2... 进行插入排序
             */
            for (int i = n; i + n < arr.length; i++) {
                E tempE = arr[i];
                int j;
                //当前位置元素往前插
                for (j = i; j - n >= 0 && arr[j].compareTo(arr[j - n]) < 0; j -= n) {
                    arr[j] = arr[j - n];
                }
                arr[j] = tempE;
            }
            n = n / 2;
        }
    }


    public static void main(String[] args) {
        int n = 1000;
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
    //TODO:报错

}