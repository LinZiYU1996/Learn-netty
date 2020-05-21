package data_structure.tree.blog_5_13;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/13
 * \* Time: 21:50
 * \* Description:
 * \
 */
public class BinaryTree {

    public TreeNode root = null;

    public BinaryTree(int[] array, int index) {
        root = createBinaryTree(array, index);
    }
    // 创建二叉树

    private TreeNode createBinaryTree(int[] array, int index) {

        TreeNode treeNode = null;
        if (index < array.length) {
            treeNode = new TreeNode(array[index]);
            // 对于顺序存储的完全二叉树，如果某个节点的索引为index，其对应的左子树的索引为2*index+1，右子树为2*index+1
            treeNode.left = createBinaryTree(array, 2 * index + 1);
            treeNode.right = createBinaryTree(array, 2 * index + 2);
        }
        return treeNode;

    }

    private void showData(TreeNode node) {
        System.out.print(node);
    }

    // 递归遍历二叉树

    // 先序遍历（前序遍历）
    public void preOrder(TreeNode node) {
        if (node != null) {
            showData(node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 中序遍历
    public void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            showData(node);
            inOrder(node.right);
        }
    }
    // 后序遍历

    public void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            showData(node);
        }
    }

    // 非递归遍历二叉树

    // 前序遍历

    public void noRecursionPreOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        if (node != null) {
            stack.push(node);
            while (!stack.empty()) {
                node = stack.pop();
                showData(node);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }

    }

    // 中序遍历
    public void noRecursionInOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = node;
        while (p != null || stack.size() > 0) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (stack.size() > 0) {
                p = stack.pop();
                showData(p);
                p = p.right;
            }
        }
    }

    //后序遍历 ,需要记录遍历过的节点
    public void noRecursionPostOrder(TreeNode node)
    {
        TreeNode pre=node;
        Stack<TreeNode> stack=new Stack<>();
        while(node!=null)
        {
            for(;node.left!=null;node=node.left)
            {
                stack.push(node);
            }
            while(node!=null&&(node.right==null||node.right==pre))
            {
                showData(node);
                pre=node;
                if(stack.empty()) return;
                node=stack.pop();
            }
            stack.push(node);
            node=node.right;
        }
    }

    // 二叉树 层次遍历
    public void levelOrder(TreeNode node) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        TreeNode p;
        while ( !queue.isEmpty()) {

            p = queue.poll();
            showData(p);

            if (p.left != null) {
                queue.add(p.left);
            }

            if (p.right != null) {
                queue.add(p.right);
            }

        }

    }

    // 求二叉树中节点个数
    public int NodeCount(TreeNode node) {

        int n1, n2;

        if (node == null) {
            return 0;
        } else {

            n1 = NodeCount(node.left);
            n2 = NodeCount(node.right);
            return (n1 + n2 + 1);
        }


    }


    public int BTHeight(TreeNode node) {

        int l, r;

        if (node == null) {
            return 0;
        } else {

            l = BTHeight(node.left);
            r = BTHeight(node.right);

            return l > r ? l + 1 : r + 1;

        }




    }


    public void insertNode(TreeNode node, TreeNode one) {

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(node);

        TreeNode p;

        while ( !queue.isEmpty()) {

            p = queue.poll();
            if ( p.left == null) {
                p.left = one;
                return;
            }

            if ( p.right == null) {
                p.right = one;
                return;
            }

            if (p.left != null) {
                queue.add(p.left);
            }

            if (p.right != null) {
                queue.add(p.right);
            }


        }


    }


    public void delete(int d) {



    }

    public static void main(String[] args)
    {
        //顺序存储的满二叉树或者完全二叉树
        int[] arr={1,2,3,4,5,6,7,8,9};
        BinaryTree bt=new BinaryTree(arr, 0);
        System.out.println("递归前序遍历：");
        bt.preOrder(bt.root);
        System.out.println();
        System.out.println("递归中序遍历：");
        bt.inOrder(bt.root);
        System.out.println();
        System.out.println("递归后序遍历：");
        bt.postOrder(bt.root);
        System.out.println();
        System.out.println("非递归前序遍历：");
        bt.noRecursionPreOrder(bt.root);
        System.out.println();
        System.out.println("非递归中序遍历：");
        bt.noRecursionInOrder(bt.root);
        System.out.println();
        System.out.println("非递归后序遍历：");
        bt.noRecursionPostOrder(bt.root);

        System.out.println();
        System.out.println("层次遍历: \n");
        bt.levelOrder(bt.root);


        System.out.println("\n===============================================");

        System.out.println(bt.NodeCount(bt.root));

        System.out.println("\n===============================================");


        System.out.println(bt.BTHeight(bt.root));

        bt.insertNode(bt.root, new TreeNode(100));

        bt.insertNode(bt.root, new TreeNode(1001));


        bt.insertNode(bt.root, new TreeNode(10001));

        System.out.println("\n===============================================");

        System.out.println("层次遍历: \n");
        bt.levelOrder(bt.root);
        System.out.println("\n===============================================");


        System.out.println("递归前序遍历：");
        bt.preOrder(bt.root);
    }

}
/*
递归前序遍历：
1 2 4 8 9 5 3 6 7
递归中序遍历：
8 4 9 2 5 1 6 3 7
递归后序遍历：
8 9 4 5 2 6 7 3 1
非递归前序遍历：
1 2 4 8 9 5 3 6 7
非递归中序遍历：
8 4 9 2 5 1 6 3 7
非递归后序遍历：
8 9 4 5 2 6 7 3 1
 */