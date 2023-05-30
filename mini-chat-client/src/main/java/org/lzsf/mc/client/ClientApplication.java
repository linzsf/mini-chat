package org.lzsf.mc.client;

import org.lzsf.mc.client.netty.NettyClient;
import org.lzsf.protocol.request.LoginRequest;
import org.lzsf.protocol.request.Request;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
//        SpringApplication.run(ClientApplication.class);
        NettyClient nettyClient = new NettyClient();
        nettyClient.start();
    }
}
