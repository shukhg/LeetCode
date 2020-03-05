/*
（1）为了处理大正数相加应该使用两字符串相加的程序，并且与和的字符串比较，避免转换为int消耗时间与溢出。
（2）dfs 时的 i,j,k 分别代表第一个、第二个和第三个数字的起始下标，这样好处在于计算各个字符串时都很方便。
（3）第一个数字的起始下标一定是 0，但是第二和第三个数字的起始下标不固定，需要通过两层循环枚举，在拿到起始数字之后，就可以dfs一直到最后验证是否整个字符串符合要求。
（4）这道题dfs的递归结束条件和普通稍有不同，要仔细思考。这里递归成功的标志是一直到字符串最后一个字符都满足要求，即是累加序列，那么我们需要看是否能够递归到最后一个位置正好结束
（5）dfs 内部，先求出 第一个数 和 第二个数的和，拿到这个 和 的字符串长度。第三个数 首先判断长度是否满足要求，长度满足要求再判断 值 是否满足要求
（6）如果长度满足要求 并且 值 也满足要求，则进行下一趟 dfs 或者 在到达结尾的时候直接返回 false 
*/


class Solution {
    public boolean isAdditiveNumber(String num) {
        if(num == null || num.length() <= 2){
            return false;
        }
        int i = 0;
        for(int j = 1; j < num.length() - 1; j ++){
            for(int k = j + 1; k < num.length(); k ++){
                if(dfs(num, i, j, k)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dfs(String num, int i, int j, int k){
        if((num.charAt(i) == '0' && j - i > 1) || (num.charAt(j) == '0' && k - j > 1 ) ){   // 第一个数字 和 第二个数字 以 0 开头
            return false;
        }
        String a = num.substring(i, j);
        String b = num.substring(j, k);
        String sum = add(a, b);
        int n = sum.length();
        if(k + n - 1 > num.length() - 1 || !sum.equals(num.substring(k, k + n)) ) return false;  // 越界 或者 不等
        if(k + n - 1 == num.length() - 1)   return true;    // 恰好在边界，直接返回
        return dfs(num, j, k, k + n);
    }

    public String add(String num1, String num2) {
        StringBuilder result = new StringBuilder("");
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while(i >= 0 || j >= 0){
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            result.append(tmp % 10);
            i--; 
            j--;
        }
        if(carry == 1) result.append(1);
        return result.reverse().toString();
    }
}