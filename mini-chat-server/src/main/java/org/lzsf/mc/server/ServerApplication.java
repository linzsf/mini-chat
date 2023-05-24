package org.lzsf.mc.server;

import org.lzsf.mc.server.netty.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
//        SpringApplication.run(ServerApplication.class);
        NettyServer nettyServer = new NettyServer();
        nettyServer.start();
    }
}
