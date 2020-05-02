package flash.pack7.protocol.request;

import flash.pack7.protocol.Packet;
import lombok.Data;

import static flash.pack7.protocol.command.Command.LOGIN_REQUEST;


@Data
public class LoginRequestPacket extends Packet {
    private String userName;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}
