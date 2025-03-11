# Leetcode_16：最接近的三数之和

给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。

返回这三个数的和。

假定每组输入只存在恰好一个解。

# 示例

**示例 1：**

>输入：nums = [-1,2,1,-4], target = 1  
输出：2  
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2)。

**示例 2：**

>输入：nums = [0,0,0], target = 1  
输出：0  
解释：与 target 最接近的和是 0（0 + 0 + 0 = 0）。


# 提示

- 3 <= nums.length <= 1000
- -1000 <= nums[i] <= 1000
- -104 <= target <= 104


# 解答
- 省流：和T15解题思路几乎一样甚至更简单！
- 多了个target，那就多计算一个差分就好！
```java
public class code16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int mindiff = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int diff = nums[i] + nums[L] + nums[R] - target;
                if (diff == 0) {
                    return target;
                } else if (diff < 0) {
                    mindiff = Math.abs(mindiff) > Math.abs(diff) ? diff : mindiff;
                    L++;  // 这里面写不写while都一样，时间复杂度一样还费代码，还不如不写...
                } else {
                    mindiff = Math.abs(mindiff) > Math.abs(diff) ? diff : mindiff;
                    R--;
                }
            }
        }
        return target + mindiff;
    }
}

```