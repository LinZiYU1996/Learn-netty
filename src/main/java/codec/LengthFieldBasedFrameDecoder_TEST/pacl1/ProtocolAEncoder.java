package codec.LengthFieldBasedFrameDecoder_TEST.pacl1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/28
 * \* Time: 20:05
 * \* Description:
 * \
 */
public class ProtocolAEncoder extends MessageToByteEncoder<ProtocolA> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ProtocolA protocolA, ByteBuf byteBuf) throws Exception {
        System.out.println("encode");
        encode(protocolA,byteBuf);

    }

    private void encode(ProtocolA protocolA, ByteBuf byteBuf) {

//        byteBuf.writeInt(protocolA.getMagic_cod());

        byteBuf.writeInt(protocolA.getLength());

        byteBuf.writeBytes(protocolA.getBody().getBytes());



    }
}
