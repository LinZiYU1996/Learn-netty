package guava.String.Lab526;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/26
 * \* Time: 22:46
 * \* Description:
 * \
 */
@Slf4j
public class StringTest {

    //字符串拼接
    //  字符串的拼接采用的是Guava中的Joiner类中的方法，用法如下


    @Test
    public void testJoin(){
        List<String> strList = Lists.newArrayList("1","2",null,"3","4");
        //字符串拼接，跳过空字符串
        String str = Joiner.on(",").skipNulls().join(strList);
        log.info(str);
        Assert.assertEquals("1,2,3,4",str);
    }

    //字符串分割
    //  字符串分割采用的是Guava中的Splitter类中的方法，用法如下：

    @Test
    public void testSplit(){
        String str = "1,2,   3  ,,4,";
        //trimResults():去除空格，omitEmptyStrings()：删除空数组
        List<String> strList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
        strList.forEach(s -> log.info(s));
        Assert.assertEquals(4,strList.size());
    }

    //字符串变换
    //  字符串变换采用的是Guava中的CharMatcher类中的方法，用法如下：
    @Test
    public void testConversion(){
        String str = "abcdef";
        String newStr = str.replace("bcde","hello");
        log.info(newStr);
        Assert.assertEquals("ahellof",newStr);
        String str2 = "ABC    abc    123";
        //打印数字
//        log.info(CharMatcher.JAVA_DIGIT.retainFrom(str2));
//        //打印小写字母
//        log.info(CharMatcher.JAVA_LOWER_CASE.retainFrom(str2));
//        //打印大写字母
//        log.info(CharMatcher.JAVA_UPPER_CASE.retainFrom(str2));
//        //打印所有字母
//        log.info(CharMatcher.JAVA_LETTER.retainFrom(str2));
//        //将多余的空格替换成一个空格
//        log.info(CharMatcher.whitespace().trimAndCollapseFrom(str2,' '));
//        //统计出现的字母
//        log.info(String.valueOf(CharMatcher.JAVA_LETTER.countIn(str2)));
    }


}
