package org.lzsf.mc.server.service;

import io.netty.channel.ChannelHandlerContext;
import org.lzsf.protocol.request.Request;
import org.lzsf.protocol.response.Response;

public class ChatMessageService implements ServerService{
    @Override
    public Response excute(ChannelHandlerContext ctx, Request request) {
        return null;
    }
}
