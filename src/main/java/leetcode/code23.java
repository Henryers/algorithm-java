package leetcode;

public class code23 {

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
