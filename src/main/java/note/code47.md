# LeetCode_47：全排列Ⅱ

给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

# 示例

**示例 1：**

```
输入：nums = [1,1,2]  
输出：
[[1,1,2],
[1,2,1],
[2,1,1]]
```

**示例 2：**

>输入：nums = [1,2,3]  
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

# 提示

- 1 <= nums.length <= 8
- -10 <= nums[i] <= 10

# 解答

和上一题一样，注意先排序，然后在递归中如果一样的数先填了后面的，统统不算!（continue）
```java
public class code47 {
    boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> res = new ArrayList<Integer>();
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        process(result, res, 0, nums);
        return result;
    }

    public void process(List<List<Integer>> result, List<Integer> res, int start, int[] nums) {
        if (start == nums.length) {        // 越界，表示所有数字都填完了
            result.add(new ArrayList<Integer>(res));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 比上一题多了这个（重点）：如果前一个相同的数没填进去，直接continue
            // 一样的数按顺序来，别重新再打乱顺序来！多次一举！
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) continue;
            // 动态维护数组
            res.add(nums[i]);
            visited[i] = true;
            // 继续递归填下一个数
            process(result, res, start + 1, nums);
            // 撤销操作
            visited[i] = false;
            res.remove(start);
        }
    }
}
```