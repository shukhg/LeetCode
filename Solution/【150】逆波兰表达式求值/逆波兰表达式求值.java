/*
后缀表达式求值，利用栈，遇到数字就入栈，遇到运算符就出栈两个元素进行运算，再将结果入栈。
注意数组只有一个元素的情况
*/



class Solution {
    public int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length == 0)
            return 0;
        if(tokens.length == 1)
            return Integer.parseInt(tokens[0]);
        Stack<Integer> stack = new Stack<>();
        HashSet<String> hashset = new HashSet<>();
        hashset.add("+");
        hashset.add("-");
        hashset.add("*");
        hashset.add("/");
        int temp = 0;
        for(int i = 0 ; i < tokens.length ; i ++){
            if(hashset.contains(tokens[i]) == false ){
                int number = Integer.parseInt(tokens[i]);
                stack.push(number);
            }
            else{
                int num1 = stack.pop();
                int num2 = stack.pop();
                if(tokens[i].equals("+")){
                    temp = num1 + num2;
                    stack.push(temp);
                }
                else if(tokens[i].equals("-")){
                    temp = num2 - num1;
                    stack.push(temp);
                }
                else if(tokens[i].equals("*")){
                    temp = num2 * num1;
                    stack.push(temp);
                }
                else{
                    temp = num2 / num1;
                    stack.push(temp);
                }
            }
        }
        return temp;
    }
}