package interview_03;

import java.util.Arrays;

import org.junit.Test;

// 插入排序
public class InsertSort {
    
    public static <T extends Comparable<? super T>> void sort(T[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            // 将a[i]插入到a[i - 1]、a[i - 2]、a[i - 3]...之中
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    private static <T extends Comparable<? super T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    private static <T> void exch(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    @Test
    public void testCase() {
        Integer[] arr = { 10, -1, 2, 3, 1, 0, 5, 4, 7, 9 };
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
