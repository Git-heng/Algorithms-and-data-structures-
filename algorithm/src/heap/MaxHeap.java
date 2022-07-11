package heap;


import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Random;

public class MaxHeap {

    private final int[] data;
    private int size;

    public MaxHeap(int[] array) {
        data = array;
        size = data.length;
        format();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public int getParent(int index) {
        return (index - 1) / 2;
    }

    public int leftChild(int index) {
        return index * 2 + 1;
    }

    public int rightChild(int index) {
        return index * 2 + 2;
    }

    public int getMax() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data[0];
    }


    public void add(int a) {
        if (isFull()) {
            return;
        }
        data[size] = a;
        size++;
        siftUp(size - 1);
    }

    public void remove() {
        if (isEmpty()) {
            return;
        }
        swap(0, size - 1);
        data[size - 1] = 0;
        size--;
        siftDown(0);
    }

    public int extractedMax() {
        int max = getMax();
        remove();
        return max;
    }

    public void format() {
        int notLeafNode = getParent(size-1);
        for (int i = notLeafNode; i >= 0 ; i--) {
            siftDown(i);
        }
    }

    public void sort(){
        for (int i = size - 1; i > 0 ; i--) {
            swap(0, i);
            siftDown(0, i);
        }
    }

    /**
     * 上浮index位置的元素
     */
    public void siftUp(int index) {
        int parentIndex = getParent(index);
        //还有父节点并且该项大于父节点
        while (parentIndex >= 0 && data[index] > data[parentIndex]) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = getParent(parentIndex);
        }
    }

    public void siftDown(int index) {
        //如果有左右子节点，与最大的交换位置
        while (leftChild(index) < size) {
            int maxIndex = data[index] > data[leftChild(index)] ? index : leftChild(index);
            if (rightChild(index) < size) {
                maxIndex = data[maxIndex] > data[rightChild(index)] ? maxIndex : rightChild(index);
            }
            if (maxIndex == index) {
                break;
            }
            swap(index, maxIndex);
            index = maxIndex;
        }
    }


//--------------------------------续-----------------------------
    /**
     * 对范围为[0,heapEnd)的堆进行siftDown操作
     */
    public void siftDown(int index, int heapEnd) {
        //如果有左右子节点，与最大的交换位置
        while (leftChild(index) < heapEnd) {
            int maxIndex = data[index] > data[leftChild(index)] ? index : leftChild(index);
            if (rightChild(index) < heapEnd) {
                maxIndex = data[maxIndex] > data[rightChild(index)] ? maxIndex : rightChild(index);
            }
            if (maxIndex == index) {
                break;
            }
            swap(index, maxIndex);
            index = maxIndex;
        }
    }

    public void swap(int index1, int index2) {
        if (index1 < 0 || index1 >= size
                || index2 < 0 || index2 >= size) {
            throw new IllegalArgumentException("Index is wrong");
        }
        int temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

    public static void main(String[] args) {
        int n = 100;
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(n);
        }

        System.out.println(Arrays.toString(arr));
        MaxHeap maxHeap = new MaxHeap(arr);
        maxHeap.sort();
        for (int i = 0; i + 1 < n; i++) {
            if (arr[i] > arr[i + 1]) {
                System.out.println("error: " + i);
            }
        }
        System.out.println(Arrays.toString(arr));

    }

}