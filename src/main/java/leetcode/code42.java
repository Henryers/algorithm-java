package leetcode;

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
