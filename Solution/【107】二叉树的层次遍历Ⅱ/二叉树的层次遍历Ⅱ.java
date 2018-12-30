/*
此题与普通的层次遍历一样，可以利用栈实现反转，也可以直接插入的时候用前插法
*/

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if(root  == null)  return ret;
        levelOrderBottomCore(root , ret);
        return ret;
    }
    
    public void levelOrderBottomCore(TreeNode root , List<List<Integer>> ret){
        List<Integer> temp = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int curCount = 1;
        int nextCount = 0;
        TreeNode pointer = null;
        queue.offer(root);
        while(queue.isEmpty() == false){
            pointer = queue.poll();
            temp.add(pointer.val);
            curCount --;
            if(pointer.left != null){
                queue.offer(pointer.left);
                nextCount ++;
            }
            if(pointer.right != null){
                queue.offer(pointer.right);
                nextCount ++;
            }
            if(curCount == 0){
                curCount = nextCount ; 
                nextCount = 0;
                ret.add( 0 , new ArrayList<>(temp));  // 靠此处实现
                temp.clear();
            }
        }
    }
}