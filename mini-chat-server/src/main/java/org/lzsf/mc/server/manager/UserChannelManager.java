package org.lzsf.mc.server.manager;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

import java.util.HashMap;
import java.util.Map;

public class UserChannelManager {

    private static Map<Long, Channel> userChannelMap = new HashMap<>();

    public static void userOnline(Long userId, Channel channel) {
        channel.attr(AttributeKey.valueOf("userId")).set(userId);
        userChannelMap.put(userId, channel);
    }

    public static Channel getChannelByUserId(Long userId) {
        return userChannelMap.get(userId);
    }

    public static void userOffline(Channel channel) {
        Long userId = (Long) channel.attr(AttributeKey.valueOf("userId")).get();
         userChannelMap.remove(userId);
    }
}
