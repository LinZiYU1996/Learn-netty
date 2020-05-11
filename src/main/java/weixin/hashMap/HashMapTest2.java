package weixin.hashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/10
 * \* Time: 19:31
 * \* Description:
 * \
 */
public class HashMapTest2 {

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
        迭代器 EntrySet
         */
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.print(entry.getKey());
            System.out.print(entry.getValue());
            System.out.println("");
        }
    }


}
