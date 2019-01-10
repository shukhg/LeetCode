/*
（1）方法一，可以将所有元素存个 list，然后排序，最后生成结点
*/


// 方法一，所有元素存入 list 然后排序，最后生成结点
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