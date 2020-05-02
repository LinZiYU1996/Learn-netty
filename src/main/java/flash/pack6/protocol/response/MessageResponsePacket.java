package flash.pack6.protocol.response;

import flash.pack6.protocol.Packet;
import lombok.Data;

import static flash.pack5.protocol.command.Command.MESSAGE_RESPONSE;


@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
