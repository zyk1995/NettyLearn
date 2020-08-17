package com.zyk.netty.asyncbio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    public static void main(String[] args) throws IOException {

        final int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("The Time Server is start in port:" + port);
        TimeServerHandlerExecutorPool singleExecutor = new TimeServerHandlerExecutorPool(50, 10000);
        while (true) {
            Socket socket = serverSocket.accept();
            singleExecutor.execute(new TimerServerHandler(socket));
        }
//        serverSocket.close();
    }
}


