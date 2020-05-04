package flash.pack9.protocol.request;

import flash.pack9.protocol.Packet;
import lombok.Data;

import static flash.pack9.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;


@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
