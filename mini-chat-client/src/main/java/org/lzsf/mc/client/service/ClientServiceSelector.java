package org.lzsf.mc.client.service;

import org.lzsf.protocol.Command;

import java.util.HashMap;
import java.util.Map;

public class ClientServiceSelector {

    private static Map<Byte, ClientService> serviceMap = new HashMap<>();

    static {
//        serviceMap.put(Command.REGIST, new RegistService());
        serviceMap.put(Command.LOGIN, new LoginService());
//        serviceMap.put(Command.CHAT, new ChatService());
    }

    public static ClientService select(Byte command) {
        return serviceMap.get(command);
    }
}
