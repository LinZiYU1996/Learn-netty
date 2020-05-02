package flash.pack7.protocol.request;

import flash.pack7.protocol.Packet;
import lombok.Data;

import java.util.List;

import static flash.pack7.protocol.command.Command.CREATE_GROUP_REQUEST;


@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_REQUEST;
    }
}
