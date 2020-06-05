package data_structure.tree.AVL.myLab64;

import lombok.extern.slf4j.Slf4j;

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




}
