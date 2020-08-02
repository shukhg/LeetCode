/*
（1）n个点，n-1条边组成得图是无环图，所以要遍历所有点，最少得走法是所有边走俩两边再减去图中得最长路径，所以本题可以理解为，用 bfs 求图得最长路径
（2）可以利用 List<List<>> 或者 Set<>[] 去存储邻接矩阵
（3）对于每一个结点，可以用一个 index 和 deep 去表示，分别表示 当前是第index 个结点 和 该结点的深度
*/



import java.util.*;
public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<List<Integer>> map = new ArrayList<>(n + 1);

        for(int i = 0; i <= n; i ++){
            map.add(new ArrayList<>());
        }
        for (int i = 1; i <= n - 1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            List<Integer> lst = map.get(x);
            lst.add(y);
            lst = map.get(y);
            lst.add(x);
        }

        boolean[] vis = new boolean[n + 1];

        class Element {
            private int point;
            private int deep;
            private Element(int point, int deep) {
                this.point = point;
                this.deep = deep;
            }
        }

        Queue<Element> queue = new LinkedList<>();
        queue.add(new Element(1, 0));
        vis[1] = true;
        int max = 0;
        while (!queue.isEmpty()) {
            Element p = queue.poll();

            if(p.deep > max) {
                max = p.deep;
            }

            List<Integer> lst = map.get(p.point);
            if(lst.isEmpty()) {
                continue;
            }

            for (int t : lst) {
                if(!vis[t]) {
                    vis[t] = true;
                    queue.add(new Element(t, p.deep + 1));
                }
            }
        }
        System.out.println(2 * (n - 1) - max);
    }
}
