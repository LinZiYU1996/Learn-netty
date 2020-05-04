package flash.pack9.protocol.response;


import flash.pack9.protocol.Packet;

import static flash.pack9.protocol.command.Command.HEARTBEAT_RESPONSE;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
