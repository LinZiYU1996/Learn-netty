package my_protocol.custom.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import my_protocol.MessageType;
import my_protocol.custom.struct.Header;
import my_protocol.custom.struct.NettyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/28
 * \* Time: 10:49
 * \* Description:
 * \
 */
public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartBeatReqHandler.class);

    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        NettyMessage message = (NettyMessage) msg;

// 握手成功，主动发送心跳消息
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000,
                    TimeUnit.MILLISECONDS);
        } else if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
            LOGGER.info("Client receive server heart beat message : ---> {}", message);
        } else
            ctx.fireChannelRead(msg);

    }


    private class HeartBeatTask implements Runnable {

        private final ChannelHandlerContext ctx;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage heatBeat = buildHeatBeat();
            LOGGER.info("Client send heart beat messsage to server : ---> {}", heatBeat);
            ctx.writeAndFlush(heatBeat);
        }

        private NettyMessage buildHeatBeat() {
            NettyMessage message = new NettyMessage();
            Header header = new Header();
            header.setType(MessageType.HEARTBEAT_REQ.value());
            message.setHeader(header);
            return message;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //断连期间，心跳定时器停止工作，不再发送心跳请求信息
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);    }
}
