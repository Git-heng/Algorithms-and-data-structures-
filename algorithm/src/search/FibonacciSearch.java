package search;

public class FibonacciSearch {

    /**
     * F(k) = F(k-1) + F(k-2） ——>每一个 F(k) - 1 都可以拆分成三部分 F(k-1)-1、mid、 F(k-2)-1，F(k-1)-1又可以拆分成三部分
     * 让mid保持在数组的黄金分割点处，mid前面长度为F[K-1]-1，后面长度为F[K-2]-1，数组总长度为F[K]-1，mid在黄金分割点
     * https://blog.csdn.net/Scccc_/article/details/107101647
     */

    public static int find(int[] arr, int a) {
        if (arr == null) {
            return -1;
        }
        int[] f = getFiBo(arr.length); //获取斐波那契数组
        int start = 0;                     //原数组的左下标
        int end = arr.length - 1;          //原数组的右下标
        int k = 0;
        //调整k值,使得f[k]-1的长度覆盖（大于等于）查找数组的长度
        while (f[k] - 1 < arr.length) {
            k++;
        }

        //获取一个覆盖了原数组的新数组
        int[] newArr = new int[f[k] - 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        for (int i = arr.length; i < newArr.length; i++) {
            newArr[i] = arr[end];
        }

        while (start <= end) {
            int mid = start + f[k - 1] - 1;
            if (newArr[mid] > a) {
                end = mid - 1;
                k -= 1;
            } else if (newArr[mid] < a) {
                start = mid + 1;
                k -= 2;
            } else {
                return Math.min(mid, arr.length - 1);
            }
        }

        return -1;
    }

    /**
     * @param length 长度
     * @return 斐波那契数组
     */
    private static int[] getFiBo(int length) {
        int[] fiBos = new int[length];
        fiBos[0] = 1;
        fiBos[1] = 1;
        for (int i = 2; i < length; i++) {
            fiBos[i] = fiBos[i - 1] + fiBos[i - 2];
        }
        return fiBos;
    }


}