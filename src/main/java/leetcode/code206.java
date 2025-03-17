package leetcode;

public class code206 {
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
        // if (head == null || head.next == null) return head;
        // ListNode newHead = reverseList(head.next);
        // head.next.next = head;
        // head.next = null;
        // return newHead;
    }
}
