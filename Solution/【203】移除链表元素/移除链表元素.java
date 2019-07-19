/*
只要从左往右遍历即可
*/


public class Solution {
    public ListNode removeElements(ListNode head, int val) {
    	ListNode addNode = new ListNode(0);
    	addNode.next = head;
    	
    	ListNode previous = addNode;
    	ListNode current = head;
    	
        while(current != null){
        	if(current.val == val){
        		previous.next = current.next;
        		current = current.next;
        	} else {
        		previous = current;
        		current = current.next;
        	}
        }
        return addNode.next;
    }
}
