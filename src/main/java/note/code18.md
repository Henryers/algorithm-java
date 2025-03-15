# LeetCode_18：四数之和

给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：

0 <= a, b, c, d < n  
a、b、c 和 d 互不相同  
nums[a] + nums[b] + nums[c] + nums[d] == target  
你可以按 任意顺序 返回答案 。

# 示例

**示例 1：**

>输入：nums = [1,0,-1,0,-2,2], target = 0  
输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]  

**示例 2：**

>输入：nums = [2,2,2,2,2], target = 8 
输出：[[2,2,2,2]]

# 提示

- 1 <= nums.length <= 200
- -109 <= nums[i] <= 109
- -109 <= target <= 109

# 解答
- 前面三数之和是排序+第一层去重+第二、三层双指针
- 现在四数之和就是同理，排序+第一、二层去重+第三、四层双指针
- 不可能有更好的办法，难不成你还想复杂度和三数之和一样？

```java
public class code18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; first++) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            for (int second = first + 1; second < n; second++) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // d 对应的指针初始指向数组的最右端
                int fourth = n - 1;
                long tmptarget = (long) target - nums[first] - nums[second]; // 防止溢出
                // 枚举 c
                for (int third = second + 1; third < n; third++) {
                    // 需要和上一次枚举的数不相同
                    if (third > second + 1 && nums[third] == nums[third - 1]) {
                        continue;
                    }
                    // 需要保证 c 的指针在 d 的指针的左侧
                    while (third < fourth && nums[third] + nums[fourth] > tmptarget) {
                        --fourth;
                    }
                    // 如果一直> tmptarget，直到指针重合
                    // 随着 c 后续的增加, c+d 恒> tmptarget，还不如直接break
                    if (third == fourth) {
                        break;
                    }
                    if (nums[third] + nums[fourth] == tmptarget) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[third]);
                        list.add(nums[fourth]);
                        ans.add(list);
                    }
                }
            }
        }
        return ans;
    }
}
```