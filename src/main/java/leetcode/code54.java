package leetcode;

import java.util.*;

public class code54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int firstRow = 0;
        int firstCol = 0;
        int lastRow = matrix.length - 1;
        int lastCol = matrix[0].length - 1;
        while (firstRow < lastRow && firstCol < lastCol) {
            int rowTimes = lastCol - firstCol;
            int colTimes = lastRow - firstRow;
            for (int i = 0; i != rowTimes; i++) {
                list.add(matrix[firstRow][firstCol + i]);
            }
            for (int i = 0; i != colTimes; i++) {
                list.add(matrix[firstRow + i][lastCol]);
            }
            for (int i = 0; i != rowTimes; i++) {
                list.add(matrix[lastRow][lastCol - i]);
            }
            for (int i = 0; i != colTimes; i++) {
                list.add(matrix[lastRow - i][firstCol]);
            }
            firstRow++;
            firstCol++;
            lastRow--;
            lastCol--;
        }
        if (firstRow == lastRow) {
            for (int i = 0; i <= lastCol - firstCol; i++) {
                list.add(matrix[firstRow][firstCol + i]);
            }
        } else if (firstCol == lastCol) {
            for (int i = 0; i <= lastRow - firstRow; i++) {
                list.add(matrix[firstRow + i][firstCol]);
            }
        }
        return list;
    }
}
