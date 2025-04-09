package leetcode;

import java.util.*;

public class code56 {
    public int[][] merge(int[][] intervals) {
        // 你当然可以手写快排，但是直接调包+比较器为什么不用呢？
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<List<Integer>> res = new ArrayList<>();
        res.add(Arrays.asList(intervals[0][0], intervals[0][1]));
        for (int i = 0; i < intervals.length - 1; i++) {
            // 交叉并且 [1 {3 2] 4} 这种才要合并，交叉但是 [1 {2 3} 4] 这种啥都不做，保留原来的[1 4]就行
            if (res.get(res.size() - 1).get(1) >= intervals[i + 1][0]) {
                if (res.get(res.size() - 1).get(1) < intervals[i + 1][1]) {
                    res.set(res.size() - 1, Arrays.asList(res.get(res.size() - 1).get(0), intervals[i + 1][1]));
                }
            } else {  // 没交叉就直接加
                res.add(Arrays.asList(intervals[i + 1][0], intervals[i + 1][1]));
            }
        }
        // 转为题目要求的静态数组return
        int[][] result = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            for (int j = 0; j < 2; j++) {
                result[i][j] = res.get(i).get(j);
            }
        }
        return result;
    }
}
