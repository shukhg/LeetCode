/*
（1）既然要求最小高度，那么从我们直觉来看，结点肯定是在最内部的，因为题目说了是无向图，所以越靠近边缘，高度肯定就会越大。
（2）跳出循环的条件是 只剩下 1 个 或者 2个结点，这点要注意，而且要想清楚 1个、2个结点是怎么来的
（3）仿照拓扑排序的过程，利用一个 boolean 数组标记结点是否被访问，等剩下 1个、2个结点的时候将其添加到结果中即可
*/




class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if(n <= 2){
            for(int i = 0; i < n; i ++){
                result.add(i);
            }
            return result;
        }
        int[] inDegrees = new int[n];    // 入度数组
        boolean[] flag = new boolean[n];  // 如果剔除了，则为 true
        Set<Integer>[] adjs = new Set[n];   // 邻接矩阵
        for(int i = 0; i < n; i ++){
            adjs[i] = new HashSet<>();
        }
        for(int[] edge: edges){
            int start = edge[0];
            int end = edge[1];
            adjs[start].add(end);
            adjs[end].add(start);
            inDegrees[start] += 1;
            inDegrees[end] += 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i ++){
            if(inDegrees[i] == 1){
                queue.offer(i);
            }
        }
        while(n > 2){    // 剩余 1 个或者 2 个结点
            int size = queue.size();
            n = n - size;
            for(int i = 0; i < size; i ++){
                int top = queue.poll();
                flag[top] = true;
                inDegrees[top] -= 1;
                Set<Integer> curAdjs = adjs[top];
                for(Integer curAdj : curAdjs){
                    inDegrees[curAdj] -= 1;
                    if(inDegrees[curAdj] == 1){
                        queue.offer(curAdj);
                    }
                }
            }
        }
        for(int i = 0; i < flag.length; i ++){
            if(flag[i] == false){
                result.add(i);
            }
        }
        return result;
    }
}