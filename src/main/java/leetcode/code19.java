package leetcode;

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
