/*
（1）所谓的 序列化与反序列化 其实就是找个形式把二叉树存起来，然后再根据这个形式取解析重建二叉树
（2）按照前序遍历的思想，把二叉树的结点值用 字符串表示，如果二叉树为空则用 '#' 表示，结点之间用 '!' 去分隔
（3）在解析的时候，先 split，然后得到的序列就是重建二叉树需要根据的前序遍历序列
（4）原则上根据前序遍历序列是无法重建二叉树的，但是我们加入了 '#' 结点表示 空结点，这样就可以重建了
（5）将 split 后的数组放入 Queue 中，按照出队的顺序去前序遍历就可以了，注意遇到 "#" 则返回 null
*/




public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "#!";
        }
        String str = root.val + "!";
        str = str + serialize(root.left);
        str = str + serialize(root.right);
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split("!");
        Queue<String> queue = new LinkedList<>();
        for(int i = 0; i < arr.length; i ++){
            queue.offer(arr[i]);
        }
        return deserializeHelp(queue);
    }
    public TreeNode deserializeHelp(Queue<String> queue){
        String nodeString = queue.poll();
        if(nodeString.equals("#")){
            return null;
        }
        int nodeValue = Integer.parseInt(nodeString);
        TreeNode root = new TreeNode(nodeValue);
        root.left = deserializeHelp(queue);
        root.right = deserializeHelp(queue);
        return root;
    }
}