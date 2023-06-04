package org.lzsf.mc.server.service;

import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.StringUtils;
import org.lzsf.mc.server.manager.AuthManager;
import org.lzsf.mc.server.manager.UserChannelManager;
import org.lzsf.protocol.Command;
import org.lzsf.protocol.request.LoginRequest;
import org.lzsf.protocol.request.Request;
import org.lzsf.protocol.response.LoginResponse;
import org.lzsf.protocol.response.Response;
import org.lzsf.protocol.response.ResponseCode;

public class LoginService implements ServerService{

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
    public Response execute(ChannelHandlerContext ctx, Request request) {
        LoginRequest loginRequest = (LoginRequest) request;
        LoginResponse loginResponse = new LoginResponse();
        Long userId = AuthManager.getUserId(loginRequest.getUserName(), loginRequest.getPassword());
        if (userId == null) {
            loginResponse.setResult(ResponseCode.LOGIN_FAIL.getCode());
            return loginResponse;
        }
        String token = AuthManager.genUserToken(userId);
        if (StringUtils.isEmpty(token)) {
            loginResponse.setResult(ResponseCode.LOGIN_FAIL.getCode());
            return loginResponse;
        }
        UserChannelManager.userOnline(userId, ctx.channel());
        loginResponse.setJwtToken(token);
        loginResponse.setCommand(Command.LOGIN);
        return loginResponse;
    }
}
