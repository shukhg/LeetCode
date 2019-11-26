/*
*  想清楚里面一步步的逻辑
（1）计算出 第一行 和 最后一行中 两列 之间的元素间隔
（2）填充第一行和最后一行
（3）计算出 中间行 的两列之间的元素间隔  k2 = k1 - 2 * i
（4）注意 上述中间行间隔是在两个值之间变化的，因为两轮 k2  = 一轮 k1
（5）注意 numRows == 1 的特殊情况
*/




class Solution {
public:
    string convert(string s, int numRows) {
        if(s.empty() || s.size() <= numRows || numRows <= 1){
            return s;
        }
        string result = "";
        int index = 0;
        int k1 = 2 * numRows - 2;
        for(int i = 0 ; i < numRows ; i ++){
            if(i == 0 || i == numRows - 1){
                result = result + s.at(i);
                index = i + k1;
                while(index < s.size()){
                    result = result + s.at(index);
                    index = index + k1;
                }
            }
            else{
                result = result + s.at(i);
                int k2 = k1 - 2 * i;
                index = i + k2;
                while(index < s.size()){
                    result = result + s.at(index);
                    k2 = k1 - k2;
                    index = index + k2;
                }
            }
        }
        return result;
    }
};