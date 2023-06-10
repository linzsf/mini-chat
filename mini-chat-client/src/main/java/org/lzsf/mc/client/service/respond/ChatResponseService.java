package org.lzsf.mc.client.service.respond;

import lombok.extern.slf4j.Slf4j;
import org.lzsf.mc.client.cache.RequestCacheManager;
import org.lzsf.mc.client.service.ClientService;
import org.lzsf.protocol.Packet;
import org.lzsf.protocol.request.ChatMessageRequest;
import org.lzsf.protocol.response.ChatMessageResponse;

@Slf4j
public class ChatResponseService implements ClientService {
    @Override
    public void excute(Packet pkt) {
        ChatMessageResponse response = (ChatMessageResponse) pkt;
        ChatMessageRequest request = (ChatMessageRequest) RequestCacheManager.getRequest(response.getRequestId());
        if (response.isSuccess()) {
            log.info("消息发送成功，消息内容{}，发送方：{}，接收方{}", request.getContent(), request.getFromUserName(), request.getToUserName());
        } else  {
            log.error("消息发送成功，消息内容{}，发送方：{}，接收方{}", request.getContent(), request.getFromUserName(), request.getToUserName());
        }
        RequestCacheManager.removeRequest(response.getRequestId());
    }
}
