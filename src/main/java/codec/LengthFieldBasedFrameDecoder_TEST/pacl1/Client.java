package codec.LengthFieldBasedFrameDecoder_TEST.pacl1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/28
 * \* Time: 20:12
 * \* Description:
 * \
 */
public class Client {


    public void connect(int port, String host) {

        EventLoopGroup group = new NioEventLoopGroup();

        try {

            Bootstrap b = new Bootstrap();

            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            ChannelPipeline p = socketChannel.pipeline();
                            p.addLast(new ProtocolADecoder(
                                    1024*1024*5,
                                    0,
                                    2,
                                    0,
                                    0
                            ));
                            p.addLast(new ProtocolAEncoder());
                            p.addLast(new ClientHandler());
                        }
                    });


            ChannelFuture f = b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }


    }

    public static void main(String[] args) {

        new Client().connect(9901,"localhost");

    }
}
