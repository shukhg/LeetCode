/*
（1）先用 map 存储好每个数字字符对应的字符串
（2）回溯，注意 for 循环中遍历的是 每个数字字符对应的字符串中每个字符
*/





class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new LinkedList<String>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        Map<Character, String> map = new HashMap<Character, String>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        dfs("", digits, 0, result, map);
        return result;
    }
    public void dfs(String s, String digits, int i, List<String> result, Map<Character, String> map) {
        if (i == digits.length()) {
            result.add(s);
            return;
        }
        String letters = map.get(digits.charAt(i));
        for (int j = 0; j < letters.length(); j++){
            dfs(s+letters.charAt(j),digits,i+1,result,map);
        }
    }
}