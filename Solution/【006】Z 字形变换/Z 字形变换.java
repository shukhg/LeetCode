/*
*  想清楚里面一步步的逻辑
*/




class Solution {
    public String convert(String s, int numRows) {
        if(s == null || s.length() <= numRows || numRows == 1)
            return "";
        String result = "";
        int k1 = 2 * numRows - 2;   // k1 表示 第一行 和 最后一行 相邻两个元素之间差的元素
		int index = 0;
		for(int i = 0 ; i < numRows ; i ++){
            if(i == 0 || i == numRows - 1){
                result = result + s.charAt(i);
                index = i + k1;                // 取第一行或者最后一行下一个元素
                while(index < s.length()){
                    result = result + s.charAt(index);
                    index = index + k1;
                }
            }
            else{
                result = result + s.charAt(i);          // 注意这里是 i ，一开始的时候是从 i 位置开始
                int k2 = k1 - 2 * i;
                index = i + k2;
                while(index < s.length()){
                    result = result + s.charAt(index);
                    k2 = k1 - k2;                   // 这里要注意，每循环一次 k2 的值会变
                    index = index + k2;
                }
            }
        }
        return result;
    }
}