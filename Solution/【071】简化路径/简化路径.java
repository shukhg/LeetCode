/*
（1）先把字符串以 "/" 为分隔符分割成数组,此时数组有 "路径"、""、"."、".." 这四种情况
（2）遍历分割结果数组,当 s[i].equals("..") 并且栈不空时 pop, 
当 s[i].equals("")  == false && s[i].equals(".") == false && s[i].equals("..") == false, 即 s[i] 是路径的时候，则入栈
（3）栈空,返回"/",栈非空,用StringBuffer做一个连接返回即可
*/



class Solution {
    public String simplifyPath(String path) {
        String[] s = path.split("/");
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < s.length; i ++){
            if(stack.isEmpty() == false && s[i].equals("..")){
                stack.pop();
            }
            else if(s[i].equals("") == false && s[i].equals(".") == false && s[i].equals("..") == false){
                stack.push(s[i]);
            }
        }
        if(stack.isEmpty() == true){
            return "/";
        }

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < stack.size(); i ++){
            result.append("/" + stack.get(i));    
        }
        return result.toString();
    }
}