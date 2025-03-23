package leetcode;

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
