package flash.pack7.protocol.request;

import flash.pack7.protocol.Packet;
import lombok.Data;

import static flash.pack7.protocol.command.Command.LOGOUT_REQUEST;


@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {

        return LOGOUT_REQUEST;
    }
}
