/*
其实就是利用哈希表去遍历并且记录字符出现的次数
*/


class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote == null || ransomNote.length() == 0)
            return true;
        HashMap<Character , Integer> hashmap = new HashMap<>();
        for(int i = 0 ; i < magazine.length() ; i ++){
            char temp = magazine.charAt(i);
            if(hashmap.containsKey(temp) == false){
                hashmap.put(temp , 1);
            }
            else{
                int count = hashmap.get(temp);
                hashmap.put(temp , count + 1);
            }
        }
        for(int i = 0 ; i < ransomNote.length() ; i ++){
            char temp = ransomNote.charAt(i);
            if(hashmap.containsKey(temp) == true){
                int count = hashmap.get(temp);
                if(count == 0)
                    return false;
                hashmap.put(temp , count - 1);
            }
            else{
                return false;
            }
        }
        return true;
    }
}