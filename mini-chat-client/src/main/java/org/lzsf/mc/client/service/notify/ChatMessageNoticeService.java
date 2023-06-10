package org.lzsf.mc.client.service.notify;

import org.lzsf.mc.client.service.ClientService;
import org.lzsf.protocol.Packet;
import org.lzsf.protocol.notice.ChatMessageNotice;
import org.lzsf.protocol.response.Response;

public class ChatMessageNoticeService implements ClientService {
    @Override
    public void excute(Packet pkt) {
        ChatMessageNotice chatMessageNotice = (ChatMessageNotice) pkt;
        System.out.println("收到来自[" + chatMessageNotice.getFromUserName() + "]的消息[" + chatMessageNotice.getContent() +"]");
    }
}
