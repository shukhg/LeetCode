/*
HashMap 解决
或者利用字符的 ASCII码，得到每个字符相对于 '0'的大小
然后计算其和，两个和相减即为答案
*/


class Solution {
    public char findTheDifference(String s, String t) {
        int sSum = getValue(s);
        int tSum = getValue(t);
        // 差值就是多出来的字符
        return (char)(tSum - sSum);
    }
    // 记录字符串的字符的ascii的和
    private static int getValue(String str) {
        int res = 0;
        for (char c : str.toCharArray()) {
            res += c;
        }
        return res;
    }
}





class Solution {
    public char findTheDifference(String s, String t) {
        HashMap<Character , Integer> hashmap = new HashMap<>();
        for(int i = 0 ; i < s.length() ; i ++){
            char temp = s.charAt(i);
            if(hashmap.containsKey(temp)){
                int count = hashmap.get(temp);
                hashmap.put(temp , count + 1);
            }
            else{
                hashmap.put(temp , 1);
            }
        }
        for(int i = 0 ; i < t.length() ; i ++){
            char temp = t.charAt(i);
            if(hashmap.containsKey(temp)){
                int count = hashmap.get(temp);
                if(count == 1){
                    hashmap.remove(temp);
                }
                else{
                    hashmap.put(temp , count - 1);
                }
            }
            else{
                return temp;
            }
        }
        return '.';
    }
}