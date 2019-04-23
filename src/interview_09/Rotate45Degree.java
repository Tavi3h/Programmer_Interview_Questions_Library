package interview_09;

import org.junit.Test;

public class Rotate45Degree {
    
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = len - 1; i > 0; i--) {
            int row = 0, col = i;
            while (col < len) {
                System.out.print(matrix[row++][col++] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < len; i++) {
            int row = i, col = 0;
            while (row < len) {
                System.out.print(matrix[row++][col++] + " ");
            }
            System.out.println();
        }
    }
    
    @Test
    public void testCase() {
        int[][] matrix = {
                {1, 2, 3, 10},
                {4, 5, 6, 11},
                {7, 8, 9, 12},
                {13, 14, 15, 16}
        };
        rotate(matrix);
    }
}
