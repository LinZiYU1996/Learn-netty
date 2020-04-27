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
 * \* Time: 9:39
 * \* Description:
 * \
 */
public class SslOneWayServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        String sChatPath = ("E:\\Learn-netty\\Learn-netty\\src\\main\\java\\SSl\\netty\\config\\oneway\\serverStore.jks");

        SSLEngine engine = SslOneWayContextFactory.getServerContext(sChatPath).createSSLEngine();
        engine.setUseClientMode(false);//设置为服务器模式
        //engine.setNeedClientAuth(false);//不需要客户端认证，默认为false，故不需要写这行。

        pipeline.addLast("ssl", new SslHandler(engine));

        // On top of the SSL handler, add the text line codec.
        pipeline.addLast("framer", new LineBasedFrameDecoder(1024, false, false));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // and then business logic.
        pipeline.addLast("handler", new SslOneWayServerHandler());
    }
}
