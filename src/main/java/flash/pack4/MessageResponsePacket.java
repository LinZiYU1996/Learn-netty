package flash.pack4;

import flash.pack3.Packet;
import lombok.Data;

import static flash.pack3.Command.MESSAGE_RESPONSE;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/30
 * \* Time: 20:51
 * \* Description:
 * \
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
