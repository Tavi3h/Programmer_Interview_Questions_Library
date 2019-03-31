package interview_01;

import org.junit.Test;

// 递归计算阶乘
public class Factorial {
    public int fac(int n) {
        return n == 1 ? 1 : n * fac(n - 1);
    }

    @Test
    public void testCase() {
        System.out.println(fac(6));
    }
}
