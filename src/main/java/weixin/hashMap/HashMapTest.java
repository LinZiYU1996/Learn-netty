package weixin.hashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/10
 * \* Time: 19:30
 * \* Description:
 * \
 */
public class HashMapTest {

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
        ForEach EntrySet
         */
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.print(entry.getKey());
            System.out.print(entry.getValue());
            System.out.println("");

        }
    }


}
