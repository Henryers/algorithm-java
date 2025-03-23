package leetcode;

public class code33 {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {  // 旋转下标靠右侧，左侧正常升序
                if (nums[0] <= target && target < nums[mid]) {  // 正常的左侧来判断二分
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {   // 旋转下标靠左侧，右侧正常升序
                if (nums[mid] < target && target <= nums[n - 1]) {  // 正常的右侧来判断二分
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
