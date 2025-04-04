package leetcode;

import java.util.*;

public class code46 {
    boolean[] visited;

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> res = new ArrayList<Integer>();
        visited = new boolean[nums.length];  // 记录当前位置是否被填过
        process(result, res, 0, nums);
        return result;
    }

    public void process(List<List<Integer>> result, List<Integer> res, int start, int[] nums) {
        if (start == nums.length) {        // 越界，表示所有数字都填完了
            result.add(new ArrayList<Integer>(res));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            // 动态维护数组
            res.add(nums[i]);
            visited[i] = true;
            // 继续递归填下一个数
            process(result, res, start + 1, nums);
            // 撤销操作
            res.remove(start);
            visited[i] = false;
        }
    }
}
