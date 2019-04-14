package interview_07;

// 寻找一条从左上角（`arr[0][0]`）到右下角（`arr[m-1][n-1]`）的路线，使得沿途经过的数组中的整数之和最小。每次只能向右走或向下走。
public class MinPathOfTopleftToLowerright {

    // recursive
    public static int getMinPathRecursive(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return getMinPathRecursive(arr, arr.length - 1, arr[0].length - 1);
    }

    private static int getMinPathRecursive(int[][] arr, int i, int j) {
        if (i > 0 && j > 0) {
            return arr[i][j] + Math.min(getMinPathRecursive(arr, i - 1, j), getMinPathRecursive(arr, i, j - 1));
        } else if (i > 0 && j == 0) {
            return arr[i][j] + getMinPathRecursive(arr, i - 1, j);
        } else if (i == 0 && j > 0) {
            return arr[i][j] + getMinPathRecursive(arr, i, j - 1);
        } else {
            return arr[i][j];
        }
    }

    // dp
    public static int getMinPathDP(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int m = arr.length, n = arr[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = arr[0][0];

        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }
        
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    dp[i][j] = arr[i][j] + dp[i][j - 1];
                    System.out.print("[" + i + ", " + (j - 1) + "] ");
                } else {
                    dp[i][j] = arr[i][j] + dp[i - 1][j];
                    System.out.print("[" + (i - 1) + ", " + j + "] ");
                }
            }
        }
        
        System.out.println("[" + (m - 1) + ", " + (n - 1) + "]");
        
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] arr = { { 2, 4, 0, -2 }, { -5, -6, -7, 1 } };
        System.out.println(getMinPathRecursive(arr));
        System.out.println(getMinPathDP(arr));
    }
}
