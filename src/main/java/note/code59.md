# LeetCode59：螺旋矩阵Ⅱ

和T54差不多，只需要把打印列表换成打印矩阵，且矩阵的数从1依次递增

# 解答
把T54的加入列表，换成这里的填入矩阵即可，遍历顺序相同，同时方阵不用考虑最后的长条分类

```java
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
```