# LeetCode_35：搜索插入位置

给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

请必须使用时间复杂度为 O(log n) 的算法。

# 示例

**示例 1:**

>输入: nums = [1,3,5,6], target = 5  
输出: 2

**示例 2:**

>输入: nums = [1,3,5,6], target = 2  
输出: 1

**示例 3:**

>输入: nums = [1,3,5,6], target = 7  
输出: 4

# 提示

- 1 <= nums.length <= 104
- -104 <= nums[i] <= 104
- nums 为 无重复元素 的 升序 排列数组
- -104 <= target <= 104

# 解答
- 看似简单二分，实则还要找插入位置导致不能直接用简单的`l<r`、`l<=r`来退出循环，还得用mid的变化（短时间内有点纠结，有点难想...）
- 为什么不能呢？因为在循环里面不能简单地认为 `r = mid - 1`，`l = mid + 1`，否则可能跳出插入的位置
- 因此退出条件需要让`l`和`mid`比较，确保`mid`变化之后还和`l`一样，才退出循环
```java
public class code35 {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        int mid = (l + r) >> 1;
        if (target < nums[0]) {
            return 0;
        }
        if (target > nums[n - 1]) {
            return n;
        }
        while (l != mid) {
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                r = mid;
            } else {
                l = mid;
            }
            mid = (l + r) >> 1;
        }
        return nums[mid] == target ? mid : mid + 1;
    }
}
```