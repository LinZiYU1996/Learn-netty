package flash.pack9.protocol.response;

import flash.pack9.protocol.Packet;
import lombok.Data;

import static flash.pack9.protocol.command.Command.MESSAGE_RESPONSE;


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
