package weixin.hashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/10
 * \* Time: 19:32
 * \* Description:
 * \
 */
public class HashMapTest3 {

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
        迭代器 KeySet
         */
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.print(key);
            System.out.print(map.get(key));
        }
    }
}
