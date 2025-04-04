# LeetCode_46：全排列

给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

# 示例

**示例 1：**

>输入：nums = [1,2,3]  
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]  

**示例 2：**

>输入：nums = [0,1]  
输出：[[0,1],[1,0]]  

**示例 3：**

>输入：nums = [1]  
输出：[[1]]

# 提示

- 1 <= nums.length <= 6
- -10 <= nums[i] <= 10
- nums 中的所有整数 互不相同

# 解答

经典递归，还要加`visited[]`数组记录
```
for(循环){
    res.add();
    process();
    res.remove();
}
```
```java
public class code46 {
    boolean[] visited;
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> res = new ArrayList<Integer>();
        visited = new boolean[nums.length];  // 记录当前位置是否被填过
        process(result, res, 0, nums);
        return result;
    }

    public void process (List<List<Integer>> result, List<Integer> res, int start, int[] nums){
        if (start == nums.length){        // 越界，表示所有数字都填完了
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
```