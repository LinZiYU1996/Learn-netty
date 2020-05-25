package guava.Object.p524;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.primitives.Ints;

import static org.junit.Assert.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/24
 * \* Time: 22:12
 * \* Description:
 *
 * Objects提供了Java对象的equals、hashCode、toString等方法
 *
 * equals
 * 覆写equals方法时, 减少了null判断和分支处理
 *
 * Objects.equal("a", "a"); // returns true
 * Objects.equal(null, "a"); // returns false
 * Objects.equal("a", null); // returns false
 * Objects.equal(null, null); // returns true
 * hashCode
 * 更方便地完成多个属性的hash
 *
 * Objects.hashCode(Object...)
 * Objects.hashCode(field1, field2, ..., fieldn)
 * toString
 * 对象的toString方法更多是为了更好的可读性, ToStringHelper可以通过链式更方便地将对象的各属性都加入
 *
 * Objects.toStringHelper(Persion.class)
 *        .add("name", this.name)
 *        .add("age", this.age)
 *        .toString();
 *
 *compare/compareTo
 * compareTo是java.lang.Comparable接口中的方法
 *
 * guava提供了所有原始类型的对比工具
 *
 * Ints.compare(int a, int b)
 * Longs.compare(long a, long b)
 * Shorts.compare(short a, short b)
 * Doubles.compare(double a, double b)
 * Floats.compare(float a, floab b)
 * Booleans.compare(boolean a, boolean b)
 * Chars.compare(char a, char b)
 * 同时，guava还提供了链式对比的工具ComparisonChain
 *
 * ComparisonChain.start()
 *          .compare(this.aString, that.aString)
 *          .compare(this.anInt, that.anInt)
 *          .compare(this.anEnum, that.anEnum, Ordering.natural().nullsLast())
 *          .result();
 * ComparisonChain是一个lazy的比较过程， 当比较结果为0的时候， 即相等的时候， 会继续比较下去， 出现非0的情况， 就会忽略后面的比较
 *
 * \
 */
public class ObjectsTest {

    private void testEquals() {
        assertTrue(Objects.equal("a", "a"));
        assertFalse(Objects.equal("a", null));
        assertFalse(Objects.equal(null, "a"));
        assertTrue(Objects.equal(null, null));
    }

    private void testHashCode() {
        assertNotSame(Objects.hashCode("a", "b", "c"), Objects.hashCode("c", "b", "a"));
    }

//    private void testToString() {
//        assertEquals("Object{name=test, age=18}",
//                Objects(Object.class).add("name", "test").add("age", 18).toString());

//    Objects.toStringHelper(this).add("x", 1).toString();

//    }

    private void testCompare() {
        assertEquals(-1, Ints.compare(1, 2));
        assertEquals(1, Ints.compare(2, 1));
        assertEquals(0, Ints.compare(1, 1));

        assertEquals(1, ComparisonChain.start().compare(1, 1).compare("aString", "aString").compare(true, false).result());
    }
}
