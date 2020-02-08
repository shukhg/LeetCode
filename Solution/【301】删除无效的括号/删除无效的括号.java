/*
（1）此题与之前的"括号生成"是相反的过程，生成时我们需要记录左括号和右括号的数目，删除的时候我们需要记录不合法括号的数目
（2）left 和 right 记录不合法的左右括号的数目，遇到 '('则 left++，遇到 ')' 要么 left-- 要么 right ++，所以进入 dfs 之前 left 和 right 其中之一为 0
（3）用 dfs 不断删除 '(' 或者 ')'，可利用两个字串进行拼接实现
（4）常用的去除重复的套路    if(i > start && s.charAt(i) == s.charAt(i - 1))
（5）写个 judge 函数判断当前的字符串是否符合要求。如果 left == 0 && right == 0，则判断是否符合要求，符合要求则加入到结果中
（6）注意所有遍历的 for 循环都不能 if-else，因为除了 '(' 和 ')' 还有普通的字母
*/




class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        int left = 0, right = 0;
        for(char ch: s.toCharArray()){
            if(ch == '('){
                left ++;
                continue;
            }
            if(ch == ')'){
                if(left > 0)    left --;
                else    right ++;
            }
        }
        dfs(result, s, 0, left, right);
        return result;
    }
    public void dfs(List<String> result, String s, int start, int left, int right){
        if(left == 0 && right == 0){
            if(judge(s)){
                result.add(s);
            }
            return ;
        }
        for(int i = start; i < s.length(); i ++){
            if(i > start && s.charAt(i) == s.charAt(i - 1)){
                continue;
            }
            if(left > 0 && s.charAt(i) == '('){
                dfs(result, s.substring(0, i) + s.substring(i + 1, s.length()), i, left - 1, right);
                continue;
            }
            if(right > 0 && s.charAt(i) == ')'){
                dfs(result, s.substring(0, i) + s.substring(i + 1, s.length()), i, left, right - 1);
            }
        }
    }

    public boolean judge(String s){   // 判断字符串是否有效
        int count = 0;
        for(char ch: s.toCharArray()){
            if(ch == '('){
                count ++;
                continue;
            }
            if(ch == ')'){
                if(count == 0)  return false;
                count --;
            }
        }
        return count == 0;
    }
}