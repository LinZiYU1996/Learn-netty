package data_structure.tree.AVL.myLab64;

import data_structure.tree.AVL.myLab531.MyAVLTree;

import static data_structure.tree.AVL.myLab64.Operation.insert;
import static data_structure.tree.AVL.myLab64.Operation.setBalance;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/6/4
 * \* Time: 20:00
 * \* Description:
 * \
 */
public class T1 {


    public static Node_64 rotateRight(Node_64 a) {

        Node_64 b = a.left;
        b.parent = a.parent;

        a.left = b.right;

        if (a.left != null)
            a.left.parent = a;

        b.right = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        setBalance(a, b);

        return b;
    }
    public static void main(String[] args) {
        int[] arr = {12,10,6,5,9,11,20};


        int i;

        for(i=0; i<arr.length; i++) {
            insert(arr[i]);
        }
        BTreePrinter.printNode(Operation.root);

        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");

        Operation.root = rotateRight(Operation.root);

        BTreePrinter.printNode(Operation.root);


    }

}

/*


       12
      / \
     /   \
    /     \
   /       \
   10       20
  / \
 /   \
 6   11
/ \
5 9





   10
  / \
 /   \
 6   12
/ \ / \
5 9 11 20





 */