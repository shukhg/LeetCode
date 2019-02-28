/*
利用 Stack 和 HashMap ， 遍历到左括号存入 Stack，遍历到右括号就看当前 Stack栈顶的元素是否匹配右括号
如果不匹配就直接返回 flase，匹配就再进行后面的步骤
中间要设置一个count ，如果是左括号就 count++ 右括号就 count -- 
如果中间过程的时候 count < 0 了 说明右括号比左括号多 肯定是 false
最后结束的时候，还要判断 Stack中是否有元素
*/

class Solution {
    public boolean isValid(String s) {
        if(s == null || s.length() == 0)
            return true;
        if(s.length() % 2 == 1)
            return false;
        HashMap<Character , Character> hashmap = new HashMap<>();
        hashmap.put('(' , ')');
        hashmap.put('[' , ']');
        hashmap.put('{' , '}');
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < s.length() ; i ++){
            if(hashmap.containsKey(s.charAt(i))){
                stack.push(s.charAt(i));
            }
            else{
                if( !stack.isEmpty() && hashmap.get(stack.peek()) == s.charAt(i)  ){
                    stack.pop();
                }
                else{
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}