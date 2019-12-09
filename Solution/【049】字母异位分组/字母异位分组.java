/*
利用 HashMap<String,Integer> 存储单个单词重排-第count个List
只有这种才可以让重排后相同的单词加到一条链中。要想到利用 count进行计数
*/


// 利用字符出现次数进行计数，判断两个字符串是否相同
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hashmap = new HashMap<>();
        for(int i = 0; i < strs.length; i ++){
            int[] num = new int[26];
            for(int j = 0; j < strs[i].length(); j ++){
                num[strs[i].charAt(j) - 'a'] ++;
            }
            String key = "";
            for(int j = 0; j < num.length; j ++){
                key = key + num[j] + "#";
            }
            if(hashmap.containsKey(key)){
                hashmap.get(key).add(strs[i]);
            }
            else{
                List<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                hashmap.put(key, temp);
            }
        }
        return new ArrayList<List<String>>(hashmap.values());
    }
}








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



