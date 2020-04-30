package codec.LengthFieldBasedFrameDecoder_TEST.git;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/29
 * \* Time: 9:56
 * \* Description:
 * \
 */
public class Nums {

    final static int LENGTH_FIELD_OFFSET= 0;

    final static int LENGTH_FIELD_LENGTH= 4;

    final static int LENGTH_ADJUSTMENT= 0;

    final static int INITIAL_BYTES_TO_STRIP= 0;

    public static int getLengthFieldOffset() {
        return LENGTH_FIELD_OFFSET;
    }

    public static int getLengthFieldLength() {
        return LENGTH_FIELD_LENGTH;
    }

    public static int getLengthAdjustment() {
        return LENGTH_ADJUSTMENT;
    }

    public static int getInitialBytesToStrip() {
        return INITIAL_BYTES_TO_STRIP;
    }
}
