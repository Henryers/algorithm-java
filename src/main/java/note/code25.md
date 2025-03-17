# LeetCode_25：K个一组翻转链表

给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

# 示例

**示例 1：**

>输入：head = [1,2,3,4,5], k = 2  
输出：[2,1,4,3,5]

**示例 2：**

>输入：head = [1,2,3,4,5], k = 3  
输出：[3,2,1,4,5]

# 提示

- 链表中的节点数目为 n
- 1 <= k <= n <= 5000
- 0 <= Node.val <= 1000

# 解答
用LeetCode_206：反转链表 作为子过程，然后直接递归
```java
public class code25 {
    public static ListNode reverseKGroup(ListNode head, int k) {
        // 退出条件，当head开始的链表节点数不足k个时
        int i = 0;
        ListNode cur = head;
        ListNode pre = cur;
        while (cur != null && i != k) {
            pre = cur;
            cur = cur.next;
            i++;
        }
        // k个节点与后续节点断开，然后这个部分反转，后部分递归，最后加起来
        if (pre != null) pre.next = null;
        if (i < k) return head;
        // 反转前k个节点 + 递归
        ListNode newHead = reverseList(head);
        head.next = reverseKGroup(cur, k);
        return newHead;
    }

    // LeetCode_206
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
```