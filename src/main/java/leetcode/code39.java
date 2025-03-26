package leetcode;

import java.util.*;

public class code39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param len        candidates.length，可以不传
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            // 尝试加入，进递归
            path.addLast(candidates[i]);
            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            // 比如 2 2 3  就是i是0 0 1的情况
            // 但是 2 3 2  不可能出现，因为i: 0 1 0 但是我规定了i在递归时不能回退/减小！！！
            // 所以不可能出现重复的情况！！！
            dfs(candidates, i, len, target - candidates[i], path, res);
            // 状态重置，重新循环下一种情况
            path.removeLast();
        }
    }
}
