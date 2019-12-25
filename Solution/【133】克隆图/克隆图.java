/*
此题分为 深度优先遍历 和 广度优先遍历，后续图的遍历都是以这两个为模板的
*/


// 深度优先遍历
class Solution {
    public Node cloneGraph(Node node) {
        HashMap<Node, Node> hashmap = new HashMap<>();
        Node result = dfs(node, hashmap);
        return result;
    }
    public Node dfs(Node node, HashMap<Node, Node> hashmap){
        if(node == null){
            return null;
        }
        if(hashmap.containsKey(node)){  // 如果已经遍历了，则返回
            return hashmap.get(node);
        }
        Node clone = new Node(node.val, new ArrayList<>());
        hashmap.put(node, clone);    // 一定要记得对已经访问过的结点进行标记
        for(Node n: node.neighbors){
            clone.neighbors.add(dfs(n, hashmap));  // 这里体现了深度优先遍历
        }
        return clone;
    }
}


// 广度优先遍历
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null)    return null;
        HashMap<Node, Node> hashmap = new HashMap<>();
        Node clone = new Node(node.val, new ArrayList<>());
        hashmap.put(node, clone);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while(queue.isEmpty() == false){
            Node temp = queue.poll();
            for(Node n: temp.neighbors){
                if(hashmap.containsKey(n) == false){
                    hashmap.put(n, new Node(n.val, new ArrayList<>()));
                    queue.offer(n);
                }
                hashmap.get(temp).neighbors.add(hashmap.get(n));
            }
        }
        return clone;
    }
}

