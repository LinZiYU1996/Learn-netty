package guava.Table.lab526;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/26
 * \* Time: 22:14
 * \* Description:
 * \
 */
public class TableTest {

    //　　当我们需要多个索引的数据结构的时候，通常情况下，
    // 我们只能用这种丑陋的Map<FirstName, Map<LastName, Person>>来实现。
    // 为此Guava提供了一个新的集合类型－Table集合类型，来支持这种数据结构的使用场景。
    // Table支持“row”和“column”，而且提供多种视图。　

    @Test
    public void TableTest(){
        Table<String, Integer, String> aTable = HashBasedTable.create();

        for (char a = 'A'; a <= 'C'; ++a) {
            for (Integer b = 1; b <= 3; ++b) {
                aTable.put(Character.toString(a), b, String.format("%c%d", a, b));
            }
        }
//　　Table视图：
//　　rowMap()返回一个Map<R, Map<C, V>>的视图。rowKeySet()类似地返回一个Set<R>。
//　　row(r)返回一个非null的Map<C, V>。修改这个视图Map也会导致原表格的修改。
//　　和列相关的方法有columnMap(), columnKeySet()和column(c)。（基于列的操作会比基于行的操作效率差些）
//　　cellSet()返回的是以Table.Cell<R, C, V>为元素的Set。这里的Cell就类似Map.Entry，但是它是通过行和列来区分的。
//
//　　Table有以下实现：
//　　HashBasedTable：基于HashMap<R, HashMap<C, V>>的实现。
//　　TreeBasedTable：基于TreeMap<R, TreeMap<C, V>>的实现。
//　　ImmutableTable：基于ImmutableMap<R, ImmutableMap<C, V>>的实现。（注意，ImmutableTable已对稀疏和密集集合做了优化）
//　　ArrayTable：ArrayTable是一个需要在构建的时候就需要定下行列的表格。这种表格由二维数组实现，这样可以在密集数据的表格的场合，提高时间和空间的效率。
        System.out.println(aTable.column(2));
        System.out.println(aTable.row("B"));
        System.out.println(aTable.get("B", 2));

        System.out.println(aTable.contains("D", 1));
        System.out.println(aTable.containsColumn(3));
        System.out.println(aTable.containsRow("C"));
        System.out.println(aTable.columnMap());
        System.out.println(aTable.rowMap());

        System.out.println(aTable.remove("B", 3));

        //{A=A2, B=B2, C=C2}
        //{1=B1, 2=B2, 3=B3}
        //B2
        //false
        //true
        //true
        //{1={A=A1, B=B1, C=C1}, 2={A=A2, B=B2, C=C2}, 3={A=A3, B=B3, C=C3}}
        //{A={1=A1, 2=A2, 3=A3}, B={1=B1, 2=B2, 3=B3}, C={1=C1, 2=C2, 3=C3}}
        //B
    }

}
