/*
（1）暴力解法，从 2 遍历到 n，对其间的每个数 i 再从 2 遍历 到 i - 1 看能否找到整除了
（2）略微优化：其实判断 i 是否是质数的时候，不需要遍历 2 到 i - 1，只需要 2 - 根号 i  就可以了。但是还是会超时
（3）排除法：先遍历一趟，遍历 2 到 n-1，如果 i 是质数，则 i 的倍数都不是质数了，也就排除掉。最后再遍历一趟统计有多少质数
（4）排除法优化：我们同样不需要遍历 2 到 n - 1，只需要遍历 2 到 sqrt(n) 进行排除即可
（5）排除法再优化：内层 for 循环也可以优化，比如 n = 25, i = 4 的时候，会排除 8、12，
    但是其实在 i = 2 的时候就已经排除了 8，i = 3 的时候就排除了 12，所以内层循环变量可以直接从 i * i 开始
*/



// 排除法最终版，时间复杂度比较难算，为 O(n loglogn)
class Solution {
    int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++) 
            if (isPrim[i]) 
                for (int j = i * i; j < n; j += i) 
                    isPrim[j] = false;
        
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;
        
        return count;
    }
}





// 排除法基础版
class Solution {
    int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        // 将数组都初始化为 true
        Arrays.fill(isPrim, true);
        for (int i = 2; i < n; i++) 
            if (isPrim[i]) 
                // i 的倍数不可能是素数了
                for (int j = 2 * i; j < n; j += i) 
                        isPrim[j] = false;       
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;
        
        return count;
    }
}




// 略微优化，时间复杂度 O(n * sqrt(n))
class Solution {
    public int countPrimes(int n) {
        int count = 0;
        for(int i = 2; i < n; i++)
            if(isPrime(i)) count++;
        return count;

    }
    // 判断整数 n 是否是素数
    boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0)
                // 有其他整除因子
                return false;
        return true;
    }
}




// 暴力解法，时间复杂度 O(n2)
class Solution {
    public int countPrimes(int n) {
        int count = 0;
        for(int i = 2; i < n; i++)
            if(isPrime(i)) count++;
        return count;

    }
    // 判断整数 n 是否是素数
    boolean isPrime(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                // 有其他整除因子
                return false;
        return true;
    }
}