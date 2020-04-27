package SSl.netty.oneway;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/26
 * \* Time: 9:38
 * \* Description:
 * \
 */
public class SslOneWayClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        String cChatPath =  "E:\\Learn-netty\\Learn-netty\\src\\main\\java\\SSl\\netty\\config\\oneway\\clientStore.jks";

        SSLEngine engine = SslOneWayContextFactory.getClientContext(cChatPath)
                .createSSLEngine();//创建SSLEngine
        engine.setUseClientMode(true);//客户方模式
        pipeline.addLast("ssl", new SslHandler(engine));

        // On top of the SSL handler, add the text line codec.
        pipeline.addLast("framer", new LineBasedFrameDecoder(1024, false, false));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // and then business logic.
        pipeline.addLast("handler", new SslOneWayClientHandler());
    }

}
