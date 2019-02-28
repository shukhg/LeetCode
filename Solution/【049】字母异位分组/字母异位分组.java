/*
利用 HashMap<String,Integer> 存储单个单词重排-第count个List
只有这种才可以让重排后相同的单词加到一条链中。要想到利用 count进行计数
*/


class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if(strs == null || strs.length == 0)
            return result;
        HashMap<String , Integer> hashmap = new HashMap<>();
        int index = 0;   // 表示有几个list 的索引
        for(int i = 0 ; i < strs.length ; i ++){
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String str = String.valueOf(arr);
            if(hashmap.containsKey(str)){
                result.get(hashmap.get(str)).add(strs[i]);   // 当前字符加入相应的队列
            }
            else{
                hashmap.put(str , index);
                index ++;
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                result.add(list);
            }
        }
        return result;
    }
}