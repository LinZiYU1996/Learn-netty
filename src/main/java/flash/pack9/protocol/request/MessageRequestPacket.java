package flash.pack9.protocol.request;

import flash.pack9.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static flash.pack9.protocol.command.Command.MESSAGE_REQUEST;


@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    private String toUserId;
    private String message;

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
