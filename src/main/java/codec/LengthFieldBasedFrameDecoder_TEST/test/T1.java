package codec.LengthFieldBasedFrameDecoder_TEST.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/30
 * \* Time: 11:01
 * \* Description:
 * \
 */
public class T1 {

    private Msg get() {

        Msg msg = new Msg();
        String s = "hello world";
        msg.setHeader((byte) 0x12);
        msg.setContent(s);
        msg.setLength(s.getBytes().length);
        return msg;
    }

    private Msg get1() {

        Msg msg = new Msg();
        String s = "hello world";
        msg.setContent(s);
        msg.setLength(s.getBytes().length);
        msg.setHdr1((byte) 0xCA);
        msg.setHdr2((byte) 0xFE);
        return msg;
    }


    private ByteBuf getBF() {
        ByteBuf buf = Unpooled.buffer();

        Msg msg = get();

        buf.writeByte(msg.getHeader());

        buf.writeInt(msg.getLength());

        buf.writeBytes(msg.getContent().getBytes());

        EmbeddedChannel channel = new EmbeddedChannel(new LengthFieldBasedFrameDecoder(
                1024,
                1,
                4,
                0,
                0
        ));
        channel.writeInbound(buf);
        ByteBuf b = channel.readInbound();

        return b;

    }

    private ByteBuf getBF1() {
        ByteBuf buf = Unpooled.buffer();

        Msg msg = get();

        buf.writeInt(msg.getLength());

        buf.writeByte(msg.getHeader());

        buf.writeBytes(msg.getContent().getBytes());

        EmbeddedChannel channel = new EmbeddedChannel(new LengthFieldBasedFrameDecoder(
                1024,
                0,
                4,
                1,
                0
        ));
        channel.writeInbound(buf);
        ByteBuf b = channel.readInbound();

        return b;

    }

    private ByteBuf getBF2() {
        ByteBuf buf = Unpooled.buffer();

        Msg msg = get1();

        buf.writeByte(msg.getHdr1());

        buf.writeInt(msg.getLength());

        buf.writeByte(msg.getHdr2());

        buf.writeBytes(msg.getContent().getBytes());

        EmbeddedChannel channel = new EmbeddedChannel(new LengthFieldBasedFrameDecoder(
                1024,
                1,
                4,
                1,
                5
        ));
        channel.writeInbound(buf);
        ByteBuf b = channel.readInbound();

        return b;

    }


    /*
     * <b>lengthFieldOffset</b>   = <b>1</b> (= the length of Header 1)
     * <b>lengthFieldLength</b>   = <b>4</b>
     * lengthAdjustment    = 0
     * initialBytesToStrip = 0
     *
     * BEFORE DECODE (17 bytes)                      AFTER DECODE (17 bytes)
     * +----------+----------+----------------+      +----------+----------+----------------+
     * | Header 1 |  Length  | Actual Content |----->| Header 1 |  Length  | Actual Content |
     * |  0xCAFE  | 0x00000C | "HELLO WORLD" |      |  0xCAFE  | 0x00000C | "HELLO WORLD" |
     * +----------+----------+----------------+      +----------+----------+----------------+
     * </pre>

     */
    @Test
    public void test_lengthFieldOffset() {

        ByteBuf buf = getBF();

        assertEquals(16,buf.readableBytes());

        assertEquals(0x12,buf.readByte());

        assertEquals(11,buf.readInt());

        while (buf.isReadable()){
            System.out.print((char)buf.readByte());
        }
    }


    /*
     * lengthFieldOffset   = 0
     * lengthFieldLength   = 4
     * <b>lengthAdjustment</b>    = <b>1</b> (= the length of Header 1)
     * initialBytesToStrip = 0
     *
     * BEFORE DECODE (17 bytes)                      AFTER DECODE (17 bytes)
     * +----------+----------+----------------+      +----------+----------+----------------+
     * |  Length  | Header 1 | Actual Content |----->|  Length  | Header 1 | Actual Content |
     * | 0x00000C |  0xCAFE  | "HELLO, WORLD" |      | 0x00000C |  0xCAFE  | "HELLO, WORLD" |
     * +----------+----------+----------------+      +----------+----------+----------------+
     * </pre>
     */
    @Test
    public void test_lengthAdjustment() {


        ByteBuf buf = getBF1();

        assertEquals(16,buf.readableBytes());

        assertEquals(11,buf.readInt());

        assertEquals(0x12,buf.readByte());
        while (buf.isReadable()){
            System.out.print((char)buf.readByte());
        }
    }

    /*
     * lengthFieldOffset   = 1 (= the length of HDR1)
     * lengthFieldLength   = 4
     * <b>lengthAdjustment</b>    = <b>1</b> (= the length of HDR2)
     * <b>initialBytesToStrip</b> = <b>5</b> (= the length of HDR1 + LEN)
     *
     * BEFORE DECODE (16 bytes)                       AFTER DECODE (13 bytes)
     * +------+--------+------+----------------+      +------+----------------+
     * | HDR1 | Length | HDR2 | Actual Content |----->| HDR2 | Actual Content |
     * | 0xCA | 0x000C | 0xFE | "HELLO, WORLD" |      | 0xFE | "HELLO, WORLD" |
     * +------+--------+------+----------------+      +------+----------------+
     * </pre>
     *
     */
    @Test
    public void test_4() {
        ByteBuf buf = getBF2();
        assertEquals(12,buf.readableBytes());

        System.out.println(buf.readByte());

        while (buf.isReadable()){
            System.out.print((char)buf.readByte());
        }
    }
}
