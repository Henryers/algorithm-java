package leetcode;

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
