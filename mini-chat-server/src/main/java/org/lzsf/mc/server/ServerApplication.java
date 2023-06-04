package org.lzsf.mc.server;

import org.lzsf.mc.server.netty.NettyServer;


//@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
//        SpringApplication.run(ServerApplication.class);
        NettyServer nettyServer = new NettyServer();
        nettyServer.start();
    }
}
