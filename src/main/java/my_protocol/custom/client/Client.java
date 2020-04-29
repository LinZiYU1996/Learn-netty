package my_protocol.custom.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import my_protocol.NettyConstant;
import my_protocol.custom.codec.NettyMessageDecoder;
import my_protocol.custom.codec.NettyMessageEncoder;
import my_protocol.custom.struct.Header;
import my_protocol.custom.struct.NettyMessage;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/28
 * \* Time: 10:55
 * \* Description:
 * \
 */
public class Client {

    public static void main(String[] args) {
        new Client().connect(NettyConstant.PORT, NettyConstant.REMOTEIP);


    }



    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    EventLoopGroup group = new NioEventLoopGroup();

    public void connect(int pory, String host) {


        try {

            Bootstrap b = new Bootstrap();

            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                            ch.pipeline().addLast(new NettyMessageEncoder());
                            ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                            ch.pipeline().addLast("LoginAuthHandler", new LoginAuthReqHandler());
                            ch.pipeline().addLast("HeartBeatHandler", new HeartBeatReqHandler());

                        }
                    });

            // 发起异步连接操作
            ChannelFuture future = b.connect(new InetSocketAddress(host, pory),
                    new InetSocketAddress(NettyConstant.LOCALIP, NettyConstant.LOCAL_PORT)).sync();

            //手动发测试数据，验证是否会产生TCP粘包/拆包情况
			Channel c = future.channel();
            			for (int i = 0; i < 500; i++) {
                            NettyMessage message = new NettyMessage();
                            Header header = new Header();
                            header.setSessionID(1001L);
                            header.setPriority((byte) 1);
                            header.setType((byte) 0);
                            message.setHeader(header);
                            message.setBody("我是请求数据" + i);
                            c.writeAndFlush(message);
                        }
//			}
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        try {
                            connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }



}
