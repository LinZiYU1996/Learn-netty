package websocket.chatroom;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/5/5
 * \* Time: 20:18
 * \* Description:
 * \
 */
//扩展 SimpleChannel-InboundHandler 以处理FullHttpRequest 消息
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String wsUri;
    private static final File INDEX;

    static {
        URL location = HttpRequestHandler.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            String path = location.toURI() + "index.html";
            path = !path.contains("file:") ? path : path.substring(5);
            INDEX = new File(path);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Unable to locate WebsocketChatClient.html", e);
        }
    }

    public HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

        if (wsUri.equalsIgnoreCase(request.uri())) {//如果请求了 WebSocket协议升级，则增加引用 计数（调用retain()方法），并将它传递给下一个ChannelInboundHandler
            ctx.fireChannelRead(request.retain()); //
        } else {
            if (HttpUtil.is100ContinueExpected(request)) {//处理 100 Continue请求以符合 HTTP 1.1 规范
                send100Continue(ctx); //
            }

            RandomAccessFile file = new RandomAccessFile(INDEX, "r");//

            HttpResponse response = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");

            boolean keepAlive = HttpUtil.isKeepAlive(request);

            if (keepAlive) { //如果请求了keep-alive，则添加所需要的HTTP 头信息
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }
            ctx.write(response); //将 HttpResponse写到客户端

            if (ctx.pipeline().get(SslHandler.class) == null) { //将 index.html写到客户端
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);//写LastHttpContent并冲刷至客户端
            if (!keepAlive) {//如果没有请求keep-alive，则在写操作完成后关闭 Channel
                future.addListener(ChannelFutureListener.CLOSE); //
            }

            file.close();
        }
    }

    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:" + incoming.remoteAddress() + "异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
