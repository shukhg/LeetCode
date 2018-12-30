/*
因为中间可以无数次买入和卖出，所以要理解只要后一天大于前一天，就可以买入。
*/



class Solution {
    public int maxProfit(int[] prices) {
        int thisSum = 0;
        int maxSum = 0;
        for(int i = 1 ; i < prices.length ; i++){
            if(prices[i] > prices[i - 1]){
                maxSum = maxSum + prices[i] - prices[i - 1]; 
            }
        }
        return maxSum ;
    }
}