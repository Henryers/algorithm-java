package leetcode;

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
