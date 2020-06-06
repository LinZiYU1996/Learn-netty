package data_structure.tree.AVL.myLab64;

import static data_structure.tree.AVL.myLab64.Operation.insert;
import static data_structure.tree.AVL.myLab64.T1.rotateRight;
import static data_structure.tree.AVL.myLab64.T2.rotateLeft;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/6/5
 * \* Time: 21:19
 * \* Description:
 * \
 */
public class T4 {



    public static Node_64 rotateLeftThenRight(Node_64 n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }
    public static void main(String[] args) {

        int[] arr = {15,10,7,12,11,13,20};
        int i;

        for(i=0; i<arr.length; i++) {
            insert(arr[i]);
        }
        BTreePrinter.printNode(Operation.root);

        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");

        Operation.root = rotateLeftThenRight(Operation.root);


        BTreePrinter.printNode(Operation.root);

        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");

        Operation.delete(10);
        BTreePrinter.printNode(Operation.root);

    }
}
