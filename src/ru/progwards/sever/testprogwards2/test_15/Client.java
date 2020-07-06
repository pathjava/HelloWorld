// Oleg Kiselev
// 06.07.2020, 14:13

package ru.progwards.sever.testprogwards2.test_15;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try (Socket client = new Socket("yandex.ru", 443)) {
            System.out.println(client.isConnected());
            System.out.println(client.isClosed());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        try (Socket client = new Socket("vk.com", 80)) {
            String request = "GET / HTTP/1.1 \n" +
                    "host:vk.com \n\n";

            InputStream ips = client.getInputStream();
            OutputStream ops = client.getOutputStream();

            PrintWriter pw = new PrintWriter(ops);
            pw.println(request);
            pw.flush();

            Scanner scanner = new Scanner(ips);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

            System.out.println(client.isConnected());
            System.out.println(client.isClosed());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        try (Socket client = new Socket("time-A.timefreq.bldrdoc.gov", 13)) {
            InputStream ips = client.getInputStream();
            Scanner scanner = new Scanner(ips);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
