# LeetCode_19：删除链表的倒数第N个节点

给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

# 示例

**示例 1：**

>输入：head = [1,2,3,4,5], n = 2  
输出：[1,2,3,5]

**示例 2：**

>输入：head = [1], n = 1  
输出：[]  

**示例 3：**

>输入：head = [1,2], n = 1  
输出：[1]

# 提示

- 链表中结点的数目为 sz
- 1 <= sz <= 30
- 0 <= Node.val <= 100
- 1 <= n <= sz

# 解答
- 先记录链表长度，然后看正序遍历到哪个节点时，让cur.next = cur.next.next即可
- 然后头节点被删除的情况单独处理return即可
```java
public class code19 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        int cutleft = len - n;  // 删除节点的左节点
        if (cutleft == 0) return head.next;  // 把第一个删了，后面可能还有
        cur = head;
        while (cutleft > 1) {
            cur = cur.next;
            cutleft--;
        }
        cur.next = cur.next.next;
        return head;
    }
}
```