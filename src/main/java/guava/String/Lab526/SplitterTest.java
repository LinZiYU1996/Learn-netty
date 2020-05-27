package guava.String.Lab526;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/26
 * \* Time: 22:52
 * \* Description:
 * \
 */
public class SplitterTest {

    @Test
    public void splitTest() {
        Iterable<String> iterableList = Splitter.on(',').trimResults() // 移除前面和后面的空白
                .omitEmptyStrings() // 去掉null
                .split("foo,bar,,   qux");
        List<String> resultList = Lists.newArrayList(iterableList);
        for (String item : resultList) {
            System.out.println(item);
        }
    }

    /**
     * splitToList 最终直接返回List
     */
    @Test
    public void splitToListTest() {
        List<String> resultList = Splitter.on(',').trimResults().omitEmptyStrings().splitToList("foo,bar,,   qux");
        for (String item : resultList) {
            System.out.println(item);
        }
    }

    /**
     * MapSplitter
     */
    @Test
    public void mapSplitterTest() {
        String source = "key0:value0#key1:value1";
        Map<String, String> resultMap = Splitter.on("#").withKeyValueSeparator(":").split(source);
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }
    }


}
//public class Splitter {
//
//    /**
//     * 指定按单个字符拆分
//     */
//    public static Splitter on(char separator);
//
//    /**
//     * 指定按字符匹配器拆分
//     */
//    public static Splitter on(final CharMatcher separatorMatcher);
//
//    /**
//     * 指定按字符串拆分
//     */
//    public static Splitter on(final String separator);
//
//    /**
//     * 指定按正则表达式拆分
//     */
//    @GwtIncompatible // java.util.regex
//    public static Splitter on(Pattern separatorPattern);
//
//    /**
//     * 指定按正则表达式拆分
//     */
//    @GwtIncompatible // java.util.regex
//    public static Splitter onPattern(String separatorPattern);
//
//    /**
//     * 创建Splitter对象, 按固定长度拆分；最后一段可能比给定长度短，但不会为空
//     */
//    public static Splitter fixedLength(final int length);
//
//    /**
//     * 从结果中自动忽略空字符串
//     */
//    public Splitter omitEmptyStrings();
//
//    /**
//     * 限制拆分出的字符串数量
//     */
//    public Splitter limit(int limit) {
//        checkArgument(limit > 0, "must be greater than zero: %s", limit);
//        return new Splitter(strategy, omitEmptyStrings, trimmer, limit);
//    }
//
//    /**
//     * 移除结果字符串的前导空白和尾部空白
//     */
//    public Splitter trimResults() {
//        return trimResults(CharMatcher.whitespace());
//    }
//
//    /**
//     * 给定匹配器，移除结果字符串的前导匹配字符和尾部匹配字符
//     */
//    // TODO(kevinb): throw if a trimmer was already specified!
//    public Splitter trimResults(CharMatcher trimmer);
//
//    /**
//     * 按照赵鼎的拆分条件拆分, 返回Iterable<String>
//     */
//    public Iterable<String> split(final CharSequence sequence);
//
//    /**
//     * 按照指定的条件拆分，返回List<String>
//     */
//    public List<String> splitToList(CharSequence sequence);
//
//    /**
//     * 返回MapSplitter并且指定key,value之间的拆分字符串
//     */
//    @Beta
//    public MapSplitter withKeyValueSeparator(String separator);
//
//    /**
//     * 返回MapSplitter并且指定key,value之间的拆分字符串
//     */
//    @Beta
//    public MapSplitter withKeyValueSeparator(char separator);
//
//    /**
//     * 返回MapSplitter并且指定key,value之间的拆分规则为Splitter
//     */
//    @Beta
//    public MapSplitter withKeyValueSeparator(Splitter keyValueSplitter);

