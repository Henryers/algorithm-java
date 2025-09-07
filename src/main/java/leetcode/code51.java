package leetcode;

import java.util.*;

public class code51 {
    int N;
    int[] positionInRow;  // positionInRow[i] 表示第i行填皇后的索引位置
    boolean[] column;     // 列约束，下面两行是左斜线和右斜线的约束
    boolean[] left;       // max(row+col) = squares*2 - 2
    boolean[] right;
    List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        N = n;
        column = new boolean[N];
        positionInRow = new int[N];
        left = new boolean[N * 2 - 1]; // max(row+col) = squares*2 - 2
        right = new boolean[N * 2 - 1];
        Arrays.fill(positionInRow, -1);        // 全部填充为-1
        Arrays.fill(column, true);             // 全部填充为true
        Arrays.fill(left, true);       // 全部填充为true
        Arrays.fill(right, true);      // 全部填充为true
        // 从第一行开始填
        putQueen(0);
        return res;
    }

    public void putQueen(int row) {
        for (int col = 0; col < N; col++) {
            // 如果当前位置可放置皇后
            if (column[col] && left[row + col] && right[row - col + N - 1]) {
                // 1 记录位置 + 约束
                positionInRow[row] = col;   // 第row行的皇后放在第col列
                column[col] = false;
                left[row + col] = false;
                right[row - col + N - 1] = false;
                // 2 递归
                if (row < N - 1)
                    putQueen(row + 1);
                else {
                    List<String> small_res = new ArrayList<>();
                    for (int j = 0; j < N; j++) {
                        // 用啥stringbuilder然后遍历啊？全部初始化'.'再直接单个赋值'Q'，直接过！
                        char[] rowChars = new char[N];
                        Arrays.fill(rowChars, '.');
                        rowChars[positionInRow[j]] = 'Q';
                        String rowStr = new String(rowChars);
                        small_res.add(rowStr);
                    }
                    res.add(small_res);
                }
                // 3 回溯，去除皇后在该位置上的约束
                column[col] = true;
                left[row + col] = true;
                right[row - col + N - 1] = true;
            }
        }
    }
}