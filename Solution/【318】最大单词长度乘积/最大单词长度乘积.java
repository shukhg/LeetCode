/*
（1）暴力法的话，每两个字符串都需要建立一个数组 或者 哈希表 去判断是否包含重复元素
（2）优化的点就在 可以把字符串变成 int类型的数，每一位代表一个字符是否出现过
（3）判断两个字符串是否有公共字符的时候，直接两个字符串对应的 int 数取 & 即可
*/



class Solution {
    public int maxProduct(String[] words) {
        int[] arr = new int[words.length];
        for(int i = 0; i < words.length; i ++){
            String word = words[i];
            for(int j = 0; j < word.length(); j ++){
                char ch = word.charAt(j);
                arr[i] = arr[i] | (1 << (ch - 'a'));
            }
        }
        int result = 0;
        for(int i = 0; i < words.length; i ++){
            for(int j = i + 1; j < words.length; j ++){
                if((arr[i] & arr[j]) == 0){
                    int k = words[i].length() * words[j].length();
                    result = Math.max(result, k);
                }
            }
        }
        return result;
    }
}