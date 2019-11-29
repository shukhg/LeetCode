







class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        if(l1 == NULL){
            return l2;
        }
        if(l2 == NULL){
            return l1;
        }
        ListNode* new_head = new ListNode(0);
        ListNode* q = new_head;  
        ListNode* p1 = l1;
        ListNode* p2 = l2;
        while(p1 != NULL && p2 != NULL){
            if(p1->val < p2->val){
                q->next = p1;
                p1 = p1->next;
                q = q->next;
            }
            else{
                q->next = p2;
                p2 = p2->next;
                q = q->next;
            }
        }
        if(p1 == NULL){
            while(p2 != NULL){
                q->next = p2;
                p2 = p2->next;
                q = q->next;
            }
        }
        else{
            while(p1 != NULL){
                q->next = p1;
                p1 = p1->next;
                q = q->next;
            }
        }
        return new_head->next;
    }
};






