// Oleg Kiselev
// 06.07.2020, 14:13

package ru.progwards.sever.testprogwards2.test_15;

import java.io.IOException;
import java.io.InputStream;
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
