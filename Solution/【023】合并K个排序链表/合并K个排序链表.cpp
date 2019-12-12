<<<<<<< HEAD
/*
（1）一个新思路，将原先给定的数组或者 vector 转换成 queue，方便进行归并
（2）注意双端队列的写法，以及转换方法   deque<ListNode*> my_queue = deque<ListNode*>(lists.begin(), lists.end()) 
                                    deque<int> my_queue = deque<int>(num, my_vector + n)
（3）注意双端队列的用法，front()  pop_front()  push_front()
*/
=======
>>>>>>> a281a274fb275d4086ca5ecc03b84e8919d4c44b





class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
<<<<<<< HEAD
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
=======
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
>>>>>>> a281a274fb275d4086ca5ecc03b84e8919d4c44b
            if(l1->val < l2->val){
                q->next = l1;
                l1 = l1->next;
                q = q->next;
            }
            else{
                q->next = l2;
                l2 = l2->next;
<<<<<<< HEAD
                q = q->next;
            }
        }
        if(l1 == nullptr)   q->next = l2;
        else    q->next = l1;
=======
                q = q ->next;
            }
        }
        if(l1 == NULL){
            q->next = l2;
        }
        else{
            q->next = l1;
        }
>>>>>>> a281a274fb275d4086ca5ecc03b84e8919d4c44b
        return new_head->next;
    }
};

<<<<<<< HEAD
=======


>>>>>>> a281a274fb275d4086ca5ecc03b84e8919d4c44b
