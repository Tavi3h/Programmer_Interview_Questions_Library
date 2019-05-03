package interview_15;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IndexEqualsToValue {
    public List<Integer> find(int[] arr) {
        List<Integer> res = new ArrayList<>();
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            if (arr[i] == i) {
                res.add(i++);
            } else if (arr[i] > i) {
                i = arr[i];
            } else {
                ++i;
            }
        }
        return res;
    }

    @Test
    public void testCase() {
        int[] arr = { 2, 3, 3, 3, 7, 8, 8, 9, 9, 9, 10 };
        List<Integer> res = find(arr);
        for (int i : res) {
            System.out.println("arr[" + i + "] = " + i);
        }
    }
}
