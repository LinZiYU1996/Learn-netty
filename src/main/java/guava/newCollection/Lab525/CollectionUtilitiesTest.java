package guava.newCollection.Lab525;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/25
 * \* Time: 21:09
 * \* Description:
 *
 * Multiset
 * SortedMultiset
 * MultiMap
 * BiMap
 * Table
 * ClassToInstanceMap
 * RangeSet
 *
 *
 *Multiset
 * Multiset和Set的区别就是可以保存多个相同的对象。
 * Multiset占据了List和Set之间的一个灰色地带：允许重复，但是不保证顺序。
 * 常见使用场景：Multiset有一个有用的功能，就是跟踪每种对象的数量，所以你可以用来进行数字统计。
 *
 * Multiset接口定义的接口主要有：
 *
 * add(E element): 向其中添加单个元素
 * add(E element,int occurrences): 向其中添加指定个数的元素
 * count(Object element): 返回给定参数元素的个数
 * remove(E element): 移除一个元素，其count值 会响应减少
 * remove(E element,int occurrences): 移除相应个数的元素
 * elementSet(): 将不同的元素放入一个Set中
 * entrySet(): 类似与Map.entrySet 返回Set<Multiset.Entry>。包含的Entry支持使用getElement()和getCount()
 * setCount(E element ,int count): 设定某一个元素的重复次数
 * setCount(E element,int oldCount,int newCount): 将符合原有重复个数的元素修改为新的重复次数
 * retainAll(Collection c): 保留出现在给定集合参数的所有的元素
 * removeAll(Collectionc): 去除出现给给定集合参数的所有的元素
 *
 *
 *
 * Guava提供了很多和JDK中的Map对应的Multiset的实现
 *
 * Map	对应的MultiSet	支持null值
 * HashMap	HashMultiset	是
 * TreeMap	TreeMultiSet	是
 * LinkedHashMap	LinkedHashMultiset	是
 * ConcurrentHashMap	ConcurrentHashMultiset	否
 * ImmutableMap	ImmutableMultiset	否
 *
 *
 *
 *SortedMultiset
 * SortedMultiset是Multiset 接口的变种，它支持高效地获取指定范围的子集。
 * 比如，你可以用 latencies.subMultiset(0,BoundType.CLOSED, 100, BoundType.OPEN).size()来统计你的站点中延迟在100毫秒以内的访问，然后把这个值和latencies.size()相比，以获取这个延迟水平在总体访问中的比例。
 *
 * TreeMultiset实现SortedMultiset接口。
 *
 *MultiMap
 * 经常会遇到这种结构 Map<K, List>或Map<K, Set>
 * Multimap可以很容易地把一个键映射到多个值。换句话说，Multimap是把键映射到任意多个值的一种方式。
 *
 * 可以用两种方式思考Multimap的概念:
 *
 * "键-单个值映射"的集合: a->1, a->2, a->4, b->3, c->5
 * "键-值集合映射"的映射: a->[1,2,4], b->3, c->5
 * 一般情况下都会使用ListMultimap或SetMultimap接口，它们分别把键映射到List或Set。
 * Multimap.get(key)以集合形式返回键所对应的值视图, 即使没有任何对应的值，也会返回空集合。
 * 对值视图集合进行的修改最终都会反映到底层的Multimap。
 *
 *Multimap不是Map
 * Multimap<K, V>不是Map<K,Collection>
 *
 * Multimap.get(key)总是返回非null、但是可能空的集合。这并不意味着Multimap为相应的键花费内存创建了集合，而只是提供一个集合视图方便你为键增加映射值
 * 如果你更喜欢像Map那样，为Multimap中没有的键返回null，请使用asMap()视图获取一个Map<K, Collection>
 * 当且仅当有值映射到键时，Multimap.containsKey(key)才会返回true
 * Multimap.entries()返回Multimap中所有”键-单个值映射”——包括重复键。如果你想要得到所有”键-值集合映射”，请使用asMap().entrySet()。
 * Multimap.size()返回所有”键-单个值映射”的个数，而非不同键的个数。要得到不同键的个数，请改用Multimap.keySet().size()。
 *
 *
 *
 *BiMap
 * BiMap提供了key和value双向关联的数据结构。
 *
 * 可以用inverse()反转BiMap<K, V>的键值映射, 反转的map不是新的map对象，它实现了一种视图关联，这样你对于反转后的map的所有操作都会影响原先的map对象
 * 保证值是唯一的，因此 values()返回Set而不是普通的Collection
 * 如果你想把键映射到已经存在的值，会抛出IllegalArgumentException异常, 使用BiMap.forcePut(key, value)可强制替换
 *
 *
 * Table
 * 当我们需要多个索引的数据结构的时候，通常情况下，我们只能用这种丑陋的Map<FirstName, Map<LastName, Person>>来实现。为此Guava提供了一个新的集合类型－Table集合类型，来支持这种数据结构的使用场景。
 *
 *
 *
 *ClassToInstanceMap
 * ClassToInstanceMap<B> 相当于 Map<Class<? extends B>, B>, 它的键是类型，而值是符合键所指类型的对象。 ClassToInstanceMap额外声明了两个方法：T getInstance(Class) 和T putInstance(Class, T)，从而避免强制类型转换，同时保证了类型安全。
 *
 * RangeSet
 * RangeSet描述了一组不相连的、非空的区间。当把一个区间添加到可变的RangeSet时，所有相连的区间会被合并，空区间会被忽略。
 * 例如
 *
 *
 * \
 */
public class CollectionUtilitiesTest {

    @Test
    public void test() {
        testLists();
        testSets();
    }

    private void testLists() {
        Lists.newArrayList();
        Lists.newArrayList(1, 2, 3);
        Lists.newArrayList(Sets.newHashSet(1, 2, 3));
        Lists.newArrayListWithCapacity(10);
        Lists.newArrayListWithExpectedSize(10);

        Lists.newLinkedList();
        Lists.newLinkedList(Sets.newHashSet(1, 2, 3));

        Lists.partition(Lists.newArrayList(1, 2, 3, 4, 5), 2);
        Lists.reverse(Lists.newArrayList(1, 2, 3, 4, 5));
    }

    private void testSets() {
        // 静态工厂方法
        Sets.newHashSet();
        Sets.newHashSet(1, 2, 3);
        Sets.newHashSetWithExpectedSize(10);
        Sets.newHashSet(Lists.newArrayList(1, 2, 3));

        Sets.newLinkedHashSet();
        Sets.newLinkedHashSetWithExpectedSize(10);
        Sets.newLinkedHashSet(Lists.newArrayList(1, 2, 3));

        Sets.newTreeSet();
        Sets.newTreeSet(Lists.newArrayList(1, 2, 3));
        Sets.newTreeSet(Ordering.natural());

        // 集合运算(返回SetView)
        Sets.union(Sets.newHashSet(1, 2, 3), Sets.newHashSet(4, 5, 6)).toString(); // 取并集[1,2,3,4,5]
        Sets.intersection(Sets.newHashSet(1, 2, 3), Sets.newHashSet(3, 4, 5)); // 取交集[3]
        Sets.difference(Sets.newHashSet(1, 2, 3), Sets.newHashSet(3, 4, 5)); // 只在set1, 不在set2[1,2]
        Sets.symmetricDifference(Sets.newHashSet(1, 2, 3), Sets.newHashSet(3, 4, 5)); // 交集取反[1,2,4,5]

        // 其他工具方法
        Sets.cartesianProduct(Lists.newArrayList(Sets.newHashSet(1, 2), Sets.newHashSet(3, 4))); // 返回所有集合的笛卡尔积
        Sets.powerSet(Sets.newHashSet(1, 2, 3)); // 返回给定集合的所有子集
    }




}
