package codec.bytebuf_Test.pack1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/28
 * \* Time: 21:24
 * \* Description:
 * \
 */
public class T1 {

    public static void main(String[] args) {

        ByteBuf buf = Unpooled.buffer(17);

        buf.writeInt(100);

        buf.writeLong(122L);

        buf.writeFloat(23.26f);

        printf(buf);

        System.out.println("");
        System.out.println(buf.readInt());

        System.out.println("" +
                "");
        printf(buf);


        System.out.println(buf.readLong());

        System.out.println("");

        printf(buf);


        System.out.println(buf.readFloat());

        System.out.println("");

        printf(buf);


    }

    private static void printf(ByteBuf byteBuf){
        System.out.println(byteBuf.capacity());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        System.out.println("===============================");
    }
}
