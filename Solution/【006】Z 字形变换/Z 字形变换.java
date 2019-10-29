/*
*  想清楚里面一步步的逻辑
（1）计算出 第一行 和 最后一行中 两列 之间的元素间隔
（2）填充第一行和最后一行
（3）计算出 中间行 的两列之间的元素间隔  k2 = k1 - 2 * i
（4）注意 上述中间行间隔是在两个值之间变化的
（5）注意 numRows == 1 的特殊情况
*/




class Solution {
    public String convert(String s, int numRows) {
        if(s == null || s.length() <= numRows || numRows == 1){
            return s;
        }
        String result = "";
        int k1 = 2 * numRows - 2; // k1 表示第一行和最后一行 相邻的两个元素之间差的元素
        int index = 0;
        for(int i = 0; i < numRows; i ++){    // 遍历每一行
            if(i == 0 || i == numRows - 1){    // 第一行或者最后一行
                result = result + s.charAt(i);   // 注意这里都是从 s 中的 i 开始
                index = i + k1;     // 取第一行或者最后一行 在 s 中的下一个元素
                while(index < s.length()){
                    result = result + s.charAt(index);
                    index = index + k1;
                }
            }
            else{
                result = result + s.charAt(i);
                int k2 = k1 - 2 * i;      // 中间行的距离 和 k1 以及 第几行都是有关的
                index = i + k2;
                while(index < s.length()){
                    result = result + s.charAt(index);
                    k2 = k1 - k2;   // 注意这里，每层的 k2 是在两个值之间切换的
                    index = index + k2;
                }
            }
        }
        return result;
    }
}