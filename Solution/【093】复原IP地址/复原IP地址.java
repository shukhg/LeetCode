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
        int n = s.length();
        dfs(0, result, "", 4, s, n); // ip 地址分为4段
        return result;
    }
    public void dfs(int index, List<String> result, String temp, int flag, String s, int n){
        if(index == n && flag == 0){
            result.add(temp.substring(0, temp.length() - 1));  // -1 是因为最后的 .
            return ;
        }
        if(flag < 0)    return;
        for(int j = index; j < index + 3; j ++){  // 最多考虑包含 index 的后面3个字符
            if(j < n){   
                if(index == j && s.charAt(j) == '0'){   // index 位置为 '0'，则直接当作 ip 地址的一个部分
                    dfs(j + 1, result, temp + s.charAt(j) + ".", flag - 1, s, n);
                    break;
                }
                if(Integer.parseInt(s.substring(index, j + 1)) <= 255){   // 把字串当作 ip 地址的一个部分
                    dfs(j + 1, result, temp + s.substring(index, j + 1) + ".", flag - 1, s, n);
                }
            }
        }
    }
}


