package org.lzsf.mc.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.lzsf.mc.client.cache.RequestCacheManager;
import org.lzsf.protocol.Packet;
import org.lzsf.protocol.request.Request;


import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class NettyClient {

    private static String REMOTE_HOST = "127.0.0.1";

    private static int REMOTE_PORT = 9091;
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    private Channel channel;

    public void start(CountDownLatch count) {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientNettyInitializer())
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .remoteAddress(REMOTE_HOST, REMOTE_PORT);
            ChannelFuture future = bootstrap.connect().sync();
            count.countDown();
            channel = future.channel();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void send(Request request) {
        UUID uuid = UUID.randomUUID();
        request.setRequestId(uuid.toString());
        channel.writeAndFlush(request);
        RequestCacheManager.addRequest(request);
    }

    public void close() {
        if (workerGroup != null && !workerGroup.isShutdown() && !workerGroup.isTerminated()) {
            log.info("start disconnecting from remote server...");
            workerGroup.shutdownGracefully();
            log.info("disconnected from remote server");
        }
    }
}

