package http.blog;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/22
 * \* Time: 11:09
 * \* Description:
 * \
 */
public class HttpHelloWorldServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        /**
         * 或者使用HttpRequestDecoder & HttpResponseEncoder
         */
        p.addLast(new HttpServerCodec());
        /**
         * 在处理POST消息体时需要加上
         */
        p.addLast(new HttpObjectAggregator(1024*1024));
        p.addLast(new HttpServerExpectContinueHandler());
        p.addLast(new HttpHelloWorldServerHandler());
    }



}
