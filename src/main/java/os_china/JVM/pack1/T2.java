package os_china.JVM.pack1;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/27
 * \* Time: 12:53
 * \* Description:
 * \
 */
public class T2 {

    public static void main(String[] args) {
        //打印实例的内存布局
        System.out.println(ClassLayout.parseInstance(new TestNotNull()).toPrintable());
        //打印对象的所有相关内存占用
        System.out.println(GraphLayout.parseInstance(new TestNotNull()).toPrintable());
        //打印对象的所有内存结果并统计
        System.out.println(GraphLayout.parseInstance(new TestNotNull()).toFootprint());
    }


}
/*
os_china.JVM.pack1.TestNotNull object internals:
 OFFSET  SIZE                            TYPE DESCRIPTION                               VALUE
      0     4                                 (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4                                 (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4                                 (object header)                           43 c1 00 20 (01000011 11000001 00000000 00100000) (536920387)
     12     4                             int TestNotNull.a                             0
     16     4   os_china.JVM.pack1.NullObject TestNotNull.nullObject                    (object)
     20     4                                 (loss due to the next object alignment)
Instance size: 24 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

os_china.JVM.pack1.TestNotNull@45fe3ee3d object externals:
          ADDRESS       SIZE TYPE                           PATH                           VALUE
         d6f122d0         24 os_china.JVM.pack1.TestNotNull                                (object)
         d6f122e8         16 os_china.JVM.pack1.NullObject  .nullObject                    (object)


os_china.JVM.pack1.TestNotNull@58d25a40d footprint:
     COUNT       AVG       SUM   DESCRIPTION
         1        16        16   os_china.JVM.pack1.NullObject
         1        24        24   os_china.JVM.pack1.TestNotNull
         2                  40   (total)
 */

//我们可以看到TestNotNull的类占用空间是24字节，其中头部占用12字节，变量a是int类型，占用4字节,变量nullObject是引用，占用了4字节，最后填充了4个字节，总共是24个字节，与我们之前的预测一致。但是，因为我们实例化了NullObject,这个对象一会存在于内存中，所以我们还需要加上这个对象的内存占用16字节，那总共就是24bytes+16bytes=40bytes。我们图中最后的统计打印结果也是40字节，所以我们的分析正确。
//
//这也是如何分析一个对象真正的占用多少内存的思路，根据这个思路加上openJDK的jol工具就可以基本的掌握自己写的“对象”究竟败家了你多少内存。

