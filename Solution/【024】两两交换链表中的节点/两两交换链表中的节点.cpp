


class Solution {
public:
    ListNode* swapPairs(ListNode* head) {
        if(head == nullptr || head->next == nullptr){
            return head;
        }
        ListNode* new_head = new ListNode(0);
        ListNode* p = head;   // 移动的工作指针
        ListNode* q = new_head;   // 已经交换结束的部分的末尾指针
        ListNode* r = new_head;   // 记录 p->next->next
        while(p && p->next){
            r = p->next->next;
            q->next = p->next;
            p->next->next = p;
            p->next = r;
            q = p;
            p = r;
        }
        return new_head->next;
    }
};
