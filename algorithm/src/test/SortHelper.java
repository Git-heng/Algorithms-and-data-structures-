package test;

import nlognsort.MergeSorterFromBottom;
import nlognsort.QuickSorter;
import nnsort.BubbleSorter;
import nnsort.InsertSorter;
import nlognsort.MergeSorter;
import nnsort.SelectSorter;

public class SortHelper {

    public static <E extends Comparable<E>> void sortTest(String sortName, E[] arr) {
        long startTime = System.nanoTime();

        //根据名称使用不同的排序
        switch (sortName){

            case "QuickSorter":
                QuickSorter.quickSort(arr);
                break;

            case "MergeSorter":
                MergeSorter.sort(arr);
                break;

            case "MergeSorterFromBottom":
                MergeSorterFromBottom.sort(arr);
                break;

            case "BubbleSorter":
                BubbleSorter.sort(arr);
                break;
            case "InsertSorter":
                InsertSorter.sort(arr);
                break;
            case "SelectSorter":
                SelectSorter.sort(arr);
                break;

            default:
                break;
        }

        long endTime = System.nanoTime();
        System.out.println(isSort(arr));
        System.out.println("耗时为：" + (endTime - startTime)/1000000000.0);
    }

    public static <E extends Comparable<E>> boolean isSort(E[] arr) {
        for (int i = 0; i + 1 < arr.length; i++) {
            if (arr[i + 1].compareTo(arr[i]) < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] integers = ArrayGenerator.getRandomArray(30000);
        sortTest(InsertSorter.class.getSimpleName(), integers);
        sortTest(BubbleSorter.class.getSimpleName(), integers);
        sortTest(SelectSorter.class.getSimpleName(), integers);

        sortTest(MergeSorter.class.getSimpleName(), integers);
        sortTest(MergeSorterFromBottom.class.getSimpleName(), integers);
        sortTest(QuickSorter.class.getSimpleName(), integers);

    }
}
