package leetcode;

public class code2 {

    // Definition for singly-linked list.*
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

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode res = dummyHead;
        int add1 = 0;
        while (l1 != null || l2 != null || add1 == 1) {
            res.next = new ListNode();
            res = res.next;
            int l1_value = l1 == null ? 0 : l1.val;
            int l2_value = l2 == null ? 0 : l2.val;
            res.val = (l1_value + l2_value + add1) % 10;
            add1 = (l1_value + l2_value + add1) / 10;
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }
        return dummyHead.next;

//        没用哑节点，明显后面判断复杂得要死，不仅要判断当前节点是否为null，还要判断 xxx.next == null ?
//        ListNode res = new ListNode();
//        ListNode result = res;
//        int add1 = 0;
//        while (l1 != null || l2 != null || add1 == 1) {
//            int l1_value = l1 == null ? 0 : l1.val;
//            int l2_value = l2 == null ? 0 : l2.val;
//            res.val = (l1_value + l2_value + add1) % 10;
//            add1 = (l1_value + l2_value + add1) / 10;
//            if (add1 != 0 || (l1 != null && l1.next != null) || (l2 != null && l2.next != null)) {
//                res.next = new ListNode();
//            }
//            l1 = l1 == null ? l1 : l1.next;
//            l2 = l2 == null ? l2 : l2.next;
//            res = res.next;
//        }
//        return result;
    }

    public static void print(ListNode res) {
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = 9;
        l1.next = new ListNode();
        l1.next.val = 9;
        l1.next.next = new ListNode();
        l1.next.next.val = 9;

        ListNode l2 = new ListNode();
        l2.val = 9;
        l2.next = new ListNode();
        l2.next.val = 9;

        ListNode listNode = addTwoNumbers(l1, l2);
        print(listNode);
    }
}
