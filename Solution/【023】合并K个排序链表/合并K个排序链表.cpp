/*
（1）一个新思路，将原先给定的数组或者 vector 转换成 queue，方便进行归并
（2）注意双端队列的写法，以及转换方法   deque<ListNode*> my_queue = deque<ListNode*>(lists.begin(), lists.end()) 
                                    deque<int> my_queue = deque<int>(num, my_vector + n)
（3）注意双端队列的用法，front()  pop_front()  push_front()
*/





class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        if(lists.empty() || lists.size() == 0)  return nullptr;
        if(lists.size() == 1)   return lists[0];
        deque<ListNode*> my_queue = deque<ListNode*>(lists.begin(), lists.end());
        while(my_queue.size() != 1){
            ListNode* l1 = my_queue.front();
            my_queue.pop_front();
            ListNode* l2 = my_queue.front();
            my_queue.pop_front();
            my_queue.push_back(merge(l1, l2));
        }
        return my_queue.front();
    }
    ListNode* merge(ListNode* l1, ListNode* l2){
        ListNode* new_head = new ListNode(0);
        ListNode* q = new_head;
        while(l1 && l2){
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
        if(l1 == nullptr)   q->next = l2;
        else    q->next = l1;
        return new_head->next;
    }
};

