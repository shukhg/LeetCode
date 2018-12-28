/*
* 利用 HashMap存储 元素值-索引，然后在要一个 index ，保存当前不重复子串的开始位置，
* 重点是这个 index 要取原先 index 和 hashmap.get(str.charAt(i))的最大值
*/
class Solution {
    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0)  return 0;
        HashMap<Character , Integer> hashmap = new HashMap<>();
        int max = 0;
        int index = 0;
        for(int i = 0 ; i < s.length() ; i++){
            if(hashmap.containsKey(s.charAt(i)) == true){
                index = Math.max(hashmap.get(s.charAt(i)) + 1 , index);  // 关键是这一步，新的index取
                                                                        // 当前index和符合条件的字符在map中对应的索引的最大值
            }
            hashmap.put(s.charAt(i) , i);
            max = Math.max(max, i - index + 1);
        }
        return max;
    }
}