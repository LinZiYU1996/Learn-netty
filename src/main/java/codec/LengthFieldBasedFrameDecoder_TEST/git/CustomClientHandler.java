package codec.LengthFieldBasedFrameDecoder_TEST.git;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/29
 * \* Time: 9:20
 * \* Description:
 * \
 */
public class CustomClientHandler extends ChannelInboundHandlerAdapter {

    private byte type;
    private byte flag;
    private int length;
    private String body;
    private String encoding = "UTF-8";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(String.format("channelActive %s", ctx.channel().remoteAddress()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(" statrt channelRead");
        ByteBuf buf = (ByteBuf) msg;
        test(buf);
        type = buf.readByte();
        flag = buf.readByte();
        length = buf.readInt();

        int len = buf.readableBytes();
        byte[] req = new byte[len];
        buf.readBytes(req);
        body = new String(req, encoding);

        CustomMsg entityMessage = new CustomMsg(type, flag, length, body);

        System.out.println(String.format("ip:%s %s", ctx.channel().remoteAddress(), entityMessage));
    }

    private void test(ByteBuf byteBuf) {
        System.out.println("====================================================");
        System.out.println(byteBuf.capacity());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        System.out.println("====================================================");
    }

}
