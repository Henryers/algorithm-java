package leetcode;

import java.util.*;

public class code18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; first++) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            for (int second = first + 1; second < n; second++) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // d 对应的指针初始指向数组的最右端
                int fourth = n - 1;
                long tmptarget = (long) target - nums[first] - nums[second]; // 防止溢出
                // 枚举 c
                for (int third = second + 1; third < n; third++) {
                    // 需要和上一次枚举的数不相同
                    if (third > second + 1 && nums[third] == nums[third - 1]) {
                        continue;
                    }
                    // 需要保证 c 的指针在 d 的指针的左侧
                    while (third < fourth && nums[third] + nums[fourth] > tmptarget) {
                        --fourth;
                    }
                    // 如果一直> tmptarget，直到指针重合
                    // 随着 c 后续的增加, c+d 恒> tmptarget，还不如直接break
                    if (third == fourth) {
                        break;
                    }
                    if (nums[third] + nums[fourth] == tmptarget) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[third]);
                        list.add(nums[fourth]);
                        ans.add(list);
                    }
                }
            }
        }
        return ans;
    }
}
