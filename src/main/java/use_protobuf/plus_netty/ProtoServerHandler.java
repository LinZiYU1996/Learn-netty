package use_protobuf.plus_netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/20
 * \* Time: 20:45
 * \* Description:
 * \
 */
public class ProtoServerHandler extends SimpleChannelInboundHandler<DataInfo.RequestUser> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.RequestUser msg) throws Exception {
        System.out.println(msg.getUserName());
        System.out.println(msg.getAge());
        System.out.println(msg.getPassword());

        DataInfo.ResponseBank bank = DataInfo.ResponseBank.newBuilder().setBankName("中国工商银行")
                .setBankNo("6222222200000000000").setMoney(560000.23).build();

        ctx.channel().writeAndFlush(bank);
    }
}
