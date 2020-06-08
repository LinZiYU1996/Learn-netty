package data_structure.tree.AVL.myLab64;

import static data_structure.tree.AVL.myLab64.Operation.insert;
import static data_structure.tree.AVL.myLab64.T1.rotateRight;
import static data_structure.tree.AVL.myLab64.T2.rotateLeft;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/6/4
 * \* Time: 20:55
 * \* Description:
 * \
 */
public class T3 {



    public static Node_64 rotateRightThenLeft(Node_64 n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }

    public static void main(String[] args) {

        int[] arr = {10,9,20,15,11,17,30};
        int i;

        for(i=0; i<arr.length; i++) {
            insert(arr[i]);
        }
        BTreePrinter.printNode(Operation.root);

        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");

        Operation.root = rotateRightThenLeft(Operation.root);


        BTreePrinter.printNode(Operation.root);

    }
}
/*






       10
      / \
     /   \
    /     \
   /       \
   9       20
          / \
         /   \
         15   30
        / \
        11 17


================================================


================================================


================================================

   15
  / \
 /   \
 10   20
/ \ / \
9 11 17 30













 */