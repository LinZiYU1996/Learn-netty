package codec.LengthFieldBasedFrameDecoder_TEST.git;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/29
 * \* Time: 10:12
 * \* Description:
 * \
 */
public class MyDecoder extends LengthFieldBasedFrameDecoder {



    public MyDecoder(
            int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        super(
                maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        System.out.println(in.readableBytes());

        Object o = super.decode(ctx, in);




//        test(in);

//        test1(in);
        return o;
    }

    private void test(ByteBuf byteBuf) {
        System.out.println("====================================================");
        System.out.println(byteBuf.capacity());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        System.out.println(byteBuf.isReadable());
        System.out.println("====================================================");
    }

    private void test1(ByteBuf buf) throws UnsupportedEncodingException {
        byte type = buf.readByte();
        byte flag = buf.readByte();
        int length = buf.readInt();

        int len = buf.readableBytes();
        byte[] req = new byte[len];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");

        CustomMsg entityMessage = new CustomMsg(type, flag, length, body);

        System.out.println(entityMessage.toString());
    }
}
