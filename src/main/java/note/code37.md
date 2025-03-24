# LeetCode_37：解数独

编写一个程序，通过填充空格来解决数独问题。

数独的解法需遵循如下规则：

数字 1-9 在每一行只能出现一次。  
数字 1-9 在每一列只能出现一次。  
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）  
数独部分空格内已填入了数字，空白格用 '.' 表示。

# 示例

```
输入：board = 
[["5","3",".",".","7",".",".",".","."],
["6",".",".","1","9","5",".",".","."],
[".","9","8",".",".",".",".","6","."],
["8",".",".",".","6",".",".",".","3"],
["4",".",".","8",".","3",".",".","1"],
["7",".",".",".","2",".",".",".","6"],
[".","6",".",".",".",".","2","8","."],
[".",".",".","4","1","9",".",".","5"],
[".",".",".",".","8",".",".","7","9"]]
输出：
[["5","3","4","6","7","8","9","1","2"],
["6","7","2","1","9","5","3","4","8"],
["1","9","8","3","4","2","5","6","7"],
["8","5","9","7","6","1","4","2","3"],
["4","2","6","8","5","3","7","9","1"],
["7","1","3","9","2","4","8","5","6"],
["9","6","1","5","3","7","2","8","4"],
["2","8","7","4","1","9","6","3","5"],
["3","4","5","2","8","6","1","7","9"]]
```

# 提示

- board.length == 9
- board[i].length == 9
- board[i][j] 是一位数字或者 '.'
- 题目数据 保证 输入数独仅有一个解

# 解答
- 下面注释挺清楚的，自己看
- 有用到上一题的思路来判断数独是否有效
- 深度优先遍历

```java
public class code37 {
    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bud = new boolean[9][10];
        initMap(board, row, col, bud);
        process(board, 0, 0, row, col, bud);
    }

    public void initMap(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bud) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int bid = 3 * (i / 3) + (j / 3);
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    bud[bid][num] = true;
                }
            }
        }
    }

    public boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bud) {
        if (i == 9) {
            return true;
        }
        int nexti = j == 8 ? i + 1 : i;
        int nextj = j == 8 ? 0 : j + 1;
        // 有数字了，不用填，跳过
        if (board[i][j] != '.') {
            return process(board, nexti, nextj, row, col, bud);
        } else {
            int bid = 3 * (i / 3) + (j / 3);
            for (int num = 1; num <= 9; num++) {
                // 三个都没出现过，就设为true，把数字填进去board
                if ((!row[i][num]) && (!col[j][num]) && (!bud[bid][num])) {
                    // 尝试一下，玩深度优先遍历，不对再回溯时改成false
                    row[i][num] = true;
                    col[j][num] = true;
                    bud[bid][num] = true;
                    board[i][j] = (char) (num + '0');
                    if (process(board, nexti, nextj, row, col, bud)) {
                        return true;
                    }
                    // 尝试失败，改回原始数据
                    row[i][num] = false;
                    col[j][num] = false;
                    bud[bid][num] = false;
                    board[i][j] = '.';
                }
            }
            return false;
        }
    }
}
```