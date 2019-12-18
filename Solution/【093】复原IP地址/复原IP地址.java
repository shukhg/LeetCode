/*
（1）利用 flag 标记 ip 地址的 4 段，不能超过 4 段也不能小于 4 段
（2）for 循环中不同于其他的回溯，此处只用考虑包含 index 在内的 3个字符
（3）如果 index 位置为 '0'，直接当作 ip 地址的一部分。如果不为 '0'，则考虑子串
（4）既然 for 中是考虑 index + 3，那么可能存在越界，注意此处的越界判断
*/




// 回溯法
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if(s == null || s.length() == 0)    return result;
        dfs(result, s, "", 4, 0, s.length());  // ip 地址分为4段
        return result;
    }
    public void dfs(List<String> result, String s, String temp, int flag, int index, int n){
        if(index == n && flag == 0){
            result.add(temp.substring(0, temp.length() - 1));
            return ;
        }
        if(flag < 0)    return ;
        for(int i = index; i < index + 3; i ++){  // 最多考虑包含 index 的后面3个字符
            if(i >= n){
                return;
            }
            if(i == index && s.charAt(i) == '0'){  // index 位置为 '0'，则直接当作 ip 地址的一个部分
                dfs(result, s, temp + s.charAt(i) + '.', flag - 1, i + 1, n);
                break;   // 这个 break 很关键，不然会出现 001  010 这样的情况
            }
            else if(Integer.parseInt(s.substring(index, i + 1)) <= 255){
                dfs(result, s, temp + s.substring(index, i + 1) + '.', flag - 1, i + 1, n);
            }
        }
    }
}


