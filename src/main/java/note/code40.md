# LeetCode_40：组合总和Ⅱ

给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用 一次 。

注意：解集不能包含重复的组合。

# 示例

**示例 1:**

```
输入: candidates = [10,1,2,7,6,1,5], target = 8,
输出:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
```

**示例 2:**
```
输入: candidates = [2,5,2,1,2], target = 5,
输出:
[
[1,2,2],
[5]
]
```

# 提示

- 1 <= candidates.length <= 100
- 1 <= candidates[i] <= 50
- 1 <= target <= 30

# 解答
- 和39题一样，就是需要去重
- 先排序，然后在递归里面判断去重
- 不是begin的元素才要去重，确保后面的元素不一样
- begin元素和上一个元素位置不同（递归里有+1），但元素值可能一样，需要保留
- **本质：就是必须保证每次递归只有刚开始的那个元素允许有相同的元素（排序好保证不会乱），不然后面可能乱套了！**

```java
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
```