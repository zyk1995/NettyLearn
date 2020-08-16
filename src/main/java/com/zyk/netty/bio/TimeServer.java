package com.zyk.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    public static void main(String[] args) throws IOException {

        final int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("The Time Server is start in port:" + port);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new TimerServerHandler(socket)).start();
        }
//        serverSocket.close();
    }
}


