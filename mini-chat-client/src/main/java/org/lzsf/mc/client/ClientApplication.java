package org.lzsf.mc.client;

import org.lzsf.mc.client.netty.NettyClient;
import org.lzsf.protocol.Command;
import org.lzsf.protocol.request.LoginRequest;

import java.util.concurrent.CountDownLatch;

//@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ClientApplication.class);
        try {
            CountDownLatch count = new CountDownLatch(1);
            NettyClient nettyClient = new NettyClient();
            // 异步启动netty
            Thread nettyThread = new Thread(() -> {
                nettyClient.start(count);
            });
            nettyThread.start();
            // 等待netty启动完毕
            count.await();

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setCommand(Command.LOGIN);
            loginRequest.setUserName("zhangsan");
            loginRequest.setPassword("123456");
            nettyClient.send(loginRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
