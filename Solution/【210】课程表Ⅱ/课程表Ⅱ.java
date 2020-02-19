/*
（1）拓扑排序
（2）在开始排序前，扫描对应的存储空间（使用邻接表），将入度为 0 的结点放入队列。
（3）只要队列非空，就从队首取出入度为 0 的结点，将这个结点输出到结果集中，并且将这个结点的所有邻接结点（它指向的结点）的入度减 1，在减 1 以后，如果这个被减 1 的结点的入度为 0 ，就继续入队。
（4）当队列为空的时候，检查结果集中的顶点个数是否和课程数相等，如果相等则返回 结果数组。
（5）可以利用 adj[set] 的形式，也就是 set 数组保存每个节点的出边（后继节点）
（6）专门用一个数组记录每个节点的 入度
（7）为什么这里是使用队列呢？如果不使用队列，要想得到当前入度为 0 的结点，就得遍历一遍入度数组。使用队列即用空间换时间
*/



class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        int index = 0;
        if (numCourses <= 0) {
            return result;
        }

        // 特判
        int pLen = prerequisites.length;
        // if (pLen == 0) {
        //     return result;
        // }

        int[] inDegree = new int[numCourses];   // 入度
        HashSet<Integer>[] adj = new HashSet[numCourses];    // 邻接矩阵，用  adj[set] 的形式，可以通过 索引 得到每个节点的 后续节点
        for (int i = 0; i < numCourses; i++) {    // 将原先的数组转化为 邻接矩阵 
            adj[i] = new HashSet<>();
        }

        for (int[] p : prerequisites) {
            inDegree[p[0]] ++;
            adj[p[1]].add(p[0]);    // 记录的是 出边
        }

        Queue<Integer> queue = new LinkedList<>();

        // 入度为 0 的结点 入队
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 记录已经出队的课程数量
        int count = 0;
        while (!queue.isEmpty()) {
            Integer top = queue.poll();
            result[index] = top;
            index ++;

            count = count + 1;
            // 遍历当前出队结点的所有后继结点
            for (int successor : adj[top]) {
                inDegree[successor]--;
                if (inDegree[successor] == 0) {
                    queue.add(successor);
                }
            }
        }
        if(count == numCourses){
            return result;
        }
        else{
            return new int[0];
        }
    }
}