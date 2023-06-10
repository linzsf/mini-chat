package org.lzsf.mc.server.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.lzsf.mc.server.service.ServerService;
import org.lzsf.mc.server.service.ServerServiceSelector;
import org.lzsf.protocol.request.Request;
import org.lzsf.protocol.response.Response;

@Slf4j
public class RequestDispatchHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request request = (Request) msg;
        ServerService service = ServerServiceSelector.select(request.getCommand());
        Response response = service.execute(ctx, request);
        ctx.channel().writeAndFlush(response);
    }
}
