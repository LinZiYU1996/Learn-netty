package my_protocol.custom.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import my_protocol.custom.struct.Header;
import my_protocol.custom.struct.NettyMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/28
 * \* Time: 10:16
 * \* Description:
 * \
 */
public class TestCodeC {

    MarshallingEncoder marshallingEncoder;
    MarshallingDecoder marshallingDecoder;

    public TestCodeC() throws IOException {
        marshallingDecoder = new MarshallingDecoder();
        marshallingEncoder = new MarshallingEncoder();
    }

    public NettyMessage getMessage() {
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setLength(123);
        header.setSessionID(99999);
        header.setType((byte) 1);
        header.setPriority((byte) 7);
        Map<String, Object> attachment = new HashMap<String, Object>();
        for (int i = 0; i < 10; i++) {
            attachment.put("ciyt --> " + i, "lilinfeng " + i);
        }
        header.setAttachment(attachment);
        nettyMessage.setHeader(header);
        nettyMessage.setBody("abcdefg-----------------------AAAAAA");
        return nettyMessage;
    }

    public ByteBuf encode(NettyMessage msg) throws Exception {
        ByteBuf sendBuf = Unpooled.buffer();
        sendBuf.writeInt((msg.getHeader().getCrcCode()));
        sendBuf.writeInt((msg.getHeader().getLength()));
        sendBuf.writeLong((msg.getHeader().getSessionID()));
        sendBuf.writeByte((msg.getHeader().getType()));
        sendBuf.writeByte((msg.getHeader().getPriority()));
        sendBuf.writeInt((msg.getHeader().getAttachment().size()));
        String key = null;
        byte[] keyArray = null;
        Object value = null;

        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value = param.getValue();
            marshallingEncoder.encode(value, sendBuf);
        }
        key = null;
        keyArray = null;
        value = null;
        if (msg.getBody() != null) {
            marshallingEncoder.encode(msg.getBody(), sendBuf);
        } else
            sendBuf.writeInt(0);
        sendBuf.setInt(4, sendBuf.readableBytes());
        return sendBuf;
    }

    public NettyMessage decode(ByteBuf in) throws Exception {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        header.setSessionID(in.readLong());
        header.setType(in.readByte());
        header.setPriority(in.readByte());

        int size = in.readInt();
        if (size > 0) {
            Map<String, Object> attch = new HashMap<String, Object>(size);
            int keySize = 0;
            byte[] keyArray = null;
            String key = null;
            for (int i = 0; i < size; i++) {
                keySize = in.readInt();
                keyArray = new byte[keySize];
                in.readBytes(keyArray);
                key = new String(keyArray, "UTF-8");
                attch.put(key, marshallingDecoder.decode(in));
            }
            keyArray = null;
            key = null;
            header.setAttachment(attch);
        }
        if (in.readableBytes() > 4) {
            message.setBody(marshallingDecoder.decode(in));
        }
        message.setHeader(header);
        return message;
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        TestCodeC testC = new TestCodeC();
        NettyMessage message = testC.getMessage();
        System.out.println(message + "[body ] " + message.getBody());
        NettyMessageDecoder nettyMessageDecoder = new NettyMessageDecoder(1024*1024*5, 4, 4);
        for (int i = 0; i < 5; i++) {
            ByteBuf buf = testC.encode(message);
//            nettyMessageDecoder.
//            NettyMessage decodeMsg = testC.decode(buf);
//            System.out.println(decodeMsg + "[body ] " + decodeMsg.getBody());
//            System.out.println("-------------------------------------------------");

        }

    }


}
/*
NettyMessage [header=Header
[crcCode=-1381039867, length=429, sessionID=99999, type=1, priority=7,
attachment={ciyt --> 4=lilinfeng 4, ciyt --> 3=lilinfeng 3, ciyt --> 2=lilinfeng 2, ciyt --> 1=lilinfeng 1, ciyt --> 8=lilinfeng 8, ciyt --> 7=lilinfeng 7,
ciyt --> 6=lilinfeng 6, ciyt --> 5=lilinfeng 5, ciyt --> 9=lilinfeng 9, ciyt --> 0=lilinfeng 0}]][body ] abcdefg-----------------------AAAAAA
-------------------------------------------------
NettyMessage
[header=Header [crcCode=-1381039867, length=429, sessionID=99999, type=1, priority=7, attachment={ciyt --> 4=lilinfeng 4, ciyt --> 3=lilinfeng 3,
 ciyt --> 2=lilinfeng 2, ciyt --> 1=lilinfeng 1, ciyt --> 8=lilinfeng 8, ciyt --> 7=lilinfeng 7, ciyt --> 6=lilinfeng 6, ciyt --> 5=lilinfeng 5,
  ciyt --> 9=lilinfeng 9, ciyt --> 0=lilinfeng 0}]][body ] abcdefg-----------------------AAAAAA
-------------------------------------------------
NettyMessage
[header=Header [crcCode=-1381039867, length=429, sessionID=99999, type=1, priority=7, attachment={ciyt --> 4=lilinfeng 4, ciyt --> 3=lilinfeng 3, ciyt --> 2=lilinfeng 2,
ciyt --> 1=lilinfeng 1, ciyt --> 8=lilinfeng 8, ciyt --> 7=lilinfeng 7, ciyt --> 6=lilinfeng 6, ciyt --> 5=lilinfeng 5, ciyt --> 9=lilinfeng 9, ciyt --> 0=lilinfeng
 0}]][body ] abcdefg-----------------------AAAAAA
-------------------------------------------------
NettyMessage [header=Header [crcCode=-1381039867, length=429, sessionID=99999, type=1, priority=7, attachment={ciyt --> 4=lilinfeng 4, ciyt --> 3=lilinfeng 3, ciyt --> 2=lilinfeng 2, ciyt --> 1=lilinfeng 1, ciyt --> 8=lilinfeng 8, ciyt --> 7=lilinfeng 7, ciyt --> 6=lilinfeng 6, ciyt --> 5=lilinfeng 5, ciyt --> 9=lilinfeng 9, ciyt --> 0=lilinfeng 0}]][body ] abcdefg-----------------------AAAAAA
-------------------------------------------------
NettyMessage [header=Header [crcCode=-1381039867, length=429, sessionID=99999, type=1, priority=7, attachment={ciyt --> 4=lilinfeng 4, ciyt --> 3=lilinfeng 3, ciyt --> 2=lilinfeng 2, ciyt --> 1=lilinfeng 1, ciyt --> 8=lilinfeng 8, ciyt --> 7=lilinfeng 7, ciyt --> 6=lilinfeng 6, ciyt --> 5=lilinfeng 5, ciyt --> 9=lilinfeng 9, ciyt --> 0=lilinfeng 0}]][body ] abcdefg-----------------------AAAAAA
-------------------------------------------------
 */