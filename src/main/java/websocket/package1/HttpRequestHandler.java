package websocket.package1;

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
 * \* Date: 2020/4/23
 * \* Time: 17:59
 * \* Description:
 * \
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


    private final String wsUri;

    private static File INDEX ;

    static  {

        URL location = HttpRequestHandler.class
                .getProtectionDomain()
                .getCodeSource().getLocation();

        try {

            String path = location.toURI() + "index.html";

            path = !path.contains("file:") ? path : path.substring(5);

            INDEX = new File(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    public HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {

        if (wsUri.equalsIgnoreCase(fullHttpRequest.getUri())) {

            channelHandlerContext.fireChannelRead(fullHttpRequest.retain());
        } else {

            if (HttpHeaders.is100ContinueExpected(fullHttpRequest)) {

                send100Continue(channelHandlerContext);
            }

            RandomAccessFile file = new RandomAccessFile(INDEX,"r");

            HttpVersion version;
            HttpResponseStatus status;
            HttpResponse response = new DefaultHttpResponse(
                    fullHttpRequest.getProtocolVersion(),
                    HttpResponseStatus.OK
            );

            response.headers().set(
                    HttpHeaders.Names.CONTENT_TYPE,
                    "text/plain;charset=UTF-8"
            );

            boolean keepAlive = HttpHeaders.isKeepAlive(fullHttpRequest);

            if (keepAlive) {

                response.headers().set(
                        HttpHeaders.Names.CONTENT_LENGTH,
                        file.length()
                );

                response.headers().set(
                        HttpHeaders.Names.CONNECTION,
                        HttpHeaders.Values.KEEP_ALIVE
                );

            }

            channelHandlerContext.write(response);

            if (channelHandlerContext.pipeline().get(SslHandler.class) == null) {

                channelHandlerContext.write(
                        new DefaultFileRegion(
                                file.getChannel(),
                                0,
                                file.length()
                        )
                );
            } else {
                channelHandlerContext.write(new ChunkedNioFile(
                        file.getChannel()
                ));
            }

            Object response1;
            ChannelFuture f = channelHandlerContext.writeAndFlush(
                    LastHttpContent.EMPTY_LAST_CONTENT
            );

            if(!keepAlive) {
                f.addListener(
                        ChannelFutureListener.CLOSE
                );
            }



        }



    }

    private static void send100Continue(ChannelHandlerContext ctx) {

        HttpVersion version;
        HttpResponseStatus status;
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);

        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
