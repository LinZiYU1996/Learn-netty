package flash.pack8.protocol.response;

import flash.pack8.protocol.Packet;
import lombok.Data;

import static flash.pack8.protocol.command.Command.MESSAGE_RESPONSE;


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
