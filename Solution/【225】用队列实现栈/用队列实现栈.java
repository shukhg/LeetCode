/*
（1）用两个队列实现
（2）调用构造器的时候也要调用两个队列的构造器
（3）push() 操作的一定是每次往非空（除非两个全为空）的队列添加元素
（4）pop() 操作是一个队列往另一个队列中加入元素，直到剩余一个元素,然后将这个剩余的元素poll()
（5）top() 操作类似于 pop() ，注意其中的细节，最好是用result存储，注意要将全部元素放到另一个队列后再return
（6）判断为空时要两个队列均为空
*/




class MyStack {
    Queue<Integer> ListA;    //实现栈要两个队列
    Queue<Integer> ListB;
    /** Initialize your data structure here. */
    public MyStack() {
        ListA = new LinkedList<Integer>();
        ListB = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        if(ListA.isEmpty() == false){       //一次操作结束后一定是有一个队列为空的
            ListA.offer(x);                //每次 push 要对非空队列操作
        }
        else{
            ListB.offer(x);
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int result = 0;
        if(ListA.isEmpty() == false){
            while(ListA.size() > 1){           //出队至剩余一个元素
                int temp = ListA.poll();
                ListB.offer(temp);
            }
            result = ListA.poll();
        }
        else{
            while(ListB.size() > 1){
                int temp = ListB.poll();
                ListA.offer(temp);
            }
            result = ListB.poll();
        }
        return result;
    }
    
    /** Get the top element. */
    public int top() {              //    top操作和pop操作相似，注意区别 
        int result = 0;
        if(ListA.isEmpty() == false){
            while(ListA.size() > 1){
                int temp = ListA.poll();
                ListB.offer(temp);
            }
            result = ListA.poll();
            ListB.offer(result);
        }
        else{
            while(ListB.size() > 1){
                int temp = ListB.poll();
                ListA.offer(temp);
            }
            result = ListB.poll();
            ListA.offer(result);
        }
        return result;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        if(ListA.isEmpty() == true && ListB.isEmpty() == true){
            return true;
        }
        else{
            return false;
        }
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */