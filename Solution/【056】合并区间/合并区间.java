/*
（1）先自定义 Comparator，将二维数组按照首位置排序
（2）判断两个区间有没有交集，如果没有交集就把此区间添加到结果中
（3）如果有交集，左边位置一定是确定，就是 a[0]，而右边位置是 max(a[1], b[1])
（4）List 变成 数组的 toArray 方法要注意，这里指定了类型
（5）此处用 LinkedList   ArrayList 都可以
*/





class Solution {
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        if (intervals == null || intervals.length == 0) {
            return res.toArray(new int[0][]);
        }

        Arrays.sort(intervals, new Comparator<int[]>() {   // 先按首位置进行排序
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < intervals.length; i++) {
            if (res.isEmpty() || res.getLast()[1] < intervals[i][0]) {  // 没有交集
                res.add(intervals[i]);
            } 
            else {  // 有交集的时候，左边位置一定是确定，就是 a[0]，而右边位置是 max(a[1], b[1])
                res.getLast()[1] = Math.max(res.getLast()[1], intervals[i][1]);
            }
        }
        return res.toArray(new int[0][0]);  //  new int[0][0] 的作用是指定数据类型为 int
    }
}