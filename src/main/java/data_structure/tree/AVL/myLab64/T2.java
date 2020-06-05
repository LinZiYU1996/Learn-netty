package data_structure.tree.AVL.myLab64;

import static data_structure.tree.AVL.myLab64.Operation.insert;
import static data_structure.tree.AVL.myLab64.Operation.setBalance;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/6/4
 * \* Time: 20:54
 * \* Description:
 * \
 */
public class T2 {

    public static Node_64 rotateLeft(Node_64 a) {

        Node_64 b = a.right;
        b.parent = a.parent;

        a.right = b.left;

        if (a.right != null)
            a.right.parent = a;

        b.left = a;
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

        int[] arr = {10,9,15,11,20,17,30};

        int i;

        for(i=0; i<arr.length; i++) {
            insert(arr[i]);
        }
        BTreePrinter.printNode(Operation.root);

        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");

        Operation.root = rotateLeft(Operation.root);

        BTreePrinter.printNode(Operation.root);
    }
}
