package org.lzsf.mc.client.service.respond;

import lombok.extern.slf4j.Slf4j;
import org.lzsf.mc.client.cache.RequestCacheManager;
import org.lzsf.mc.client.manager.AuthManager;
import org.lzsf.mc.client.service.ClientService;
import org.lzsf.protocol.Packet;
import org.lzsf.protocol.response.LoginResponse;
import org.lzsf.protocol.response.Response;

@Slf4j
public class LoginResponseService implements ClientService {
    @Override
    public void excute(Packet pkt) {
        Response response = (Response) pkt;
        if (!response.isSuccess()) {
            log.error("login fail");
            return;
        }
        LoginResponse loginResponse = (LoginResponse) response;
        log.info("get token success!Token: " + loginResponse.getJwtToken());
        AuthManager.setJwtToken(loginResponse.getJwtToken());
        RequestCacheManager.removeRequest(loginResponse.getRequestId());
    }
}
