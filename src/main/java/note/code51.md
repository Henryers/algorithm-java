# LeetCode_51：N皇后Ⅰ

列举n时的所有填法， 'Q' 和 '.' 分别代表了皇后和空位。

# 解答
- 动态规划 + 递归
- 按行填，每一行中，选好一个填上后，进下一行的递归
- 因此行不会重复，只需考虑列，左斜线，右斜线的约束
- 斜线约束利用 `row + col` 相等来判断，因此数组`leftDiagonal`、`rightDiagonal`需要扩大两倍

```java
public class code51 {
    int N;
    static boolean available = true;  // 语义化标记而已（常量为true）
    int[] positionInRow;    // positionInRow[i] 表示第i行填皇后的索引位置
    boolean[] column;       // 列约束，下面两行是左斜线和右斜线的约束
    boolean[] leftDiagonal; // max(row+col) = squares*2 - 2
    boolean[] rightDiagonal;
    List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        N = n;
        column = new boolean[N];
        positionInRow = new int[N];
        leftDiagonal = new boolean[N * 2 - 1]; // max(row+col) = squares*2 - 2
        rightDiagonal = new boolean[N * 2 - 1];
        // min(row-col) = -(squares-1)  min(row-col+norm) = 0   max(row-col+norm) = squares*2 - 2
        for (int i = 0; i < n; i++)
            positionInRow[i] = -1;   // 当前第i行还没放皇后
        for (int i = 0; i < n; i++)
            column[i] = available;
        for (int i = 0; i < n * 2 - 1; i++)
            leftDiagonal[i] = rightDiagonal[i] = available;
        // 从第一行开始填
        putQueen(0);
        return res;
    }

    public void putQueen(int row) {
        for (int col = 0; col < N; col++) {
            // 如果当前位置可放置皇后
            if (column[col] == available &&
                    leftDiagonal[row + col] == available &&
                    rightDiagonal[row - col + N - 1] == available) {
                // 将皇后放置在当前位置
                positionInRow[row] = col;   // 第row行的皇后放在第col列
                // 记录这个positionInRow一维数组相当于记录了一种情况下n皇后的摆法
                column[col] = !available;
                leftDiagonal[row + col] = !available;
                rightDiagonal[row - col + N - 1] = !available;

                if (row < N - 1)
                    // 递归调用，尝试在下一行放置皇后
                    putQueen(row + 1);
                else {
                    // 如果已经放置了最后一行的皇后，记录此次有效棋盘状态
                    List<String> small_res = new ArrayList<>();
                    for (int j = 0; j < N; j++) {
                        StringBuilder tmp = new StringBuilder();
                        for (int k = 0; k < N; k++) {
                            if (k != positionInRow[j])
                                tmp.append(".");
                            else
                                tmp.append("Q");
                        }
                        small_res.add(tmp.toString());
                    }
                    res.add(small_res);
                }
                // 回溯，将皇后拿掉，以便尝试其他位置
                column[col] = available;
                leftDiagonal[row + col] = available;
                rightDiagonal[row - col + N - 1] = available;
            }
        }
    }
}
```