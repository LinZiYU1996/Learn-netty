package flash.pack6.protocol.request;

import flash.pack6.protocol.Packet;
import lombok.Data;

import static flash.pack5.protocol.command.Command.LOGIN_REQUEST;


@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}
