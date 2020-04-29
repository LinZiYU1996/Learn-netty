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
        ProtocolA a = new ProtocolA();
        String msg = "hello world";
        a.setLength(msg.getBytes().length);
        a.setBody(msg);
        ctx.writeAndFlush(a);
        System.out.println("send done");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}
