package org.lzsf.mc.client.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.lzsf.mc.client.netty.handler.MessageDecoder;
import org.lzsf.mc.client.netty.handler.MessageEncoder;
import org.lzsf.mc.client.netty.handler.ResponseDispatcherHandler;

public class ClientNettyInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 出站事件
        ch.pipeline().addLast(new MessageEncoder());
        // 入站事件
        ch.pipeline().addLast(new MessageDecoder());
        ch.pipeline().addLast(new ResponseDispatcherHandler());
    }
}
