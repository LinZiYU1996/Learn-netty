package flash.pack9.protocol.response;

import flash.pack9.protocol.Packet;
import lombok.Data;

import static flash.pack9.protocol.command.Command.QUIT_GROUP_RESPONSE;


@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_RESPONSE;
    }
}
