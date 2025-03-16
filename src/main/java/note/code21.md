# LeetCode_21：合并两个有序链表

将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

# 示例

**示例 1：**

>输入：l1 = [1,2,4], l2 = [1,3,4]  
输出：[1,1,2,3,4,4]  

**示例 2：**

>输入：l1 = [], l2 = []  
输出：[]  

**示例 3：**

>输入：l1 = [], l2 = [0]  
输出：[0]

# 提示

- 两个链表的节点数目范围是 [0, 50]
- -100 <= Node.val <= 100
- l1 和 l2 均按 非递减顺序 排列

# 解答
就是哪边小加哪个，注意一下判断即可，因为可能有链表先遍历完，得做一下null判断（此处代码经过浓缩简略）
```java
public class code21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        ListNode cur = new ListNode();
        ListNode res = cur;
        while(cur1 != null || cur2 != null){
            if (cur2 == null || (cur1 != null && cur1.val <= cur2.val)){
                cur.next = cur1;
                cur1 = cur1.next;
            }else{
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        return res.next;
    }
}
```