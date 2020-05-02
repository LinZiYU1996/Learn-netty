package flash.pack5.protocol.request;

import flash.pack5.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;


import static flash.pack5.protocol.command.Command.MESSAGE_REQUEST;

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String message;

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
