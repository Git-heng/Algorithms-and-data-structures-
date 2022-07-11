package test;

import java.util.HashSet;
import java.util.Hashtable;

class Solution {

    public int x,y;
    
    public boolean searchMatrix(int[][] matrix, int target) {

        int i = 0;
        int j = matrix[0].length-1;
        while (i < matrix.length && i >= 0 && j < matrix[0].length && j >=0){
            if (matrix[i][j] == target){
                return true;
            } else if (matrix[i][j] < target){
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr = {{1,4,7,},{2,7,8},{3,8,9}};
        System.out.println(solution.searchMatrix(arr,1));
    }
}