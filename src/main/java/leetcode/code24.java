package leetcode;

public class code24 {

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


    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        // 至少有两个节点，需要交换
        ListNode next = head.next;
        // 每两个交换都是一个子过程，next指向后面的东西可以用递归
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}
