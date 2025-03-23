package leetcode;

public class code34 {
    public int[] searchRange(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int first = -1;
        int last = -1;
        // 找第一个等于target的位置
        while (l <= r) {
            int middle = (l + r) >> 1;
            if (nums[middle] == target) {
                first = middle;
                r = middle - 1; //重点：我当然可以往左移啊，因为我上面都已经记录first的下标了
            } else if (target <= nums[middle]) {
                r = middle - 1;
            } else {
                l = middle + 1;
            }
        }

        // 最后一个等于target的位置
        l = 0;
        r = nums.length - 1;
        while (l <= r) {
            int middle = (l + r) >> 1;
            if (nums[middle] == target) {
                last = middle;
                l = middle + 1; // 重点：我当然可以往右移啊，因为我上面已经记录last的下标了
            } else if (nums[middle] > target) {
                r = middle - 1;
            } else {
                l = middle + 1;
            }
        }

        return new int[]{first, last};
    }
}
