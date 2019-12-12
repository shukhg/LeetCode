/*
（1）使用 StringBuilder 可以实现字符串的插入删除（sb.deleteCharAt(index)），比较满足此题的要求
（2）发现规律
*/




class Solution {
    public String getPermutation(int n, int k) {
        String result = "";
        int num = k;
        StringBuilder s = new StringBuilder("");
        int factorial = cal_factorial(n);
        
        for(int i = 1; i <= n; i ++){
            s.append(i);
        }
        for(int i = n; i >= 1; i --){
            factorial = factorial / i;
            int index = (num - 1) / factorial;

            String temp = "" + s.charAt(index);
            result = result + temp;
            num = num - index * factorial;
            s = s.deleteCharAt(index);    // 删除指定位置的字符
        }
        return result.toString();
    }
    public int cal_factorial(int n){
        int ans = 1;
        for(int i = 1; i <= n; i ++){
            ans = ans * i;
        }
        return ans;
    }
}