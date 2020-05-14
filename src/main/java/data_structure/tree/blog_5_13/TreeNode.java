package data_structure.tree.blog_5_13;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/13
 * \* Time: 21:50
 * \* Description:
 * \
 */
public class TreeNode {

    public int data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int data) {
        super();
        this.data = data;
    }

    @Override
    public String toString() {
        return data + " ";
    }


}
