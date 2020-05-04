package flash.pack9.protocol.response;

import flash.pack9.protocol.Packet;
import lombok.Data;

import static flash.pack9.protocol.command.Command.LOGOUT_RESPONSE;


@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
    }
}
