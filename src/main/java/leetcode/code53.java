package leetcode;

public class code53 {
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            tmp += nums[i];            // 不断向右扩
            res = Math.max(res, tmp);  // 始终记录，不会遗漏
            tmp = tmp >= 0 ? tmp : 0;  // 确保前缀这边>0
        }
        return res;
    }
}
