package guava.String.Lab526;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/26
 * \* Time: 22:50
 * \* Description:
 *
 *
 * \
 */
public class JoinerTest {

    @Test
    public void joinTest() {
        List<String> stringSrc = Lists.newArrayList("C", "Android", "Java");
        String resultString  = Joiner.on("; ")
                .join(stringSrc);
        System.out.println(resultString);
        //C; Android; Java

    }

    /**
     * useForNull 用指定的值来替换null
     */
    @Test
    public void useForNullTest() {
        String resultString = Joiner.on("; ")
                .useForNull("abc")
                .join("C", null, "Android", "Java");
        System.out.println(resultString);

        //C; abc; Android; Java
    }

    /**
     * skipNulls 跳过null
     */
    @Test
    public void skipNullsTest() {
        String resultString = Joiner.on("; ")
                .skipNulls()
                .join("C", null, "Android", "Java");
        System.out.println(resultString);
        //C; Android; Java
    }

    /**
     * withKeyValueSeparator
     */
    @Test
    public void withKeyValueSeparatorTest() {
        Map<String, String> mapSrc = new HashMap<>();
        mapSrc.put("key0", "value0");
        mapSrc.put("key1", "value1");
        String resultString = Joiner.on("; ")
                .withKeyValueSeparator("&")
                .join(mapSrc);
        System.out.println(resultString);

        //key1&value1; key0&value0
    }




}


//public class Joiner {
//
//    /**
//     * 创建连接器 Joiner
//     * @param separator 连接符
//     */
//    public static Joiner on(String separator);
//
//    /**
//     * 创建连接器 Joiner
//     * @param separator 连接符
//     */
//    public static Joiner on(char separator);
//
//
//    /**
//     * 相当于parts中间添加appendable
//     * StringBuffer也是一个Appendable
//     */
//    @CanIgnoreReturnValue
//    public <A extends Appendable> A appendTo(A appendable, Iterable<?> parts) throws IOException;
//
//    /**
//     * 相当于parts中的每个元素都转换为String,然后在每个元素的中间添加appendable
//     * StringBuffer也是一个Appendable
//     */
//    @CanIgnoreReturnValue
//    public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException;
//
//    /**
//     * 相当于parts中的每个元素都转换为String,然后在每个元素的中间添加appendable
//     * StringBuffer也是一个Appendable
//     */
//    @CanIgnoreReturnValue
//    public final <A extends Appendable> A appendTo(A appendable, Object[] parts) throws IOException;
//
//    /**
//     * 把元素转换为String,然后在每个元素的中间添加appendable
//     */
//    @CanIgnoreReturnValue
//    public final <A extends Appendable> A appendTo(
//        A appendable, @Nullable Object first, @Nullable Object second, Object... rest)
//        throws IOException;
//
//    /**
//     * 相当于parts中的每个元素都转换为String,然后在每个元素的中间添加builder.toString()
//     */
//    @CanIgnoreReturnValue
//    public final StringBuilder appendTo(StringBuilder builder, Iterable<?> parts);
//
//    /**
//     * 相当于parts中的每个元素都转换为String,然后在每个元素的中间添加builder.toString()
//     */
//    @CanIgnoreReturnValue
//    public final StringBuilder appendTo(StringBuilder builder, Iterator<?> parts);
//
//    /**
//     * 相当于parts中的每个元素都转换为String,然后在每个元素的中间添加builder.toString()
//     */
//    @CanIgnoreReturnValue
//    public final StringBuilder appendTo(StringBuilder builder, Object[] parts);
//
//    /**
//     * 相当于每个元素都转换为String,然后在每个元素的中间添加builder.toString()
//     */
//    @CanIgnoreReturnValue
//    public final StringBuilder appendTo(
//        StringBuilder builder, @Nullable Object first, @Nullable Object second, Object... rest);
//
//    /**
//     * 把parts每个元素转换为字符串，再添加连接符之后返回
//     */
//    public final String join(Iterable<?> parts);
//
//    /**
//     * 把parts每个元素转换为字符串，再添加连接符之后返回
//     */
//    public final String join(Iterator<?> parts);
//
//    /**
//     * 把parts每个元素转换为字符串，再添加连接符之后返回
//     */
//    public final String join(Object[] parts);
//
//    /**
//     * 给参数里面的每个元素转换为String,再添加连接符之后返回
//     */
//    public final String join(@Nullable Object first, @Nullable Object second, Object... rest);
//
//    /**
//     * 用指定的字符串替换掉null对象
//     */
//    public Joiner useForNull(final String nullText);
//
//
//    /**
//     * 连接Map的Joiner 设置key, value之间的分隔符
//     */
//    public MapJoiner withKeyValueSeparator(char keyValueSeparator);
//
//    /**
//     * 连接Map的Joiner 设置key, value之间的分隔符
//     */
//    public MapJoiner withKeyValueSeparator(String keyValueSeparator);
//
//}


