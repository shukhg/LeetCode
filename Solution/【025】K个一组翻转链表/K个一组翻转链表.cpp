/*
(1)

*/




class Solution {
public:
    ListNode* reverseKGroup(ListNode* head, int k) {
        if(head == nullptr) return nullptr;
        if(k <= 0)  return nullptr;
        if(k == 1)  return head;
        ListNode* new_head = new ListNode(0);
        new_head->next = head;
        ListNode* temp = new_head;
        while(temp->next != nullptr){
            temp = reverseSingleK(temp, k);
        }
        return new_head->next;
    }
    ListNode* reverseSingleK(ListNode* before, int k){   // 每次翻转 before 之后的 k 个
        ListNode* pre = before->next;     // pre 是 K 个中的第一个
        ListNode* cur = pre->next;          // cur 是 K 个中的第二个
        int flag = 0;
        for(int i = 1 ; i < k ; i ++){    // pre 是 K 个中的第一个，所以从 1 开始
            if(cur == nullptr){
                flag = i;
                break;
            }
            ListNode* temp = cur->next;     //  temp 用来暂存 cur->next，下面四行都是 头插法
            cur->next = pre;            
            pre = cur;
            cur = temp;
        }
        if(flag != 0){            //  不足 k 的再进行一次翻转
            ListNode* result = pre;
            cur = pre->next;
            for(int i = 2; i < flag; i ++){
                ListNode* temp = cur->next;
                cur->next = pre;
                pre = cur;
                cur = temp;
            }
            result->next = nullptr;
            return result;
        }
        else{                   // 够 k 个就不进行翻转
            ListNode* result = before->next;
            before->next = pre;
            result->next = cur;
            return result;
        }
    }
};
