package flash.pack3;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static flash.pack3.Command.LOGIN_REQUEST;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/30
 * \* Time: 19:58
 * \* Description:
 * \
 */

@Data

public class LoginRequestPacket extends Packet{

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}
