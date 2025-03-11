package leetcode;

import java.util.Arrays;

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
