/*
（1）当前先手对应n个石头，后手就对应n-1、n-2、n-3三种集合的石头
（2）每个集合一定都会对应必胜或者必败，因此可以得到这样一个递推关系：
（3）只有当 n-1、n-2、n-3 三种集合都必胜时，n 对应的集合才必败。因为不管 n 走哪条路，都一定对应着后手必胜，也就是对应着先手必败。
（4）而但凡后手对应的 n-1、n-2、n-3 三种集合有一种对应的是必败，先手都一定是必胜。因为玩家是绝对聪明的，一定会走让后手必败的路线。
（5）所以可以得到状态转移方程 f(n) = !(f(n-1) && f(n-2) && f(n-3))   f(0) = false
（6）但是上面这样会出现超时，不过思路是可以解决的
（7）观察其实会发现，每 4 个组成一个循环，所以可以不是 f(n - 1) 而是 f((n - 1) % 4)
（8）其实再仔细想想，只要判断是否是 4 的倍数即可
*/



class Solution {
    public boolean canWinNim(int n) {
        return (n % 4 != 0);
    }
}



class Solution {
    public boolean canWinNim(int n) {
        boolean[] dp = new boolean[4];
        for(int i = 1; i <= n; i++) {
            boolean ret = true;
            ret &= dp[(i - 1) % 4];
            if(i >= 2) {
                ret &= dp[(i - 2) % 4];
            }
            if(i >= 3) {
                ret &= dp[(i - 3) % 4];
            }
            dp[i % 4] = !ret;
        }
        return dp[n % 4];
    }
}
