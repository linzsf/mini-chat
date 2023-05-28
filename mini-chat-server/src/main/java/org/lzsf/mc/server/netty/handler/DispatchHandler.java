package org.lzsf.mc.server.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.lzsf.protocol.request.ChatRequest;
import org.lzsf.protocol.Packet;
import org.lzsf.protocol.request.LoginRequest;
import org.lzsf.protocol.request.RegistRequest;

@Slf4j
public class DispatchHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packet packet = (Packet) msg;
        if (packet instanceof RegistRequest) {
            log.info("regist:" + packet);
        } else if (packet instanceof LoginRequest) {
            log.info("auth:" + packet);
        } else if (packet instanceof ChatRequest) {

            log.info("chat:" + packet);
        }
    }
}
