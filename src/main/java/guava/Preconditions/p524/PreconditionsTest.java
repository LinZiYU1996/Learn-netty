package guava.Preconditions.p524;

import com.google.common.collect.Lists;
import javafx.geometry.VPos;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/24
 * \* Time: 21:57
 * \* Description:
 * \
 */
public class PreconditionsTest {
//// 检查boolean是否为真
//// 失败时抛出 IllegalArgumentException
//Preconditions.checkArgument(boolean expression, String errMsg, Object... errMsgArgs)
//
//// 检查value是否为null
//// 失败时抛出 NullPointerException
//Preconditions.checkNotNull(T reference, String errMsg, Object... errMsgArgs)
//
//// 检查对象的一些状态, 不依赖方法参数(相比checkArgument, 在某些情况下更有语义...)
//// 失败时抛出 IllegalStateException
//Preconditions.checkState(boolean expression, String errMsg, Object... errMsgArgs)
//
//// 检查index是否在合法范围[0, size)(不包含size)
//// 失败时抛出 IndexOutOfBoundsException
//Preconditions.checkElementIndex(int index, int size, String desc)
//
//// 检查位置是否在合法范围[0, size](包含size)
//// 失败时抛出 IndexOutOfBoundsException
//Preconditions.checkPositionIndex(int index, int size, String desc)
//
//// 检查[start, end)是一个长度为size的集合合法的子集范围
//// 失败时抛出 IndexOutOfBoundsException
//Preconditions.checkPositionIndexs(int start, int index, int size)


    @Test
    public void test() {
//        testCheckArgument();
        testCheckElementIndex();
    }

    private void testCheckArgument() {
        int i = 1;
        checkArgument(i > 0, "参数是%s, 参数必须为正整数", i);

        try {
            i = -1;
            checkArgument(-1 > 0, "参数是%s, 参数必须为正整数", -1);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }


    private void testCheckNotNull() {
        Object value = new Object();

        checkNotNull(value, "参数是null");

        try {
            value = null;
            checkNotNull(value, "参数是null");
            fail();
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }


    private void testCheckState() {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        checkState(list.size() < 6, "集体长度应该小于5");

        list.add(6);
        try {
            checkState(list.size() < 6, "集体长度应该小于5");
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    private void testCheckElementIndex() {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3);
        // [0, size)
        checkElementIndex(list.size(), 4);

        try {
            checkElementIndex(list.size(), 3);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }


    private void CheckState() {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3);
        // [0, size)
        checkElementIndex(list.size(), 4);

        /*
        Exception in thread "main" java.lang.IndexOutOfBoundsException: index (3) must be less than size (3)

         */
        checkElementIndex(list.size(), 3);

    }



    private void testCheckPositionIndexs() {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        checkPositionIndexes(4, 5, list.size());

        try {
            checkPositionIndexes(5, 6, list.size());
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }




    private void NotNull() {
        Object value = new Object();
        value = null;
        checkNotNull(value, "参数是null");

    }



    public static void main(String[] args) {

//        new PreconditionsTest().testCheckArgument();
//        new PreconditionsTest().NotNull();
//        new PreconditionsTest().CheckState();



    }
}
