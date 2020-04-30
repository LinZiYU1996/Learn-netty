package codec.LengthFieldBasedFrameDecoder_TEST.pacl1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/28
 * \* Time: 20:14
 * \* Description:
 * \
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(msg1());
        ctx.writeAndFlush(msg2());
        System.out.println("send done");

    }

    // len + content
    private ProtocolA msg1() {
        ProtocolA a = new ProtocolA();
        String msg = "hello world";
        a.setLength(msg.getBytes().length);
        a.setBody(msg);
        return a;
    }


    // header + len + content
    private ProtocolA msg2() {
        ProtocolA a = new ProtocolA();
        String msg = "hello world";
        a.setCodes((byte) 0x011);
        a.setLength(msg.getBytes().length);
        a.setBody(msg);
        return a;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}
