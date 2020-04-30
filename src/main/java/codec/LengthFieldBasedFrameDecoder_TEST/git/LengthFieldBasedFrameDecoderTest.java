package codec.LengthFieldBasedFrameDecoder_TEST.git;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.TooLongFrameException;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;


/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/29
 * \* Time: 11:16
 * \* Description:
 * \
 */
public class LengthFieldBasedFrameDecoderTest {

    public static void main(String[] args) {

        new LengthFieldBasedFrameDecoderTest().testDiscardTooLongFrame1();
    }


    @Test
    public void testDiscardTooLongFrame1() {
        ByteBuf buf = Unpooled.buffer();

        CustomMsg msg = getCustomMsg();

        String body = msg.getBody();

        byte[] bodyBytes = body.getBytes(Charset.forName("UTF-8"));

        //NSG:|1|1|4|BODY|
        buf.writeByte(msg.getType());      //系统编号
        buf.writeByte(msg.getFlag());      //信息标志
        buf.writeInt(bodyBytes.length);   //消息长度
        buf.writeBytes(bodyBytes);         //消息正文

        EmbeddedChannel channel = new EmbeddedChannel(new LengthFieldBasedFrameDecoder(
                50,
                2,
                4,
                0,
                0));

        channel.writeInbound(buf);


        assertTrue(channel.finish());

        ByteBuf b = channel.readInbound();
        System.out.println(b.capacity());
//        assertEquals(5, b.readableBytes());
//        assertEquals(1, b.readInt());
//        assertEquals('a', b.readByte());
        b.release();
//        Assert.assertEquals(5, b.readableBytes());
//        Assert.assertEquals);
//        Assert.assertEquals('a', b.readByte());
//        b.release();

//        Assert.assertNull(channel.readInbound());
        channel.finish();
    }


    private CustomMsg getCustomMsg(){
        String msgBody = "hello world";

        CustomMsg msgEntity = new CustomMsg(
                (byte) 10,
                (byte) 0xCD,
                msgBody.length(),
                msgBody);

        return msgEntity;
    }

    public void testDiscardTooLongFrame2() {
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(32);
        for (int i = 0; i < 32; i++) {
            buf.writeByte(i);
        }
        buf.writeInt(1);
        buf.writeByte('a');
        EmbeddedChannel channel = new EmbeddedChannel(new LengthFieldBasedFrameDecoder(16, 0, 4));
        try {
            channel.writeInbound(buf.readRetainedSlice(14));
//            Assert.fail();
        } catch (TooLongFrameException e) {
            // expected
        }
//        Assert.assertTrue(channel.writeInbound(buf.readRetainedSlice(buf.readableBytes())));

//        Assert.assertTrue(channel.finish());

        ByteBuf b = channel.readInbound();
//        Assert.assertEquals(5, b.readableBytes());
//        Assert.assertEquals(1, b.readInt());
//        Assert.assertEquals('a', b.readByte());
        b.release();

//        Assert.assertNull(channel.readInbound());
        channel.finish();

        buf.release();
    }

    @Test
    public void testDiscardTooLongFrame111() {
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(32);
        for (int i = 0; i < 32; i++) {
            buf.writeByte(i);
        }
        buf.writeInt(1);
        buf.writeByte('a');
        assertEquals(41,buf.readableBytes());
        EmbeddedChannel channel = new EmbeddedChannel(new LengthFieldBasedFrameDecoder(
                1024,
                0,
                4,
                0,
                0
        ));
        try {
            channel.writeInbound(buf);
//            Assert.fail();
        } catch (TooLongFrameException e) {
            // expected
        }
        assertTrue(channel.finish());

        ByteBuf b = channel.readInbound();
//        assertEquals(32,b.capacity());
//        while (b.isReadable()) {
//            System.out.print(b.readByte());
//        }
        assertEquals(36, b.readableBytes());
        assertEquals(32, b.readInt());
        while (b.isReadable()) {
            System.out.print(b.readByte()+" ");
        }
//        assertEquals('a', b.readByte());
        b.release();

//        assertNull(channel.readInbound());
        channel.finish();
    }


    @Test
    public void test_initialBytesToStrip(){
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(32);
        for (int i = 0; i < 32; i++) {
            buf.writeByte(i);
        }
        buf.writeInt(1);
        buf.writeByte('a');
        assertEquals(41,buf.readableBytes());
        EmbeddedChannel channel = new EmbeddedChannel(new LengthFieldBasedFrameDecoder(
                1024,
                0,
                4,
                0,
                4
        ));
        channel.writeInbound(buf);
        ByteBuf b = channel.readInbound();
        assertEquals(32,b.readableBytes());


    }


    @Test
    public void test_lengthAdjustment(){

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(32);
        for (int i = 0; i < 32; i++) {
            buf.writeByte(i);
        }
        buf.writeInt(1);
        buf.writeByte('a');
        assertEquals(41,buf.readableBytes());
        EmbeddedChannel channel = new EmbeddedChannel(new LengthFieldBasedFrameDecoder(
                1024,
                0,
                4,
                -2,
                0
        ));
        channel.writeInbound(buf);
        ByteBuf b = channel.readInbound();
        assertEquals(36,b.readableBytes());
    }

    @Test
    public void test_lengthFieldOffset() {
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(32);
        for (int i = 0; i < 32; i++) {
            buf.writeByte(i);
        }
        buf.writeInt(1);
        buf.writeByte('a');
        assertEquals(41,buf.readableBytes());
        EmbeddedChannel channel = new EmbeddedChannel(new LengthFieldBasedFrameDecoder(
                1024,
                2,
                4,
                0,
                0
        ));
        channel.writeInbound(buf);
        ByteBuf b = channel.readInbound();
        assertEquals(32,b.readableBytes());
    }
}
