package guava.collection.blog519;

import com.google.common.collect.*;

import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/19
 * \* Time: 22:38
 * \* Description:
 * \
 */
public class Test {

    public static void main(String[] args) {
        // 普通Collection的创建
        List<String> list = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
        Map<String, String> map = Maps.newHashMap();

// 不变Collection的创建
        ImmutableList<String> iList = ImmutableList.of("a", "b", "c");
        ImmutableSet<String> iSet = ImmutableSet.of("e1", "e2");
        ImmutableMap<String, String> iMap = ImmutableMap.of("k1", "v1", "k2", "v2");

        /*

        Exception in thread "main" java.lang.UnsupportedOperationException

         */
//        iList.add("a");

        /*

        创建不可变集合 先理解什么是immutable(不可变)对象

在多线程操作下，是线程安全的
所有不可变集合会比可变集合更有效的利用资源
中途不可改变
ImmutableList<String> immutableList = ImmutableList.of("1","2","3","4");
这声明了一个不可变的List集合，
List中有数据1，2，3，4。类中的 操作集合的方法
（譬如add, set, sort, replace等）都被声明过期
，并且抛出异常。 而没用guava之前是需要声明并且加各种包裹集合才能实现这个功能


         */

/*
当我们需要一个map中包含key为String类型，value为List类型的时候，以前我们是这样写的


 */

        System.out.println("==============================================================");
        Map<String,List<Integer>> map1 = new HashMap<String,List<Integer>>();
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        map1.put("aa", list1);
        System.out.println(map.get("aa"));//[1, 2]

        /*
        而现在


         */

        System.out.println("==============================================================");

        Multimap<String,Integer> map2 = ArrayListMultimap.create();
        map2.put("aa", 1);
        map2.put("aa", 2);
        System.out.println(map2.get("aa"));  //[1, 2]
        System.out.println("==============================================================");


        /*
        其他的黑科技集合


         */

        System.out.println("==============================================================");


//        MultiSet: 无序+可重复   count()方法获取单词的次数  增强了可读性+操作简单
//        创建方式:  Multiset<String> set = HashMultiset.create();

//        Multimap: key-value  key可以重复
//        创建方式: Multimap<String, String> teachers = ArrayListMultimap.create();
//
//        BiMap: 双向Map(Bidirectional Map) 键与值都不能重复
//        创建方式:  BiMap<String, String> biMap = HashBiMap.create();
//
//        Table: 双键的Map Map--> Table-->rowKey+columnKey+value  //和sql中的联合主键有点像
//        创建方式: Table<String, String, Integer> tables = HashBasedTable.create();



    }
}
