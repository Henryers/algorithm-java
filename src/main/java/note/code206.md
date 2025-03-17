# LeetCode：反转链表

给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。

# 示例

**示例 1：**

>输入：head = [1,2,3,4,5]  
输出：[5,4,3,2,1]

**示例 2：**

>输入：head = [1,2]  
输出：[2,1]

**示例 3：**

>输入：head = []  
输出：[]


# 提示

- 链表中节点的数目范围是 [0, 5000]
- -5000 <= Node.val <= 5000

# 解答
反转可以用迭代，传统四步骤；或者用递归
```java
public class code206 {
    public ListNode reverseList(ListNode head) {
        // 迭代写法
        ListNode cur = head;
        ListNode pre = null;
        ListNode to = null;
        while(cur != null){
            to = cur.next;
            cur.next = pre;
            pre = cur;
            cur = to;
        }
        return pre;

        // 递归写法
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

```