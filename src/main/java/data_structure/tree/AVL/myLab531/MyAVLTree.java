package data_structure.tree.AVL.myLab531;


import data_structure.tree.AVL.myLab531.try61.BTreePrinter;
import org.junit.Test;

import java.util.Stack;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/31
 * \* Time: 22:31
 * \* Description:
 * \
 */
public class MyAVLTree<T extends Comparable<T>> {

    // AVL树的节点(内部类)
    public class MyNode<T extends Comparable<T>>  {
       public T key;                // 关键字(键值)
        public int height;         // 高度
        public int balance;
        public MyNode<T> left;    // 左孩子
        public  MyNode<T> right;    // 右孩子

        /*
        几个组成对象:
        (01) key -- 是关键字，是用来对AVL树的节点进行排序的。
        (02) left -- 是左孩子。
        (03) right -- 是右孩子。
        (04) height -- 是高度。
         */
        public MyNode(T key, MyNode<T> left, MyNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 0;
        }


    }

    /**
     * 使用树形结构显示
     */
    public void displayTree(MyAVLTree.MyNode root){
        Stack globalStack=new Stack();
        globalStack.push(root);
        // int nBlank=32;
        int nBlank=32;
        boolean isRowEmpty=false;
        String dot="............................";
        System.out.println(dot+dot+dot);
        while (isRowEmpty==false){
            Stack localStack=new Stack();
            isRowEmpty=true;
            for (int j=0;j<nBlank;j++)//{
            {

                System.out.print("-");
            }
            while (globalStack.isEmpty()==false){
                //里面的while循环用于查看全局的栈是否为空
                MyAVLTree.MyNode temp=(MyAVLTree.MyNode)globalStack.pop();
                if (temp!=null){
                    System.out.print(temp.key);

                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    //如果当前的节点下面还有子节点，则必须要进行下一层的循环
                    if (temp.left!=null||temp.right!=null){
                        isRowEmpty=false;

                    }
                }else {
                    //如果全局的栈则不为空
                    System.out.print(" #! ");
                    localStack.push(null);
                    localStack.push(null);

                }


                //打印一些空格
                for (int j=0;j<nBlank*2-2;j++){
                    //System.out.print("&");
                    System.out.print(" ");
                }




            }//while end


            System.out.println();
            nBlank/=2;
            //这个while循环用来判断，local栈是否为空,不为空的话，则取出来放入全局栈中
            while (localStack.isEmpty()==false){
                globalStack.push(localStack.pop());
            }

            // }
        }//大while循环结束之后，输出换行
        System.out.println(dot+dot+dot);

    }




    public MyNode<T> root;    // 根结点

    public MyAVLTree() {
        root = null;
    }

    /*
     * 获取树的高度
     */
    public int height(MyNode<T> tree) {
        if (tree != null)
            return tree.height;

        return 0;
    }

    /*
    采用维基百科上的定义：树的高度为最大层次。即空的二叉树的高度是0，非空树的高度等于它的最大层次(根的层次为1，根的子节点为第2层，依次类推)。
     */
    public int height() {
        return height(root);
    }

    /*
     * 比较两个值的大小
     */
    public int max(int a, int b) {
        return a>b ? a : b;
    }


    public void insert(T value) {

        root = insert(root, value);

    }

    public MyNode<T> insert(MyNode<T> tree, T value) {

        if (tree == null ){

            //新建节点
                    tree = new MyNode<T>(value, null, null);
            if (tree==null) {
                System.out.println("ERROR: create avltree node failed!");
                return null;
            }

        } else {

            int cmp = value.compareTo(tree.key);


            if (cmp < 0) {    // 应该将key插入到"tree的左子树"的情况

                tree.left = insert(tree.left, value);

            } else if (cmp > 0) {    // 应该将key插入到"tree的右子树"的情况

                tree.right = insert(tree.right, value);

            }


        }

        tree.height = max( height(tree.left), height(tree.right)) + 1;

        return tree;
    }



    public void inOrder(MyNode<T> tree) {

        if (tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key + "      ");
            inOrder(tree.right);
        }
    }



    public  void show( MyNode root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        System.out.println("treeDepth>>>>" + treeDepth);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度

        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 2 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i ++) {
            for (int j = 0; j < arrayWidth; j ++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth/ 2, res, treeDepth);

        System.out.println(res.length+"<><><><><>"+res[0].length);
        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line: res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i ++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2: line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

    // 用于获得树的层数
    public  int getTreeDepth(MyNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }


    public  void writeArray(MyNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.key);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }


//
//          @Test
//        public void test1() {
//
//            int[] arr = {5,3,1,4,8};
//            MyAVLTree<Integer> tree = new MyAVLTree<>();
//            int i;
//
//            for(i=0; i<arr.length; i++) {
//                tree.insert(arr[i]);
//            }
//
//            tree.show(tree.root);
//
//
//        }



    public static void main(String[] args) {

        int arr[]= {3,2,1,4,5,6,7,16,15};

        MyAVLTree<Integer> tree = new MyAVLTree<>();

        int i;

        for(i=0; i<arr.length; i++) {
            System.out.printf("%d ", arr[i]);
            tree.insert(arr[i]);


        }

//        System.out.println("\n=========================================\n");
//
//        tree.inOrder(tree.root);
//
//        System.out.println("\n=========================================\n");


        BTreePrinter.printNode(tree.root);


    }



}


/*

                                                                  3
                                                          /           \
                                                    2                       4                                                    
                                               /                                 \
                                          1                                           5
                                                                                          \
                                                                                              6
                                                                                                 \
                                                                                                    7
                                                                                                      \
                                                                                                        16
                                                                                                       /
                                                                                                      15



 */