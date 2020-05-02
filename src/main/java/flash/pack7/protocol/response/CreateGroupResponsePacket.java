package flash.pack7.protocol.response;

import flash.pack7.protocol.Packet;
import lombok.Data;;

import java.util.List;

import static flash.pack7.protocol.command.Command.CREATE_GROUP_RESPONSE;

@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_RESPONSE;
    }
}
