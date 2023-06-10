package org.lzsf.mc.server.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.lzsf.mc.server.manager.UserChannelManager;

@Slf4j
public class ConnectionHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("client({}) disconnected", ctx.channel().remoteAddress());
        UserChannelManager.userOffline(ctx.channel());
    }
}
