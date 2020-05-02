package flash.pack8.protocol.response;

import flash.pack8.protocol.Packet;
import lombok.Data;

import static flash.pack8.protocol.command.Command.LOGOUT_RESPONSE;


@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
    }
}
