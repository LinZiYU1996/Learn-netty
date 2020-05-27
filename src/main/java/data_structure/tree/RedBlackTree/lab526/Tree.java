package data_structure.tree.RedBlackTree.lab526;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/26
 * \* Time: 20:00
 * \* Description:
 * \
 */
public class Tree {

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    private class Node {
        int data;
        boolean color;
        Node left, right, parent;

        public Node(int data) {
            this.data = data;
            this.color = RED;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }


    //红黑树中结点的左旋操作 目标 g
    void rotateLeft(Node root, Node g) {

        Node p = g.right;

        g.right = p.left;

        if (g.right != null) {
            g.right.parent = g;
        }

        p.parent = g.parent;

        if (g.parent == null) {
            root = p;
        } else if (g == g.parent.left) {
            g.parent.left = p;
        } else {

            g.parent.right = p;
        }

        p.left = g;

        g.parent = p;


    }

    //红黑树中结点的右旋操作
    void rotateRight(Node root, Node g) {

        Node p = g.left;
        g.left = p.right;
        if (g.left != null)
            g.left.parent = g;
        p.parent = g.parent;
        if (g.parent == null)
            root = p;
        else if (g == g.parent.left)
            g.parent.left = p;
        else
            g.parent.right = p;

        p.right = g;

        g.parent = p;

    }

    Node BSTInsert(Node root, Node x) {

        if (root == null) {
            return x;
        }

        if (x.data < root.data) {
            root.left = BSTInsert(root.left, x);

            root.left.parent = root;
        }else if (x.data > root.data)
        {
            root.right = BSTInsert(root.right, x);
            root.right.parent = root;
        }
        /* 返回插入结点 x 的树根*/
        return root;
    }


    void fixViolation(Node root, Node pt) {

        Node parent_pt = null;

        Node grand_parent_pt = null;

        while ((pt != root) && (pt.color != BLACK) && (pt.parent.color == RED)) {
            parent_pt = pt.parent;

            grand_parent_pt = pt.parent.parent;

            /* 情况一:
pt的父结点parent_pt 是其爷爷结点grand_parent_pt的左孩子 */

            if (parent_pt == grand_parent_pt.left) {
                Node uncle_pt = grand_parent_pt.right;

                /* Case : a) pt 的叔叔结点 uncle_pt 为红色结点
只需要调整结点颜色，不需要旋转*/

                if (uncle_pt != null && uncle_pt.color == RED) {
                    grand_parent_pt.color = RED;
                    parent_pt.color = BLACK;
                    uncle_pt.color = BLACK;
                    pt = grand_parent_pt;
                } else {
                        // Case :b) x 的叔叔结点为黑色
                    /* Case : 2
pt 是其父结点的右孩子
需要左旋操作 */
                    if (pt == parent_pt.right) {
                        rotateLeft(root, parent_pt);
                        pt = parent_pt;
                        parent_pt = pt.parent;
                    }

                    /* Case : 3
pt 是其父结点的左孩子
需要右旋操作 */

                    rotateRight(root, grand_parent_pt);


                    pt = parent_pt;
                }
            }
        }



    }

}

