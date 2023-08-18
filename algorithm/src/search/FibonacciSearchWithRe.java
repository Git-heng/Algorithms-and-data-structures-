package search;

import java.util.Arrays;

public class FibonacciSearchWithRe {

    public static int find(int[] arr, int a) {
        if (arr == null) {
            return -1;
        }
        int[] fibo = new int[100];
        int i;//斐波那契数列的有效长度
        fibo[0] = 1;
        fibo[1] = 1;
        for (i = 1; fibo[i] - 1 < arr.length; ) {
            i++;
            fibo[i] = fibo[i - 1] + fibo[i - 2];
        }
        int[] arrCopy = Arrays.copyOf(arr, fibo[i] - 1);
        return find(arrCopy, 0, arrCopy.length - 1, fibo, i, a);
    }

    private static int find(int[] arrCopy, int start, int end, int[] fib, int i, int a) {
        if (i < 1) {
            return -1;
        }
        int mid = start + fib[i - 1] - 1;
        if (mid < start || mid > end) {
            return -1;
        }
        if (a < arrCopy[mid]) {
            return find(arrCopy, start, mid - 1, fib, i - 1, a);
        } else if (a > arrCopy[mid]) {
            return find(arrCopy, mid + 1, end, fib, i - 2, a);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {

    }
}
