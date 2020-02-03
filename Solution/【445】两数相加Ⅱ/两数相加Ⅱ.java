/*
（1）因为此题是从后面对齐，可以想到先把长度补齐（一个链表先走几步），但是这样不太好操作
（2）从后面对齐可以想到用 栈，注意这种需要从后往前的问题，很多时候都是需要利用栈
（3）两个栈只要有一个不为空 并且 没有进位 就一直循环，注意这里有对进位的判断
（4）生成的新的结点是用 头插法 实现反向
*/



class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack();
        Stack<Integer> stack2 = new Stack();
        ListNode node1 = l1;
        while(node1 != null){
            stack1.push(node1.val);
            node1 = node1.next;
        }
        ListNode node2=l2;
        while(node2 != null){
            stack2.push(node2.val);
            node2 = node2.next;
        }
        ListNode head = null;
        int flag = 0;  // 用来表明进位
        while(!stack1.isEmpty() || !stack2.isEmpty() || flag!=0){  // 这里要添加没有进位的判断
            int value = 0;
            if(!stack1.isEmpty())
                value = value + stack1.pop();
            if(!stack2.isEmpty())
                value = value + stack2.pop();
            value = value + flag;
            ListNode node = new ListNode(value % 10);
            flag = value / 10;
            node.next = head;   // 头插法
            head = node;
        }
       return head;
    }
}

