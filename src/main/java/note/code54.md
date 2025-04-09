# LeetCode_54：螺旋

给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

# 解答
- 类似之前的旋转矩阵，从外到内，每一层都是在4个方向上按顺序遍历，按`上-右-下-左`的顺序
- 如果是长方形，遍历到最内部还有一个长小条，需要单独遍历（可能是横着摆也可能是竖着摆）

```java
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
```