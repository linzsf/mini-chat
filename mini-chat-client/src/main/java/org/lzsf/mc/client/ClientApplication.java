package org.lzsf.mc.client;

import org.lzsf.mc.client.manager.AuthManager;
import org.lzsf.mc.client.netty.NettyClient;
import org.lzsf.protocol.Command;
import org.lzsf.protocol.request.ChatMessageRequest;
import org.lzsf.protocol.request.LoginRequest;

import java.util.Scanner;
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

            while (true) {
                Scanner scanner = new Scanner(System.in);
                String instr = scanner.nextLine();
                String[] strs = instr.split(" ");
                if (strs.length == 1 && strs[0].equals("exit")) {
                    nettyClient.close();
                    break;
                } else if (strs.length == 2) {
                    String toUserName = strs[0];
                    String content = strs[1];
                    ChatMessageRequest chatMessageRequest = new ChatMessageRequest();
                    chatMessageRequest.setCommand(Command.MESSAGE);
                    chatMessageRequest.setToken(AuthManager.getJwtToken());
                    chatMessageRequest.setFromUserName(userName);
                    chatMessageRequest.setContent(content);
                    chatMessageRequest.setToUserName(toUserName);
                    nettyClient.send(chatMessageRequest);
                }
            }
//
//            ChatMessageRequest chatMessageRequest = new ChatMessageRequest();
//            chatMessageRequest.setCommand(Command.MESSAGE);
//            chatMessageRequest.setToken(AuthManager.getJwtToken());
//            chatMessageRequest.setFromUserName(userName);
//            chatMessageRequest.setToUserName(toUserName);
//            chatMessageRequest.setContent("你好" + toUserName + "，我是"+userName);
//            nettyClient.send(chatMessageRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
