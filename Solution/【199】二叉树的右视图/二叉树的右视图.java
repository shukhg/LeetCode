/*
层次遍历，遍历完一层后将最右边的元素加入到结果中即可
*/




class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null)    return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int curCount = 1, nextCount = 0;
        while(queue.isEmpty() == false){
            TreeNode pointer = queue.poll();
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
                result.add(pointer.val);
                curCount = nextCount;
                nextCount = 0;
            }
        }
        return result;
    }
}