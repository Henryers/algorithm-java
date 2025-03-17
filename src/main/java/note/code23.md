# LeetCode_23：合并K个升序链表

给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。

# 示例

**示例 1：**

>输入：lists = [[1,4,5],[1,3,4],[2,6]]  
输出：[1,1,2,3,4,4,5,6]  
解释：链表数组如下：  
```
[
1->4->5,
1->3->4,
2->6
]
```
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6

**示例 2：**

>输入：lists = []  
输出：[]

**示例 3：**

>输入：lists = [[]]  
输出：[]

# 提示

- k == lists.length
- 0 <= k <= 10^4
- 0 <= lists[i].length <= 500
- -10^4 <= lists[i][j] <= 10^4
- lists[i] 按 升序 排列
- lists[i].length 的总和不超过 10^4

# 解答
二分递归，回溯时合并两个有序链表，像21题那样
```java
public class code23 {

    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        // 二分递归到底层: mergeTwoLists(list[l], list[r]);
        //                mergeTwoLists(null, list[r]);   ... 等等情况
        // 可认为回溯时不断向上merge两边，类似归并排序那样
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    // 嘻嘻，完全就是21题自己写的代码耶！！！
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        ListNode cur = new ListNode();
        ListNode res = cur;
        while (cur1 != null || cur2 != null) {
            if (cur2 == null || (cur1 != null && cur1.val <= cur2.val)) {
                cur.next = cur1;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        return res.next;
    }
}
```