package flash.login;

import flash.pack3.Packet;
import lombok.Data;

import static flash.pack3.Command.LOGIN_RESPONSE;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/30
 * \* Time: 20:18
 * \* Description:
 * \
 */

@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
