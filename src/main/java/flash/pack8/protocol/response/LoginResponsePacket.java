package flash.pack8.protocol.response;

import flash.pack8.protocol.Packet;
import lombok.Data;

import static flash.pack8.protocol.command.Command.LOGIN_RESPONSE;


@Data
public class LoginResponsePacket extends Packet {
    private String userId;

    private String userName;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGIN_RESPONSE;
    }
}
