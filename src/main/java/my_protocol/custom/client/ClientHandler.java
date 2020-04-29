package my_protocol.custom.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import my_protocol.custom.struct.NettyMessage;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/28
 * \* Time: 10:55
 * \* Description:
 * \
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {

            NettyMessage message = (NettyMessage)msg;
            System.err.println("Client receive message from server: " + message.getBody());

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.err.println("----------客户端数据读异常-----------");
        ctx.close();
    }
}
