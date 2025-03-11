# LeetCode_15：三数之和

给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。


# 示例


示例 1：

>输入：nums = [-1,0,1,2,-1,-4]  
输出：[[-1,-1,2],[-1,0,1]]  
解释：  
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。  
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。  
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。  
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。  
注意，输出的顺序和三元组的顺序并不重要。  

**示例 2：**

>输入：nums = [0,1,1]  
输出：[]  
解释：唯一可能的三元组和不为 0 。

**示例 3：**

>输入：nums = [0,0,0]  
输出：[[0,0,0]]  
解释：唯一可能的三元组和为 0 。


# 提示

- 3 <= nums.length <= 3000
- -105 <= nums[i] <= 105

# 解答
- 先排序，因为和索引无关
- 再去重，`i`位置先选，`j`、`k`利用 **双指针** 往中间缩
```java
public class code15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if (len < 3) return ans;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) L++; // 去重
                    while (L < R && nums[R] == nums[R - 1]) R--; // 去重
                    L++; // L+1 和 L 相同，也要跳过
                    R--; // 同理，跳过
                } else if (sum < 0) L++;
                else R--;
            }
        }
        return ans;
    }
}

```