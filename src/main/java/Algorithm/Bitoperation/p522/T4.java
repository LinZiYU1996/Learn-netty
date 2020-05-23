package Algorithm.Bitoperation.p522;


import com.sun.org.apache.bcel.internal.generic.SWAP;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/22
 * \* Time: 21:09
 * \* Description:
 *
 * 第344题：编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 示例 1：
 *
 * 输入：["h","e","l","l","o"]
 *
 * 输出：["o","l","l","e","h"]
 *
 *
 *
 * 示例 2：
 *
 * 输入：["H","a","n","n","a","h"]
 *
 * 输出：["h","a","n","n","a","H"]
 * \
 */
public class T4 {

    //相当简单的经典题目，直接上题解：使用双指针进行反转字符串。
    //
    //假设输入字符串为["h","e","l","l","0"]
    //
    //定义left和right分别指向首元素和尾元素
    //
    //当left < right ，进行交换。
    //
    //交换完毕，left++，right--
    //
    //直至left == right

    public static void reverseString(char[] chars) {

        int left = 0;

        int right = chars.length - 1;

        while (left < right) {


            swap(chars, left, right);

            left++;

            right--;

        }

        System.out.println(String.valueOf(chars));

    }

    private static void swap(char[] c,int i,int j) {

        char t;

        t = c[i];

        c[i] = c[j];

        c[j] = t;
    }

    public static void main(String[] args) {

        char[] chars =
                {
                  'a','b','c','d','e','f','g','1'
                };

        reverseString(chars);

        System.out.println(String.valueOf(chars));

    }

}
