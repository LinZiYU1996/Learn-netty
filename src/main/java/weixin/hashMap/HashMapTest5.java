package weixin.hashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/10
 * \* Time: 19:33
 * \* Description:
 * \
 */
public class HashMapTest5 {

    public static void main(String[] args) {
// 创建并赋值 HashMap
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");
// 遍历

        /*
        Lambda
         */
        map.forEach((key, value) -> {
            System.out.print(key);
            System.out.print(value);
        });
    }
}
