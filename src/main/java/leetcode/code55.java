package leetcode;

public class code55 {
    public boolean canJump(int[] nums) {
        int farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == farthest) {
                return false;
            }
        }
        return true;
    }
}
