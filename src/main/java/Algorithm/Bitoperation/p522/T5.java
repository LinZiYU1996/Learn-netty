package Algorithm.Bitoperation.p522;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/22
 * \* Time: 21:22
 * \* Description:
 *
 * 全排列问题：给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 *
 *
 * 输入: [1,2,3]
 *
 * 输出:
 *
 * [
 *
 *   [1,2,3],
 *
 *   [1,3,2],
 *
 *   [2,1,3],
 *
 *   [2,3,1],
 *
 *   [3,1,2],
 *
 *   [3,2,1]
 *
 * ]
 *
 *
 *
 * \
 */
public class T5 {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
                dfs(nums, new ArrayList<>());
                return ans;
    }

    private void dfs(int[] nums, List<Integer> tmp) {
        System.out.println(Arrays.toString(nums) + "," + tmp);
        if (tmp.size() == nums.length) {
            ans.add(new ArrayList<>(tmp));
        } else {
            for (int num : nums) {
                if (!tmp.contains(num)) {
                    tmp.add(num);
                    dfs(nums, tmp);
                    tmp.remove(tmp.size() - 1);
                }
            }
        }
    }
//其实这个代码还是很容易理解的，他干了个啥事？就是当我们按顺序去枚举每一位时，我们要把已经选择过的数字排除掉（第16行代码），比如我们上面选择三个数字：
//
//
//
//在枚举第一位的时候，就有三种情况
//
//在枚举第二位的时候，就只有两种情况（前面已经出现的一个数字不可以再出现）
//
//在枚举第三位的时候，就只有一种情况（前面已经出现的两个数字不可以再出现）
//
//
//
//整个代码其实就干了这么一件事！而 第12行 的代码，其实就是说当枚举到最后一位的时候，这个就是我们要的排列结果，所以我们要放入到全排列结果集中。
//
//
//
//那这里还有一个很重要的代码，其实是 第19行，这一步其实是干啥！说白了就是在回到上一位时，我们要就把上一次的选择结果撤销掉。不然如果你之前选过了，后面不就不能继续用了么。
    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();

        integers.add(1);
        integers.add(2);
        integers.add(3);

        int[] n = {1,2,3};
        new T5().permute(n);

    }
    //[1, 2, 3],[]
    //[1, 2, 3],[1]
    //[1, 2, 3],[1, 2]
    //[1, 2, 3],[1, 2, 3]
    //[1, 2, 3],[1, 3]
    //[1, 2, 3],[1, 3, 2]
    //[1, 2, 3],[2]
    //[1, 2, 3],[2, 1]
    //[1, 2, 3],[2, 1, 3]
    //[1, 2, 3],[2, 3]
    //[1, 2, 3],[2, 3, 1]
    //[1, 2, 3],[3]
    //[1, 2, 3],[3, 1]
    //[1, 2, 3],[3, 1, 2]
    //[1, 2, 3],[3, 2]
    //[1, 2, 3],[3, 2, 1]
}
