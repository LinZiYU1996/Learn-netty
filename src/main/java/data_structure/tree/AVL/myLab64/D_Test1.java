package data_structure.tree.AVL.myLab64;

import static data_structure.tree.AVL.myLab64.Operation.insert;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/6/5
 * \* Time: 21:54
 * \* Description:
 * \
 */
public class D_Test1 {


    public static void main(String[] args) {
        int[] arr = {12,5,3,8,6,9,13,20,25};
        int i;

        for(i=0; i<arr.length; i++) {
            insert(arr[i]);
        }
        BTreePrinter.printNode(Operation.root);

        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");


        Operation.delete(8);
        BTreePrinter.printNode(Operation.root);

    }
}
