// Oleg Kiselev
// 06.07.2020, 19:03

package ru.progwards.java2.lessons.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AtmServer {

    private static final int PORT_ID = 45000;

    private void server() {
        try (ServerSocket serverSocket = new ServerSocket(PORT_ID)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new AtmServerThread(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        AtmServer atmServer = new AtmServer();
        atmServer.server();
    }
}
