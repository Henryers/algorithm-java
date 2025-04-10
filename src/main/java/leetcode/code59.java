package leetcode;

public class code59 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int firstRow = 0;
        int firstCol = 0;
        int lastRow = n - 1;
        int lastCol = n - 1;
        int count = 1;
        while (firstRow < lastRow && firstCol < lastCol) {
            int rowTimes = lastCol - firstCol;
            int colTimes = lastRow - firstRow;
            for (int i = 0; i != rowTimes; i++) {
                matrix[firstRow][firstCol + i] = count++;
            }
            for (int i = 0; i != colTimes; i++) {
                matrix[firstRow + i][lastCol] = count++;
            }
            for (int i = 0; i != rowTimes; i++) {
                matrix[lastRow][lastCol - i] = count++;
            }
            for (int i = 0; i != colTimes; i++) {
                matrix[lastRow - i][firstCol] = count++;
            }
            firstRow++;
            firstCol++;
            lastRow--;
            lastCol--;
        }
        if (firstCol == lastCol && firstRow == lastRow)
            matrix[firstRow][firstCol] = count;
        return matrix;
    }
}
