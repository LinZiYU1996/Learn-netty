package guava.Ordering.p524;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/24
 * \* Time: 22:25
 * \* Description:
 *
 *
 * Ordering是Guava类库提供的一个犀利强大的比较器工具，Guava的Ordering和JDK Comparator相比功能更强。它非常容易扩展，
 * 可以轻松构造复杂的comparator，然后用在容器的比较、排序等操作中。
 *
 *常用静态方法
 *
 * Ordering.natural();                  // 使用Comparable类型的自然顺序， 例如：整数从小到大，字符串是按字典顺序;
 * Ordering.usingToString();            // 使用toString()返回的字符串按字典顺序进行排序；
 * Ordering.from(Comparator);           // 将Comparator转换为Ordering
 * new Ordering<T>(){                   // 或者直接构建一个Ordering对象，并实现compare方法
 * 	public int compare(T left, T right){}
 * }
 *
 * 实例方法(支持链式)
 * com.google.common.collect.Ordering
 *
 *
 *reverse();                            //返回与当前Ordering相反的排序
 * nullsFirst();                         //返回一个将null放在non-null元素之前的Ordering，其他的和原始的Ordering一样
 * nullsLast();                          //返回一个将null放在non-null元素之后的Ordering，其他的和原始的Ordering一样
 * compound(Comparator);                 //返回一个使用Comparator的Ordering，Comparator作为第二排序元素
 * lexicographical();                    //返回一个按照字典元素迭代的Ordering
 * onResultOf(Function);                 //将function应用在各个元素上之后, 在使用原始ordering进行排序
 * greatestOf(Iterable iterable, int k); //返回指定的前k个可迭代的最大的元素，按照当前Ordering从最大到最小的顺序
 * leastOf(Iterable iterable, int k);    //返回指定的前k个可迭代的最小的元素，按照当前Ordering从最小到最大的顺序
 * isOrdered(Iterable);                  //是否有序(前面的元素可以大于或等于后面的元素)，Iterable不能少于2个元素
 * isStrictlyOrdered(Iterable);          //是否严格有序(前面的元素必须大于后面的元素)，Iterable不能少于两个元素
 * sortedCopy(Iterable);                 //返回指定的元素作为一个列表的排序副本
 * \
 */
public class OrderingTest {

    @Test
    public void test() {
        testNatural();
//        testFrom();
//        testReverse();
//        testNullFirst();
//        testNullLast();
//        testCompound();
//        testOnResultOf();
//        testGreatestOf();
//        testLeastOf();
//        testIsOrdered();
//        testIsStrictlyOrdered();
//        testSortedCopy();
    }


    private void testNatural() {
        // test int order
        List<Integer> unorderedIntList = Lists.newArrayList(5, 3, 2, 4, 1);
        List<Integer> orderedIntList = Lists.newArrayList(1, 2, 3, 4, 5);
        Collections.sort(unorderedIntList, Ordering.natural());
        assertTrue(orderedIntList.equals(unorderedIntList));

        // test string order
        List<String> unorderedStringList = Lists.newArrayList("Test", "Jerry", "Rock", "Ohaha", "Yeah");
        List<String> orderedStringList = Lists.newArrayList("Jerry", "Ohaha", "Rock", "Test", "Yeah");
        Collections.sort(unorderedStringList, Ordering.natural());
        assertTrue(orderedStringList.equals(unorderedStringList));
    }


    private void testFrom() {
        List<Integer> unorderedIntList = Lists.newArrayList(5, 3, 2, 4, 1);
        List<Integer> orderedIntList = Lists.newArrayList(1, 2, 3, 4, 5);
        Collections.sort(unorderedIntList, Ordering.from(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1.compareTo(i2);
            }
        }));
        assertTrue(orderedIntList.equals(unorderedIntList));
    }


    private void testReverse() {
        List<Integer> unorderedIntList = Lists.newArrayList(5, 3, 2, 4, 1);
        List<Integer> orderedIntList = Lists.newArrayList(5, 4, 3, 2, 1);
        Collections.sort(unorderedIntList, Ordering.natural().reverse());
        assertTrue(orderedIntList.equals(unorderedIntList));
    }

    private void testNullFirst() {
        List<Integer> unorderedIntList = Lists.newArrayList(5, 3, null, 4, 1);
        List<Integer> orderedIntList = Lists.newArrayList(null, 1, 3, 4, 5);
        Collections.sort(unorderedIntList, Ordering.natural().nullsFirst());
        assertTrue(orderedIntList.equals(unorderedIntList));
    }

    private void testNullLast() {
        List<Integer> unorderedIntList = Lists.newArrayList(5, 3, null, 4, 1);
        List<Integer> orderedIntList = Lists.newArrayList(1, 3, 4, 5, null);
        Collections.sort(unorderedIntList, Ordering.natural().nullsLast());
        assertTrue(orderedIntList.equals(unorderedIntList));
    }




}
