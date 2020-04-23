//package use_protobuf.netty1;
//
//import com.google.protobuf.ExtensionRegistry;
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.handler.codec.protobuf.ProtobufDecoder;
//import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
//import io.netty.handler.codec.string.StringEncoder;
//
//import java.net.InetSocketAddress;
//import java.nio.channels.Channels;
//import java.util.concurrent.Executors;
//
///**
// * \* Created with IntelliJ IDEA.
// * \* User: LinZiYu
// * \* Date: 2020/4/22
// * \* Time: 10:56
// * \* Description:
// * \
// */
//public class PbServer {
//
//
//    public static void main(String[] args) {
//
//        ServerBootstrap bootstrap = new ServerBootstrap(
//                new NioServerSocketChannelFactory(
//                        Executors.newCachedThreadPool(),
//                        Executors.newCachedThreadPool()));
//        final PbServerHandler handler = new PbServerHandler();
//        final ExtensionRegistry registry = ExtensionRegistry.newInstance();
//        Protocol.registerAllExtensions(registry);
//
//        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
//            public ChannelPipeline getPipeline() {
//                ChannelPipeline pipeline = Channels.pipeline();
//                pipeline.addLast("encoder", new StringEncoder());
//
//                pipeline.addLast("frameDecoder",
//                        new ProtobufVarint32FrameDecoder());
//                pipeline.addLast("protobufDecoder", new ProtobufDecoder(
//                        Protocol.Request.getDefaultInstance(), registry));
//                pipeline.addLast("handler", handler);
//                return pipeline;
//            }
//        });
//        bootstrap.bind(new InetSocketAddress(8080));
//        System.out.println("----------server is ok-----------");
//
//
//
//    }
//}
