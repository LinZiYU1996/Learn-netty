package flash.pack8.protocol.request;

import flash.pack8.protocol.Packet;
import lombok.Data;

import static flash.pack8.protocol.command.Command.LOGOUT_REQUEST;


@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {

        return LOGOUT_REQUEST;
    }
}
