# LeetCode_24：两两交换链表中的节点

给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。

# 示例

**示例 1：**

>输入：head = [1,2,3,4]  
输出：[2,1,4,3]

**示例 2：**

>输入：head = []  
输出：[]

**示例 3：**

>输入：head = [1]  
输出：[1]

# 提示

- 链表中节点的数目在范围 [0, 100] 内
- 0 <= Node.val <= 100

# 解答
- 处理两个交换就好，后面的子过程用递归
- 刚好0-1个节点就是递归的退出条件
```java
public class code24 {
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
```