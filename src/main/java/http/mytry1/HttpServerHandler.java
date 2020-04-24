package http.mytry1;

import com.alibaba.fastjson.JSONObject;
import http.blog.HttpHelloWorldServerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/23
 * \* Time: 9:36
 * \* Description:
 * \
 */


public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

    private static final Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

        // 获取请求头
        HttpHeaders httpHeaders = msg.headers();
//        System.out.println(httpHeaders.toString());
        // 查看请求头的详细信息
        logger.info(httpHeaders.toString());
        logger.info("*****************************");
        // 打印uri
        logger.info(msg.uri());

        // 获取Content-Type信息
        String strContentType = httpHeaders.get("Content-Type").trim();
        Map<String, Object> mapReturnData = new HashMap<String, Object>();
        System.out.println("ContentType:" + strContentType);

        // 处理get请求
        QueryStringDecoder decoder = new QueryStringDecoder(
                msg.getUri());
        if (msg.getMethod() == HttpMethod.GET) {
            Map<String, List<String>> get_parame = decoder.parameters();

            System.out.println("GET方式：" + get_parame.toString());

            response_message(ctx);
        } else if (msg.getMethod() == HttpMethod.POST) {

            if (strContentType.contains("application/json")) {
                    ByteBuf content = msg.content();
                    Map<String, List<String>> post_parame = decoder.parameters();
                    System.out.println("POST方式：" + post_parame.toString());
                    if (content.capacity() != 0 ) {
                        byte[] resquestContent = new byte[content.readableBytes()];
                        content.readBytes(resquestContent);
                        String strContent = new String(resquestContent, "UTF-8");
                        JSONObject jsonParamRoot = JSONObject.parseObject(strContent);
                        for (String key : jsonParamRoot.keySet()) {
                            mapReturnData.put(key, jsonParamRoot.get(key));
                        }
                    }
                }

//                logger.info("POST: " + mapReturnData.toString());
        }


    }


    // 服务端回应数据
    private void response_message(ChannelHandlerContext ctx) {

        // 构造返回数据
        JSONObject jsonRootObj = new JSONObject();
        JSONObject jsonUserInfo = new JSONObject();
        jsonUserInfo.put("id", 1);
        jsonUserInfo.put("name", "张三");
        jsonUserInfo.put("password", "123");
        jsonRootObj.put("userInfo", jsonUserInfo);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
        response.headers().set(CONTENT_TYPE, "application/json; charset=UTF-8");
        StringBuilder bufRespose = new StringBuilder();
        bufRespose.append(jsonRootObj.toJSONString());
        ByteBuf buffer = Unpooled.copiedBuffer(bufRespose, CharsetUtil.UTF_8);
        response.content().writeBytes(buffer);
        buffer.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);


    }
}
//DefaultHttpHeaders
// [
// Content-Type: application/json,
// User-Agent: PostmanRuntime/7.24.1,
// Accept: */*,
// Cache-Control: no-cache,
// Postman-Token: ebe8b6b7-f508-430b-8793-e289cba115e7,
// Host: localhost:9921,
// Accept-Encoding: gzip,
// deflate, br,
// Connection: keep-alive,
// content-length: 0
// ]


//11:02:02.751
// [nioEventLoopGroup-3-2] INFO http.mytry1.HttpServerHandler -
//
//
// DefaultHttpHeaders
// [
// Content-Type: application/json,
// User-Agent: PostmanRuntime/7.24.1,
// Accept: */*,
// Cache-Control: no-cache,
// Postman-Token: 4a9564c6-dc77-4b57-bc58-fd90d961c7a2,
// Host: localhost:9921, Accept-Encoding: gzip, deflate, br, Connection: keep-alive, content-length: 0]
