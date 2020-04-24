package http.blog2;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/22
 * \* Time: 20:49
 * \* Description:
 * \
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


    /**
     * 获取传递的参数
     *
     * @param ctx
     * @param fullHttpRequest
     * @return
     * @throws UnsupportedEncodingException
     */
    private static Map<String, Object> getParamsFromChannel(
            ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest)
            throws UnsupportedEncodingException {
        HttpHeaders headers = fullHttpRequest.headers();
        String strContentType = headers.get("Content-Type").trim();
        System.out.println("ContentType:" + strContentType);
        Map<String, Object> mapReturnData = new HashMap<String, Object>();
        if (fullHttpRequest.getMethod() == HttpMethod.GET) {
            // 处理get请求
            QueryStringDecoder decoder = new QueryStringDecoder(
                    fullHttpRequest.getUri());
            Map<String, List<String>> parame = decoder.parameters();
            for (Map.Entry<String, List<String>> entry : parame.entrySet()) {
                mapReturnData.put(entry.getKey(), entry.getValue().get(0));
            }
            System.out.println("GET方式：" + parame.toString());
        } else if (fullHttpRequest.getMethod() == HttpMethod.POST) {
            // 处理POST请求
            if (strContentType.contains("x-www-form-urlencoded")) {
                HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(
                        new DefaultHttpDataFactory(false), fullHttpRequest);
                List<InterfaceHttpData> postData = decoder.getBodyHttpDatas();
                for (InterfaceHttpData data : postData) {
                    if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                        MemoryAttribute attribute = (MemoryAttribute) data;
                        mapReturnData.put(attribute.getName(),
                                attribute.getValue());
                    }
                }
            } else if (strContentType.contains("application/json")) {
                // 解析json数据
                ByteBuf content = fullHttpRequest.content();
                byte[] reqContent = new byte[content.readableBytes()];
                content.readBytes(reqContent);
                String strContent = new String(reqContent, "UTF-8");
                System.out.println("接收到的消息" + strContent);
                JSONObject jsonParamRoot = JSONObject.parseObject(strContent);
                for (String key : jsonParamRoot.keySet()) {
                    mapReturnData.put(key, jsonParamRoot.get(key));
                }
            } else {
                FullHttpResponse response = new DefaultFullHttpResponse(
                        HTTP_1_1, HttpResponseStatus.INTERNAL_SERVER_ERROR);
                ctx.writeAndFlush(response).addListener(
                        ChannelFutureListener.CLOSE);
            }
            System.out.println("POST方式：" + mapReturnData.toString());
        }
        return mapReturnData;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        // 构造返回数据
        JSONObject jsonRootObj = new JSONObject();
        JSONObject jsonUserInfo = new JSONObject();
        jsonUserInfo.put("id", 1);
        jsonUserInfo.put("name", "张三");
        jsonUserInfo.put("password", "123");
        jsonRootObj.put("userInfo", jsonUserInfo);
        // 获取传递的数据
        Map<String, Object> params = getParamsFromChannel(ctx, msg);
        jsonRootObj.put("params", params);

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
