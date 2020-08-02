/*
（1）根据层次遍历的结果生成树的代码要注意。层次遍历是用 队列，反过来同样是用队列。每次生成出队元素的左右结点
（2）可以先找到 左边的最大值 和 右边的最大值，与 根结点的值进行判断，节约时间
*/





import java.util.*;
//定义树结点
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val=val;
    }
}
public class test{
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        //按照顺序存放树结点，由于树结点的左、右结点还没赋值 后面会用到
        Queue<TreeNode> queue=new LinkedList<>();
        String [] str=sc.nextLine().split(",");
        //题意空树或空结点时是输入None
        //判断是否为None，空数也是二叉搜索树
        if(str[0].equals("None")){
            System.out.print("True");
            System.exit(0);
        }
        //定义根结点
        TreeNode head=new TreeNode(Integer.parseInt(str[0]));
        //再保存一个根结点，后面用来判断是否是二叉搜索树用到
        TreeNode root=head;
        //按顺序添加结点
        queue.add(head);
        //判断左结点有没有赋值
        boolean flag=false;
        //重构二叉树代码
        for(int i=1;i<str.length;i++){
            //添加结点
            TreeNode temp=new TreeNode(Integer.parseInt(str[i]));
            queue.add(temp);
            //如果没有给左结点赋值，则给左结点赋值
            if(!flag){
                head.left=temp;
                flag=true;
            }
            //如果左结点赋值，则给右结点赋值
            else{
                head.right=temp;
                //左、右结点都赋值完了，出队列
                queue.poll();
                //重新找一个新的结点来赋值左、右结点，按入队顺序来
                head=queue.peek();
                flag=false;
            }
        }
        //找出左子树的最大值
        int max=GetMaxLeft(root.left);
        //找到右子树的最小值
        int min=GetMinRight(root.right);


        //左子树的所有值都要小于根结点的值，所有找出最大值来判断
        //右子树的所有值都要大于根结点的值，所有找出最小值来判断
        //如果不满足这两个条件，直接输出False,后面都不用判断二叉搜索树了
        //不找出最值只能通过90%
        if(root.val<max||root.val>min){
            System.out.print("False");
        }
        //判断是否是二叉搜索树
        else if(check(root)){
            System.out.print("True");
        }
        else{
            System.out.print("False");
        }
    }
    //找左子树的最大值的方法
    public static int GetMaxLeft(TreeNode left){
        int max=left.val;
        LinkedList<TreeNode> list=new LinkedList<>();
        list.add(left);
        while(list.size()>0){
            TreeNode node=list.poll();
            if(node.val>max){max=node.val;}
            if(node.left!=null){
                list.add(node.left);
            }
            if(node.right!=null){
                list.add(node.right);
            }
        }
        return max;
    }
    //找右子树的最小值的方法
    public static int GetMinRight(TreeNode right){
        int min=right.val;
        LinkedList<TreeNode> list=new LinkedList<>();
        list.add(right);
        while(list.size()>0){
            TreeNode node=list.poll();
            if(node.val>min){min=node.val;}
            if(node.left!=null){
                list.add(node.left);
            }
            if(node.right!=null){
                list.add(node.right);
            }
        }
        return min;
    }
    //判断二叉搜索树的方法
    //递归
    public static boolean check(TreeNode root){
        //递归结束条件
        if(root.left==null&&root.right==null) return true;
        else if((root.left.val<root.val)&&(root.right.val>root.val)){
            return check(root.left)&&check(root.right);
        }
        else {
            return false;
        }
    }
}