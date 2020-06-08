package data_structure.tree.AVL.myLab64;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/6/7
 * \* Time: 20:46
 * \* Description:
 * \
 */
public class Delete_1 {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] arr = {12,5,3,8,6,9,25,13,20};
        int i;
        System.out.println("Inserting values 1 to 10");
        for ( i = 0; i < arr.length; i++)
            tree.insert(arr[i]);

        System.out.print("Printing balance: ");
        tree.printBalance();

        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");

        BTreePrinter.printNode(tree.root);

        tree.delete(12);

        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");

        BTreePrinter.printNode(tree.root);
    }
}
