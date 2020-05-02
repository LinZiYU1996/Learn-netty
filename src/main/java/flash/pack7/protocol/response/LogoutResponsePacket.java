package flash.pack7.protocol.response;

import flash.pack7.protocol.Packet;
import lombok.Data;

import static flash.pack7.protocol.command.Command.LOGOUT_RESPONSE;


@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
    }
}
