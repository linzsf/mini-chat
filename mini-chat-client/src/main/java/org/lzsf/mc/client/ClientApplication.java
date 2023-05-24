package org.lzsf.mc.client;

import org.lzsf.mc.client.netty.NettyClient;
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
