package test;

import java.util.Arrays;
import java.util.Random;

public class LeetCode40 {

        public int kValue;


        public int[] getLeastNumbers(int[] arr, int k) {
            if(arr == null || arr.length == 0){
                return arr;
            }
            kValue = k -1;
            Random random = new Random();
            quickSort(arr, 0, arr.length - 1);
            System.out.println(Arrays.toString(arr));
            //排序找到该点，复制前k小的元素
            return Arrays.copyOfRange(arr, 0 , Math.min(arr.length, k));

        }

        public void quickSort(int[] arr, int start, int end){
            if(start > end){
                return;
            }

            int left = start;
            int right = start;
            //start、[start+1, left]、[left+1, right]
            //当前处理点为right+1
            for(; right+1<=end;right++){
                if(arr[right+1] < arr[start]){
                    swap(arr, left+1, right+1);
                    left++;
                }
            }
            swap(arr, start, left);
            int p = left;
            if( kValue < p){
                quickSort(arr, start, p-1);
            } else if ( kValue > p) {
                quickSort(arr, p+1, end);
            }


        }

        public void swap(int[] arr, int a, int b){
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }


    public static void main(String[] args) {
        LeetCode40 leetCode40 = new LeetCode40();
        int[] a = {4,3,-1,3,2,1};
        System.out.println(Arrays.toString(leetCode40.getLeastNumbers(a , 8)));
    }

}
