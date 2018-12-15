/*   这种代码一定要背出来
*/

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if(root == null) return ret;
        levelOrderCore(root , ret);
        return ret;
    }
    
    public void levelOrderCore(TreeNode root , List<List<Integer>> ret){
        List<Integer> temp = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode pointer = null;
        queue.offer(root);
        int curCount = 1;
        int nextCount = 0;
        while(queue.isEmpty() == false){     // 队列不空就循环
            pointer = queue.poll();         // 当前结点出队
            temp.add(pointer.val);
            curCount --;           // 当前结点已经被访问
            if(pointer.left != null){     // 左子树
                queue.offer(pointer.left);
                nextCount ++;            // 下层结点数目 +1
            }
            if(pointer.right != null){      // 右子树 
                queue.offer(pointer.right);
                nextCount ++;               // 下层结点数目 +1
            }
            if(curCount == 0){           // 当前结点数目 == 0
                curCount = nextCount;      // 更新当前结点 
                nextCount = 0;             // 更新下层结点
                ret.add(new ArrayList<>(temp));   // 添加进结果 
                temp.clear();
            }
        }
    }
}