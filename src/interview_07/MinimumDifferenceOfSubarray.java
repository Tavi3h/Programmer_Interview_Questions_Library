package interview_07;

public class MinimumDifferenceOfSubarray {
    
    public static void func(int[] arr) {

        int sum = 0;
        for (int i : arr) {
            sum += i;
        }

        // flag[i][j]：任意i个整数之和是j，则flag[i][j]为true，即flag[i][j]为true，那么一定能找到i个整数，使它们的和是j。
        boolean[][] flag = new boolean[arr.length + 1][sum / 2 + 1];
        flag[0][0] = true;

        for (int k = 0; k < arr.length; k++) {
            for (int i = Math.min(arr.length / 2, k); i >= 1; i--) {
                for (int j = 0; j <= sum / 2; j++) {
                    if (j >= arr[k] && flag[i - 1][j - arr[k]]) {
                        flag[i][j] = true;
                    }
                }
            }
        }

        for (int j = sum / 2; j >= 0; j--) {
            if (flag[arr.length / 2][j]) {
                System.out.println("Sum is " + sum);
                System.out.println("Sum / 2 is " + sum / 2);
                System.out.println("j is " + j);
                System.out.println("Minimum delta is " + Math.abs(2 * j - sum));
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 5 };
        func(arr);
    }
}
