package org.lzsf.mc.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.lzsf.mc.client.netty.handler.ClientHandler;
import org.lzsf.protocol.request.LoginRequest;
import org.lzsf.protocol.request.Request;

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
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setCommand(Request.LOGIN);
            loginRequest.setUserName("zhangsan");
            loginRequest.setPassword("123456");
            ByteBuf byteBuf = EncodeManager.encode(loginRequest);
            future.channel().writeAndFlush(byteBuf);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
