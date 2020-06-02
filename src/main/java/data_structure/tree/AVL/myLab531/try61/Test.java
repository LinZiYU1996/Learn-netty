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






    public static void main(String[] args) {
        int[] arr = {5,3,1,4,8};
        MyAVLTree<Integer> tree = new MyAVLTree<>();
        int i;

        for(i=0; i<arr.length; i++) {
            tree.insert(arr[i]);
        }

        BTreePrinter.printNode(tree.root);
    }
}
