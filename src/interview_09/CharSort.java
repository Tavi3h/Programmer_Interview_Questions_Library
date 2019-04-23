package interview_09;

import java.util.Arrays;

import org.junit.Test;

public class CharSort {
    public char[] charSort(char[] chars) {
        int[] lcCount = new int[26], ucCount = new int[26];
        for (char c : chars) {
            if (c >= 97 && c <= 122) {
                ++lcCount[c - 'a'];
            } else {
                ++ucCount[c - 'A'];
            }
        }
        char[] res = new char[chars.length];
        int resIdx = 0;
        for (int i = 0; i < lcCount.length; i++) {
            if (lcCount[i] != 0) {
                for (int j = 0; j < lcCount[i]; j++) {
                    res[resIdx++] = (char) (i + 'a');
                }
            }
        }

        for (int i = 0; i < ucCount.length; i++) {
            if (ucCount[i] != 0) {
                for (int j = 0; j < ucCount[i]; j++) {
                    res[resIdx++] = (char) (i + 'A');
                }
            }
        }
        return res;
    }

    @Test
    public void testCase() {
        char[] chars = { 'a', 'b', 'e', 'e', 'e', 'Z', 'E', 'D', 'C', 'd', 'v', 'a', 'x', 'o' };
        System.out.println(Arrays.toString(charSort(chars)));
    }
}
