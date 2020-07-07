// Oleg Kiselev
// 06.07.2020, 20:52

package ru.progwards.java2.lessons.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AtmServerThread implements Runnable {

    private final Socket socket;

    public AtmServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream ips = socket.getInputStream(); OutputStream ops = socket.getOutputStream()) {
            Scanner scanner = new Scanner(ips);
            getParameters(scanner);

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                PrintWriter pw = new PrintWriter(ops, true);
                pw.println(str); //TODO temp
                //TODO - think about - shutdownOutput();

                if (str.equalsIgnoreCase("quit"))
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // "GET /resource?param1=value1&param2=value2 HTTP/1.1"
    private void getParameters(Scanner scanner) {
        String firstLine = scanner.nextLine();
        String str = firstLine.substring(5, firstLine.length() - 9);

    }
}
