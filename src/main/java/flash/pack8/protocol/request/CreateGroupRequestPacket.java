package flash.pack8.protocol.request;

import flash.pack8.protocol.Packet;
import lombok.Data;

import java.util.List;

import static flash.pack8.protocol.command.Command.CREATE_GROUP_REQUEST;


@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_REQUEST;
    }
}
