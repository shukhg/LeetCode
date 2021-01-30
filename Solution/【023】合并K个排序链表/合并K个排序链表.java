/*
（1）将数组转化为队列
（2）每次从队列中取出两个元素，然后将这两个元素进行合并，将合并的结果重新放到链表中，直到队列长度为 1
*/



class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int size = lists.length;
        if(size == 0)   return null;
        if(size == 1)   return lists[0];
        Queue<ListNode> queue = new LinkedList<>(Arrays.asList(lists));
        while(queue.size() > 1){
            ListNode l1 = queue.poll();
            ListNode l2 = queue.poll();
            queue.offer(mergeTwoLists(l1, l2));
        }
        return queue.poll();
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode new_head = new ListNode(0);
        ListNode q = new_head;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                q.next = l1;
                l1 = l1.next;
                q = q.next;
            }
            else{
                q.next = l2;
                l2 = l2.next;
                q = q.next;
            }
        }
        if(l1 == null){
            q.next = l2;
        }
        else{
            q.next = l1;
        }
        return new_head.next;
    }
}



// 二分
class Solution {
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
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }
}



// 最简单的办法，所有元素存入 list 然后排序，最后生成结点
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0 ){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        ListNode result = null;
        for(int i = 0 ; i < lists.length ; i ++){   // 全部存储到 list 中
            ListNode temp = lists[i];
            while(temp != null){
                list.add(temp.val);
                temp = temp.next;
            }
        }
        Collections.sort(list);
        if(list.size() == 0)
            return result;
        ListNode head = new ListNode(0);
        ListNode p = head;
        for(int i = 0 ; i < list.size(); i ++){   // 从 list中新建结点 
            ListNode node = new ListNode(list.get(i));
            p.next = node;
            p = node;
        }
        return head.next;
    }
}