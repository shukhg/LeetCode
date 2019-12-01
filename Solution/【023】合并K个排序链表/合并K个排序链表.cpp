




class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        int size = lists.size();
        if(size == 0){
            return nullptr;
        }
        if(size == 1){
            return lists[0];
        }
        queue<ListNode*> waiting(deque<ListNode*>(lists.begin(), lists.end())); //将vector转为队列
        //如果队列元素大于1，则取出两个进行合并，合并后的链表继续添加到链表尾部
        while (waiting.size() > 1) {
            ListNode *l1 = waiting.front();
            waiting.pop();
            ListNode *l2 = waiting.front();
            waiting.pop();
            waiting.push(mergeTwoLists(l1, l2));
        } 
        return waiting.front();
    }

    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2){
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
                q = q ->next;
            }
        }
        if(l1 == NULL){
            q->next = l2;
        }
        else{
            q->next = l1;
        }
        return new_head->next;
    }
};



