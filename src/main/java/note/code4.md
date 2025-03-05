# LeetCode_4：寻找两个正序数组的中位数
给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

算法的时间复杂度应该为 O(log (m+n)) 。


# 示例

**示例 1：**

>输入：nums1 = [1,3], nums2 = [2]  
输出：2.00000  
解释：合并数组 = [1,2,3] ，中位数 2  

**示例 2：**

>输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

# 提示

- nums1.length == m
- nums2.length == n
- 0 <= m <= 1000
- 0 <= n <= 1000
- 1 <= m + n <= 2000
- -106 <= nums1[i], nums2[i] <= 106

# 解答
双指针or滑动窗口，O(m+n)，线性，没优化到log，题解太复杂了没必要...
```java
public class code4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0;
        // 合并数组，本来升序好，现在合并用滑动窗口！！！
        int[] nums3 = new int[nums1.length + nums2.length];
        int i = 0;
        int l = 0;
        int r = 0;
        while (l != nums1.length && r != nums2.length){   // 数组越界，停止添加
            if (nums1[l] == nums2[r]){        // 相等，加一个就行(你力扣要求重复就加两次！)
                nums3[i] = nums1[l];
                nums3[++i] = nums2[r];
                l++;
                r++;
            }else if (nums1[l] < nums2[r]){   // 左边小加完跳
                nums3[i] = nums1[l];
                l++;
            }else {                           // 右边小加完跳
                nums3[i] = nums2[r];
                r++;
            }
            i++;
        }
        if (l == nums1.length){
            // 把nums2剩余的部分直接搬到nums3后面
            while (r != nums2.length){
                nums3[i] = nums2[r];
                r++;
                i++;
            }
        }else {
            // 把nums1剩余的部分直接搬到nums3后面
            while (l != nums1.length){
                nums3[i] = nums1[l];
                l++;
                i++;
            }
        }
        // 奇数
        if ((nums3.length & 1) == 1) result = nums3[nums3.length / 2];
        // 偶数
        if ((nums3.length & 1) == 0) result = (double)(nums3[nums3.length / 2] + nums3[nums3.length / 2 - 1] ) / 2;
        return result;
    }
}
```