package org.lzsf.mc.client.service;

import lombok.extern.slf4j.Slf4j;
import org.lzsf.protocol.response.LoginResponse;
import org.lzsf.protocol.response.Response;

@Slf4j
public class LoginService implements ClientService{
    @Override
    public void excute(Response response) {
        if (!response.isSuccess()) {
            log.error("login fail");
            return;
        }
        LoginResponse loginResponse = (LoginResponse) response;
        log.info("get token success!Token: " + loginResponse.getJwtToken());
        AuthManager.setJwtToken(loginResponse.getJwtToken());
    }
}
