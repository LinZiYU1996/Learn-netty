package data_structure.tree.AVL.Lab528.my;


import data_structure.tree.AVL.Lab528.AVLTree;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/28
 * \* Time: 20:28
 * \* Description:
 * \
 */
public class Tree {

    private Node root;

    private class Node {
        private int key;
        private int balance;
        private int height;
        private Node left, right, parent;

        Node(int k, Node p) {
            key = k;
            parent = p;
        }
    }

    public boolean insert(int key) {
        if (root == null)
            root = new Node(key, null);
        else {
            Node n = root;
            Node parent;
            while (true) {
                if (n.key == key)
                    return false;

                parent = n;

                boolean goLeft = n.key > key;
                n = goLeft ? n.left : n.right;

                if (n == null) {
                    if (goLeft) {
                        parent.left = new Node(key, parent);
                    } else {
                        parent.right = new Node(key, parent);
                    }
                    break;
                }
            }
        }
        return true;
    }

    public void printBalance() {
        printBalance(root);
    }

    private void printBalance(Node n) {
        if (n != null) {
            printBalance(n.left);
            System.out.printf("%s ", n.balance);
            printBalance(n.right);
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();

        System.out.println("Inserting values 1 to 10");
        for (int i = 1; i < 10; i++)
            tree.insert(i);

        System.out.print("Printing balance: ");
        tree.printBalance();
    }
}
