/*
（1）滑动窗口，指针为 left 和 right，right 一直往右边移动，直到窗口内的元素都满足要求
（2）移动 left，直到窗口内的元素不满足要求。如果 result 有更新，则更新 result
（3）然后重复直到 right 到达边界

注意从 map 中取出的数据是 Integer，尽管会自动拆箱，但是在 == 的时候还是会出问题，所以加了 intValue()
*/




class Solution {
    public String minWindow(String s, String t) {
        int start = 0, min_len = Integer.MAX_VALUE;
        int left = 0, right = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> needs = new HashMap<>();
        for(char c: t.toCharArray()){
            if(needs.containsKey(c))    needs.put(c, needs.get(c) + 1);
            else    needs.put(c, 1);
        }
        int match = 0;
        while(right < s.length()){
            char c1 = s.charAt(right);
            if(needs.containsKey(c1)){
                if(window.containsKey(c1))  window.put(c1, window.get(c1) + 1);
                else    window.put(c1, 1);
                if(window.get(c1).intValue() == needs.get(c1).intValue()){  // 字符c1 的出现次数符合要求了  注意这里要 .intValue()
                    match ++;
                }
            }
            right ++;
            while(match == needs.size()){  // window 中的字符符合 needs 中的要求,这里一定要用 map 的 size 而不能用 t.length
                if(right - left < min_len){
                    min_len = right - left;
                    start = left;
                }
                char c2 = s.charAt(left);
                if(needs.containsKey(c2)){
                    window.put(c2, window.get(c2) - 1);
                    if(window.get(c2) < needs.get(c2)){
                        match --;
                    }
                }
                left ++;
            }
        }
        if(min_len == Integer.MAX_VALUE)    return "";
        else    return s.substring(start, start + min_len);
    }
}