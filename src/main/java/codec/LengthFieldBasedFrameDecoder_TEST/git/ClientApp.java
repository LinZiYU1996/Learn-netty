package codec.LengthFieldBasedFrameDecoder_TEST.git;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import javax.swing.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/29
 * \* Time: 9:19
 * \* Description:
 * \
 */
public class ClientApp {

    private static final int MAX_FRAME_LENGTH = 1024 * 1024 * 10;
    private static final int LENGTH_FIELD_OFFSET = 2;
    private static final int LENGTH_FIELD_LENGTH = 4;
    private static final int LENGTH_ADJUSTMENT = 0;
    private static final int INITIAL_BYTES_TO_STRIP = 0;

    static final String HOST = System.getProperty("host", "192.168.1.102");
    static final int PORT = Integer.parseInt(System.getProperty("port", "9924"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {

        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();

        try {

            Bootstrap b = new Bootstrap();

            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {


//                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(
//                                    MAX_FRAME_LENGTH,
//                                    Nums.getLengthFieldOffset(),
//                                    Nums.getLengthFieldLength(),
//                                    Nums.getLengthAdjustment(),
//                                    Nums.getInitialBytesToStrip(),
//                                    false));


                            ch.pipeline().addLast(new MyDecoder(
                                    MAX_FRAME_LENGTH,
                                    Nums.getLengthFieldOffset(),
                                    Nums.getLengthFieldLength(),
                                    Nums.getLengthAdjustment(),
                                    Nums.getInitialBytesToStrip(),
                                    false));

                            ch.pipeline().addLast(new CustomClientHandler());


                            //给服务端发送数据时编码
                            ch.pipeline().addLast(new CustomEncoder());
                        }
                    });

            //异步连接到服务
            ChannelFuture future = b.connect("localhost", PORT).sync();

            Channel clientChannel = future.channel();

            int currentCount = 3;

            while (currentCount > 0) {

                Thread.sleep(1000);

                String msgBody = "hello world";

                CustomMsg msgEntity = new CustomMsg(
                        (byte) 10,
                        (byte) 0xCD,
                        msgBody.length(),
                        msgBody);

                //发送数据
                clientChannel.writeAndFlush(msgEntity);

                //System.out.println(msgBody);

                System.out.println("send--------------------- " + currentCount);
                currentCount -= 1;

            }

            System.out.println("Close Channel");
            clientChannel.closeFuture().sync();

            System.out.println("Client Exit");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }


}
