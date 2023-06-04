package org.lzsf.mc.server.service;

import io.netty.channel.ChannelHandlerContext;
import org.lzsf.mc.server.util.JwtUtil;
import org.lzsf.protocol.Command;
import org.lzsf.protocol.request.LoginRequest;
import org.lzsf.protocol.request.Request;
import org.lzsf.protocol.response.LoginResponse;
import org.lzsf.protocol.response.Response;
import org.lzsf.protocol.response.ResponseCode;

import java.util.HashMap;
import java.util.Map;

public class LoginService implements ServerService{

    private static Map<String, String> userInfoTestMap = new HashMap<>();

    private static Map<String, Long> userNameIdMap = new HashMap<>();

    static {
        userInfoTestMap.put("zhangsan", "123456");
        userNameIdMap.put("zhangsan", 24532122451234L);
    }

//    public static void login(ChannelHandlerContext ctx, LoginRequest request) {
//        if (!userInfoTestMap.containsKey(request.getUserName())) {
//
//        }
//        String token = JwtUtil.genJwtToken(request.getUserName());
//        LoginResponse loginResponse = new LoginResponse();
//        loginResponse.setJwtToken(token);
//        loginResponse.setCommand(Command.LOGIN);
//        ctx.channel().writeAndFlush(loginResponse);
//    }

    @Override
    public Response excute(ChannelHandlerContext ctx, Request request) {
        LoginRequest loginRequest = (LoginRequest) request;
        LoginResponse loginResponse = new LoginResponse();
        if (!userInfoTestMap.containsKey(loginRequest.getUserName())) {
            loginResponse.setResult(ResponseCode.LOGIN_FAIL.getCode());
            return loginResponse;
        }
        String token = JwtUtil.genJwtToken(loginRequest.getUserName());

        loginResponse.setJwtToken(token);
        loginResponse.setCommand(Command.LOGIN);
        return loginResponse;
    }
}
