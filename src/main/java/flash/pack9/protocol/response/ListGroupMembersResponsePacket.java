package flash.pack9.protocol.response;

import flash.pack9.protocol.Packet;
import flash.pack9.session.Session;
import lombok.Data;

import java.util.List;

import static flash.pack9.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;


@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
