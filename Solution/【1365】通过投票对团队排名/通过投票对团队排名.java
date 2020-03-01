/*
（1）题目要求根据 每个字符的投票次数 进行排序输出结果，所以应该是要想到利用排序去做
（2）因为要记录每个字符的投票结果，所以需要用 map 记录，key 为 字符，value 是一个 数组，表示字符在不同位置被投票的次数
（3）题意是需要对 map 中的 key 进行排序，可以将 map 先输出到 List 中再对 List 进行排序
（4）Map.Entry<Character, int[]> 得到 pair 对，key 为 Character，value 为 int[]
（5）my_map.entrySet() 得到 my_map 的 key-value 组成的 pair 对的 Set。因为是 pair，所以有 getKey()、getValue() 方法
（6）不写 Comparator 和 compare 接口，注意 lambda 表达式的写法
*/



class Solution {
    public String rankTeams(String[] votes) {
        // key 是参赛团队，value 是该团队每个排位获得的票数
        Map<Character, int[]> teamRankMap = new HashMap<>();

        for (String vote : votes) {
            for (int i = 0; i < vote.length(); i++) {
                int[] rankArr = teamRankMap.getOrDefault(vote.charAt(i), new int[26]);
                rankArr[i] ++;   // 排第 i 位的投票次数 ++
                teamRankMap.put(vote.charAt(i), rankArr);
            }
        }
        // 将 map 中的 key-value 对当作 List 的泛型
        List<Map.Entry<Character, int[]>> teamRankList = new ArrayList<>(teamRankMap.entrySet());  
        Collections.sort(teamRankList, (team1, team2) -> {
            int[] ranks1 = team1.getValue();
            int[] ranks2 = team2.getValue();
            // 根据投票排序
            for (int i = 0; i < 26; i++) {
                if (ranks1[i] != ranks2[i]) {
                    return ranks2[i] - ranks1[i];
                }
            }
            // 字母顺序排序
            return team1.getKey() - team2.getKey();
        });

        // 转换为字符串输出
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < teamRankList.size(); i ++) {
            stringBuilder.append(teamRankList.get(i).getKey());
        }
        String res = stringBuilder.toString();
        return res;
    }
}