package Algorithm.Bitoperation.p521;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/21
 * \* Time: 22:26
 * \* Description:
 *
 * 第231题：给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 *
 * \
 */
public class T3 {


    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & ( n - 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(218));
    }

}
