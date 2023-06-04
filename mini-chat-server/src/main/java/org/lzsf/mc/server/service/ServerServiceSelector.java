package org.lzsf.mc.server.service;

import org.lzsf.protocol.Command;

import java.util.HashMap;
import java.util.Map;

public class ServerServiceSelector {
    private static Map<Byte, ServerService> serviceMap = new HashMap<>();

    static {
        serviceMap.put(Command.REGIST, new RegistService());
        serviceMap.put(Command.LOGIN, new LoginService());
        serviceMap.put(Command.CHAT, new ChatMessageService());
    }

    public static ServerService select(Byte command) {
        return serviceMap.get(command);
    }
}
