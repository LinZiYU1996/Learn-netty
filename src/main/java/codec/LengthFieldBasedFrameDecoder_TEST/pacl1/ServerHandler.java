package codec.LengthFieldBasedFrameDecoder_TEST.pacl1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import use_protobuf.netty1.Protocol;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/28
 * \* Time: 20:18
 * \* Description:
 * \
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf) msg;
        test1(buf);
        while (buf.isReadable()) {
            System.out.print((char)buf.readByte());
        }

    }

    private void test1(ByteBuf buf) {
        System.out.println(buf.capacity());
        System.out.println(buf.readerIndex());
        System.out.println(buf.writerIndex());
    }
}

//  len + content
//0 4 0 4
//decode
//11
//0
//11
//hello world

//0400
//decode
//15
//0
//15
//   hello world

