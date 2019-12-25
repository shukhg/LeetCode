/*
（1）先写个函数判断一个字符串是否为回文串
（2）剩下的都是常规的 dfs 的框架
*/



// 每次遍历到一个字符都判断
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if(s == null || s.length() == 0)    return result;
        dfs(result, s, new ArrayList<String>(), 0);
        return result;
    }
    public void dfs(List<List<String>> result, String s, List<String> temp, int index){
        if(index == s.length()){
            result.add(new ArrayList<String>(temp));
            return ;
        }
        for(int i = index; i < s.length(); i ++){
            if(judge(s.substring(index, i + 1))){
                temp.add(s.substring(index, i + 1));
                dfs(result, s, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
    public boolean judge(String s){
        int left = 0, right = s.length() - 1;
        while(left <= right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }
}