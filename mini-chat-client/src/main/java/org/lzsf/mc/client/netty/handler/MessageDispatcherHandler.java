package org.lzsf.mc.client.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.lzsf.mc.client.service.ClientService;
import org.lzsf.mc.client.service.ClientServiceSelector;
import org.lzsf.protocol.Packet;
import org.lzsf.protocol.response.Response;

@Slf4j
public class MessageDispatcherHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("connect to " + ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packet packet = (Packet) msg;
        ClientService clientService = ClientServiceSelector.select(packet);
        clientService.excute(packet);
    }
}
