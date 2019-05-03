package interview_15;

import java.util.Arrays;

import org.junit.Test;

public class MaxAX {
    public int getMaxAX(int[] arr) {
        Arrays.sort(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (Arrays.binarySearch(arr, j + 1, i, arr[i] - arr[j]) >= 0) {
                    return arr[i];
                }
            }
        }
        return 0;
    }

    @Test
    public void testCase() {
        int[] arr = { 15, 2, 3, 3, 4, 3, 24, 8, 9, 6 };
        System.out.println(getMaxAX(arr));
    }
}
