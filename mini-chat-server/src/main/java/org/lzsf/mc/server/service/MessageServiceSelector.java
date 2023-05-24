package org.lzsf.mc.server.service;

import org.lzsf.exception.NotFoundMessageServiceException;
import org.lzsf.protocol.MessageTypeEnum;

import java.util.HashMap;
import java.util.Map;

public class MessageServiceSelector {

    private static Map<MessageTypeEnum, AbstractMessageService> serviceMap = new HashMap<>();

    static {
        serviceMap.put(MessageTypeEnum.AUTH_MESSAGE, new AuthMessageService());
        serviceMap.put(MessageTypeEnum.USER_MESSAGE, new UserMessageService());
    }

    public static AbstractMessageService select(MessageTypeEnum typeEnum) {
        if (!serviceMap.containsKey(typeEnum)) {
            throw new NotFoundMessageServiceException("not found message service:" + typeEnum);
        }
        return serviceMap.get(typeEnum);
    }
}
