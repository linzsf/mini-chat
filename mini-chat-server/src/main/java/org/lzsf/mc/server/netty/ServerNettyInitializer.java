package org.lzsf.mc.server.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import org.lzsf.mc.server.netty.handler.MessageEncoder;
import org.lzsf.mc.server.netty.handler.RequestDispatchHandler;
import org.lzsf.mc.server.netty.handler.MessageDecoder;

public class ServerNettyInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MessageDecoder());
        pipeline.addLast(new RequestDispatchHandler());
        pipeline.addLast(new MessageEncoder());
    }
}
