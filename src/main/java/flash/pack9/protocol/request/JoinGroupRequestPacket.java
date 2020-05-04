package flash.pack9.protocol.request;

import flash.pack9.protocol.Packet;
import lombok.Data;

import static flash.pack9.protocol.command.Command.JOIN_GROUP_REQUEST;


@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return JOIN_GROUP_REQUEST;
    }
}
