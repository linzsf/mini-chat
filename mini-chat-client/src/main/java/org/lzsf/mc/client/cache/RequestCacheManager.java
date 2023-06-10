package org.lzsf.mc.client.cache;

import org.lzsf.protocol.request.Request;

import java.util.HashMap;
import java.util.Map;

public class RequestCacheManager {

    private static Map<String, Request> requestMap = new HashMap<>();

    public static Request getRequest(String requestId) {
        return requestMap.get(requestId);
    }

    public static void addRequest(Request request) {
        requestMap.put(request.getRequestId(), request);
    }

    public static void removeRequest(String requestId) {
        requestMap.remove(requestId);
    }
}
