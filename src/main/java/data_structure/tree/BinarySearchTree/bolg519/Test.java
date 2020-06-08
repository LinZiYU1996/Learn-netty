package data_structure.tree.BinarySearchTree.bolg519;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/6/7
 * \* Time: 20:32
 * \* Description:
 * \
 */
public class Test {

    public static void main(String[] args) {

        SearchTree tree = new SearchTree();

        int[] arr = {12,5,3,8,6,9,25,13,20};
        int i;
        System.out.println("Inserting values 1 to 10");
        for ( i = 0; i < arr.length; i++)
            tree.NoRecursionAddTreeNode(tree.root, arr[i]);


        BTreePrinter.printNode(tree.root);


        /*
        12
      / \
     /   \
    /     \
   /       \
   5       25
  / \     /
 /   \   /
 3   8   13
    / \   \
    6 9   20
         */
    }
}
