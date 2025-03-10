# LeetCode_11：盛最多水的容器
给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。

找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

说明：你不能倾斜容器。

# 示例

**示例 1：**

>输入：[1,8,6,2,5,4,8,3,7]    
输出：49    
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

**示例 2：**

>输入：height = [1,1]  
输出：1


# 提示

- n == height.length
- 2 <= n <= 105
- 0 <= height[i] <= 104

# 解答
双指针，不是单向的快慢指针，而是从两边往中间缩  
哪边矮就先缩哪边，确保更新的过程中始终宽度减小，高度增加，才有可能比原先的答案好
```java
public class code11 {
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length-1;
        int res = 0;
        // 双指针，从两边往中间缩，哪边高度小就先往中间缩，然后不断更新 O(n)
        while (l < r){
            res = height[l] < height[r] ?
                    Math.max(res, (r - l) * height[l++]) : Math.max(res, (r - l) * height[r--]);
        }
        return res;
    }
}

```