/*
关键是想清楚逻辑，想明白了实现就简单，没必要看别人的代码。
（1）第一个栈 stack1 专门用来进队操作
（2）第二个栈 stack2 专门用来出队操作
（3）基于上面两点，出队和peek()方法的时候，是先把 stack1中的元素加入到 stack2，然后出队或者取peek(),再把元素倒回到 stack1 这样就完成了一次过程
*/

class MyQueue {
    Stack<Integer> stack1 ;   // stack1 用来入队
    Stack<Integer> stack2 ;   // stack2 用来出队
    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        while(stack1.size() > 0 ){
            int temp = stack1.pop();
            stack2.push(temp);
        }
        int result = stack2.pop();
        while(stack2.size() > 0){
            int temp = stack2.pop();
            stack1.push(temp);
        }
        return result;
    }
    
    /** Get the front element. */
    public int peek() {
        while(stack1.size() > 0 ){
            int temp = stack1.pop();
            stack2.push(temp);
        }
        int result = stack2.peek();
        while(stack2.size() > 0){
            int temp = stack2.pop();
            stack1.push(temp);
        }
        return result;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        if(stack1.isEmpty() == true)
            return true;
        else
            return false;
    }
}