package codec.LengthFieldBasedFrameDecoder_TEST.pacl1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/28
 * \* Time: 20:16
 * \* Description:
 * \
 */
public class Server {


    public void bind(int port) {

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            ServerBootstrap sb = new ServerBootstrap();;

            sb.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            ChannelPipeline p = socketChannel.pipeline();
                            p.addLast(new ProtocolADecoder(1024*1024*5,
                                    2,
                                    3,
                                    0,
                                    0));
                            p.addLast(new ProtocolAEncoder());
                            p.addLast(new ServerHandler());
                        }
                    });



            ChannelFuture f = sb.bind(port).sync();
            System.out.println("sverer。。。。。。。。。。。。。。");
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {

        new Server().bind(9901);

    }
}
