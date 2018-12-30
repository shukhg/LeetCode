/*
此题的思路很好理解，就和普通的回溯差不多，但是有以下几点要注意
(1)判断是否是回文字符串的时候，尽量用一半字符的方法，而不是逆置再equals()
(2)dfs中循环遍历是没有 start的，此处采用不断更新的 s ，每次循环的终点是 s.length()
    循环变量对应了一个个子串。  记住这种操作，在用到字符串子串的时候经常使用
*/




class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if(s == null || s.length() == 0)
            return result;
        dfs(result , s , new ArrayList<String>() );
        return result;
    }
    public void dfs(List<List<String>> result , String s , List<String> list ){
        if( s.length() == 0 ) {
            result.add(new ArrayList<String>(list));
            return ;
        }    // s 在此处也是不断更新，每次都是一个子串
        for(int i = 0 ; i < s.length() ; i ++){  // 此处没用 start，而是用了 substring()
            if( judge(s.substring(0 , i + 1) ) ){
                list.add(s.substring(0, i + 1 ));
                dfs(result , s.substring( i + 1 ) , list );   // 注意此处更新后的 s 是剩余部分的 s
                list.remove(list.size() - 1);
            }
        }
    }
    public boolean judge(String s){  // 最好是用这个判断是否是回文串，如果全部生成逆置字符串再判断会超出内存
        for(int i = 0 ; i < s.length() / 2 ; i ++){
            if(s.charAt(i) != s.charAt(s.length() - 1 - i)){
                return false;
            }
        }
        return true;
    }
}