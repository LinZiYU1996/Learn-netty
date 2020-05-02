package flash.pack7.protocol.response;

import flash.pack7.protocol.Packet;
import lombok.Data;

import static flash.pack7.protocol.command.Command.MESSAGE_RESPONSE;


@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
