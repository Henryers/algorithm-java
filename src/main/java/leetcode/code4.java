package leetcode;

public class code4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0;
        // 合并数组，本来升序好，现在合并用滑动窗口！！！
        int[] nums3 = new int[nums1.length + nums2.length];
        int i = 0;
        int l = 0;
        int r = 0;
        while (l != nums1.length && r != nums2.length){   // 数组越界，停止添加
            if (nums1[l] == nums2[r]){        // 相等，加一个就行(你力扣要重复就加两次！)
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

    public static void main(String[] args) {
        int[] nums1 = {1,3,7};
        int[] nums2 = {2,6,9};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
