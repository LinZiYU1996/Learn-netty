package flash.pack9.protocol.response;

import flash.pack9.protocol.Packet;
import lombok.Data;

import static flash.pack9.protocol.command.Command.JOIN_GROUP_RESPONSE;


@Data
public class JoinGroupResponsePacket extends Packet {
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return JOIN_GROUP_RESPONSE;
    }
}
