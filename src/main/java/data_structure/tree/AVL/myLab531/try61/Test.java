package data_structure.tree.AVL.myLab531.try61;

import data_structure.tree.AVL.myLab531.MyAVLTree;

import java.util.Stack;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/6/1
 * \* Time: 22:24
 * \* Description:
 * \
 */
public class Test {


    /*
     * 获取树的高度
     */
    public static int height(MyAVLTree.MyNode tree) {
        if (tree != null)
            return tree.height;

        return 0;
    }

    public static void setBalance(MyAVLTree.MyNode... nodes) {
        for (MyAVLTree.MyNode n : nodes) {
            reheight(n);
            int b = height(n.right) - height(n.left);
            n.balance = b;
        }
    }

    public static void reheight(MyAVLTree.MyNode node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    /*
     * LL：左左对应的情况(左单旋转)。
     *
     * 返回值：旋转后的根节点
     */
    public static MyAVLTree.MyNode leftLeftRotation(MyAVLTree.MyNode k2) {
        MyAVLTree.MyNode k1;

        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.height = Math.max( height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max( height(k1.left), k2.height) + 1;

        setBalance(k1,k2);
        return k1;
    }

    public static void print(MyAVLTree.MyNode root) {

        if (root != null) {

            System.out.println(root.balance);
            print(root.left);
            print(root.right);

        }


    }

    public static void adjustBalance(MyAVLTree.MyNode root) {
        if (root != null) {
            setBalance(root);
            adjustBalance(root.left);
            adjustBalance(root.right);
        }
    }



    public static void main(String[] args) {
        int[] arr = {12,10,6,5,9,11,20};
        MyAVLTree<Integer> tree = new MyAVLTree<>();
        int i;

        for(i=0; i<arr.length; i++) {
            tree.insert(arr[i]);

        }
        System.out.println("\n=========================================\n");
        BTreePrinter.printNode(tree.root);


        System.out.println("\n=========================================\n");

        tree.root = leftLeftRotation(tree.root);


        System.out.println("\n=========================================\n");


        BTreePrinter.printNode(tree.root);
        System.out.println("\n=========================================\n");

    }
}
