package my_protocol.custom.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import my_protocol.MessageType;
import my_protocol.ResultType;
import my_protocol.custom.struct.Header;
import my_protocol.custom.struct.NettyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.PrinterURI;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/28
 * \* Time: 10:26
 * \* Description:
 * \
 */
public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {


    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAuthReqHandler.class);


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.info("通道激活，握手请求认证..................");

        ctx.writeAndFlush(buildLoginReq());
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;

        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()) {

            byte loginResult = (byte) message.getBody();

            if (loginResult != ResultType.SUCCESS.value()) {
                ctx.close();
            } else {
                System.out.println("Login is OK ： " + message);
                ctx.fireChannelRead(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        message.setHeader(header);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }
}
