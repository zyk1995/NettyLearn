package com.zyk.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimerServerHandler implements Runnable{

    private Socket socket;
    TimerServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) { break; }
                System.out.println("The time server receive order:" + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                        new Date(System.currentTimeMillis()).toString()
                        :"BAD ORDER";
                out.println(currentTime);
            }

        } catch (Exception e) {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException el) {
                e.printStackTrace();
            }

        }
    }
}
