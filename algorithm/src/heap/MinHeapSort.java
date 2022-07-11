package heap;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Random;

public class MinHeapSort {

    private int[] data;
    private int size;

    public MinHeapSort(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public MinHeapSort(int[] data) {
        this.data = data;
        size = data.length;
        for (int i = getParent(size-1); i >= 0 ; i--) {
            siftDown(i);
        }
    }

    //原地堆排序，排好的放后面[0, heapEnd]、(heapEnd, size-1]
    public void sort(){
        int heapEnd = size -1;
        for (int i = heapEnd; i >= 0; i--) {
            swap(0, heapEnd);
            heapEnd--;
            siftDown(0, heapEnd);
        }
    }

    public void add(int a) {
        if (size >= data.length) {
            return;
        }
        size++;
        data[size - 1] = a;
        siftUp(size - 1);
    }

    /**
     * 逐级上浮，更小就往上放
     */
    public void siftUp(int index) {
        while (getParent(index) >= 0 && data[index] < data[getParent(index)]) {
            swap(index, getParent(index));
            index = getParent(index);
        }
    }

    public int extractMin(){
        if (size < 0){
            throw new EmptyStackException();
        }
        int ret = data[0];
        swap(0, size-1);
        size--;
        siftDown(0);
        return ret;
    }

    public void siftDown(int index){
        while (leftNode(index) < size){
            int minIndex = data[index] < data[leftNode(index)] ? index : leftNode(index);
            if (rightNode(index) < size){
                minIndex = data[minIndex] < data[rightNode(index)] ? minIndex : rightNode(index);
            }
            if (index == minIndex){
                break;
            }
            swap(index, minIndex);
            index = minIndex;
        }
    }

    /**
     * siftDown [0,heapEnd]
     */
    public void siftDown(int index, int heapEnd){
        while (leftNode(index) <= heapEnd){
            int minIndex = data[index] < data[leftNode(index)] ? index : leftNode(index);
            if (rightNode(index) <= heapEnd){
                minIndex = data[minIndex] < data[rightNode(index)] ? minIndex : rightNode(index);
            }
            if (index == minIndex){
                break;
            }
            swap(index, minIndex);
            index = minIndex;
        }
    }

    public void swap(int index1, int index2){
        int temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

    public int getParent(int index) {
        return (index - 1) / 2;
    }

    public int leftNode(int index) {
        return index * 2 + 1;
    }

    public int rightNode(int index) {
        return index * 2 + 2;
    }


    public static void main(String[] args) {
        int n = 10;
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(n);
        }
        System.out.println(Arrays.toString(arr));

//        for (int i = 0; i + 1 < n; i++) {
//            if (arr[i] > arr[i + 1]) {
//                System.out.println("error: " + i);
//            }
//        }
        MinHeapSort minHeapSort = new MinHeapSort(arr);
        System.out.println(Arrays.toString(minHeapSort.data));
        minHeapSort.sort();
        System.out.println(Arrays.toString(minHeapSort.data));

    }

}