package git.pack1;

/**
 * Created by yaozb on 15-4-11.
 * 心跳检测的消息类型
 */
public class PingMsg extends BaseMsg {
    public PingMsg() {
        super();
        setType(MsgType.PING);
    }
}
