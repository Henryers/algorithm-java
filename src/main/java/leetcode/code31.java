package leetcode;

public class code31 {
    public void nextPermutation(int[] nums) {
        int N = nums.length;
        // 从右往左第一次降序的位置
        int firstLess = -1;
        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstLess = i;
                break;
            }
        }
        // 4321 -> 1234  表示已经到达最大排列，因此变成最小排列
        if (firstLess < 0) {
            reverse(nums, 0, N - 1);
        } else {
            int rightClosestMore = -1;
            // 找最靠右的、同时比nums[firstLess]大的数，位置在哪
            // 这里其实也可以用二分优化，但是这种优化无关紧要了
            for (int i = N - 1; i > firstLess; i--) {
                if (nums[i] > nums[firstLess]) {
                    rightClosestMore = i;
                    break;
                }
            }
            swap(nums, firstLess, rightClosestMore);
            // > firstLess一定降序排列，reverse就行（不信？自己举例子看看！）
            // 5 9 8 7 6 4 3 2 1
            // |       |            firstless 和 rightClosestMore 交换
            // 6 9 8 7 5 4 3 2 1    可发现firstless的右侧严格降序排列，这部分reverse就行！
            reverse(nums, firstLess + 1, N - 1);
        }
    }

    public static void reverse(int[] nums, int L, int R) {
        while (L < R) {
            swap(nums, L++, R--);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
