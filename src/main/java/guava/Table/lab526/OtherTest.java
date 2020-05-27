package guava.Table.lab526;

import com.google.common.collect.*;
import org.junit.Test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/26
 * \* Time: 22:19
 * \* Description:
 * \
 */
public class OtherTest {

    //　ClassToInstanceMap
    //
    //　　有的时候，你的map的key并不是一种类型，他们是很多类型，你想通过映射他们得到这种类型，guava提供了ClassToInstanceMap满足了这个目的。
    //　　除了继承自Map接口，ClassToInstaceMap提供了方法 T getInstance(Class<T>) 和 T putInstance(Class<T>, T),消除了强制类型转换。
    //　　该类有一个简单类型的参数，通常称为B，代表了map控制的上层绑定，例如：
    //
    //ClassToInstanceMap<Number> numberDefaults = MutableClassToInstanceMap.create();
    //numberDefaults.putInstance(Integer.class, Integer.valueOf(0));
    //　　从技术上来说，ClassToInstanceMap<B> 实现了Map<Class<? extends B>, B>，或者说，这是一个从B的子类到B对象的映射，
    // 这可能使得ClassToInstanceMap的泛型轻度混乱，但是只要记住B总是Map的上层绑定类型，通常来说B只是一个对象。
    //　　guava提供了有用的实现， MutableClassToInstanceMap 和 ImmutableClassToInstanceMap.
    //　　重点：像其他的Map<Class,Object>,ClassToInstanceMap 含有的原生类型的项目，一个原生类型和他的相应的包装类可以映射到不同的值；
    @Test
    public  void ClassToInstanceMapTest() {
        ClassToInstanceMap<String> classToInstanceMapString = MutableClassToInstanceMap.create();
        ClassToInstanceMap<Person> classToInstanceMap =MutableClassToInstanceMap.create();
        Person person= new Person("peida",20);
        System.out.println("person name :"+person.name+" age:"+person.age);
        classToInstanceMapString.put(String.class, "peida");
        System.out.println("string:"+classToInstanceMapString.getInstance(String.class));

        classToInstanceMap.putInstance(Person.class,person);
        Person person1=classToInstanceMap.getInstance(Person.class);
        System.out.println("person1 name :"+person1.name+" age:"+person1.age);
    }


    //RangeSet
    //
    //　　RangeSet用来处理一系列不连续，非空的range。
    // 当添加一个range到一个RangeSet之后，任何有连续的range将被自动合并，
    // 而空的range将被自动去除。例如：


    @Test
    public void RangeSetTest(){
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        System.out.println("===================================");
        rangeSet.add(Range.closed(1, 10));
        System.out.println("rangeSet:"+rangeSet);
        System.out.println("===================================");
        rangeSet.add(Range.closedOpen(11, 15));
        System.out.println("rangeSet:"+rangeSet);
        System.out.println("===================================");
        rangeSet.add(Range.open(15, 20));
        System.out.println("rangeSet:"+rangeSet);
        System.out.println("===================================");
        rangeSet.add(Range.openClosed(0, 0));
        System.out.println("rangeSet:"+rangeSet);
        System.out.println("===================================");
        rangeSet.remove(Range.open(5, 10));
        System.out.println("rangeSet:"+rangeSet);
        System.out.println("===================================");
    }

    //注意，像合并Range.closed(1, 10)和Range.closedOpen(11, 15)这样的情况，
    // 我们必须先用调用Range.canonical(DiscreteDomain)传入DiscreteDomain.integers()处理一下。
    //
    //　　RangeSet的视图
    //　　RangeSet的实现支持了十分丰富的视图，包括：
    //　　complement():是个辅助的RangeSet，它本身就是一个RangeSet，因为它包含了非连续，非空的range。
    //　　subRangeSet(Range<C>): 返回的是一个交集的视图。
    //　　asRanges():返回可以被迭代的Set<Range<C>>的视图。
    //　　asSet(DiscreteDomain<C>) (ImmutableRangeSet only):返回一个ImmutableSortedSet<C>类型的视图,里面的元素是range里面的元素
    // ，而不是range本身。（如果DiscreteDomain和RangeSet的上限或下限是无限的话，这个操作就不能支持）
    //
    //　　Queries
    //　　除了支持各种视图，RangeSet还支持各种直接的查询操作，其中最重要的是：
    //　　contains(C):这是RangeSet最基本的操作，它能查询给定的元素是否在RangeSet里。
    //　　rangeContaining(C): 返回包含给定的元素的Range，如果不存在就返回null。
    //　　encloses(Range<C>): 用来判断给定的Range是否包含在RangeSet里面。
    //　　span():返回一个包含在这个RangeSet的所有Range的并集。
    //
    //　　RangeMap
    //　　RangeMap代表了非连续非空的range对应的集合。不像RangeSet，RangeMap不会合并相邻的映射，甚至相邻的range对应的是相同的值。例如：

    @Test
    public void RangeMapTest(){
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo");
        System.out.println("rangeMap:"+rangeMap);
        System.out.println("===================================");

        rangeMap.put(Range.open(3, 6), "bar");
        System.out.println("rangeMap:"+rangeMap);
        System.out.println("===================================");

        rangeMap.put(Range.open(10, 20), "foo");
        System.out.println("rangeMap:"+rangeMap);
        System.out.println("===================================");

        rangeMap.remove(Range.closed(5, 11));
        System.out.println("rangeMap:"+rangeMap);
        System.out.println("===================================");

    }






}

class Person {
    public String name;
    public int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
