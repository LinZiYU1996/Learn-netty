package guava.newCollection.Lab525;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/25
 * \* Time: 22:17
 * \* Description:
 * \
 */
public class BimapTest {

    //BiMap提供了一种新的集合类型，它提供了key和value的双向关联的数据结构。
    //　　通常情况下，我们在使用Java的Map时，往往是通过key来查找value的，但是如果出现下面一种场景的情况，我们就需要额外编写一些代码了。首先来看下面一种表示标识序号和文件名的map结构。


    @Test
    public void logMapTest(){
        Map<Integer,String> logfileMap = Maps.newHashMap();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");
        System.out.println("logfileMap:"+logfileMap);
    }

    //　　当我们需要通过序号查找文件名，很简单。但是如果我们需要通过文件名查找其序号时，我们就不得不遍历map了。当然我们还可以编写一段Map倒转的方法来帮助实现倒置的映射关系。

    /**
     * 逆转Map的key和value
     * @param <S>
     * @param <T>
     * @param map
     * @return
     */
    public static <S,T> Map<T,S> getInverseMap(Map<S,T> map) {
        Map<T,S> inverseMap = new HashMap<T,S>();
        for(Map.Entry<S,T> entry: map.entrySet()) {
            inverseMap.put(entry.getValue(), entry.getKey());
        }
        return inverseMap;
    }

    @Test
    public void logMapTest1(){
        Map<Integer,String> logfileMap = Maps.newHashMap();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");

        System.out.println("logfileMap:"+logfileMap);

        Map<String,Integer> logfileInverseMap = Maps.newHashMap();

        logfileInverseMap=getInverseMap(logfileMap);

        System.out.println("logfileInverseMap:"+logfileInverseMap);
    }


//上面的代码可以帮助我们实现map倒转的要求，但是还有一些我们需要考虑的问题:
//    　　1. 如何处理重复的value的情况。不考虑的话，反转的时候就会出现覆盖的情况.
//    　　2. 如果在反转的map中增加一个新的key，倒转前的map是否需要更新一个值呢?
//　　在这种情况下需要考虑的业务以外的内容就增加了，编写的代码也变得不那么易读了。这时我们就可以考虑使用Guava中的BiMap了。


    @Test
    public void BimapTest(){
        BiMap<Integer,String> logfileMap = HashBiMap.create();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");
        System.out.println("logfileMap:"+logfileMap);
        BiMap<String,Integer> filelogMap = logfileMap.inverse();
        System.out.println("filelogMap:"+filelogMap);
    }

//Bimap数据的强制唯一性
//
//　　在使用BiMap时，会要求Value的唯一性。如果value重复了则会抛出错误：java.lang.IllegalArgumentException，例如：

    @Test
    public void BimapTest1(){
        BiMap<Integer,String> logfileMap = HashBiMap.create();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");
        logfileMap.put(4,"d.log");
        logfileMap.put(5,"d.log");
    }



    //理解inverse方法
    //　　inverse方法会返回一个反转的BiMap，但是注意这个反转的map不是新的map对象，它实现了一种视图关联，这样你对于反转后的map的所有操作都会影响原先的map对象。例如：


    @Test
    public void BimapTest2(){
        BiMap<Integer,String> logfileMap = HashBiMap.create();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");
        System.out.println("logfileMap:"+logfileMap);
        BiMap<String,Integer> filelogMap = logfileMap.inverse();
        System.out.println("filelogMap:"+filelogMap);

        logfileMap.put(4,"d.log");

        System.out.println("logfileMap:"+logfileMap);
        System.out.println("filelogMap:"+filelogMap);
    }
}
