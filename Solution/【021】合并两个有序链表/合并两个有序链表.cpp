







class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode* new_head = new ListNode(0);
        ListNode* q = new_head;  
        while(l1 != nullptr && l2 != nullptr){
            if(l1->val < l2->val){
                q->next = l1;
                l1 = l1->next;
                q = q->next;
            }
            else{
                q->next = l2;
                l2 = l2->next;
                q = q->next;
            }
        }
        if(l1 == nullptr){
            q->next = l2;
        }
        else{
            q->next = l1;
        }
        return new_head->next;
    }
};





