package leetcode;

import java.util.*;

public class code57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;      // 插入的newInterval是否加入到答案中
        List<int[]> ansList = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 当前区间在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});  //（还没加入，那得加！）
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 当前区间在插入区间的左侧且无交集（都没遍历到并添加怎么可能有交集？placed判断也省了）
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});  // 插入区间在最右侧且无交集，要加啊！
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);  // 为啥不用遍历两遍？因为List里面套的<int[]>
        }
        return ans;
    }
}
