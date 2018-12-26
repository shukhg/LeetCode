/*
判断两个相邻的字符对应的阿拉伯数字，如果左边小于右边，就减去左边。如果左边大于右边，就加上左边
从前往后或者从后往前处理都是可以的，从前往后就要处理最后一个字符
*/



class Solution {
    public int romanToInt(String s) {
        if( s == null || s.length() == 0)
            return 0;
        HashMap<Character , Integer> hashmap = new HashMap<>();
        hashmap.put('I' , 1);
        hashmap.put('V' , 5);
        hashmap.put('X' , 10);
        hashmap.put('L' , 50);
        hashmap.put('C' , 100);
        hashmap.put('D' , 500);
        hashmap.put('M' , 1000);
        int result = 0;
        for(int i = 1 ; i < s.length() ; i ++){
            char c1 = s.charAt(i - 1);
            char c2 = s.charAt( i );
            if( hashmap.get(c1) < hashmap.get(c2) ){  // 小的在前 
                result = result - hashmap.get(c1);
            }
            else{                                  // 小的在后
                result = result + hashmap.get(c1);
            }
        }
        result = result + hashmap.get( s.charAt(s.length() - 1));  // 处理最后一个字符
        return result ;
    }
}