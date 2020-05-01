package flash.pack3;

import lombok.Data;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/30
 * \* Time: 19:57
 * \* Description:
 * \
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;


    public abstract Byte getCommand();
}
