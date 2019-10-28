/**
注意 初始化链表的时候是要带  *
 */






class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        if(l1 == NULL && l2 == NULL){
            return NULL;
        }
        if(l1 == NULL){
            return l2;
        }
        if(l2 == NULL){
            return l1;
        }
        ListNode *result = new ListNode(0);
        ListNode *temp = result;
        while(true){
            if(l1 != NULL){
                temp->val = temp->val + l1->val;
                l1 = l1->next;
            }
            if(l2 != NULL){
                temp->val = temp->val + l2->val;
                l2 = l2->next;
            }
            temp->next = new ListNode(temp->val / 10);
            temp->val = temp->val % 10;
            if(l1 == NULL && l2 == NULL){
                if(temp->next->val == 0){
                    temp->next = NULL;
                }
                break;
            }
            temp = temp->next;
        }
        return result;
    }
};