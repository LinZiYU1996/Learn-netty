package data_structure.tree.AVL.myLab64;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/6/7
 * \* Time: 20:47
 * \* Description:
 * \
 */
public class Delete_2 {

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();
        int[] arr = {20,10,9,11,50,40,30,45,70,60,90};
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

        tree.delete(50);

        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");
        System.out.println("\n================================================\n");

        BTreePrinter.printNode(tree.root);


    }
}
