package guava.Range.lab526;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;
import org.junit.Test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/26
 * \* Time: 22:36
 * \* Description:
 *
 * 在Guava中新增了一个新的类型Range，从名字就可以了解到，这个是和区间有关的数据结构。从Google官方文档可以得到定义：
 * Range定义了连续跨度的范围边界，这个连续跨度是一个可以比较的类型(Comparable type)。比如1到100之间的整型数据。
 *
 * 　　在数学里面的范围是有边界和无边界之分的；同样，在Guava中也有这个说法。如果这个范围是有边界的，那么这个范围又可以分为包括开集（
 * 不包括端点）和闭集（包括端点）；如果是无解的可以用+∞表示。如果枚举的话，一共有九种范围表示：
 *
 * Guava Range 概念，范围和方法
 * 概念	表示范围	guava对应功能方法
 * (a..b)	{x | a < x < b}	open(C, C)
 * [a..b]	{x | a <= x <= b} 	closed(C, C)
 * [a..b)	{x | a <= x < b}	closedOpen(C, C)
 * (a..b]	{x | a < x <= b}	openClosed(C, C)
 * (a..+∞)	{x | x > a}	greaterThan(C)
 * [a..+∞)	{x | x >= a}	atLeast(C)
 * (-∞..b)	{x | x < b}	lessThan(C)
 * (-∞..b]	{x | x <= b}	atMost(C)
 * (-∞..+∞)	all values	all()
 *
 * 　上表中的guava对应功能方法那一栏表示Range类提供的方法，分别来表示九种可能出现的范围区间。如果区间两边都存在范围
 * ，在这种情况下，区间右边的数不可能比区间左边的数小。在极端情况下，区间两边的数是相等的，但前提条件是最少有一个边界
 * 是闭集的，否则是不成立的。比如：
 * 　　[a..a] : 里面只有一个数a；
 * 　　[a..a); (a..a] : 空的区间范围，但是是有效的；
 * 　　(a..a) : 这种情况是无效的，构造这样的Range将会抛出异常。
 * 　　在使用Range时需要注意：在构造区间时，尽量使用不可改变的类型。如果你需要使用可变的类型，在区间类型构造完成的情况下，请不要改变区间两边的数。
 *
 *
 * \
 */
public class TestBaseRange {

    @Test
    public void testRange(){
        System.out.println("open:"+ Range.open(1, 10));
        System.out.println("closed:"+ Range.closed(1, 10));
        System.out.println("closedOpen:"+ Range.closedOpen(1, 10));
        System.out.println("openClosed:"+ Range.openClosed(1, 10));
        System.out.println("greaterThan:"+ Range.greaterThan(10));
        System.out.println("atLeast:"+ Range.atLeast(10));
        System.out.println("lessThan:"+ Range.lessThan(10));
        System.out.println("atMost:"+ Range.atMost(10));
        System.out.println("all:"+ Range.all());
        System.out.println("closed:"+Range.closed(10, 10));
        System.out.println("closedOpen:"+Range.closedOpen(10, 10));
        //会抛出异常
        System.out.println("open:"+Range.open(10, 10));

        //open:(1..10)
        //closed:[1..10]
        //closedOpen:[1..10)
        //openClosed:(1..10]
        //greaterThan:(10..+∞)
        //atLeast:[10..+∞)
        //lessThan:(-∞..10)
        //atMost:(-∞..10]
        //all:(-∞..+∞)
        //closed:[10..10]
        //closedOpen:[10..10)


    }

    @Test
    public void testRange1(){
        System.out.println("downTo:"+Range.downTo(4, BoundType.OPEN));
        System.out.println("upTo:"+Range.upTo(4, BoundType.CLOSED));
        System.out.println("range:"+Range.range(1, BoundType.CLOSED, 4, BoundType.OPEN));

        //downTo:(4..+∞)
        //upTo:(-∞..4]
        //range:[1..4)


    }

    //　　1.contains：判断值是否在当前Range内


    @Test
    public void testContains(){
        System.out.println(Range.closed(1, 3).contains(2));
        System.out.println(Range.closed(1, 3).contains(4));
        System.out.println(Range.lessThan(5).contains(5));
        System.out.println(Range.closed(1, 4).containsAll(Ints.asList(1, 2, 3)));
        //true
        //false
        //false
        //true

    }

    //　　2.Endpoint相关查询方法：


    @Test
    public void testQuery(){
        System.out.println("hasLowerBound:"+Range.closedOpen(4, 4).hasLowerBound());
        System.out.println("hasUpperBound:"+Range.closedOpen(4, 4).hasUpperBound());
        System.out.println(Range.closedOpen(4, 4).isEmpty());
        System.out.println(Range.openClosed(4, 4).isEmpty());
        System.out.println(Range.closed(4, 4).isEmpty());
        // Range.open throws IllegalArgumentException
        //System.out.println(Range.open(4, 4).isEmpty());

        System.out.println(Range.closed(3, 10).lowerEndpoint());
        System.out.println(Range.open(3, 10).lowerEndpoint());
        System.out.println(Range.closed(3, 10).upperEndpoint());
        System.out.println(Range.open(3, 10).upperEndpoint());
        System.out.println(Range.closed(3, 10).lowerBoundType());
        System.out.println(Range.open(3, 10).upperBoundType());

        //hasLowerBound:true
        //hasUpperBound:true
        //true
        //true
        //false
        //3
        //3
        //10
        //10
        //CLOSED
        //OPEN


    }

    // 　　3.encloses方法：encloses(Range range)中的range是否包含在需要比较的range中

    @Test
    public void testEncloses(){
        Range<Integer> rangeBase=Range.open(1, 4);
        Range<Integer> rangeClose=Range.closed(2, 3);
        Range<Integer> rangeCloseOpen=Range.closedOpen(2, 4);
        Range<Integer> rangeCloseOther=Range.closedOpen(2, 5);
        System.out.println("rangeBase: "+rangeBase+" Enclose:"+rangeBase.encloses(rangeClose)+" rangeClose:"+rangeClose);
        System.out.println("rangeBase: "+rangeBase+" Enclose:"+rangeBase.encloses(rangeCloseOpen)+" rangeClose:"+rangeCloseOpen);
        System.out.println("rangeBase: "+rangeBase+" Enclose:"+rangeBase.encloses(rangeCloseOther)+" rangeClose:"+rangeCloseOther);
    }

    //rangeBase: (1..4) Enclose:true rangeClose:[2..3]
    //rangeBase: (1..4) Enclose:true rangeClose:[2..4)
    //rangeBase: (1..4) Enclose:false rangeClose:[2..5)







}
