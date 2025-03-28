# LeetCode_42：接雨水

给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

# 示例

**示例 1：**

>输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]  
输出：6  
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

**示例 2：**

>输入：height = [4,2,0,3,2,5]  
输出：9

# 提示

- n == height.length
- 1 <= n <= 2 * 104
- 0 <= height[i] <= 105

# 解答
- 双指针往中间缩
- 单调栈，左侧的东西单调递减，往右若是递增则呈现碗状可接雨水，同时将栈顶弹出
```java
public class code42 {
    public int trap(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        int leftmax = height[left];
        int rightmax = height[right];
        while (left < right) {
            leftmax = Math.max(leftmax, height[left]);
            rightmax = Math.max(rightmax, height[right]);
            if (height[left] < height[right]) {
                res += leftmax - height[left];
                left++;
            } else {
                res += rightmax - height[right];
                right--;
            }
        }
        return res;
    }

    // 单调栈，会慢点
    // int ans = 0;
    // Deque<Integer> stack = new LinkedList<Integer>();
    // int n = height.length;
    // for (int i = 0; i < n; ++i) {
    //     while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
    //         int top = stack.pop();
    //         if (stack.isEmpty()) {
    //             break;
    //         }
    //         int left = stack.peek();
    //         int currWidth = i - left - 1;
    //         int currHeight = Math.min(height[left], height[i]) - height[top];
    //         ans += currWidth * currHeight;
    //     }
    //     stack.push(i);
    // }
    // return ans;
}
```