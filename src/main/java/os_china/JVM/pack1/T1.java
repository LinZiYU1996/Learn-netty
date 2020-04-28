package os_china.JVM.pack1;

import org.openjdk.jol.info.ClassLayout;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/27
 * \* Time: 12:51
 * \* Description:
 * \
 */
public class T1 {

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(new NullObject()).toPrintable());
    }
}
/*
os_china.JVM.pack1.NullObject object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           43 c1 00 20 (01000011 11000001 00000000 00100000) (536920387)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 */

//Instance size：16 bytes,结果就是16字节，我们之前预测的12字节不一样，为什么会这样呢？我们看到上图中有3行 object header，每个占用4字节，所以头部就是12字节，这里和我们的计算是一致的，最后一行是虚拟机填充的4字节，那为什么虚拟机要填充4个字节呢？

//回到Java空对象填充了4个字节的问题，因为原字节头是12字节，64位机器下，内存对齐的话就是128位，也就是16字节，所以我们还需要填充4个字节。

