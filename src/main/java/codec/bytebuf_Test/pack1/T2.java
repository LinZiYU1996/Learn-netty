package codec.bytebuf_Test.pack1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/30
 * \* Time: 9:51
 * \* Description:
 * \
 */
public class T2 {

    public static void main(String[] args) {

        ByteBuf buf = Unpooled.buffer();

        buf.writeInt(100);

        /*
        public abstract long getUnsignedInt(int index)
Gets an unsigned 32-bit integer at the specified absolute index in this buffer. This method does not modify readerIndex or writerIndex of this buffer.
Throws:
java.lang.IndexOutOfBoundsException - if the specified index is less than 0 or index + 4 is greater than this.capacity
         */
        System.out.println(buf.getUnsignedInt(0));

        System.out.println(buf.getInt(0));
        System.out.println("");

    }
}
