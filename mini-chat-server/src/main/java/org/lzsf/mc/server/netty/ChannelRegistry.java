package org.lzsf.mc.server.netty;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class ChannelRegistry {

    private static Map<Long, Channel> userChannelMap = new HashMap<>();

    public static Channel get(Long userId) {
        return userChannelMap.get(userId);
    }

    public static void put(Long userId, Channel channel) {
        userChannelMap.put(userId, channel);
    }
}
