/*
（1）用一个数组表示若干个数，每一个值表达一个数。用一个二进制数 表示不同数 的使用情况
（2）思路：自己选一个最大的使得自己可以赢，或者 自己选一个后使得对方没法赢

*/




class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int canReachTotal = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if(canReachTotal < desiredTotal){
            return false;
        }
        else if(canReachTotal == desiredTotal){    // 刚好达到，则奇数赢
            if(maxChoosableInteger % 2 == 1)
                return true;
            else
                return false;
        }
        else{
            return canWin(0, desiredTotal, maxChoosableInteger, new int[1 << maxChoosableInteger]);
        }
    }
    public boolean canWin(int bits, int distance, int maxChoosableInteger, int[] dp){
        if(dp[bits] != 0){   // 已经计算过。0：未计算，1：true，2：false
            return dp[bits] == 1;
        }
        
        boolean result = false;
        for(int cur = maxChoosableInteger; cur  > 0 ; cur  --){
            int curBit = 1 << (cur - 1);
            if((bits & curBit) == 0){     // 当前值 没有被使用
                if(cur >= distance // 可以一步成功
                  || !canWin(bits | curBit, distance - cur, maxChoosableInteger, dp)) { // 如果能找到一步让对方无法赢
                    result = true;
                    break;
                }
            }
        }
        dp[bits] = result ? 1 : 2;
        return result;
    }
}