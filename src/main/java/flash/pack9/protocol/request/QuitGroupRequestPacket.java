package flash.pack9.protocol.request;

import flash.pack9.protocol.Packet;
import lombok.Data;

import static flash.pack9.protocol.command.Command.QUIT_GROUP_REQUEST;


@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_REQUEST;
    }
}
