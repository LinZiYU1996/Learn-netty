package data_structure.tree.BinarySearchTree.bolg519;

import java.util.Random;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/19
 * \* Time: 21:17
 * \* Description:
 * \
 */
public class SearchTree {


    private TreeNode root;


    //添加节点（递归模式）
    public   boolean RecursionAddTreeNode(TreeNode root, int data){
        TreeNode treeNode=new TreeNode(data);
        //树为空
        if(root==null){
            this.root=treeNode;
            return true;
        }
        //比根节点小，插入到左子树
        if(root.data>data){
            if(root.left==null){
                root.left=treeNode;
                return true;
            }else
                return RecursionAddTreeNode(root.left, data);
        }else if(root.data<data){       //比根节点大，插入到右子树
            if(root.right==null){
                root.right=treeNode;
                return true;
            }else
                return RecursionAddTreeNode(root.right,data);
        }else
            return false;
    }

    //增加节点非递归模式
    public  boolean NoRecursionAddTreeNode(TreeNode root, int data){
        //树为空
        if(root==null){
            this.root=new TreeNode(data);
            return true;
        }
        TreeNode treeNode=new TreeNode(data);
        TreeNode currNode=root;
        while(true){
            if(currNode.data>data){
                if(currNode.left!=null)
                    currNode=currNode.left;
                else{
                    currNode.left=treeNode;
                    return true;
                }
            }else if(currNode.data<treeNode.data){
                if(currNode.right!=null)
                    currNode=currNode.right;
                else {
                    currNode.right=treeNode;
                    return true;
                }
            }else
                return false;
        }
    }




    //二叉树中序遍历
    public   void midSort(TreeNode root){
        if(root==null){
            return;
        }
        midSort(root.left);
        System.out.print(root.data+"   ");
        midSort(root.right);
    }


    // 查找
    public static boolean SearchTreeNode(TreeNode root, int data){
        if(root==null){
            return false;
        }else if(root.data==data){
            return true;
        }else if(root.data>data){
            return SearchTreeNode(root.left,data);
        }else{
            return SearchTreeNode(root.right,data);
        }
    }



    //删除
    /*
    删除可为BST问题最为复杂的一部分，需要考虑一下要删除的节点的四种情况：

（1）该节点为叶子节点，删除即可

（2）该节点只有左子树，没有右子树，删除后将该节点的左子树连接到该节点的父节点（主要判断该节点为左节点还是右节点）

（3）该节点只有右子树，没有左子树，删除后将该节点的右子树连接到该节点的父节点（主要判断该节点为左节点还是右节点）

（4）该节点既有左子树，也有右子树，这时候删除比较复杂，可以分为两种情况：

首先，我们知道二叉排序树经过中序遍历后得到的是一个递增有序序列，该节点的前一个即为直接前驱，后一个为直接后继。我们要得到直接前驱和直接后继的节点。

方法一：a.得到该删除节点的左节点，如果此左节点没有右节点，则该左节点即为直接前驱；

              b.左节点有右节点，则一直沿最右侧右节点迭代下去，最后面的那个即为直接前驱。

 

方法二：a.得到该删除节点的右节点，如果此右节点没有左节点，则该右节点即为直接后继；

              b.右节点有左节点，则一直沿最左侧左节点迭代下去，最后面的那个即为直接后继。

以上四种情况均要考虑要删除的节点为根节点root的情况。

     */
    public static boolean DeleteNode(TreeNode root, int data){
        //current为查找得到的节点
        TreeNode current=root;
        //parent为时刻更新父节点
        TreeNode parent=root;
        //tempParent为同时存在左右子树的迭代临时父节点
        TreeNode tempParent=root;
        //isLeft记录current节点的左右属性
        boolean isLeft=true;
        while(current.data!=data){
            parent=current;
            //到左子树查找
            if(current.data>data){
                isLeft=true;
                current=current.left;
            }else if(current.data<data){ //到右子树查找
                isLeft=false;
                current=current.right;
            }
            //查不到，返回false
            if(current==null)
                return false;
        }
        //第一种情况：删除节点为叶节点
        if(current.left==null && current.right==null){
            if(current==root)
                root=null;
            if(isLeft) { // 该节点时父节点的左结点
                parent.left = null;
            }else{
                parent.right = null; // 该节点时父节点的右结点
            }
            return true;
        }else if(current.right==null){    //第二种情况：删除节点只有左节点
            if(current==root)
                root=current.left;
            else if(isLeft)
                parent.left=current.left;
            else
                parent.right=current.left;
            return true;
        }else if(current.left==null){    //第三种情况：删除节点只有右节点
            if(current==root)
                root=current.right;
            else if(isLeft)
                parent.left=current.right;
            else
                parent.right=current.right;
            return true;
        }else{  //第四种情况：删除节点均存在左节点和右节点
            if(current==root){
                root=root.left;
            }
            TreeNode tempNode=current.left;
            //没有左节点
            if(tempNode.right==null){
                if(isLeft)
                    parent.left=tempNode;
                else
                    parent.right=tempNode;
            }else{  //存在左节点，迭代到最右侧子节点，即直接前驱
                while(tempNode.right!=null){
                    tempParent=tempNode;
                    tempNode=tempNode.right;
                }
                if(isLeft){    //为左节点，连接
                    parent.left=tempNode;
                    parent.left.left=current.left;
                }else{  //为右节点，连接
                    parent.right=tempNode;
                    parent.right.left=current.left;
                }
                //删除前驱节点，连接
                if(tempNode.left==null)
                    tempParent.right=null;
                else
                    tempParent.right=tempNode.left;
            }
            return true;
        }
    }




    public static void main(String[] args) {

        SearchTree tree = new SearchTree();

        Random random = new Random();

//        for (int i = 0; i < d.length; i++) {
//            d[i] = random.nextInt(100);
//        }
//
//        for (int i = 0; i < d.length; i++) {
//            System.out.print(d[i]+"   ");
//            tree.RecursionAddTreeNode(tree.root, d[i]);
//        }
//
//        System.out.println("\n=======================================\n");
//
//
//        tree.midSort(tree.root);
//
//        System.out.println("\n=======================================\n");


        for (int i = 0; i < 10; i++) {
            System.out.println(i+1);

            for (int j = 0; j < 10; j++) {

                int d = random.nextInt(100);
                System.out.print(d + "   ");
                tree.NoRecursionAddTreeNode(tree.root, d);

            }

            System.out.println("\n=======================================\n");

            tree.midSort(tree.root);
            System.out.println("root ***" + tree.root.data);

            System.out.println("\n=======================================\n");

            tree.root = null;

        }






    }



}
