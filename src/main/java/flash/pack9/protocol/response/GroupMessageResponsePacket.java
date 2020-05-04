package flash.pack9.protocol.response;

import flash.pack9.protocol.Packet;
import flash.pack9.session.Session;
import lombok.Data;

import static flash.pack9.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {

        return GROUP_MESSAGE_RESPONSE;
    }
}
