/*
只允许交易一次的话，那就一趟遍历，中间记录最小的元素 min 和最大的收益 max，要是遇到更小的元素就更新 min ， 遇到更大的收益就更新 max 即可
*/


class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1)    return 0;
        int min_price = prices[0];
        int result = 0;
        for(int i = 1; i < prices.length; i ++){
            if(prices[i] < min_price){
                min_price = prices[i];
            }
            if(prices[i] - min_price > result){
                result = prices[i] - min_price;
            }
        }
        return result;
    }
}