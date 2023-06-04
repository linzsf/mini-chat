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
            String userName = args[0];
            String passWord = args[1];
            CountDownLatch count = new CountDownLatch(1);
            NettyClient nettyClient = new NettyClient();
            // 异步启动netty
            Thread nettyThread = new Thread(() -> {
                nettyClient.start(count);
            });
            nettyThread.start();
            // 等待netty启动完毕
            count.await();
            // 添加应用终止的钩子，优雅关闭netty连接
            Runtime.getRuntime().addShutdownHook(new Thread(nettyClient::close));

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setCommand(Command.LOGIN);
            loginRequest.setUserName(userName);
            loginRequest.setPassword(passWord);
            nettyClient.send(loginRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
