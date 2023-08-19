package heap;

import java.util.Arrays;

/**
 * 最大堆（从小到大），依次找到堆顶最大值往后面放
 */
public class HeapSortWithArray {

    //声明全局变量，用于记录数组array的长度；
    static int len;

    /**
     * 堆排序算法
     *
     * @param array
     * @return
     */
    public static <E extends Comparable<E>> void heapSort(E[] array) {
        len = array.length;
        if (len < 1) {
            return;
        }
        //1.构建一个最大堆
        buildMaxHeap(array);
        while (len > 0) {
            //2.循环将堆首位（最大值）与末位交换
            swap(array, 0, len - 1);
            len--;
            //3.然后在重新调整最大堆
            adjustHeap(array, 0);
        }
    }

    /**
     * 建立最大堆
     *
     * @param array
     */
    public static <E extends Comparable<E>> void buildMaxHeap(E[] array) {
        //从最后一个非叶子节点开始向上构造最大堆
        for (int i = (len / 2 - 1); i >= 0; i--) { //感谢 @让我发会呆 网友的提醒，此处应该为 i = (len/2 - 1)
            adjustHeap(array, i);
        }
    }

    /**
     * 调整使之成为最大堆
     *
     * @param array
     * @param i
     */
    public static <E extends Comparable<E>> void adjustHeap(E[] array, int i) {
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if (i * 2 < len && array[i * 2].compareTo(array[maxIndex]) > 0)
            maxIndex = i * 2;
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if (i * 2 + 1 < len && array[i * 2 + 1].compareTo(array[maxIndex]) > 0)
            maxIndex = i * 2 + 1;
        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
        if (maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(array, maxIndex);
        }
    }

    public static <E extends Comparable<E>> void swap(E[] arr, int a, int b) {
        E temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] testArray = {2, 5, 14, 76, 32, 19, 44, 34, 543, 23, 65, 51};
    }


}
