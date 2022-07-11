package nnsort;

import java.util.Arrays;


/**
 * 冒泡排序
 * 难度⭐
 * 动画想象：参与一个冒泡淘汰赛，第一名与第二名比，输了的第二，
 * 然后第二与第三比...每轮淘汰最后一位
 */
public class BubbleSorter {

    //冒泡，从大到小还是从小到大取决于传递大值到末尾还是传递小值到末尾
    //经过i轮，就会有i个拍好序的元素在末尾，它们是冒上来的泡泡
    //[0   ->  左端点(arr.length - i)  ->    (包含末尾i个元素)]
    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j + 1 < arr.length - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    E tempE = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tempE;
                }
            }
        }
    }

    //i为标记点， [0, i](i, arr.length-1]
    public static <E extends Comparable<E>> void sort0(E[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j + 1 <= i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    E tempE = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tempE;
                }
            }
        }
    }

    /**
     * 优化点1：如果在某一圈冒泡过程中，没有进行任何元素交换操作，可以认定此时数组已排序完成
     */
    public static <E extends Comparable<E>> void sort1(E[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            boolean isSorted = true;
            for (int j = 0; j + 1 <= i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    E tempE = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tempE;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    /**
     * 优化点2：由优化点1扩展，如果在某一圈的最后某一部分没有进行元素交换，可以认为该部分已经有序
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            int sortedPosition = i;
            for (int j = 0; j + 1 <= i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    E tempE = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tempE;
                    sortedPosition = j + 1;
                }
            }
            i = sortedPosition;
        }
    }

    public static <E extends Comparable<E>> void sortFromEnd(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int sortedPosition = arr.length - 1;
            for (int j = arr.length - 1; j - 1 >= i; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    E tempE = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tempE;
                    sortedPosition = j -1;
                }
            }
            i = sortedPosition;
        }
    }


    public static void main(String[] args) {
        Integer[] a = {9, 4, -1, 6, 7, 3, 12, 4, 9, 3};
        sort(a);
        System.out.println(Arrays.toString(a));
    }


    }



