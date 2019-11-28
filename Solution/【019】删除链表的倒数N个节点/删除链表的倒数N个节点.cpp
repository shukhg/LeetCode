






class Solution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        if(head == NULL){
            return NULL;
        }
        ListNode* new_head = new ListNode(0);
        new_head->next = head;
        ListNode* fast = new_head;
        ListNode* slow = new_head;
        while(n != 0){
            fast = fast->next;
            n --;
            if(fast == NULL){
                return NULL;
            }
        }
        while(fast->next != NULL){
            fast = fast->next;
            slow = slow->next;
        }
        slow->next = slow->next->next;
        return new_head->next;
    }
};