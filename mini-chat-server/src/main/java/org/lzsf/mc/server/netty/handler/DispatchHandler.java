package org.lzsf.mc.server.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.lzsf.exception.NotFoundMessageServiceException;
import org.lzsf.mc.server.service.AbstractMessageService;
import org.lzsf.mc.server.service.MessageServiceSelector;
import org.lzsf.protocol.AbstractMessage;
import org.lzsf.protocol.AuthMessage;
import org.lzsf.protocol.MessageTypeEnum;
import org.lzsf.protocol.UserMessage;

public class DispatchHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        AbstractMessage message = (AbstractMessage) msg;
        AbstractMessageService messageService = null;
        if (msg instanceof AuthMessage) {
            messageService = MessageServiceSelector.select(MessageTypeEnum.AUTH_MESSAGE);
        } else if (msg instanceof UserMessage) {
            messageService = MessageServiceSelector.select(MessageTypeEnum.USER_MESSAGE);
        }
        if (messageService == null) {
            throw new NotFoundMessageServiceException();
        }
        messageService.execute(message);
    }
}
