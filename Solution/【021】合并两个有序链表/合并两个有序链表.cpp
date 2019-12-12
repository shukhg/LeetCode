







class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode* new_head = new ListNode(0);
        ListNode* p = new_head;
        while(l1 != nullptr && l2 != nullptr){
            if(l1->val < l2->val){
                p->next = l1;
                l1 = l1->next;
                p = p->next;
            }
            else{
                p->next = l2;
                l2 = l2->next;
                p = p->next;
            }
        }
        if(l1 == nullptr){
            p->next = l2;
        }
        else{
            p->next = l1;
        }
        return new_head->next;
    }
};






