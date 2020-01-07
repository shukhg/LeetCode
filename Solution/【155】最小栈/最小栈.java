/*
首先，初始化的构造方法是可以为空的，只要在前面初始化了，当然也可以留在构造方法中初始化
此外，要理解题目的思路。
我们要用两个栈实现，一个 stack 和 一个 minstack 
最直观的思路是，每次判断入栈的元素和当前最小栈 minstack 的栈顶元素的大小，如果小于minstack 栈顶元素就把与minstack 的栈顶元素再次入 minstack
这样可能 minstack 中多个元素相同，而且保持了与 stack中元素的同步
要取出最小的元素，就直接 minstack.peek()即可
*/


class MinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minstack =  new Stack<>();
    /** initialize your data structure here. */
    public MinStack() {
        
    }
    
    public void push(int x) {
        if(minstack.isEmpty() == true || minstack.peek() > x ){
            minstack.push(x);
        }
        else{
            minstack.push(minstack.peek());
        }
        stack.push(x);
    }
    
    public void pop() {
        minstack.pop();
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minstack.peek();
    }
}
