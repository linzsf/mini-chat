package org.lzsf.mc.client.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.lzsf.protocol.AbstractMessage;
import org.lzsf.protocol.MessageProtocol;
import org.lzsf.protocol.UserMessage;

@Slf4j
public class NettyClient {

    private static String REMOTE_HOST = "127.0.0.1";

    private static int REMOTE_PORT = 9091;
    public EventLoopGroup workerGroup = new NioEventLoopGroup();

    public void start() {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .remoteAddress(REMOTE_HOST, REMOTE_PORT);
            ChannelFuture future = bootstrap.connect().sync();
            MessageProtocol protocol = new MessageProtocol();
            UserMessage message = new UserMessage();
            protocol.setClassNameLen(UserMessage.class.getName().length());
            protocol.setClassName(UserMessage.class.getName());

            protocol.setMessage(JSONObject.toJSONBytes(message));
            protocol.setLength(8 + protocol.getClassNameLen() + protocol.getMessage().length);
            future.channel().writeAndFlush(protocol);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
