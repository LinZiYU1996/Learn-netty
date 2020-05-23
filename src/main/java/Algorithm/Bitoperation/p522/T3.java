package Algorithm.Bitoperation.p522;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/22
 * \* Time: 20:38
 * \* Description:
 *
 * 第268题：不使用运算符 + 和 - ，计算两整数 a 、b 之和。
 *
 * \
 */
public class T3 {

    //下面这两个技巧大家需要记住，这也是讲解本题的目的：
    //
    //
    //
    //“异或”是一个无进位加法，说白了就是把进位砍掉。比如01^01=00。
    //
    //“与”可以用来获取进位，比如01&01=01，然后再把结果左移一位，就可以获取进位结果。

    public static int getSum(int a, int b){
        while(b != 0){
            int temp = a ^ b;
            b = (a & b) << 1;
            a = temp;
            }
        return a;
    }


    public static void main(String[] args) {

        System.out.println(getSum(100,6));

    }

}
