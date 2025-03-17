package leetcode;

public class code25 {
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

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        reverseKGroup(head, 2);
    }
}
