/*
（1）hashmap 存储 key 是 s 中的字符 ss，value 是 t 中的字符 tt
（2）首先判断 ss 是否出现过，出现过的话判断 ss 的 value 是不是 tt
（3）如果 ss 没有出现过，判断 tt 是否出现过
*/



class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> map = new HashMap<>();
        if(s.length() != t.length())    return false;
        for(int i = 0; i < s.length(); i ++){
            char ss = s.charAt(i);
            char tt = t.charAt(i);
            if(map.containsKey(ss)){  // 遇到已经出现的
                if(map.get(ss) != tt) return false;     // 如果 ss 的 value 不是 tt
            }
            else{      // ss 不重复
                if(map.containsValue(tt))   return false;   // 判断 tt 是否已经出现过
                map.put(ss,tt);
            }

        }              
        return true; 
    }
}
