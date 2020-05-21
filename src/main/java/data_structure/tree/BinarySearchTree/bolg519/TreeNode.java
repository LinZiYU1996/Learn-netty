package data_structure.tree.BinarySearchTree.bolg519;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/19
 * \* Time: 21:17
 * \* Description:
 * \
 */
public class TreeNode {

    public Integer data;

    /*该节点的父节点*/
    public TreeNode parent;

    /*该节点的左子节点*/
    public TreeNode left;

    /*该节点的右子节点*/
    public TreeNode right;

    public TreeNode(Integer data) {
        this.data = data;

    }

    @Override
    public String toString() {
        return "TreeNode [data=" + data + "]";
    }




}
