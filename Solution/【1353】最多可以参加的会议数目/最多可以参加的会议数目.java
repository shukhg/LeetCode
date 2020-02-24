/*
（1）类似于区间合并，首先对数组进行排序，将结束时间早的排前面
（2）看清楚题意，这个题只要一个区间内有一天能够去听就可以了
（3）对于每一个会议的 start-end 区间中的每一天，都判断这天能否去听
（4）用一个 set 去存储哪些天被使用过
*/



class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });
        Set<Integer> set = new HashSet<>();
        for (int[] event : events) {
            int s = event[0];
            int e = event[1];
            for (int i = s; i <=e; i++) {
                if (!set.contains(i)) {
                    set.add(i);
                    break;
                }
            }
        }
        return set.size();
    }
}

