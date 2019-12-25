/*
利用 HashMap<String,Integer> 存储单个单词重排-第count个List
最简单的，将每个字符串进行排序（toCharArray进行 sort，再 String.valueOf(arr)），然后判断 哈希表 里面有没有，再添加到相应的结果队列中即可
但是对字符串排序的过程耗费时间复杂度，可以统计字符串中每个字符的出现次数，然后用出现次数组成新的字符串，判断 哈希表 中是否存在这新的字符串即可
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



