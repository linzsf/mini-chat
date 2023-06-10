package org.lzsf.mc.server.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.lzsf.mc.server.manager.AuthManager;
import org.lzsf.mc.server.manager.UserChannelManager;
import org.lzsf.protocol.Command;
import org.lzsf.protocol.notice.ChatMessageNotice;
import org.lzsf.protocol.request.ChatMessageRequest;
import org.lzsf.protocol.request.Request;
import org.lzsf.protocol.response.ChatMessageResponse;
import org.lzsf.protocol.response.Response;

public class ChatService implements ServerService{
    @Override
    public Response execute(ChannelHandlerContext ctx, Request request) throws InterruptedException {
        ChatMessageRequest chatMessageRequest = (ChatMessageRequest) request;
        // 向接收方推送消息
        Long toUserId = AuthManager.getUserIdByUserName(((ChatMessageRequest) request).getToUserName());
        Channel toUserChannel = UserChannelManager.getChannelByUserId(toUserId);
        ChatMessageNotice chatMessageNotice = new ChatMessageNotice();
        chatMessageNotice.setCommand(Command.MESSAGE);
        chatMessageNotice.setFromUserName(chatMessageRequest.getFromUserName());
        chatMessageNotice.setToUserName(chatMessageRequest.getToUserName());
        chatMessageNotice.setContent(chatMessageRequest.getContent());
        toUserChannel.writeAndFlush(chatMessageNotice).sync();

        // 向发送方返回发送结果
        ChatMessageResponse chatMessageResponse = new ChatMessageResponse();
        chatMessageResponse.setCommand(Command.MESSAGE);
        chatMessageResponse.setRequestId(request.getRequestId());
        return chatMessageResponse;
    }
}
