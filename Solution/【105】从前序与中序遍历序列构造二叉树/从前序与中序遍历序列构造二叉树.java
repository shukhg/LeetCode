
/*
【分析】
*假设树的先序遍历是12453687，中序遍历是42516837。这里最重要的一点就是先序遍历可以提供根的所在
而根据中序遍历的性质知道根的所在就可以将序列分为左右子树。
比如上述例子，我们知道1是根，所以根据中序遍历的结果425是左子树
而6837就是右子树。接下来根据切出来的左右子树的长度又可以在先序便利中确定左右子树对应的子序列。
根据这个流程，左子树的先序遍历和中序遍历分别是245和425，右子树的先序遍历和中序遍历则是3687和6837.
我们重复以上方法，可以继续找到根和左右子树，直到剩下一个元素。
可以看出这是一个比较明显的递归过程，对于寻找根所对应的下标，我们可以先建立一个HashMap，以免后面需要进行线行搜索，
这样每次递归中就只需要常量操作就可以完成对根的确定和左右子树的分割。
算法最终相当于一次树的遍历，每个结点只会被访问一次，所以时间复杂度是O(n)。
而空间我们需要建立一个map来存储元素到下标的映射，所以是O(n)。
*
*/

/*
* 1、注意新建结点的时候，根结点是 preorder[preLeft]，先序遍历的第一个结点
* 2、注意root.left , root.right ，要仔细想明白
* 3、此处build，记住有4个位置参数，preLeft,preRight,inLeft,inRight
*/



class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null)
            return null ;
        HashMap<Integer , Integer > hashmap = new HashMap<>();   // 此处 map 是为了记录中序遍历结点下标
        for(int i = 0 ; i < inorder.length ; i ++){
            hashmap.put(inorder[i] , i);
        }
        TreeNode root = build( preorder , 0 , preorder.length - 1  , inorder , 0 , inorder.length - 1 , hashmap );
        return root;
    }
    
    public TreeNode build(int[] preorder , int preLeft , int preRight , int[] inorder , 
                          int inLeft , int inRight , HashMap<Integer,Integer> hashmap){
        if(preRight < preLeft || inLeft > inRight)
            return null;
        TreeNode root = new TreeNode( preorder[preLeft]); // 记住是以 preorder[preLeft] 当作根 
        int index = hashmap.get( root.val );
        root.left = build(preorder , preLeft + 1 , index - inLeft + preLeft ,     // 关键是这两个地方慢慢想清楚
                         inorder , inLeft , index - 1  , hashmap);
        root.right = build(preorder , preLeft + index - inLeft + 1 , preRight     // 关键是这两个地方慢慢想清楚,可以考虑从上面借鉴，也可以从 preRight 去减（此处是利用上面）
                           , inorder , index + 1 , inRight , hashmap);
        return root ; 
    }
}