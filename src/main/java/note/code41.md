# LeetCode_41：缺失的第一个正数

给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。

# 示例

**示例 1：**

>输入：nums = [1,2,0]  
输出：3  
解释：范围 [1,2] 中的数字都在数组中。

**示例 2：**

>输入：nums = [3,4,-1,1]  
输出：2  
解释：1 在数组中，但 2 没有。  

**示例 3：**

>输入：nums = [7,8,9,11,12]  
输出：1  
解释：最小的正数 1 没有出现。

# 提示

- 1 <= nums.length <= 105
- -231 <= nums[i] <= 231 - 1

# 解答

缺失的最小正整数一定在 `[1, n+1]` 范围内（n 是数组长度）。

如果数组恰好包含 `1` 到 `n`，则缺失的是 `n+1`。

否则，缺失的是 `[1, n]` 中的某个数。

**原地哈希：**
利用数组下标作为哈希键，将数值 x 放到索引 x-1 的位置（即 nums[x-1] = x）。

- 代码第一次遍历就是交换到正确的位置，不用担心while复杂度过高，因为最多就交换n次
- 第二次遍历就直接找缺失值就好

```java
public class code41 {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 1 && nums[i] <= nums.length
                    && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
```