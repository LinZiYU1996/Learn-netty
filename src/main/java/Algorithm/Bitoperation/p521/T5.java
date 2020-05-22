package Algorithm.Bitoperation.p521;

import java.util.HashSet;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/21
 * \* Time: 22:51
 * \* Description:
 *
 * 第137题：给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。说明：你的算法应该具有线性时间复杂度。你可以不使用额外空间来实现吗？
 *
 * \
 */
public class T5 {

    public static int singleNumber(int[] nums) {


        HashSet<Integer> set = new HashSet<>();



        int sum3 = 0,sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            set.add(nums[i]);
        }

        for (int i : set) {
            sum3 += i;
        }

        sum3 *= 3;

        return (sum3 - sum) / 2;

    }


    public static void main(String[] args) {

        int[] a =
                {
                  0,1,0,1,0,1,99
                };

        System.out.println(singleNumber(a));
    }

}
