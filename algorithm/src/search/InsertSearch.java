package search;

public class InsertSearch {

    /**
     * 插值查找法，相当于在函数式中y=kx+b,[y1=k(x1),y2=k(x2)]中(注：k,b未知)，根据已知的y值，求对应的x值
     * 列方程式
     * ①y1=kx1+b;
     * ②y2=kx2+b;
     * ②-①解得: k=(y2-y1)/(x2-x1),而根据b = y-kx = y1-kx1;
     * y-kx = y1-kx1 → y-y1 = k(x-x1) →
     * x = x1+(y-y1)/k = x1+(y-y1)/(y2-y1)*(x2-x1)
     */
    public static int insertFind(int[] arr, int y) {
        if (arr == null) {
            return -1;
        }
        int start = 0;
        int end = arr.length -1;
        while (start <= end) {
            int mid = start + (y - arr[start]) / (arr[end] - arr[start]) * (end - start);
            if (arr[mid] > y) {
                end = mid - 1;
            } else if (arr[mid] < y) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
