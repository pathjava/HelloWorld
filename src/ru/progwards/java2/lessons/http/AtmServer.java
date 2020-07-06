// Oleg Kiselev
// 06.07.2020, 19:03

package ru.progwards.java2.lessons.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AtmServer implements Runnable {

    private static final int PORT = 45000;

    private void server(){
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket socket = serverSocket.accept();
            InputStream ips = socket.getInputStream();
            OutputStream ops = socket.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }


    public static void main(String[] args) {

    }
}
