package Algorithm.Bitoperation.p521;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/21
 * \* Time: 22:36
 * \* Description:
 *
 * 第136题：给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 *
 * \
 */
public class T4 {


    public static int singleNumber(int[] nums) {

        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {

            ans = ans ^ nums[i];
            System.out.println(ans);
        }

        return ans;
    }

    public static void main(String[] args) {

        System.out.println( 5 ^ 3);
        //6

        System.out.println(4 ^ 1);
        //5

        int[] a = {4,1,2,1,2};

        singleNumber(a);
        //5
        //7
        //6
        //4



    }
}
