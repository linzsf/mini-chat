package org.lzsf.mc.server.service;

import io.netty.channel.ChannelHandlerContext;
import org.lzsf.protocol.request.Request;
import org.lzsf.protocol.response.Response;

public interface ServerService {
    Response excute(ChannelHandlerContext ctx, Request request);
}
