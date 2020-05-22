package Algorithm.Bitoperation.p521;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/21
 * \* Time: 21:52
 * \* Description:
 *
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 * 示例 1：
 *
 * 输入: n = 3 输出: 6
 * 示例 2：
 *
 * 输入: n = 9 输出: 45
 * 限制：
 *
 * 1 <= n <= 10000
 * \
 */
public class T {


    //题目上手，因为不能使用公式直接计算（公式中包含乘除法）
    // ，所以考虑使用递归进行求解，但是递归中一般又需要使用if来指定返回条件
    // （这里不允许使用if），所以没办法使用普通的递归思路。那该怎么办呢
    // ？这里我们直接上代码（本题将展示多个语言），再进行分析。


    //将递归的返回条件取非然后作为 && 的第一个条件，递归主体转换为第二个条件语句


    public int sumNums(int n) {
               boolean b = n > 0 && ( (n += sumNums(n - 1)) > 0);
               return n;
    }


    public int sumNumsLearn(int n) {

        System.out.println("start : " + n);
        boolean b = n > 0 && ( (n += sumNumsLearn(n - 1)) > 0);
        System.out.println("end   " + n);

        return n;
    }

    public static void main(String[] args) {

        new T().sumNumsLearn(3);
        //start : 3
        //start : 2
        //start : 1
        //start : 0
        //end   0
        //end   1
        //end   3
        //end   6





    }




}
