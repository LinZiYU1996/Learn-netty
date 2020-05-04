package flash.pack9.protocol.request;


import flash.pack9.protocol.Packet;

import static flash.pack9.protocol.command.Command.HEARTBEAT_REQUEST;

public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
