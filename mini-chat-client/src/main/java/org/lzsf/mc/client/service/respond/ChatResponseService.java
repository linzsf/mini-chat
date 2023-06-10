package org.lzsf.mc.client.service.respond;

import lombok.extern.slf4j.Slf4j;
import org.lzsf.mc.client.cache.RequestCacheManager;
import org.lzsf.mc.client.service.ClientService;
import org.lzsf.protocol.Packet;
import org.lzsf.protocol.request.ChatMessageRequest;
import org.lzsf.protocol.response.ChatMessageResponse;
import org.lzsf.protocol.response.ResponseCode;

@Slf4j
public class ChatResponseService implements ClientService {
    @Override
    public void excute(Packet pkt) {
        ChatMessageResponse response = (ChatMessageResponse) pkt;
        ChatMessageRequest request = (ChatMessageRequest) RequestCacheManager.getRequest(response.getRequestId());
        if (response.isSuccess()) {
            log.info("消息发送成功，消息内容：{}，发送方：{}，接收方：{}", request.getContent(), request.getFromUserName(), request.getToUserName());
        } else {
            String errorMsg = "发送失败";
            if (ResponseCode.AUTH_FAIL.getCode().equals(response.getResult())) {
                errorMsg= "用户认证失败";
            }
            log.error("消息发送失败，消息内容：{}，发送方：{}，接收方：{}，错误信息：{}", request.getContent(), request.getFromUserName(), request.getToUserName(), errorMsg);
        }
        RequestCacheManager.removeRequest(response.getRequestId());
    }
}
