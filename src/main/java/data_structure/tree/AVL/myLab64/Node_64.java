package data_structure.tree.AVL.myLab64;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/6/4
 * \* Time: 19:50
 * \* Description:
 * \
 */
public class Node_64 {

    public int key;
    public int balance;
    public int height;
    public Node_64 left;
    public Node_64 right;
    public Node_64 parent;

    Node_64(int k,Node_64 p) {
        key = k;
        parent = p;
    }
}
