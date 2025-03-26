package leetcode;

import java.util.*;

public class code40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);  // 与39题不同，这里必须先排序
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        List<Integer> path = new ArrayList<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    private void dfs(int[] candidates, int begin, int len, int target, List<Integer> path, List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            // 和39题不同之处：去重，不过begin之处确保了可以出现不同位置的相同数字
            if (i != begin && candidates[i] == candidates[i - 1]) continue;
            path.add(candidates[i]); // 添加元素
            dfs(candidates, i + 1, len, target - candidates[i], path, res); // 与39题不同，这里必须+1
            path.remove(path.size() - 1); // 移除最后一个元素
        }
    }
}
