package codec.LengthFieldBasedFrameDecoder_TEST.pacl1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import my_protocol.custom.codec.MarshallingDecoder;

import java.io.IOException;
import java.nio.ByteOrder;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/28
 * \* Time: 20:05
 * \* Description:
 * \
 */
public class ProtocolADecoder extends LengthFieldBasedFrameDecoder {


    public ProtocolADecoder(int maxFrameLength,
                            int lengthFieldOffset,
                            int lengthFieldLength,
                            int lengthAdjustment,
                            int initialBytesToStrip) throws IOException {
        super(maxFrameLength,
                lengthFieldOffset,
                lengthFieldLength,
                lengthAdjustment,
                initialBytesToStrip);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        System.out.println("decode");
        return super.decode(ctx, in);
    }
}
