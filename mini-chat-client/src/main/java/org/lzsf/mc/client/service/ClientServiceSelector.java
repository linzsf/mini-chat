package org.lzsf.mc.client.service;

import org.lzsf.exception.NotFoundMessageServiceException;
import org.lzsf.mc.client.service.notify.ChatMessageNoticeService;
import org.lzsf.mc.client.service.respond.ChatResponseService;
import org.lzsf.mc.client.service.respond.LoginResponseService;
import org.lzsf.protocol.Command;
import org.lzsf.protocol.Packet;
import org.lzsf.protocol.notice.Notice;
import org.lzsf.protocol.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ClientServiceSelector {

    /**
     * 服务端响应的处理器，处理的消息类型未Response
     */
    private static Map<Byte, ClientService> responseServiceMap = new HashMap<>();

    /**
     * 服务端通知的处理器，处理的消息类型为Notice
     */
    private static Map<Byte, ClientService> noticeServiceMap = new HashMap<>();

    static {
//        responseServiceMap.put(Command.REGIST, new RegistService());
        responseServiceMap.put(Command.LOGIN, new LoginResponseService());
        responseServiceMap.put(Command.MESSAGE, new ChatResponseService());

        noticeServiceMap.put(Command.MESSAGE, new ChatMessageNoticeService());
    }

    public static ClientService select(Packet msg) {
        if (msg instanceof Response) {
            return responseServiceMap.get(msg.getCommand());
        } else if (msg instanceof Notice) {
            return noticeServiceMap.get(msg.getCommand());
        }
        throw new NotFoundMessageServiceException();
    }
}
