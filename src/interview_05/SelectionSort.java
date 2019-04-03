package interview_05;

// 选择排序
public class SelectionSort {
    
    public static <T extends Comparable<? super T>> void sort(T[] a) {
        int N = a.length; 
        for (int i = 0; i < N; i++) {
            int min = i; 
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
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
}
