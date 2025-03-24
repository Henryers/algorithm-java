package leetcode;

public class code36 {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bud = new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int bid = 3 * (i / 3) + (j / 3);
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    if (row[i][num] || col[j][num] || bud[bid][num]) {
                        return false;
                    }
                    row[i][num] = true;   // 第i行已经有数字num了
                    col[j][num] = true;   // 第i列已经有数字num了
                    bud[bid][num] = true; // 第i桶已经有数字num了
                }
            }
        }
        return true;
    }
}
