package leetcode;

import java.util.HashMap;

public class code1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            // target - 当前的数，看得到的结果是否在map里，如果在，那结合两个索引后return
            int tmp = target - nums[i];
            if(map.containsKey(tmp)) return new int[] {map.get(tmp), i};
            map.put(nums[i], i);
        }
        return new int[] {1, 1};
    }
}