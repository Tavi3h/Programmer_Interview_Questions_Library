package interview_09;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KthPermuations {
    public String getPermutation(int n, int k) {
        int i = 0, j = 0;
        boolean[] isUsed = new boolean[n];
        int[] data = new int[n];
        data[0] = 1;
        for (i = 1; i < n; i++) {
            data[i] = data[i - 1] * i;
        }
        --k;
        StringBuilder result = new StringBuilder();
        while (--i >= 0) {
            int rank = k / data[i];
            for (j = 0; j <= rank; j++) {
                if (isUsed[j]) {
                    rank++;
                }
            }
            isUsed[rank] = true;
            result.append(j);
            k = k % data[i];
        }
        return result.toString();
    }
    
    @Test
    public void testCase() {
        assertEquals("213", getPermutation(3, 3));
        assertEquals("321", getPermutation(3, 6));
        assertEquals("312", getPermutation(3, 5));
    }
}
