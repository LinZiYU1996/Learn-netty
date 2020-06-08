package data_structure.tree.AVL.myLab64;

import lombok.extern.slf4j.Slf4j;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/28
 * \* Time: 20:22
 * \* Description:
 * \
 */

@Slf4j
public class AVLTree {


    public Node_64 root;




    public boolean insert(int key) {
        if (root == null)
            root = new Node_64(key, null);
        else {
            Node_64 n = root;
            Node_64 parent;
            while (true) {
                if (n.key == key)
                    return false;

                parent = n;

                boolean goLeft = n.key > key;
                n = goLeft ? n.left : n.right;

                if (n == null) {
                    if (goLeft) {
                        parent.left = new Node_64(key, parent);
                    } else {
                        parent.right = new Node_64(key, parent);
                    }
                    rebalance(parent);
                    break;
                }
            }
        }
        return true;
    }

    private void delete(Node_64 node) {
        if (node.left == null && node.right == null) {
            log.info("node.left == null && node.right == null");

            if (node.parent == null) root = null;
            else {
                Node_64 parent = node.parent;
                if (parent.left == node) {
                    parent.left = null;
                } else parent.right = null;

                BTreePrinter.printNode(root);

                rebalance(parent);
            }
            return;
        }
        // 从左子树 找出一个点
        // 比右子树里面的都小
        // 但是要比左子树 其它节点都大
        // 那肯定找最深那个右子树的结点
        if (node.left != null) {
            log.info("node.left != null");

            Node_64 child = node.left;
            while (child.right != null) child = child.right;
            node.key = child.key;
            delete(child);
        } else {
            log.info("node.right != null");

            Node_64 child = node.right;
            while (child.left != null) child = child.left;
            node.key = child.key;
            delete(child);
        }
    }

    public void delete(int delKey) {
        if (root == null)
            return;
        Node_64 node = root;
        Node_64 child = root;

        while (child != null) {
            node = child;
            child = delKey >= node.key ? node.right : node.left;
            if (delKey == node.key) {
                delete(node);
                return;
            }
        }
    }

    private void rebalance(Node_64 n) {
        setBalance(n);


        // 左子树 里面 的结点 偏多了
        if (n.balance == -2) {
            log.info(n.key +"{}");
            if (height(n.left.left) >= height(n.left.right)) {
                log.info("height(n.left.left) >= height(n.left.right)");

                n = rotateRight(n);
            } else {

                log.info("height(n.left.left) < height(n.left.right)");

                n = rotateLeftThenRight(n);
            }

        } else if (n.balance == 2) {
            log.info(n.key +"{}");

            if (height(n.right.right) >= height(n.right.left)) {
                log.info("n.right.right) >= height(n.right.left");
                n = rotateLeft(n);
            } else {
                log.info("n.right.right) < height(n.right.left");
                n = rotateRightThenLeft(n);
            }
        }

        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }

    public Node_64 rotateLeft(Node_64 a) {

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

    private Node_64 rotateRight(Node_64 a) {

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

    private Node_64 rotateLeftThenRight(Node_64 n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }

    private Node_64 rotateRightThenLeft(Node_64 n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }

    private int height(Node_64 n) {
        if (n == null)
            return -1;
        return n.height;
    }

    private void setBalance(Node_64... nodes) {
        for (Node_64 n : nodes) {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }

    public void printBalance() {
        printBalance(root);
    }

    private void printBalance(Node_64 n) {
        if (n != null) {
            printBalance(n.left);
            System.out.printf("%s ", n.balance);
            printBalance(n.right);
        }
    }

    private void reheight(Node_64 node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    public boolean search(int key) {
        Node_64 result = searchHelper(this.root,key);
        if(result != null)
            return true ;

        return false ;
    }

    private Node_64 searchHelper(Node_64 root, int key)
    {
        //root is null or key is present at root
        if (root==null || root.key==key)
            return root;

        // key is greater than root's key
        if (root.key > key)
        return searchHelper(root.left, key); // call the function on the node's left child

        // key is less than root's key then
        //call the function on the node's right child as it is greater
        return searchHelper(root.right, key);
    }

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
