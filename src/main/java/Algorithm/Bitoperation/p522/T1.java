package Algorithm.Bitoperation.p522;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/22
 * \* Time: 20:25
 * \* Description:
 *
 *
 * 第268题：给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 *
 *
 * \
 */
public class T1 {

    //首先求出数组的和，然后再利用公式求出前n+1项之和，最终求差值，即为缺失的值！比如下面长度为4的数组，缺失4。

    public static int missingNumber(int[] a) {

        int len = a.length;

        int res = (len + 1) * len / 2;

        for (int i : a) {
            res -= i;
        }

        return res;
    }


    //位运算的方式，本质和数学法一样，都是通过与无序序列抵消，然后找到缺失值。所以不能说哪个更好，都掌握最好~
    //直接使用“异或”进行求解。这个其实讲了好多次了，就是利用“两个相同的数，使用异或可以相消除”的原理。

    public static int other_missingNumber(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++)
            res ^= nums[i] ^ i;
        return res ^ nums.length;
    }


    public static void main(String[] args) {

        int[] d =
                {
                  9,6,4,2,3,5,7,0,1
                };

        System.out.println(missingNumber(d));
        System.out.println(other_missingNumber(d));
    }
}
