package data_structure.tree.AVL.myLab64;

import lombok.extern.slf4j.Slf4j;

import static data_structure.tree.AVL.myLab64.T1.rotateRight;
import static data_structure.tree.AVL.myLab64.T2.rotateLeft;
import static data_structure.tree.AVL.myLab64.T3.rotateRightThenLeft;
import static data_structure.tree.AVL.myLab64.T4.rotateLeftThenRight;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/6/4
 * \* Time: 19:59
 * \* Description:
 * \
 */

@Slf4j
public class Operation {
    public static Node_64 root;

    public static boolean insert(int key) {
        if (root == null)
            root = new Node_64(key, null);
        else {

            Node_64 n = root;
            log.info(n.key + "{}");
            Node_64 parent;
            while (true) {
                if (n.key == key)
                    return false;

                parent = n;
                log.info(parent.key + "{parent}");
                boolean goLeft = n.key > key;
                n = goLeft ? n.left : n.right;

                if (n == null) {
                    if (goLeft) {
                        parent.left = new Node_64(key, parent);
                    } else {
                        parent.right = new Node_64(key, parent);
                    }
//                    rebalance(parent);
                    break;
                }
            }
        }
        return true;
    }


    public static void setBalance(Node_64... nodes) {
        for (Node_64 n : nodes) {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }
    public static void reheight(Node_64 node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    public static int height(Node_64 n) {
        if (n == null)
            return -1;
        return n.height;
    }





    public static void delete(Node_64 node) {
        if (node.left == null && node.right == null) {
            log.info("node.left == null && node.right == null");
            if (node.parent == null) root = null;
            else {
                Node_64 parent = node.parent;
                if (parent.left == node) {
                    parent.left = null;
                } else parent.right = null;
                rebalance(parent);
            }
            return;
        }
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

    public static void delete(int delKey) {
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

    public static void rebalance(Node_64 n) {
        setBalance(n);

        if (n.balance == -2) {
            if (height(n.left.left) >= height(n.left.right)) {
                log.info("height(n.left.left) >= height(n.left.right)");
                n = rotateRight(n);
            } else {
                log.info("height(n.left.left) < height(n.left.right)");
                n = rotateLeftThenRight(n);
            }

        } else if (n.balance == 2) {
            if (height(n.right.right) >= height(n.right.left))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }

        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }



}
