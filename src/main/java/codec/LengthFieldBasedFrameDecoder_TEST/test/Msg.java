package codec.LengthFieldBasedFrameDecoder_TEST.test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/30
 * \* Time: 11:01
 * \* Description:
 * \
 */
public class Msg {

    private byte header;

    private int length;

    private String content;

    private byte hdr1;

    private byte hdr2;

    public byte getHdr1() {
        return hdr1;
    }

    public void setHdr1(byte hdr1) {
        this.hdr1 = hdr1;
    }

    public byte getHdr2() {
        return hdr2;
    }

    public void setHdr2(byte hdr2) {
        this.hdr2 = hdr2;
    }

    public byte getHeader() {
        return header;
    }

    public void setHeader(byte header) {
        this.header = header;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "header=" + header +
                ", length=" + length +
                ", content='" + content + '\'' +
                '}';
    }
}
