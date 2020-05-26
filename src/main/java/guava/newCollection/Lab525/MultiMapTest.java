package guava.newCollection.Lab525;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.sun.corba.se.spi.orb.ParserImplBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/25
 * \* Time: 22:03
 * \* Description:
 *
 * 　　在日常的开发工作中，我们有的时候需要构造像Map<K, List<V>>或者Map<K, Set<V>>这样比较复杂的集合类型的数据结构，以便做相应的业务逻辑处理。例如：
 *
 *
 * \
 */

@Slf4j
public class MultiMapTest {

    Map<String, List<StudentScore>> StudentScoreMap = new HashMap<String, List<StudentScore>>();



    //　　说明：想 Map<String, List<StudentScore>> StudentScoreMap = new HashMap<String, List<StudentScore>>()这样的数据结构，
    // 自己实现起来太麻烦，你需要检查key是否存在，不存在时则创建一个，存在时在List后面添加上一个。这个过程是比较痛苦的，如果你希望检查List中的对象是否存在，
    // 删除一个对象，或者遍历整个数据结构，那么则需要更多的代码来实现。
    @Test
    public void testStudentScore(){

        for(int i=10;i<20;i++){
            StudentScore studentScore=new StudentScore(1,1);
            studentScore.CourseId=1001+i;
            studentScore.score=100-i;
            addStudentScore("peida",studentScore);
        }

        System.out.println("StudentScoreMap:"+StudentScoreMap.size());
        System.out.println("StudentScoreMap:"+StudentScoreMap.containsKey("peida"));

        System.out.println("StudentScoreMap:"+StudentScoreMap.containsKey("jerry"));
        System.out.println("StudentScoreMap:"+StudentScoreMap.size());
        System.out.println("StudentScoreMap:"+StudentScoreMap.get("peida").size());

        List<StudentScore> StudentScoreList=StudentScoreMap.get("peida");
        if(StudentScoreList!=null&&StudentScoreList.size()>0){
            for(StudentScore stuScore:StudentScoreList){
                System.out.println("stuScore one:"+stuScore.CourseId+" score:"+stuScore.score);
            }
        }
    }

    public void addStudentScore(final String stuName,final StudentScore studentScore) {
        List<StudentScore> stuScore = StudentScoreMap.get(stuName);
        if (stuScore == null) {
            stuScore = new ArrayList<StudentScore>();
            StudentScoreMap.put(stuName, stuScore);
        }
        stuScore.add(studentScore);
    }


    //　　Guava的Multimap就提供了一个方便地把一个键对应到多个值的数据结构。让我们可以简单优雅的实现上面复杂的数据结构，让我们的精力和时间放在实现业务逻辑上，而不是在数据结构上，下面我们具体来看看Multimap的相关知识点。



    @Test
    public void teststuScoreMultimap(){
        Multimap<String,StudentScore> scoreMultimap = ArrayListMultimap.create();
        for(int i=10;i<20;i++){
            StudentScore studentScore=new StudentScore();
            studentScore.CourseId=1001+i;
            studentScore.score=100-i;
            scoreMultimap.put("peida",studentScore);
        }
        System.out.println("scoreMultimap:"+scoreMultimap.size());
        System.out.println("scoreMultimap:"+scoreMultimap.keys());
        log.info("scoreMultimap:"+scoreMultimap.keys());
    }


    //　　调用Multimap.get(key)会返回这个键对应的值的集合的视图（view），没有对应集合就返回空集合。对于ListMultimap来说，这个方法会返回一个List，对于SetMultimap来说，这个方法就返回一个Set。修改数据是通过修改底层Multimap来实现的。例如：

    @Test
    public void teststuScoreMultimap1(){
        Multimap<String,StudentScore> scoreMultimap = ArrayListMultimap.create();
        for(int i=10;i<20;i++){
            StudentScore studentScore=new StudentScore();
            studentScore.CourseId=1001+i;
            studentScore.score=100-i;
            scoreMultimap.put("peida",studentScore);
        }
        System.out.println("scoreMultimap:"+scoreMultimap.size());
        System.out.println("scoreMultimap:"+scoreMultimap.keys());

        Collection<StudentScore> studentScore = scoreMultimap.get("peida");
        studentScore.clear();
        StudentScore studentScoreNew=new StudentScore();
        studentScoreNew.CourseId=1034;
        studentScoreNew.score=67;
        studentScore.add(studentScoreNew);

        System.out.println("scoreMultimap:"+scoreMultimap.size());
        System.out.println("scoreMultimap:"+scoreMultimap.keys());
    }

//Multimap也支持一系列强大的视图功能：
//　　1.asMap把自身Multimap<K, V>映射成Map<K, Collection<V>>视图。这个Map视图支持remove和修改操作，但是不支持put和putAll。严格地来讲，当你希望传入参数是不存在的key，而且你希望返回的是null而不是一个空的可修改的集合的时候就可以调用asMap().get(key)。（你可以强制转型asMap().get(key)的结果类型－对SetMultimap的结果转成Set，对ListMultimap的结果转成List型－但是直接把ListMultimap转成Map<K, List<V>>是不行的。）
//　　2.entries视图是把Multimap里所有的键值对以Collection<Map.Entry<K, V>>的形式展现。
//　　3.keySet视图是把Multimap的键集合作为视图
//　　4.keys视图返回的是个Multiset，这个Multiset是以不重复的键对应的个数作为视图。这个Multiset可以通过支持移除操作而不是添加操作来修改Multimap。
//　　5.values()视图能把Multimap里的所有值“平展”成一个Collection<V>。这个操作和Iterables.concat(multimap.asMap().values())很相似，只是它返回的是一个完整的Collection。
//
//　　尽管Multimap的实现用到了Map，但Multimap<K, V>不是Map<K, Collection<V>>。因为两者有明显区别：
//　　1.Multimap.get(key)一定返回一个非null的集合。但这不表示Multimap使用了内存来关联这些键，相反，返回的集合只是个允许添加元素的视图。
//　　2.如果你喜欢像Map那样当不存在键的时候要返回null，而不是Multimap那样返回空集合的话，可以用asMap()返回的视图来得到Map<K, Collection<V>>。（这种情况下，你得把返回的Collection<V>强转型为List或Set）。
//　　3.Multimap.containsKey(key)只有在这个键存在的时候才返回true。
//　　4.Multimap.entries()返回的是Multimap所有的键值对。但是如果需要key-collection的键值对，那就得用asMap().entries()。
//　　5.Multimap.size()返回的是entries的数量，而不是不重复键的数量。如果要得到不重复键的数目就得用Multimap.keySet().size()。



@Test
public void teststuScoreMultimap2(){
    Multimap<String,StudentScore> scoreMultimap = ArrayListMultimap.create();
    for(int i=10;i<20;i++){
        StudentScore studentScore=new StudentScore();
        studentScore.CourseId=1001+i;
        studentScore.score=100-i;
        scoreMultimap.put("peida",studentScore);
    }
    System.out.println("scoreMultimap:"+scoreMultimap.size());
    System.out.println("scoreMultimap:"+scoreMultimap.keys());

    Collection<StudentScore> studentScore = scoreMultimap.get("peida");
    StudentScore studentScore1=new StudentScore();
    studentScore1.CourseId=1034;
    studentScore1.score=67;
    studentScore.add(studentScore1);

    StudentScore studentScore2=new StudentScore();
    studentScore2.CourseId=1045;
    studentScore2.score=56;
    scoreMultimap.put("jerry",studentScore2);

    System.out.println("scoreMultimap:"+scoreMultimap.size());
    System.out.println("scoreMultimap:"+scoreMultimap.keys());


    for(StudentScore stuScore : scoreMultimap.values()) {
        System.out.println("stuScore one:"+stuScore.CourseId+" score:"+stuScore.score);
    }

    scoreMultimap.remove("jerry",studentScore2);
    System.out.println("scoreMultimap:"+scoreMultimap.size());
    System.out.println("scoreMultimap:"+scoreMultimap.get("jerry"));

    scoreMultimap.put("harry",studentScore2);
    scoreMultimap.removeAll("harry");
    System.out.println("scoreMultimap:"+scoreMultimap.size());
    System.out.println("scoreMultimap:"+scoreMultimap.get("harry"));
}


        @Test
        public void test() {
            Multimap<String, String> myMultimap = ArrayListMultimap.create();

            // Adding some key/value
            myMultimap.put("Fruits", "Bannana");
            myMultimap.put("Fruits", "Apple");
            myMultimap.put("Fruits", "Pear");
            myMultimap.put("Fruits", "Pear");
            myMultimap.put("Vegetables", "Carrot");

            // Getting the size
            int size = myMultimap.size();
            System.out.println(size); // 5

            // Getting values
            Collection<String> fruits = myMultimap.get("Fruits");
            System.out.println(fruits); //  [Bannana, Apple, Pear, Pear]
            System.out.println(ImmutableSet.copyOf(fruits));// [Bannana, Apple, Pear]
            // Set<Foo> set = Sets.newHashSet(list);
            // Set<Foo> foo = new HashSet<Foo>(myList);

            log.info("==============================================");
            Collection<String> vegetables = myMultimap.get("Vegetables");
            System.out.println(vegetables); // [Carrot]
            log.info("==============================================");

            // Iterating over entire Mutlimap
            for (String value : myMultimap.values()) {
                System.out.println(value);
            }
            log.info("==============================================");

            // Removing a single value
            myMultimap.remove("Fruits", "Pear");
            System.out.println(myMultimap.get("Fruits")); // [Bannana, Apple, Pear]
            log.info("==============================================");

            // Remove all values for a key
            myMultimap.removeAll("Fruits");
            System.out.println(myMultimap.get("Fruits")); // [] (Empty Collection!)
        }

}
