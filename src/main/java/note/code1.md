# LeetCode_1：两数之和
给定一个整数数组 `nums` 和一个整数目标值 `target`，请你在该数组中找出和为目标值 `target`  的那两个整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。

你可以按任意顺序返回答案。


# 示例
**示例 1：**

>输入：nums = [2,7,11,15], target = 9  
输出：[0,1]  
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 

**示例 2：**

>输入：nums = [3,2,4], target = 6  
输出：[1,2]

**示例 3：**

>输入：nums = [3,3], target = 6  
输出：[0,1]


# 提示

- `2 <= nums.length <= 104`  
- `-109 <= nums[i] <= 109`  
- `-109 <= target <= 109`  
- 只会存在一个有效答案
 

# 解答
## 法1：暴力枚举
外层找第一个数，内层找第二个数，依次比较找答案

```java
public class code1 {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[] {1, 1};
    }
}
```


## 法2：哈希表存储
哈希表存储上一个数`<value, index>`，一但找到另一个数符合就返回
> 为什么存储的是`<value, index>`反过来的？因为这样才能根据value来返回对应的index
> 那这样不会value重复吗？不可能！题目假设每种输入只会对应一个答案，如果重复说明这两个数必是答案，不会进入下一轮循环

```java
public class code1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            // target - 当前的数，看得到的结果是否在map里，如果在，那结合两个索引后return
            int tmp = target - nums[i];
            if(map.containsKey(tmp)) return new int[] {map.get(tmp), i};
            map.put(nums[i], i);
        }
        return new int[] {1, 1};
    }
}
```