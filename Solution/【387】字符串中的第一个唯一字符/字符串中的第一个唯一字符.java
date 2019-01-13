/*
哈希表暴力解决，如果要遍历哈希表的话，注意要使用 LinkedHashMap
*/


class Solution {
    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0)
            return -1;
        HashMap<Character , Integer> hashmap = new LinkedHashMap<>();   // linked 保证顺序
        for(int i = 0 ; i < s.length() ; i ++){
            char temp = s.charAt(i);
            if( !hashmap.containsKey(temp)){
                hashmap.put(temp , i);
            }
            else{
                hashmap.put(temp , -1);
            }
        }
        for(char c : hashmap.keySet()){   // 遍历 ketSet()
            if(hashmap.get(c) != -1)
                return hashmap.get(c);
        }
        return -1;
    }
}