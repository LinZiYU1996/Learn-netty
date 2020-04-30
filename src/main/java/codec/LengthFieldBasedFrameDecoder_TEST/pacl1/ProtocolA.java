package codec.LengthFieldBasedFrameDecoder_TEST.pacl1;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/28
 * \* Time: 20:02
 * \* Description:
 * \
 */
public class ProtocolA {

    private byte codes;

    public byte getCodes() {
        return codes;
    }

    public void setCodes(byte codes) {
        this.codes = codes;
    }

    //    private int magic_cod = 0xadaf0115;

    private int length;

//    private byte type;

    private String body;

//    public int getMagic_cod() {
//        return magic_cod;
//    }
//
//    public void setMagic_cod(int magic_cod) {
//        this.magic_cod = magic_cod;
//    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

//    public byte getType() {
//        return type;
//    }
//
//    public void setType(byte type) {
//        this.type = type;
//    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ProtocolA{" +

                ", length=" + length +

                ", body='" + body + '\'' +
                '}';
    }
}
