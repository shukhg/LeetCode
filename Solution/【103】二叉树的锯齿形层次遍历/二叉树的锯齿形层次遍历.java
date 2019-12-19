/*
* 基本和层次遍历一样，中间利用一个 flag 和 Collections.reverse() 实现反转
*/


class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)    return result;
        TreeNode pointer = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int cur_count = 1, next_count = 0;
        boolean reverse_flag = false;
        List<Integer> temp = new ArrayList<>();
        while(queue.isEmpty() == false){
            pointer = queue.poll();
            cur_count --;
            temp.add(pointer.val);
            if(pointer.left != null){
                queue.offer(pointer.left);
                next_count ++;
            }
            if(pointer.right != null){
                queue.offer(pointer.right);
                next_count ++;
            }
            if(cur_count == 0){
                cur_count = next_count;
                next_count = 0;
                if(reverse_flag == true){
                    Collections.reverse(temp);
                    reverse_flag = false;
                }
                else{
                    reverse_flag = true;
                }
                result.add(new ArrayList<Integer>(temp));
                temp.clear();
            }
        }
        return result;
    }
}