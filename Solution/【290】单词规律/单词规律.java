/*
（1）将词字符串利用 split 分割，判断长度是否符合要求
（2）利用 map 进行存储，key-value 是 char-string
（3）遍历每个字符，判断是否当前 char 和 string 是否在 map 中建立过 char-string 的映射
（4）如果没有建立过映射，判断这个 string 是否被使用
（5）如果建立过映射，则判断 map 中的 value 是否和当前 string 相同
*/



class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] s = str.split(" "); 
        if(s.length != pattern.length()) {  // 长度不同直接返回 false
            return false;
        } 
        HashMap<Character, String> map = new HashMap<>(); // 存放映射
        for(int i = 0; i < pattern.length(); i++){
            if(map.containsKey(pattern.charAt(i)) == false){   //  没有映射
                if(map.containsValue(s[i])){   // 没有映射的情况下s[i] 已被使用，则不匹配返回 false
                     return false; 
                }
                else{   // 否则建立映射
                    map.put(pattern.charAt(i), s[i]);  
                }
            }
            else{   // 存在映射
                if(!map.get(pattern.charAt(i)).equals(s[i])){  // 当前字符串与映射不匹配,返回false
                    return false; 
                }
            }
        }
        return true;
    }
}