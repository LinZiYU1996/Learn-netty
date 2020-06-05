package data_structure.tree.AVL.myLab531.try61;

import data_structure.tree.AVL.myLab531.MyAVLTree;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/6/3
 * \* Time: 20:34
 * \* Description:
 * \
 */
public class DeleteTest1 {


    public static void main(String[] args) {

        int[] arr = {10,9,8,7,12,20};
        MyAVLTree<Integer> tree = new MyAVLTree<>();

        int i;

        for(i=0; i<arr.length; i++) {
            tree.insert(arr[i]);

        }
        System.out.println("\n=========================================\n");

        BTreePrinter.printNode(tree.root);


        System.out.println("\n=========================================\n");

        tree.root = tree.removeROOT(tree.root, 20);

        System.out.println("\n=========================================\n");

        BTreePrinter.printNode(tree.root);


        System.out.println("\n=========================================\n");



    }
}
