/*
（1）思路：自己选一个最大的使得自己可以赢，或者 自己选一个后使得对方没法赢
（2）典型的回溯问题，但是对回溯的结果需要记忆（有些类似于动态规划）
（3）首先判断数字总和（利用求和公式）与 target 的关系，要是恰好相等，就判断可供选择的数字的个数来返回结果
（4）需要记录每个数字的使用情况，可以用一个 int 类型的 bits，每一位就代表一个数的使用情况。有新的数被使用了的话，就 bits | curBit 其中 curBit = 1 << num   这是一个很有用的技巧
（5）如果暴力 dfs 的话，那么会超时，所以需要像动态规划这样把中间的一些结果给记录下来。
（6）dp 数组是记录 bits（当前被使用的元素）之后选一个剩下的元素是否可以赢。
（7）如果 dp 数组中存在 bits 的值，就返回，不存在的话就再进行计算，节约时间
*/




class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int canReachTotal = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if(canReachTotal < desiredTotal){
            return false;
        }
        else if(canReachTotal == desiredTotal){    // 刚好达到
            if(maxChoosableInteger % 2 == 1)   // 可供选择的元素个数为奇数，则先选的人赢
                return true;
            else                       // 否则后选的人赢
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

