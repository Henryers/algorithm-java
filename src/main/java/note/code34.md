# LeetCode_34：在排序数组中找目标元素的左右区间

如：`[1,2,2,3,4,5,5,5,6,7,7]`，找`5`，那返回`[5,7]`，不存在返回`[-1,-1]`

# 解答

两次二分，分别寻找区间的最左侧和最右侧下标，各比二分多判断一个`=`的情况而已

```java
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
```