# LeetCode_2：两数之和
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。


# 示例
**示例 1：**

>输入：l1 = [2,4,3], l2 = [5,6,4]  
输出：[7,0,8]  
解释：342 + 465 = 807.

**示例 2：**

>输入：l1 = [0], l2 = [0]  
输出：[0]  

**示例 3：**

>输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]  
输出：[8,9,9,9,0,0,0,1]


# 提示

- 每个链表中的节点数在范围 [1, 100] 内  
- 0 <= Node.val <= 9  
- 题目数据保证列表表示的数字不含前导零

# 解答
不管有没有节点，都相加，再加进位符，null视为0

```java
public class code2 {
    // ListNode 有 val、next 两个属性
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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
    }
}
```