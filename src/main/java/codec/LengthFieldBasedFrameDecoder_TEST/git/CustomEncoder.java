package codec.LengthFieldBasedFrameDecoder_TEST.git;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/29
 * \* Time: 9:20
 * \* Description:
 * \
 */
public class CustomEncoder extends MessageToByteEncoder<CustomMsg> {

    public String Encoding = "utf-8";

    @Override
    protected void encode(ChannelHandlerContext ctx, CustomMsg msg, ByteBuf out) throws Exception {

        if (null == msg) {
            throw new Exception("msg is null");
        }

        String body = msg.getBody();

        byte[] bodyBytes = body.getBytes(Charset.forName(Encoding));

        //NSG:|1|1|4|BODY|
        out.writeByte(msg.getType());      //系统编号
        out.writeByte(msg.getFlag());      //信息标志
        out.writeInt(bodyBytes.length);   //消息长度
        out.writeBytes(bodyBytes);         //消息正文
        System.out.println("encode done");
    }


}
