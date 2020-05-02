package flash.pack5.protocol.response;

import flash.pack5.protocol.Packet;
import lombok.Data;

import static flash.pack5.protocol.command.Command.LOGIN_RESPONSE;


@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
