/*
用一个List 去存储遍历过程中的一条路径的值，之后再遍历 List 求和即可
也可以用一个全局遍历去存储，但是要注意如果使用全局遍历的话，函数中就不需要这个参数了，不然无法改变全局变量的值
*/




class Solution {
    int result = 0;
    public int sumNumbers(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return 0;
        search(root , list , 0);
        return result;
    }
    public void search(TreeNode root , List<Integer> list , int temp){
        if(root.left == null && root.right == null ){
            temp = temp * 10 + root.val;
            result = result + temp;
            return ;
        }
        int tempNum = temp * 10 + root.val;
        if(root.left != null)
            search(root.left , list , tempNum);
        if(root.right != null)
            search(root.right , list , tempNum);
    }
}



class Solution {
    public int sumNumbers(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return 0;
        search(root , list , 0 );
        int sum = 0;
        for(int i = 0 ; i < list.size() ; i++){
            sum = sum + list.get(i);
        }
        return sum;
    }
    public void search(TreeNode root , List<Integer> list , int temp ){
        if(root.left == null && root.right == null ){
            temp = temp * 10 + root.val;
            list.add(temp);
        }
        int tempNum = temp * 10 + root.val;
        if(root.left != null)
            search(root.left , list , tempNum );
        if(root.right != null)
            search(root.right , list , tempNum);
    }
}